package com.example.basecleanarchapp.base

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.yariksoffice.lingver.Lingver

open class BaseActivity : AppCompatActivity() {
    lateinit var loading: Dialog
    private lateinit var dialog: BaseDialog

    companion object {
        const val REQUEST_ENABLE_GPS = 200

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //   loading = initLoadingDialog()
        // window.statusBarColor = Color.WHITE
    }

    fun setAppLanguage(lang: String) {
       // todo do not forget to remove the comment
        // LocalData.setAppLocale(lang)
        Lingver.getInstance().setLocale(this, lang)

    }

    override fun onResume() {
        super.onResume()
        /*    if(isConfigChange){
                fetchConfig()
                fetchGovsData()
            }*/
    }
//
//    private fun initLoadingDialog(): Dialog {
//        loading = Dialog(this, R.style.AnimDialogStyle)
//        loading.requestWindowFeature(Window.FEATURE_NO_TITLE)
//        Objects.requireNonNull(loading.window)?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
//        loading.window?.setGravity(Gravity.CENTER)
//        val lWindowParams = WindowManager.LayoutParams()
//        lWindowParams.copyFrom(loading.window?.attributes)
//        lWindowParams.width = WindowManager.LayoutParams.WRAP_CONTENT
//        lWindowParams.height = WindowManager.LayoutParams.WRAP_CONTENT
//        lWindowParams.dimAmount = 0.4f
//        loading.window?.attributes = lWindowParams
//        loading.window?.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
//        loading.setContentView(R.layout.dialog_loading)
//        loading.setCancelable(true)
//        loading.setCanceledOnTouchOutside(true)
//        return loading
//    }

//    fun forceAppUpdate() {
//        dialog = BaseDialog(
//            R.layout.force_update_layout,
//            onBind = { view, bindingItem ->
//                val bind = bindingItem as ForceUpdateLayoutBinding
//                bind.updateNowBtn.onClick {
//                    googlePlayContact(LocalData.configs?.android!!)
//                }
//            }
//        )
//        dialog.isCancelable = false
//        dialog.show(supportFragmentManager, "")
//    }

//    fun appStopped() {
//        dialog = BaseDialog(
//            R.layout.force_update_layout,
//            onBind = { view, bindingItem ->
//                val bind = bindingItem as ForceUpdateLayoutBinding
//
//                bind.rootLt.setBackgroundColor(Color.WHITE)
//                bind.text.text = getString(R.string.app_not_working)
//                bind.updateNowBtn.gone()
//            }
//        )
//        dialog.isCancelable = false
//        dialog.show(supportFragmentManager, "")
//    }


//    @RequiresApi(Build.VERSION_CODES.M)
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if (requestCode == REQUEST_CHECK_SETTINGS) {
//            when (resultCode) {
//                AppCompatActivity.RESULT_OK -> {
//                    Hawk.put("isEnabled", true)
//                }
//                AppCompatActivity.RESULT_CANCELED -> {
//                    Log.e("GPS", "User denied to access location")
//                    openGpsEnableSetting()
//                }
//            }
//        } else if (requestCode == REQUEST_ENABLE_GPS) {
//            val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
//            val isGpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
//            if (!isGpsEnabled) {
//                openGpsEnableSetting()
//            } else {
//                Hawk.put("isEnabled", true)
//                // navigateToUser()
//            }
//        }
//    }
//
//    open fun openGpsEnableSetting() {
//        val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
//        startActivityForResult(intent, REQUEST_ENABLE_GPS)
//    }
}