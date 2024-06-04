package com.example.calendertask.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.calendertask.databinding.ViewCalenderDayBinding
import com.example.calendertask.databinding.ViewCalenderDayOffBinding
import com.example.calendertask.databinding.ViewCalenderDayTaskBinding
import com.example.calendertask.model.ItemDayTask
import com.example.calendertask.viewholders.CalenderDayOffViewHolder
import com.example.calendertask.viewholders.CalenderDayTaskViewHolder
import com.example.calendertask.viewholders.CalenderDayViewHolder

class GridAdapter(
    private val list: List<ItemDayTask>,
    private val onClick: ((item: ItemDayTask) -> Unit)?,
    private val onLongClick: ((item: ItemDayTask) -> Unit)?,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        when(viewType) {
            0 -> {
                return CalenderDayOffViewHolder(
                    ViewCalenderDayOffBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
            1 -> {
                return CalenderDayViewHolder(
                    ViewCalenderDayBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
            2 -> {
                return CalenderDayTaskViewHolder(
                    ViewCalenderDayTaskBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
            else -> {
                return CalenderDayViewHolder(
                    ViewCalenderDayBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
        }
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is CalenderDayViewHolder -> {
                holder.bind(list[position], onClick, onLongClick)
            }
            is CalenderDayOffViewHolder -> {
                holder.bind(list[position], onClick, onLongClick)
            }
            is CalenderDayTaskViewHolder -> {
                holder.bind(list[position], onClick, onLongClick)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        when(list[position].day) {
            0 ->  return 0
            else -> {
                if(list[position].task.isNullOrEmpty())
                    return 1
                else
                    return 2
            }
        }
    }
}