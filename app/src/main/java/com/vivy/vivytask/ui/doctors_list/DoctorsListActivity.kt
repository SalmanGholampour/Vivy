package com.vivy.vivytask.ui.doctors_list

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.vivy.vivytask.R
import com.vivy.vivytask.base.BaseActivity
import com.vivy.vivytask.data.DoctorData
import com.vivy.vivytask.databinding.ActivityDoctorsListBinding
import com.vivy.vivytask.ui.ViewModelFactory
import com.vivy.vivytask.ui.adapter.DoctorsAdapter
import com.vivy.vivytask.ui.adapter.RecyclerViewOnScrollListener
import com.vivy.vivytask.ui.doctor_detail.DoctorDetailViewModel
import com.vivy.vivytask.ui.doctor_detail.DoctorsDetailFragment
import kotlinx.android.synthetic.main.activity_doctors_list.*
import javax.inject.Inject

class DoctorsListActivity : BaseActivity<ActivityDoctorsListBinding, DoctorsListViewModel>(),
    RecyclerViewOnScrollListener.Listener, DoctorsAdapter.AdapterListener {
    private val adapter: DoctorsAdapter =
        DoctorsAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        setUpViewModel()
        setUp()
        mViewModel.loadData()
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var mViewModel: DoctorsListViewModel


    override fun getLayoutId(): Int {
        return R.layout.activity_doctors_list
    }

    override fun getViewModel(): DoctorsListViewModel {
        mViewModel = ViewModelProvider(this, viewModelFactory).get(DoctorsListViewModel::class.java)
        return mViewModel
    }

    private fun setUpViewModel() {
        mViewModel.listViewLiveData.observe(this, Observer {
            adapter.addItemsToList(it)
        })

    }

    private fun setUp() {
        doctorsRecyclerView.setHasFixedSize(true)

        val layoutManager = LinearLayoutManager(this)
        doctorsRecyclerView.layoutManager = layoutManager

        val dividerItemDecoration = DividerItemDecoration(
            doctorsRecyclerView.context,
            layoutManager.orientation
        )
        doctorsRecyclerView.addItemDecoration(dividerItemDecoration)
        doctorsRecyclerView.addOnScrollListener(
            RecyclerViewOnScrollListener(
                this
            )
        )
        adapter.setListener(this)
        doctorsRecyclerView.adapter = adapter

    }

    override fun fetchMoreData() {
        mViewModel.loadData()
    }

    override fun onItemClick(data: DoctorData) {
                val doctorFragment: Fragment =DoctorsDetailFragment()
        val bundle=Bundle()
        bundle.putParcelable(DoctorsDetailFragment.DOCTOR_DATA_KEY,data)
        doctorFragment.arguments=bundle
        supportFragmentManager
            .beginTransaction()
            .addToBackStack(DoctorsDetailFragment.TAG)
            .add(R.id.lytMain, doctorFragment, DoctorsDetailFragment.TAG)
            .commit()
        mViewModel.updateRecentDoctors(data)
    }
}
