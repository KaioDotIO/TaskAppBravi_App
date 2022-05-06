package com.kaio.taskappbravi

import android.annotation.SuppressLint
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.kaio.taskappbravi.db.TaskEntity
import kotlinx.android.synthetic.main.item_view.view.*
import java.util.*

class RecyclerViewAdapter(val listener: RowClickListener) :
    RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {

    var items = ArrayList<TaskEntity>()

    fun setListData(data: ArrayList<TaskEntity>) {
        this.items = data
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val inflater =
            LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
        return MyViewHolder(inflater, listener)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            listener.onItemClickListener(items[position])

        }
        holder.bind(items[position])
    }

    @RequiresApi(Build.VERSION_CODES.N)
    class MyViewHolder(view: View, val listener: RowClickListener) : RecyclerView.ViewHolder(view) {
        var cardview = view.cardview
        val tvTaskName = view.tvTaskName
        var status = view.tvStatus
        var btnStart = view.btnStart
        var btnFinish = view.btnFinish
        var btnGiveUpTask = view.btnGiveup
        var startTime = view.tvStartTime
        var endTime = view.tvEndTime


        @SuppressLint("SetTextI18n")
        fun bind(data: TaskEntity) {
            tvTaskName.text = data.activity

            btnStart.setOnClickListener {
                if (startTime.text == "You started at") {
                    startTime.text = "You started at ${
                        Calendar.getInstance().get(Calendar.HOUR)
                    }:${Calendar.getInstance().get(Calendar.MINUTE)}:${
                        Calendar.getInstance().get(Calendar.SECOND)
                    }"
                    status.text = "Status: In progress"
                }
            }

            btnFinish.setOnClickListener {
                if (endTime.text == "And finished at") {
                    endTime.text = "And finished at ${
                        Calendar.getInstance().get(Calendar.HOUR)
                    }:${Calendar.getInstance().get(Calendar.MINUTE)}:${
                        Calendar.getInstance().get(Calendar.SECOND)
                    }"
                    status.text = "Status: Finished"
                }
            }

            btnGiveUpTask.setOnClickListener {
                status.text = "Status: Abandoned"
                startTime.text = "You started at"
                endTime.text = "And finished at"
            }
        }
    }

    interface RowClickListener {
        fun onDeleteUserClickListener(user: TaskEntity)
        fun onItemClickListener(user: TaskEntity)
    }
}