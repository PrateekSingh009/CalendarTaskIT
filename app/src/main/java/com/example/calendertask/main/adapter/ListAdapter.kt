package com.example.calendertask.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.calendertask.databinding.ViewCalenderDayBinding
import com.example.calendertask.databinding.ViewDailyTaskBinding
import com.example.calendertask.model.ItemDayTask
import com.example.calendertask.viewholders.CalenderDayViewHolder
import com.example.calendertask.viewholders.DailyTaskViewHolder

class ListAdapter(
    private val list: List<String>?
) : RecyclerView.Adapter<DailyTaskViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyTaskViewHolder {
        return DailyTaskViewHolder(
            ViewDailyTaskBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = list?.size ?: 0

    override fun onBindViewHolder(holder: DailyTaskViewHolder, position: Int) {
        holder.bind(list?.get(position))
    }
}