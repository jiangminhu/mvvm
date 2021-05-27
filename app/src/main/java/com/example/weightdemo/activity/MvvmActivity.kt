package com.example.weightdemo.activity

import com.example.weightdemo.databinding.ActivityMvvmBinding
import com.example.weightdemo.viewmodel.MvvmViewModel
import com.example.weightdemo.weight.dialog.LoadingDialog

class MvvmActivity : BaseVBTitleActivity<MvvmViewModel, ActivityMvvmBinding>() {
    private var loadingDialog: LoadingDialog? = null


    override fun showProgress(text: String?) {
        if (loadingDialog == null) {
            loadingDialog = LoadingDialog(this)
        }
        loadingDialog?.show()
    }

    override fun dismissProgress() {
        loadingDialog?.let {
            if (it.isShowing) it.dismiss()
        }
    }

    override fun errorState(message: String?) {

    }


    override fun initView() {
        mViewModel.a()
    }


}