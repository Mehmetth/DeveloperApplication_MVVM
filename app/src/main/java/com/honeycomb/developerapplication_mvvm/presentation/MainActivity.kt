package com.honeycomb.developerapplication_mvvm.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.honeycomb.developerapplication_mvvm.R
import com.honeycomb.developerapplication_mvvm.utils.AndroidUtils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AndroidUtils.globalContext = applicationContext
    }

}