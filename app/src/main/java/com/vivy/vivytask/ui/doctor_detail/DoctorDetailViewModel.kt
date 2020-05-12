package com.vivy.vivytask.ui.doctor_detail

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vivy.vivytask.base.BaseViewModel
import com.vivy.vivytask.data.DoctorData
import com.vivy.vivytask.data.repository.RepositoryInterface
import com.vivy.vivytask.rx.SchedulerProvider
import com.vivy.vivytask.util.getUrl
import io.reactivex.disposables.CompositeDisposable


class DoctorDetailViewModel:ViewModel() {
    var photoId: ObservableField<String> = ObservableField()
    var name: ObservableField<String> = ObservableField()
    var address: ObservableField<String> = ObservableField()
    var rating: ObservableField<Double> = ObservableField()



    fun setDoctorData(doctorData: DoctorData) {
        photoId.set(doctorData.photoId)
        name.set(doctorData.name)
        address.set(doctorData.address)
        rating.set(doctorData.rating)
    }


}