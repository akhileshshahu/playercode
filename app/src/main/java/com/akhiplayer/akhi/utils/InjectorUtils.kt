package com.akhiplayer.akhi.utils


import android.app.Activity
import android.app.Service
import com.akhiplayer.akhi.AkhiApp
import com.akhiplayer.akhi.di.AppComponent

internal val Activity.injector: AppComponent
    get() = (applicationContext as AkhiApp).appComponent

internal val Service.injector: AppComponent
    get() = (applicationContext as AkhiApp).appComponent