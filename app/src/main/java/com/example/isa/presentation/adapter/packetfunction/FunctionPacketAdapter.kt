package com.example.isa.presentation.adapter.packetfunction

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.isa.R
import com.example.isa.domain.entity.local.display.DisplayPacketFunction

class FunctionPacketAdapter : RecyclerView.Adapter<FunctionPacketAdapter.PacketFunctionHolder>() {

    private var functionList: List<DisplayPacketFunction> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PacketFunctionHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_packet_function, parent, false)
        return PacketFunctionHolder(itemView)
    }

    override fun getItemCount(): Int = functionList.size

    override fun onBindViewHolder(holder: PacketFunctionHolder, position: Int) {
        holder.bind(functionList[position])
    }

    fun setData(newList: List<DisplayPacketFunction>) {
        val diff = FunctionPacketDiffUtil(functionList, newList)
        val result = DiffUtil.calculateDiff(diff)
        functionList = newList
        result.dispatchUpdatesTo(this)
    }

    inner class PacketFunctionHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleNameTask: TextView = itemView.findViewById(R.id.title_name_task_base)
        private val ttypeFunction: TextView = itemView.findViewById(R.id.type_function)
        private val devWebIsa: TextView = itemView.findViewById(R.id.dev_web_isa)
        private val isaFive: TextView = itemView.findViewById(R.id.isa_five)
        private val isaTen: TextView = itemView.findViewById(R.id.isa_te)
        private val webIsa: TextView = itemView.findViewById(R.id.web_isa)

        fun bind(function: DisplayPacketFunction) {
            titleNameTask.text =
                "№${function.functionId} ${function.functionIdFnm.replace(Regex("<.*?>"), "")}"
            ttypeFunction.text = function.scopeSnm.replace(Regex("<.*?>"), "")
            devWebIsa.text = function.isStartIsaWeb.replace(Regex("<.*?>"), "")
            isaFive.text = function.isIsa2005
            isaTen.text = function.isIsa2010
            webIsa.text = function.isIsaWeb

        }
    }
}