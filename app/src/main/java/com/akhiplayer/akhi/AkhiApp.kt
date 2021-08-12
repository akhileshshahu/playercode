package com.akhiplayer.akhi


import android.app.Application
import androidx.viewbinding.BuildConfig
import com.akhiplayer.akhi.di.AppComponent
import com.akhiplayer.akhi.di.DaggerAppComponent
import timber.log.Timber

internal class AkhiApp : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG)
            Timber.plant(Timber.DebugTree())

        appComponent = DaggerAppComponent.factory().create(applicationContext)

    }

}