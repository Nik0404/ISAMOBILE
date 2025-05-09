package com.example.isa.presentation.adapter.packetcontent

import androidx.recyclerview.widget.DiffUtil
import com.example.isa.domain.entity.local.display.DisplayPacketcontent

class PacketContentDiffUtil(
    private val oldList: List<DisplayPacketcontent>,
    private val newList: List<DisplayPacketcontent>
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
                oldItem.packId == newItem.packId &&
                oldItem.name == newItem.name &&
                oldItem.componentType == newItem.componentType &&
                oldItem.title == newItem.title &&
                oldItem.version == newItem.version &&
                oldItem.subsystem == newItem.subsystem &&
                oldItem.developer == newItem.developer &&
                oldItem.status == newItem.status &&
                oldItem.dtPublic == newItem.dtPublic
    }

}