package com.vivy.vivytask.di.builder


import com.vivy.vivytask.ui.doctors_list.DoctorsListActivity
import com.vivy.vivytask.ui.doctor_detail.DoctorsDetailFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector
    abstract fun bindActivity(): DoctorsListActivity

    @ContributesAndroidInjector
    abstract fun bindDoctorsListFragment(): DoctorsDetailFragment

}