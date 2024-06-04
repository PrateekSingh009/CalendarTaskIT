package com.example.calendertask.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.example.calendertask.databinding.ViewDailyTaskBinding
import com.example.calendertask.model.ItemDayTask

class DailyTaskViewHolder(private val binding: ViewDailyTaskBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: String?) {
        item?.let{
            binding.apply {
                tvTask.text = item
            }
        }
    }
}