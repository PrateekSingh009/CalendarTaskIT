package com.example.calendertask.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.example.calendertask.databinding.ViewMyTaskItemBinding
import com.example.calendertask.model.ItemDayTask
import com.example.calendertask.model.ItemTask
import com.example.calendertask.utils.extensions.dateSeparator
import com.example.calendertask.utils.extensions.monthToString
import com.example.calendertask.utils.extensions.monthToTrimmedString

class MyTaskItemViewHolder(private val binding: ViewMyTaskItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: ItemTask?) {
        item?.let{
            binding.apply {
                item.date.dateSeparator().let{
                    viewCalendarDayText.text = "${it[2]}\n${it[1].monthToTrimmedString()}"
                }
                tvTask.text = item.task ?: ""
            }
        }
    }


}