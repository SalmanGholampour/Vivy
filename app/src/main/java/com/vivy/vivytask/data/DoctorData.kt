package com.vivy.vivytask.data

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlin.math.roundToInt

data class DoctorData(

    @SerializedName("id")
    var id: String?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("photoId")
    var photoId: String?,
    @SerializedName("rating")
    private var rate: Double?,
    @SerializedName("address")
    var address: String?,
    @SerializedName("lat")
    var lat: Double?,
    @SerializedName("lng")
    var lng: Double?,
    var titleView: String?
//We can have other attributes here

) : Parcelable {
    constructor(title: String) : this(null, null, null, null, null, null, null, title)

    val rating: Double
        get() = if (this.rate != null) {
            this.rate!!.roundTo1Decimal()
        } else 0.0

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Double::class.java.classLoader) as? Double,
        parcel.readString(),
        parcel.readValue(Double::class.java.classLoader) as? Double,
        parcel.readValue(Double::class.java.classLoader) as? Double,
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(name)
        parcel.writeString(photoId)
        parcel.writeValue(rate)
        parcel.writeString(address)
        parcel.writeValue(lat)
        parcel.writeValue(lng)
        parcel.writeString(titleView)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<DoctorData> {
        override fun createFromParcel(parcel: Parcel): DoctorData {
            return DoctorData(parcel)
        }

        override fun newArray(size: Int): Array<DoctorData?> {
            return arrayOfNulls(size)
        }
    }
}

fun Double.roundTo1Decimal(): Double {

    return (this * 10.0).roundToInt() / 10.0
}