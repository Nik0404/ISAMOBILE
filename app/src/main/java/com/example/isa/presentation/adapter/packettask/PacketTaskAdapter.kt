package com.example.isa.presentation.adapter.packettask

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.isa.R
import com.example.isa.domain.entity.local.display.DisplayPacketTask
import com.example.isa.presentation.adapter.packetnegotiation.PacketNegotiationAdapter.OnClickPacketNegotiationListener

class PacketTaskAdapter : RecyclerView.Adapter<PacketTaskAdapter.PacketTaskHolder>() {

    private var taskList: List<DisplayPacketTask> = emptyList()
    private var clickListener: OnClickPacketTaskListener? = null

    interface OnClickPacketTaskListener {
        fun onClickPacketTask(position: Int)
    }

    fun setClickListener(listener: OnClickPacketTaskListener) {
        clickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PacketTaskHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_packet_base, parent, false)
        return PacketTaskHolder(itemView)
    }

    override fun getItemCount(): Int = taskList.size

    override fun onBindViewHolder(holder: PacketTaskHolder, position: Int) {
        holder.bind(taskList[position])
    }

    fun setData(newList: List<DisplayPacketTask>) {
        val diff = PacketTaskDiffUtil(taskList, newList)
        val result = DiffUtil.calculateDiff(diff)
        taskList = newList
        result.dispatchUpdatesTo(this)
    }

    inner class PacketTaskHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val namePacket: TextView = itemView.findViewById(R.id.name_packet)
        private val doc: TextView = itemView.findViewById(R.id.doc)
        private val status: TextView = itemView.findViewById(R.id.status)
        private val dateTask: TextView = itemView.findViewById(R.id.data_task)
        private val nameTask: TextView = itemView.findViewById(R.id.name_task)
        private val tst: TextView = itemView.findViewById(R.id.tst)
        private val committer: TextView = itemView.findViewById(R.id.committer)

        fun bind(task: DisplayPacketTask) {
            namePacket.text = task.taskId.toString()
            doc.text = task.docIncoming
            status.text = task.nameStateTask
            dateTask.text = task.dateTaskTr
            nameTask.text = task.nameTask
            tst.text = task.tst
            committer.text = task.committer

            val statusColor = R.color.colorRed

            if (task.nameStateTask == "Активация" || task.nameStateTask == "Закрыто" || task.nameStateTask == "Выполнено") {
                status.setTextColor(ContextCompat.getColor(itemView.context, statusColor))
            }

            itemView.setOnClickListener {
                clickListener?.onClickPacketTask(adapterPosition)
            }
        }
    }
}