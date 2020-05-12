package com.vivy.vivytask

import android.app.Application
import com.vivy.vivytask.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import uk.co.chrisjenx.calligraphy.CalligraphyConfig
import javax.inject.Inject

class TestApplication : Application(), HasAndroidInjector {


    @Inject
    internal lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Any>


    @Inject
    internal lateinit var mCalligraphyConfig: CalligraphyConfig



    override fun onCreate() {
        super.onCreate()

        DaggerAppComponent.builder()
            .application(this)
            .build()
            .inject(this)


        CalligraphyConfig.initDefault(mCalligraphyConfig)

    }

    override fun androidInjector(): AndroidInjector<Any> {
        return activityDispatchingAndroidInjector
    }
}