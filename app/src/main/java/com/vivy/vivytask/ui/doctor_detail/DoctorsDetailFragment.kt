package com.vivy.vivytask.ui.doctor_detail

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.vivy.vivytask.R
import com.vivy.vivytask.base.BaseFragment
import com.vivy.vivytask.data.DoctorData
import com.vivy.vivytask.databinding.FragmentDoctorDetailBinding
import com.vivy.vivytask.ui.SimpleViewModelFactory
import com.vivy.vivytask.ui.ViewModelFactory
import com.vivy.vivytask.ui.doctors_list.DoctorsListViewModel
import javax.inject.Inject

class DoctorsDetailFragment : BaseFragment<FragmentDoctorDetailBinding, DoctorDetailViewModel>() {
    private var doctorData: DoctorData? = null

    @Inject
    lateinit var viewModelFactory: SimpleViewModelFactory
    private lateinit var mViewModel: DoctorDetailViewModel

    companion object {
        val TAG: String = DoctorsDetailFragment::class.java.simpleName
        val DOCTOR_DATA_KEY: String = "DoctorData"

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        doctorData = arguments!!.getParcelable(DOCTOR_DATA_KEY)
        doctorData?.let {
            mViewModel.setDoctorData(doctorData!!)
        }
    }


    override fun getLayoutId(): Int {
        return R.layout.fragment_doctor_detail
    }

    override fun getViewModel(): DoctorDetailViewModel {
        mViewModel =
            ViewModelProvider(this, viewModelFactory).get(DoctorDetailViewModel::class.java)
        return mViewModel
    }

}