package com.vivy.vivytask.ui.doctors_list

import androidx.databinding.ObservableField
import com.vivy.vivytask.data.DoctorData

class ItemDoctorViewModel(val doctorData: DoctorData, val listener: ItemViewListener) {
    var photoId: ObservableField<String> = ObservableField()
    var name: ObservableField<String> = ObservableField()
    var rating: ObservableField<Double> = ObservableField()

    init {
        photoId.set(doctorData.photoId)
        name.set(doctorData.name)
        rating.set(doctorData.rating)
    }

    fun onItemClick() {
        listener.onClick(doctorData)
    }

    interface ItemViewListener {
        fun onClick(doctorData: DoctorData)
    }
}