package com.vivy.vivytask.ui.doctors_list

import androidx.lifecycle.MutableLiveData
import com.vivy.vivytask.base.BaseViewModel
import com.vivy.vivytask.data.DoctorData
import com.vivy.vivytask.data.repository.RepositoryInterface
import com.vivy.vivytask.rx.SchedulerProvider
import com.vivy.vivytask.util.getUrl
import io.reactivex.disposables.CompositeDisposable

private const val VIVY_DOCTORS = "Vivy Doctors"
private const val RECENT_DOCTORS = "Recent Doctors"

class DoctorsListViewModel(
    repository: RepositoryInterface, compositeDisposable: CompositeDisposable,
    schedulerProvider: SchedulerProvider
) : BaseViewModel(repository, compositeDisposable, schedulerProvider) {
    private var lastKey: String? = ""
    var listViewLiveData = MutableLiveData<MutableList<DoctorData>>()
    val vivyDoctors: MutableList<DoctorData> = mutableListOf()
    var recentDoctors: MutableList<DoctorData> = mutableListOf()
    fun loadData() {
        if (lastKey != null)
            compositeDisposable.add(
                repository.loadDoctors(getUrl(lastKey)).subscribeOn(schedulerProvider.io())
                    .observeOn(
                        schedulerProvider.ui()
                    ).subscribe({
                        lastKey = it.lastKey
                        vivyDoctors.addAll(it.data)
                        updateList(recentDoctors,vivyDoctors)
                    }) {
//Handle Error
                    })
    }

    fun updateRecentDoctors(data: DoctorData) {
        if (recentDoctors.contains(data)) {
            recentDoctors.remove(data)
        }
        if (vivyDoctors.contains(data)) {
            vivyDoctors.remove(data)
        }
        recentDoctors.add(0, data)
        if (recentDoctors.size > 3) {

            vivyDoctors.add(recentDoctors[3])
            recentDoctors = recentDoctors.take(3).toMutableList()
        }
        updateList(recentDoctors,vivyDoctors)
    }


    fun updateList(recentList:List<DoctorData>,mainList:List<DoctorData>) {

        val value: MutableList<DoctorData> = mutableListOf()
        if (recentList.isNotEmpty()) {
            val recentTitle = DoctorData(RECENT_DOCTORS)
            value.add(recentTitle)
            value.addAll(recentList)
        }
        val recentTitle = DoctorData(VIVY_DOCTORS)
        value.add(recentTitle)
        value.addAll(mainList.sortedByDescending { it.rating })

        listViewLiveData.value = value

    }

}