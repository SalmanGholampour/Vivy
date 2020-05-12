package com.vivy.vivytask.di.module

import android.app.Application
import android.content.Context
import com.vivy.vivytask.BuildConfig
import com.vivy.vivytask.R
import com.vivy.vivytask.data.remote.DoctorService
import com.vivy.vivytask.data.repository.DoctorRepository
import com.vivy.vivytask.data.repository.RepositoryInterface
import com.vivy.vivytask.rx.AppSchedulerProvider
import com.vivy.vivytask.rx.SchedulerProvider
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import uk.co.chrisjenx.calligraphy.CalligraphyConfig
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
class AppModule {
    @Provides
    @Singleton
    fun provideApiHelper(doctorRepository: DoctorRepository): RepositoryInterface {
        return doctorRepository
    }



    @Provides
    @Singleton
    internal fun provideAppApi(retrofit: Retrofit): DoctorService {
        return retrofit.create(DoctorService::class.java)
    }


    @Provides
    @Singleton
    internal fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }


    @Provides
    @Singleton
    internal fun provideContext(application: Application): Context {
        return application
    }



    @Provides
    @Singleton
    internal fun provideOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient().newBuilder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)

            .build()
    }

    @Provides
    internal fun provideSchedulerProvider(): SchedulerProvider {
        return AppSchedulerProvider()
    }

    @Provides
    internal fun provideCompositeDisposableProvider(): CompositeDisposable {
        return CompositeDisposable()
    }

    @Provides
    @Singleton
    internal fun provideCalligraphyDefaultConfig(): CalligraphyConfig {
        return CalligraphyConfig.Builder()
            .setDefaultFontPath("fonts/roboto.ttf")
            .setFontAttrId(R.attr.fontPath)
            .build()
    }


}
