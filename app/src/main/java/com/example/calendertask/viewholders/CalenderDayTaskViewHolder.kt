package com.example.calendertask.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.example.calendertask.databinding.ViewCalenderDayTaskBinding
import com.example.calendertask.model.ItemDayTask

class CalenderDayTaskViewHolder(private val binding: ViewCalenderDayTaskBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(
        item: ItemDayTask?,
        onClick: ((item: ItemDayTask) -> Unit)?,
        onLongClick: ((item: ItemDayTask) -> Unit)?
    ) {
        item?.let {
            binding.apply {
                viewCalendarDayText.text = item.day.toString()
            }
            itemView.setOnLongClickListener {
                onLongClick?.invoke(item)
                return@setOnLongClickListener true
            }
            itemView.setOnClickListener {
                onClick?.invoke(item)
            }
        }
    }
}