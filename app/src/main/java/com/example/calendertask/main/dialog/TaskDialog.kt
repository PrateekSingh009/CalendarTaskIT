package com.example.calendertask.main.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.calendertask.databinding.TaskDialogBinding
import com.example.calendertask.model.ItemDayTask
import com.example.calendertask.utils.extensions.dateToString
import com.example.calendertask.utils.extensions.setWidthPercent

class TaskDialog(
    private val item : ItemDayTask,
    private val saveData : (task : ItemDayTask) -> Unit
) : DialogFragment() {
    private lateinit var _binding : TaskDialogBinding
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = TaskDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setWidthPercent(95)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            tvDate.text = dateToString(item.year,item.month,item.day)
            Save.setOnClickListener {
                val taskData = nameEditText.text.toString()
                if(taskData.isNullOrEmpty()) {
                    nameEditText.error = "Field Required"
                    return@setOnClickListener
                }
                val newList = item.task?.toMutableList()
                newList?.add(taskData)
                val newTask = ItemDayTask(item.id,item.day,item.month,item.year, newList)
                saveData.invoke(newTask)
                dialog?.dismiss()
            }
        }
    }

}