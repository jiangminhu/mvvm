package com.example.weightdemo.weight

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
