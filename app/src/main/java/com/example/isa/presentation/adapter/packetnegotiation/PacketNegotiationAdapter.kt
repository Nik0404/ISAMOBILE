package com.example.isa.presentation.adapter.packetnegotiation

import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.PopupWindow
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.isa.R
import com.example.isa.domain.entity.local.display.DisplayPacketNegotiation

class PacketNegotiationAdapter :
    RecyclerView.Adapter<PacketNegotiationAdapter.PacketNegotiationViewHolder>() {

    private var packetList: List<DisplayPacketNegotiation> = emptyList()
    private var clickListener: OnClickPacketNegotiationListener? = null

    interface OnClickPacketNegotiationListener {
        fun onClickPacketNegotiation(position: Int)
        fun onApproveClicked(packet: DisplayPacketNegotiation, position: Int)
    }

    fun setClickListener(listener: OnClickPacketNegotiationListener) {
        clickListener = listener
    }

    fun setData(newList: List<DisplayPacketNegotiation>) {
        val diffUtilCallback = PacketNegotiationDiffUtil(packetList, newList)
        val diffResult = DiffUtil.calculateDiff(diffUtilCallback)
        packetList = newList
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PacketNegotiationViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_packet_negotiation, parent, false)
        return PacketNegotiationViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PacketNegotiationViewHolder, position: Int) {
        holder.bind(packetList[position])
    }

    override fun getItemCount(): Int = packetList.size

    inner class PacketNegotiationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val itemContainer: CardView =
            itemView.findViewById(R.id.packet_negotiation_container)
        private val numberPacket: TextView = itemView.findViewById(R.id.number_packet)
        private val namePacket: TextView = itemView.findViewById(R.id.name_packet)
        private val development: TextView = itemView.findViewById(R.id.development)
        private val taskActivation: TextView = itemView.findViewById(R.id.task_activation)
        private val levelChange: TextView = itemView.findViewById(R.id.level_change)
        private val task: TextView = itemView.findViewById(R.id.task)
        private val approveView: ImageView = itemView.findViewById(R.id.approve_view)
        private var currentPopup: PopupWindow? = null

        fun bind(packet: DisplayPacketNegotiation) {
            val (bgColor, statusPacketTitle) = if (packet.isSigned == 0) {
                R.color.colorWhite to itemView.context.getString(R.string.packet_negotiation_text)
            } else {
                R.color.colorGreen to itemView.context.getString(R.string.no_packet_negotiation_text)
            }

            itemContainer.setCardBackgroundColor(ContextCompat.getColor(itemView.context, bgColor))
            numberPacket.text = packet.id.toString()
            namePacket.text = packet.packName
            development.text = packet.urcDev
            task.text = packet.taskList.ifEmpty { "-" }
            taskActivation.text = packet.blockingTaskName.ifEmpty { "-" }

            val colorChange = when (packet.maxPackChangeSnm) {
                itemView.context.getString(R.string.rework) -> R.color.colorPacChangeRevision
                else -> R.color.colorPacChangeError
            }
            levelChange.setTextColor(ContextCompat.getColor(itemView.context, colorChange))
            levelChange.text = packet.maxPackChangeSnm

            itemView.setOnClickListener {
                clickListener?.onClickPacketNegotiation(adapterPosition)
            }

            approveView.setOnClickListener {
                showCustomPopup(it, packet, statusPacketTitle)
            }
        }

        private fun showCustomPopup(
            anchorView: View, packet: DisplayPacketNegotiation, statusPacketTitle: String
        ) {
            currentPopup?.dismiss()

            val popupView =
                LayoutInflater.from(anchorView.context).inflate(R.layout.popup_approve, null)

            val popupWindow = PopupWindow(
                popupView,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                true
            ).apply {
                setBackgroundDrawable(
                    ContextCompat.getDrawable(
                        anchorView.context, R.drawable.item_popup_approve_background
                    )
                )
                elevation = 10f
                setOnDismissListener { currentPopup = null }
            }

            currentPopup = popupWindow

            popupView.findViewById<TextView>(R.id.packet_title).apply {
                text = statusPacketTitle
                setOnClickListener {
                    clickListener?.onApproveClicked(packet, adapterPosition)
                    popupWindow.dismiss()
                }
            }

            val location = IntArray(2)
            anchorView.getLocationOnScreen(location)
            popupWindow.showAtLocation(
                anchorView.rootView,
                Gravity.NO_GRAVITY,
                location[0],
                location[1] + anchorView.height
            )
        }

        fun clearPopup() {
            currentPopup?.dismiss()
            currentPopup = null
        }
    }

    override fun onViewRecycled(holder: PacketNegotiationViewHolder) {
        holder.clearPopup()
        super.onViewRecycled(holder)
    }
}