package com.vivy.vivytask.data.repository

import com.vivy.vivytask.data.DoctorsResponse
import io.reactivex.Single

interface RepositoryInterface {

    fun loadDoctors(url:String?): Single<DoctorsResponse>

}