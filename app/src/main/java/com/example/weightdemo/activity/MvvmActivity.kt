package com.example.weightdemo.activity

import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.example.weightdemo.databinding.ActivityMvvmBinding
import com.example.weightdemo.viewmodel.MvvmViewModel
import com.example.weightdemo.weight.dialog.LoadingDialog
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*


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

    override fun tokenInvalidException() {
        Log.e("TGA", "token  is  invalid ")
    }


    override fun initView() {
        val exception = CoroutineExceptionHandler { context, ex ->
            ex.printStackTrace()
            Log.e("TGA", "${context.job}------------------>aaa  ")
        }
        val allJob = lifecycleScope.launch(exception) {

            supervisorScope {
                val a = launch {
                    try {
                        delay(2000)
                        val number = 2 / 0
                    } catch (e: Exception) {
                        Log.e("TGA", "a------------------>  ")
                    }

                }
                Log.e("TGA", "a------------------>${a}  ")
                val b = launch {
                    delay(5000)
                    Log.e("TGA", "b------------------>initView  ")
                }
            }

        }
        allJob.cancel()
//         mViewModel.a()
    }

    @FlowPreview
    private fun test(list: ArrayList<Int>) {
        lifecycleScope.launch {
            val errors = ArrayList<Int>()
//            val success = ArrayList<Int>() 
            list.asFlow().flatMapConcat {
                flow<Any> {
                    val a = it
                    if (a % 2 != 0) {
                        emit(it)
                    } else {
                        emit(it.toString())
                    }
                }
            }.filter {
                when (it) {
                    is Int -> {
                        errors.add(it)
                        return@filter false
                    }
                }
                true
            }.onCompletion {
                Log.e("TGA", "${errors.size}-------------------->")
                if (errors.size > 0) {
                    for (e in errors.indices) {
                        errors[e] += errors[e]
                    }
                    test(errors)
                }
            }.collect {
//                success.add(it)
            }
        }
    }


    override fun getTitleContent(): String? {
        return null
    }
}