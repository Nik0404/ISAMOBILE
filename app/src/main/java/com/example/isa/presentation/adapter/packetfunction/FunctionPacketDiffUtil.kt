package com.example.isa.presentation.adapter.packetfunction

import androidx.recyclerview.widget.DiffUtil
import com.example.isa.domain.entity.local.display.DisplayPacketFunction

class FunctionPacketDiffUtil(
    private val oldList: List<DisplayPacketFunction>,
    private val newList: List<DisplayPacketFunction>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]

        return oldItem.idTask == newItem.idTask
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]

        return oldItem.idTask == newItem.idTask &&
                oldItem.functionId == newItem.functionId &&
                oldItem.functionIdFnm == newItem.functionIdFnm &&
                oldItem.writeAccess == newItem.writeAccess &&
                oldItem.ready == newItem.ready &&
                oldItem.scope == newItem.scope &&
                oldItem.scopeSnm == newItem.scopeSnm &&
                oldItem.subsystem == newItem.subsystem &&
                oldItem.subsystemFnm == newItem.subsystemFnm &&
                oldItem.subsystemSnm == newItem.subsystemSnm &&
                oldItem.dbObjPrefix == newItem.dbObjPrefix &&
                oldItem.isIsa2005 == newItem.isIsa2005 &&
                oldItem.isIsa2010 == newItem.isIsa2010 &&
                oldItem.isIsaWeb == newItem.isIsaWeb &&
                oldItem.isStartIsaWeb == newItem.isStartIsaWeb
    }
}