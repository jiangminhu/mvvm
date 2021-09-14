package com.example.weightdemo.activity

import android.util.Log
import com.example.weightdemo.base.BaseTitleActivity
import com.example.weightdemo.databinding.ActivityConstraintlayoutBinding
import com.example.weightdemo.presenter.LifPresenter
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Retrofit
import java.io.IOException

class ConstraintlayoutActivity :
    BaseTitleActivity<ActivityConstraintlayoutBinding, LifPresenter>() {

    override fun getActivityBinding(): ActivityConstraintlayoutBinding {
        return ActivityConstraintlayoutBinding.inflate(layoutInflater)
    }


    override fun initData() {

        val okHttpClient = OkHttpClient.Builder().build()

        val requestBody = "".toRequestBody("application/json;charset=utf-8".toMediaTypeOrNull())

        val request = Request.Builder().url("http://www.baidu.com").post(requestBody).build()
        val build = Retrofit.Builder().build()
        okHttpClient.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {

            }

            override fun onResponse(call: Call, response: Response) {
                val result = response.body?.string()
                if (result != null) {
                    Log.e("TGA", result)
                }
            }

        })
    }

    override fun initView() {
        setTitleVis(false)
    }

    override fun getPresenter(): LifPresenter {
        return LifPresenter()
    }
}
