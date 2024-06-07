package com.example.calendertask.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.calendertask.databinding.ViewMyTaskBinding
import com.example.calendertask.model.ItemDayTask
import com.example.calendertask.model.ItemTask
import com.example.calendertask.viewholders.MyTaskViewHolder

class MyListAdapter(
    private val list: List<Pair<Int, List<ItemTask>>>?
) : RecyclerView.Adapter<MyTaskViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyTaskViewHolder {
        return MyTaskViewHolder(
            ViewMyTaskBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = list?.size ?: 0

    override fun onBindViewHolder(holder: MyTaskViewHolder, position: Int) {
        holder.bind(list?.get(position))
    }
}