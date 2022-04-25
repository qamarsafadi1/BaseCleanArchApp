package com.example.basecleanarchapp.utils

import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.util.*

class FileUtils() {

companion object{
fun getPath(context:Context):String{
    return context.getExternalFilesDir(null)?.absolutePath + File.separator + "CONSULTAIONS"
}
    fun getFileName(context:Context,it:Uri):String{
        val uri = it
        val filename: String
        val cursor: Cursor? =
            context?.contentResolver?.query(uri, null, null, null, null)
        if (cursor == null) filename = uri.path ?: "" else {
            val filePath = arrayOf(MediaStore.Images.Media.DATA)
            cursor.moveToFirst()
            val idx: Int =
                cursor.getColumnIndex(MediaStore.Files.FileColumns.DISPLAY_NAME)
            filename = cursor.getString(idx)
            cursor.close()
        }
        return filename
    }


    fun createFile(context:Context,it: Uri, filename: String):File{
        val path = getPath(context)

        val filePath = File(path)
        filePath.mkdirs()
       val outFile =File(path+filename)
        context.contentResolver?.openInputStream(it)?.let { it1 ->
            copyStreamToFile(it1,outFile)
        }
        return outFile

    }
    fun copyStreamToFile(inputStream: InputStream, outputFile: File) {
        try{
            inputStream.use { input ->
                val outputStream = FileOutputStream(outputFile)
                outputStream.use { output ->
                    val buffer = ByteArray(4 * 1024) // buffer size
                    while (true) {
                        val byteCount = input.read(buffer)
                        if (byteCount < 0) break
                        output.write(buffer, 0, byteCount)
                    }
                    output.flush()
                }
            }
        }catch (ex:java.lang.Exception){
            ex.printStackTrace()
        }
    }

    fun deleteRecursive(fileOrDirectory: File) {
        try{
            if (fileOrDirectory.isDirectory) for (child in Objects.requireNonNull(fileOrDirectory.listFiles())) deleteRecursive(
                child
            )
            fileOrDirectory.delete()
        }catch (ex:Exception){
            ex.printStackTrace()
        }

    }
}}