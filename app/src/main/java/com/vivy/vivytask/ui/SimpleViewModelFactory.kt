package com.vivy.vivytask.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vivy.vivytask.data.repository.RepositoryInterface
import com.vivy.vivytask.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject


class SimpleViewModelFactory @Inject constructor() : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(

        ).newInstance()
    }

}