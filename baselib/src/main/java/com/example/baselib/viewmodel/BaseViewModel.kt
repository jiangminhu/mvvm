package com.example.baselib.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.baselib.BuildConfig
import com.example.baselib.bean.DismissProgress
import com.example.baselib.bean.ShowProgress
import com.example.baselib.bean.StateActionEvent
import com.example.baselib.exception.ApiException
import com.example.baselib.res.ApiExMsg
import com.google.gson.JsonParseException
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.json.JSONException
import retrofit2.HttpException
import java.io.EOFException
import java.io.InterruptedIOException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.text.ParseException

/**
 * 处理网络请求业务体
 */
typealias  Block<T> = suspend () -> T
/**
 * 处理异常情况
 */
typealias  Error = suspend (code: Int, message: String?) -> Unit
/**
 * 处理取消
 */
typealias  Cancel = suspend (e: Exception) -> Unit

open class BaseViewModel : ViewModel() {
    //观察网络状态
    val mStateLiveData = MutableLiveData<StateActionEvent>()

    /**
     * block： 函数体
     * onError ： 错误函数体
     * isShowProcess：是否显示进度
     * cancel：是否取消
     */
    open fun launch(
        block: Block<Unit>,
        onError: Error? = null,
        isShowProcess: Boolean = false,
        cancel: Cancel? = null
    ): Job {
        return viewModelScope.launch {
            try {
                if (isShowProcess) {
                    //显示load
                    mStateLiveData.value = ShowProgress
                }
                block.invoke()
                if (isShowProcess) {
                    //关闭弹窗
                    mStateLiveData.value = DismissProgress
                }
            } catch (e: Exception) {
                if (isShowProcess) {
                    //关闭弹窗
                    mStateLiveData.value = DismissProgress
                }
                when (e) {
                    is CancellationException -> {
                        cancel?.invoke(e)
                    }
                    else -> {
                        doError(e, onError)
                    }
                }
            }
        }
    }


    private suspend fun doError(e: Exception, error: Error?) {
        if (BuildConfig.DEBUG) {
            e.printStackTrace()
        }
        when (e) {
            is HttpException -> {
                error?.invoke(e.code(), ApiExMsg.HTTP_ERROR)
            }

            is ConnectException, is UnknownHostException -> {
                error?.invoke(0, ApiExMsg.CONNECT_ERROR)
            }

            is JsonParseException, is JSONException, is ParseException -> {
                error?.invoke(0, ApiExMsg.JSON_ERROR)
            }

            is SocketTimeoutException, is InterruptedIOException -> {
                error?.invoke(0, ApiExMsg.SOCKET_ERROR)
            }

            is EOFException -> {
                error?.invoke(0, ApiExMsg.EOF_ERROR)
            }

            is IllegalStateException -> {
                error?.invoke(0, ApiExMsg.RUN_ERROR)
            }
            is ApiException -> {
                error?.invoke(0, e.message)
            }
            else -> {
                error?.invoke(0, e.message)
            }
        }
    }

}