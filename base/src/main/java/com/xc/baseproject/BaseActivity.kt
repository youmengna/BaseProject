package com.xc.baseproject

import android.annotation.SuppressLint
import android.support.v4.app.FragmentActivity

@SuppressLint("Registered")
open class BaseActivity : FragmentActivity() {

    fun getContext(): FragmentActivity = this

}