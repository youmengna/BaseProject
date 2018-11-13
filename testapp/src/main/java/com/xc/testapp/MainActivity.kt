package com.xc.testapp

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.xc.baseproject.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button.visibility = View.INVISIBLE
        button.setOnClickListener { Toast.makeText(getContext(), "111", Toast.LENGTH_SHORT).show() }
    }
}
