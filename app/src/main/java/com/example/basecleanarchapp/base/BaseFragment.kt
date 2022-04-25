package com.example.basecleanarchapp.base

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.basecleanarchapp.base.BaseActivity
import com.example.basecleanarchapp.ui.home.MainActivity

open class BaseFragment : Fragment() {
    lateinit var loading: Dialog

    val activity by lazy { getActivity() as MainActivity }
    val baseActivity by lazy { getActivity() as BaseActivity }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // loading = initLoadingDialog()
    }
//    private fun initLoadingDialog(): Dialog {
//        loading = Dialog(requireContext(), R.style.AnimDialogStyle)
//        loading.requestWindowFeature(Window.FEATURE_NO_TITLE)
//        Objects.requireNonNull(loading.window)
//                ?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
//        loading.window?.setGravity(Gravity.CENTER)
//        val lWindowParams = WindowManager.LayoutParams()
//        lWindowParams.copyFrom(loading.window?.attributes)
//        lWindowParams.width = WindowManager.LayoutParams.MATCH_PARENT
//        lWindowParams.horizontalMargin = 22f
//        lWindowParams.height = WindowManager.LayoutParams.WRAP_CONTENT
//        lWindowParams.dimAmount = 0.4f
//        loading.window?.attributes = lWindowParams
//        loading.window?.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
//        loading.setContentView(R.layout.dialog_loading)
//        loading.setCancelable(true)
//        loading.setCanceledOnTouchOutside(true)
//        return loading
//    }


}