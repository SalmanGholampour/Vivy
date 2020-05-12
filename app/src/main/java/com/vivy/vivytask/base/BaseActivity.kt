package com.vivy.vivytask.base


import android.content.Context
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.vivy.vivytask.BR
import dagger.android.AndroidInjection
import dagger.android.support.DaggerAppCompatActivity
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper

abstract class BaseActivity<T : ViewDataBinding, V : BaseViewModel> : DaggerAppCompatActivity()
{
    private lateinit var mViewDataBinding: T
    private var mViewModel: V? = null
    abstract fun getLayoutId(): Int
    abstract fun getViewModel(): V


    override fun onCreate(savedInstanceState: Bundle?) {
        performDependencyInjection()
        super.onCreate(savedInstanceState)
        performDataBinding()
    }

    private fun performDependencyInjection() {
        AndroidInjection.inject(this)
    }

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase))
    }

    private fun performDataBinding() {
        mViewDataBinding = DataBindingUtil.setContentView(this, getLayoutId())
        this.mViewModel = if (mViewModel == null) getViewModel() else mViewModel
        mViewDataBinding.setVariable(BR.viewModel, mViewModel)
        mViewDataBinding.executePendingBindings()
    }



}