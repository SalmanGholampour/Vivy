package com.vivy.vivytask.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.vivy.vivytask.BR
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import dagger.android.support.AndroidSupportInjection

abstract class BaseFragment<T : ViewDataBinding, V : ViewModel> : Fragment()
     {
    private lateinit var mActivity: BaseActivity<*, *>
    private lateinit var mRootView: View
   private lateinit var mViewDataBinding: T
    private lateinit var mViewModel: V
    abstract fun getLayoutId(): Int
    abstract fun getViewModel(): V

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BaseActivity<*, *>) {
            this.mActivity = context

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        performDependencyInjection()
        super.onCreate(savedInstanceState)
        mViewModel = getViewModel()
        setHasOptionsMenu(false)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mViewDataBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        mRootView = mViewDataBinding.root
        return mRootView
    }




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewDataBinding.setVariable(BR.viewModel, mViewModel)
        mViewDataBinding.executePendingBindings()
    }

    private fun performDependencyInjection() {
        AndroidSupportInjection.inject(this)
    }



}