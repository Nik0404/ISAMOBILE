package com.example.isa.presentation.adapter.packettask

import androidx.recyclerview.widget.DiffUtil
import com.example.isa.domain.entity.local.display.DisplayPacketTask

class PacketTaskDiffUtil(
    private val oldList: List<DisplayPacketTask>,
    private val newList: List<DisplayPacketTask>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]

        return oldItem.id == newItem.id &&
                oldItem.taskId == newItem.taskId &&
                oldItem.docIncoming == newItem.docIncoming &&
                oldItem.nameTask == newItem.nameTask &&
                oldItem.dateTaskTr == newItem.dateTaskTr &&
                oldItem.clientDevap == newItem.clientDevap &&
                oldItem.docRegistryName == newItem.docRegistryName &&
                oldItem.curator == newItem.curator &&
                oldItem.dev == newItem.dev &&
                oldItem.tst == newItem.tst &&
                oldItem.committer == newItem.committer &&
                oldItem.memoTask == newItem.memoTask
    }
}