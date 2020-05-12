package com.vivy.vivytask.data

import com.google.gson.annotations.SerializedName

data class DoctorsResponse (

    @SerializedName("lastKey")
    var lastKey: String,
    @SerializedName("doctors")
    var data: MutableList<DoctorData>
)