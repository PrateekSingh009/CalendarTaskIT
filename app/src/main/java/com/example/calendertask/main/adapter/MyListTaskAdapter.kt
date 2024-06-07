package com.example.calendertask.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.calendertask.databinding.ViewDailyTaskBinding
import com.example.calendertask.databinding.ViewMyTaskItemBinding
import com.example.calendertask.model.ItemTask
import com.example.calendertask.viewholders.DailyTaskViewHolder
import com.example.calendertask.viewholders.MyTaskItemViewHolder

class MyListTaskAdapter(
    private val list: List<ItemTask>?
) : RecyclerView.Adapter<MyTaskItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyTaskItemViewHolder {
        return MyTaskItemViewHolder(
            ViewMyTaskItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = list?.size ?: 0

    override fun onBindViewHolder(holder: MyTaskItemViewHolder, position: Int) {
        holder.bind(list?.get(position))
    }
}