package com.aaron_code.cakelist

import android.app.Activity
import android.app.Application
import android.content.Context
import com.aaron_code.cakelist.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import java.lang.ref.WeakReference
import javax.inject.Inject

class MyApplication: Application(), HasActivityInjector {

    companion object {
        var currentContext: WeakReference<Context>? = null
    }

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()

        DaggerAppComponent.builder().build().inject(this)
    }
    override fun activityInjector(): AndroidInjector<Activity> {
        return dispatchingAndroidInjector
    }
}