package com.vivy.vivytask.base

import androidx.lifecycle.ViewModel
import com.vivy.vivytask.data.repository.RepositoryInterface
import com.vivy.vivytask.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel(val repository: RepositoryInterface, val compositeDisposable: CompositeDisposable, val schedulerProvider: SchedulerProvider) : ViewModel() {
    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}