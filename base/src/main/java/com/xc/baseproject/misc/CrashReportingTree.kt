package com.xc.baseproject.misc

import android.util.Log
import timber.log.Timber


class CrashReportingTree : Timber.DebugTree() {

    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        if (priority == Log.VERBOSE || priority == Log.DEBUG) {
            return
        }

        if (t != null && priority == Log.ERROR) {
            super.log(priority, tag, message, t)
        }
    }
}