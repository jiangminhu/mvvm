package com.example.weightdemo.weight

import android.content.Intent
import android.text.InputFilter
import android.text.Spanned
import android.util.Log
import android.widget.EditText
import com.example.weightdemo.base.BaseTitleActivity
import com.example.weightdemo.databinding.ActivityFirstBinding
import com.example.weightdemo.presenter.LifPresenter
import kotlinx.android.synthetic.main.activity_first.*
import java.util.regex.Pattern

class FirstActivity : BaseTitleActivity<ActivityFirstBinding, LifPresenter>() {
    var inTest: String? = ""

    override fun getActivityBinding(): ActivityFirstBinding {
        return ActivityFirstBinding.inflate(layoutInflater)
    }

    override fun getPresenter(): LifPresenter {
        return LifPresenter()
    }

    override fun initData() {

    }

    override fun initView() {
        viewbind.button.setOnClickListener {
            var intent = Intent(mContext, SecendActivity::class.java)
            startActivity(intent)
        }
        setEditTextInhibitInputSpeChat(et_text)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
       intent?.let {
           inTest=it.getStringExtra("test")
        }
        Log.e("TGA", "FirstActivity-------$inTest----->onResume")
    }

    override fun onResume() {
        super.onResume()
        inTest=intent.getStringExtra("test")
        Log.e("TGA", "FirstActivity-------$inTest----->onResume")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("TGA", "FirstActivity------------>destroy")
    }

    /**
     * 禁止EditText输入特殊字符
     *
     * @param editText
     */
    fun setEditTextInhibitInputSpeChat(editText: EditText) {
        var a = arrayOf(InputFilter { source, start, end, dest, dstart, dend ->
            return@InputFilter null
        }, InputFilter.LengthFilter(10))
        editText.filters = a
    }
}