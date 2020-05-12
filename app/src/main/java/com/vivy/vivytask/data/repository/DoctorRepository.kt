package com.vivy.vivytask.data.repository

import com.vivy.vivytask.data.DoctorsResponse
import com.vivy.vivytask.data.remote.DoctorService
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DoctorRepository @Inject constructor():
    RepositoryInterface
{
    @Inject
    internal lateinit var doctorService: DoctorService

    override fun loadDoctors(url: String?): Single<DoctorsResponse> {
        return doctorService.loadDoctors(url)
    }


}