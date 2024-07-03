package com.example.eventapp.app

import android.app.Application
import androidx.lifecycle.LifecycleObserver
import timber.log.Timber
import androidx.lifecycle.ProcessLifecycleOwner
import com.example.eventapp.BuildConfig
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class EventApp : Application(), LifecycleObserver {
    override fun onCreate() {
        super.onCreate()
        initTimber()
    }

    private fun initTimber() {
        ProcessLifecycleOwner.get().lifecycle.addObserver(this)
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

}