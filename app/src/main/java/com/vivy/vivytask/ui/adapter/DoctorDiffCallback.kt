package com.vivy.vivytask.ui.adapter

import androidx.annotation.Nullable
import androidx.recyclerview.widget.DiffUtil
import com.vivy.vivytask.data.DoctorData

class DoctorDiffCallback(
    private val oldList: List<DoctorData>,
    private val newList: List<DoctorData>
) : DiffUtil.Callback() {
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return if (oldList[oldItemPosition].id != null && newList[newItemPosition].id != null) {
            oldList[oldItemPosition].id == newList[newItemPosition].id
        } else {
            oldList[oldItemPosition].titleView == newList[newItemPosition].titleView
        }
    }

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areContentsTheSame(oldPosition: Int, newPosition: Int): Boolean {
        val item = oldList[oldPosition]
        val item2 = newList[newPosition]
        return if (item.id != null && item2.id != null) {
            item.id == item2.id
        } else {
            item.titleView == item2.titleView
        }


    }

    @Nullable
    override fun getChangePayload(oldPosition: Int, newPosition: Int): Any? {
        return super.getChangePayload(oldPosition, newPosition)
    }
}