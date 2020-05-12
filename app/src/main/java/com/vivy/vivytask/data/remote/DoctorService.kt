package com.vivy.vivytask.data.remote


import com.vivy.vivytask.data.DoctorsResponse
import io.reactivex.Single
import retrofit2.http.*

interface DoctorService {

    @GET
    fun loadDoctors(@Url url: String?): Single<DoctorsResponse>

}