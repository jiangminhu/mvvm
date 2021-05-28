package com.example.weightdemo.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.weightdemo.R

class SecondActivity : AppCompatActivity() {
    companion object {
        const val PARAMS = "params"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
    }
}
