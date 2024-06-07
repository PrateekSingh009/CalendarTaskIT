package com.example.calendertask.viewholders

import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.calendertask.databinding.ViewMyTaskBinding
import com.example.calendertask.main.adapter.ListAdapter
import com.example.calendertask.main.adapter.MyListTaskAdapter
import com.example.calendertask.model.ItemDayTask
import com.example.calendertask.model.ItemTask
import java.time.LocalDate

class MyTaskViewHolder(private val binding: ViewMyTaskBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Pair<Int, List<ItemTask>>?) {
        item?.let{
            binding.apply {
                if(LocalDate.now().year == item.first) {
                    tvYear.visibility = GONE
                    borderView.visibility = GONE
                } else {
                    tvYear.visibility = VISIBLE
                    borderView.visibility = VISIBLE
                    tvYear.text = item.first.toString()
                }
                setupListRecyclerView(item.second)
            }
        }
    }

    private fun setupListRecyclerView(item: List<ItemTask>?) {
        if (item != null) {
            binding.dailyTaskRview.apply {
                adapter = MyListTaskAdapter(item)
                layoutManager = LinearLayoutManager(context)
            }
        }
    }
}