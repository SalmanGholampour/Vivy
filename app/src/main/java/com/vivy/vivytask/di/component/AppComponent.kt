package com.vivy.vivytask.di.component

import android.app.Application
import com.vivy.vivytask.di.builder.ActivityBuilder
import com.vivy.vivytask.di.module.AppModule
import com.vivy.vivytask.TestApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class, AppModule::class, ActivityBuilder::class])
interface AppComponent {
    fun inject(app: TestApplication)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}