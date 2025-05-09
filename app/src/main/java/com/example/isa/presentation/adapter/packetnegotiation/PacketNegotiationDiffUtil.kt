package com.example.isa.presentation.adapter.packetnegotiation

import androidx.recyclerview.widget.DiffUtil
import com.example.isa.domain.entity.local.display.DisplayPacketNegotiation

class PacketNegotiationDiffUtil(
    private val oldList: List<DisplayPacketNegotiation>,
    private val newList: List<DisplayPacketNegotiation>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size
    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]

        return oldItem.isSigned == newItem.isSigned &&
                oldItem.packName == newItem.packName &&
                oldItem.urcDev == newItem.urcDev &&
                oldItem.maxPackChangeSnm == newItem.maxPackChangeSnm &&
                oldItem.blockingTaskName == newItem.blockingTaskName &&
                oldItem.taskList == newItem.taskList
    }
}