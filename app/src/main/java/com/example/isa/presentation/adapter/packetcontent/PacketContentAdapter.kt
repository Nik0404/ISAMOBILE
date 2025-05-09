package com.example.isa.presentation.adapter.packetcontent

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.isa.databinding.ItemPacketContentBinding
import com.example.isa.domain.entity.local.display.DisplayPacketcontent

class PacketContentAdapter : RecyclerView.Adapter<PacketContentAdapter.PacketContentHolder>() {

    private var packetContentList: List<DisplayPacketcontent> = emptyList()

    fun setData(newList: List<DisplayPacketcontent>) {
        val diffUtilCallback = PacketContentDiffUtil(packetContentList, newList)
        val diffResult = DiffUtil.calculateDiff(diffUtilCallback)
        packetContentList = newList
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PacketContentHolder {
        val binding = ItemPacketContentBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PacketContentHolder(binding)
    }

    override fun getItemCount(): Int = packetContentList.size

    override fun onBindViewHolder(holder: PacketContentHolder, position: Int) {
        holder.bind(packetContentList[position])
    }

    inner class PacketContentHolder(
        private val binding: ItemPacketContentBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(packet: DisplayPacketcontent) {
            with(binding) {
                namePacket.text = packet.name.ifEmpty { "-" }
                type.text = packet.componentType.ifEmpty { "-" }
                taskActivation.text = packet.title.ifEmpty { "-" }
                version.text = packet.version.toString().ifEmpty { "-" }
                subsystem.text = packet.subsystem.ifEmpty { "-" }
                development.text = packet.developer.ifEmpty { "-" }
                status.text = packet.status.ifEmpty { "-" }
                timePublication.text = packet.dtPublic.ifEmpty { "-" }
            }
        }
    }
}