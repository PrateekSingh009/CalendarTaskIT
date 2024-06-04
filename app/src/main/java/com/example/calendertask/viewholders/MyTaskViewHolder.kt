package com.example.calendertask.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.example.calendertask.databinding.ViewMyTaskBinding
import com.example.calendertask.model.ItemDayTask
import com.example.calendertask.utils.extensions.monthToString

class MyTaskViewHolder(private val binding: ViewMyTaskBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: ItemDayTask?) {
        item?.let{
            binding.apply {
                viewCalendarDayText.text = "${item.day}\n${item.month.monthToString()}"
                tvTask.text = item.task?.get(0) ?: ""
            }
        }
    }
}