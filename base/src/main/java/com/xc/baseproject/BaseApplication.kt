package com.xc.baseproject

import android.app.Application
import com.crashlytics.android.Crashlytics
import com.crashlytics.android.ndk.CrashlyticsNdk
import com.xc.baseproject.misc.ReleaseTree
import io.fabric.sdk.android.Fabric
import timber.log.Timber

class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        } else {
            Timber.plant(ReleaseTree())
        }
        Fabric.with(this.applicationContext, Crashlytics(), CrashlyticsNdk())
    }
}