package com.example.basecleanarchapp.utils

/**
 * Created by Alaa AbuZarifa
 *  for Selsela Company
 * Copyright (c) 2020
 */

import android.content.Context
import android.content.ContextWrapper
import android.content.res.AssetFileDescriptor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.ExifInterface
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.util.Log
import com.bumptech.glide.load.resource.bitmap.TransformationUtils
import java.io.*
import java.text.SimpleDateFormat
import java.util.*

class FileHelper {

    companion object {

        fun getImage(uri : Uri?,context : Context ) : File? {
            val bitmapImage = decodeBitmap(context, uri!!)
            val cw = ContextWrapper(context)
            val directory = cw.getDir("imageDir", Context.MODE_PRIVATE)
            val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
            val imageFileName = "JPEG_$timeStamp.jpg"
            val mypath = File(directory, imageFileName)
            var fos : FileOutputStream? = null
            try {
                fos = FileOutputStream(mypath)


                bitmapImage?.compress(Bitmap.CompressFormat.PNG, 100, fos)
            }
            catch (e : Exception) {
                e.printStackTrace()
            } finally {
                try {
                    fos!!.close()
                }
                catch (e : IOException) {
                    e.printStackTrace()
                }
            }
            return mypath
        }

        private fun compressImage(context : Context, selectedImage : Uri?) : Bitmap? {
            var bm : Bitmap? = null
            val sampleSizes = intArrayOf(5, 3, 2, 1)
            var i = 0
            do {
                bm = decodeBitmap(context, selectedImage, sampleSizes[i])
                Log.d("TAG", "resizer: new bitmap width = " + bm!!.width)
                i++
            } while (bm!!.width < 400 && i < sampleSizes.size)
            return bm
        }

         fun decodeBitmap(
            context : Context, theUri : Uri?, sampleSize : Int
        ) : Bitmap? {
            val options = BitmapFactory.Options()
            options.inSampleSize = sampleSize
            var fileDescriptor : AssetFileDescriptor? = null
            try {
                fileDescriptor = context.contentResolver.openAssetFileDescriptor(theUri!!, "r")
            }
            catch (e : FileNotFoundException) {
                e.printStackTrace()
            }
            val actuallyUsableBitmap = BitmapFactory.decodeFileDescriptor(
                fileDescriptor!!.fileDescriptor, null, options
            )
            Log.d(
                "TAG", options.inSampleSize.toString() + " sample method bitmap ... " + actuallyUsableBitmap.width + " " + actuallyUsableBitmap.height
            )
            return actuallyUsableBitmap
        }
        fun decodeBitmap(context: Context, imagePath: Uri): Bitmap? {


            var inputstream: InputStream?
            val exif: ExifInterface?
            var image: Bitmap? = null
            try {
                inputstream = context.contentResolver.openInputStream(imagePath)
                image = BitmapFactory.decodeStream(inputstream)!!

//Close input stream consumed for Bitmap decode
                inputstream?.close()

// Open stream again for reading exif information for acquiring orientation details.
                // Use new input stream otherwise bitmap decode stream gets reset.
                inputstream = context.contentResolver.openInputStream(imagePath)
                var orientation: Int = ExifInterface.ORIENTATION_UNDEFINED
                try {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        exif = inputstream?.let { ExifInterface(it) }
                    } else {
                        exif = imagePath.getPath()?.let { ExifInterface(it) }
                    }
                    orientation = exif?.getAttributeInt(
                            ExifInterface.TAG_ORIENTATION,
                            ExifInterface.ORIENTATION_NORMAL
                    ) ?: ExifInterface.ORIENTATION_NORMAL
                } catch (e: IOException) {
// Log.d("IOException: " + e.message)
                }

//if you need, can correct orientation issues for gallery pick camera images with following.
                //Log.d("decodeBitmap orientation: $orientation")
                when (orientation) {
                    ExifInterface.ORIENTATION_ROTATE_90, ExifInterface.ORIENTATION_TRANSPOSE -> image =
                            TransformationUtils.rotateImage(image, 90)
                    ExifInterface.ORIENTATION_ROTATE_180, ExifInterface.ORIENTATION_FLIP_VERTICAL -> image =
                            TransformationUtils.rotateImage(image, 180)
                    ExifInterface.ORIENTATION_ROTATE_270, ExifInterface.ORIENTATION_TRANSVERSE -> image =
                            TransformationUtils.rotateImage(image, 270)
                    else -> {
                    }
                }
                inputstream?.close()
                val option = BitmapFactory.Options()
                option.inSampleSize = 5
                image = BitmapFactory.decodeStream(inputstream,null,option)!!

            } catch (e: IOException) {
                e.printStackTrace()
            }
            return image
        }

        fun bitmapToFile(bitmap: Bitmap, fileNameToSave: String): File? { // File name like "image.png"
            //create a file to write bitmap data
            var file: File? = null
            return try {
                file = File(Environment.getExternalStorageDirectory().toString() + File.separator + fileNameToSave)
                file.createNewFile()

                //Convert bitmap to byte array
                val bos = ByteArrayOutputStream()
                bitmap.compress(Bitmap.CompressFormat.PNG, 0, bos) // YOU can also save it in JPEG
                val bitmapdata = bos.toByteArray()

                //write the bytes in file
                val fos = FileOutputStream(file)
                fos.write(bitmapdata)
                fos.flush()
                fos.close()
                file
            } catch (e: Exception) {
                e.printStackTrace()
                file // it will return null
            }
        }
    }
}