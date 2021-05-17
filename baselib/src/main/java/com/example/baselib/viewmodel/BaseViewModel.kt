package com.example.baselib.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.viewbinding.BuildConfig
import com.example.baselib.bean.DismissProgress
import com.example.baselib.bean.ShowProgress
import com.example.baselib.bean.StateActionEvent
import com.example.baselib.exception.ApiCode
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

abstract class BaseViewModel : ViewModel() {
    //观察网络状态
    val mStateLiveData = MutableLiveData<StateActionEvent>()


    /**
     * block： 函数体
     * onError ： 错误函数体
     * isShowProcess：是否显示进度
     * cancel：取消操作
     *
     * */
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
                error?.invoke(ApiCode.ERROR_CODE, ApiExMsg.CONNECT_ERROR)
            }

            is JsonParseException, is JSONException, is ParseException -> {
                error?.invoke(ApiCode.ERROR_CODE, ApiExMsg.JSON_ERROR)
            }

            is SocketTimeoutException, is InterruptedIOException -> {
                error?.invoke(ApiCode.ERROR_CODE, ApiExMsg.SOCKET_ERROR)
            }

            is EOFException -> {
                error?.invoke(ApiCode.ERROR_CODE, ApiExMsg.EOF_ERROR)
            }

            is IllegalStateException -> {
                error?.invoke(ApiCode.ERROR_CODE, ApiExMsg.RUN_ERROR)
            }
            is ApiException -> {
                error?.invoke(ApiCode.ERROR_CODE, e.message)
            }
            else -> {
                error?.invoke(ApiCode.ERROR_CODE, e.message)
            }
        }
    }

}