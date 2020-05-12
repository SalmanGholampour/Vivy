package com.vivy.vivytask.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.vivy.vivytask.base.BaseViewHolder
import com.vivy.vivytask.data.DoctorData
import com.vivy.vivytask.databinding.ItemDoctorViewBinding
import com.vivy.vivytask.databinding.ItemTitleViewBinding
import com.vivy.vivytask.ui.doctors_list.ItemDoctorViewModel
import com.vivy.vivytask.ui.doctors_list.ItemTitleViewModel

private const val TYPE_DOCTOR = 1
private const val TYPE_TITLE = 2

class DoctorsAdapter : RecyclerView.Adapter<BaseViewHolder>() {
    var mListener: AdapterListener? = null

    interface AdapterListener {
        fun onItemClick(data: DoctorData)
    }

    fun setListener(listener: AdapterListener) {
        this.mListener = listener
    }

    private var data = mutableListOf<DoctorData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return when (viewType) {
            TYPE_DOCTOR -> {
                val binding: ItemDoctorViewBinding =
                    ItemDoctorViewBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                DoctorViewHolder(binding)
            }
            else -> {
                val binding: ItemTitleViewBinding =
                    ItemTitleViewBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                TitleViewHolder(binding)
            }
        }

    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (data[position].titleView != null) {
            TYPE_TITLE
        } else {
            TYPE_DOCTOR
        }


    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.onBind(position)
    }

    fun addItemsToList(newData: MutableList<DoctorData>) {
        val diffCallback = DoctorDiffCallback(data, newData)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        data = newData.toMutableList()

        diffResult.dispatchUpdatesTo(this)
    }


    inner class DoctorViewHolder(private val binding: ItemDoctorViewBinding) :
        BaseViewHolder(binding.root),
        ItemDoctorViewModel.ItemViewListener {


        override fun onClick(doctorData: DoctorData) {
            mListener?.onItemClick(doctorData)
        }

        override fun onBind(position: Int) {
            val viewModel =
                ItemDoctorViewModel(
                    data[position],
                    this
                )
            binding.viewModel = viewModel
            binding.executePendingBindings()

        }

    }

    inner class TitleViewHolder(private val binding: ItemTitleViewBinding) :
        BaseViewHolder(binding.root) {


        override fun onBind(position: Int) {
            val viewModel =
                ItemTitleViewModel(data[position].titleView!!)
            binding.viewModel = viewModel
            binding.executePendingBindings()
        }


    }
}