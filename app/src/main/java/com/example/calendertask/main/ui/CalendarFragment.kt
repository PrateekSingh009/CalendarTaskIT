package com.example.calendertask.main.ui

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.calendertask.R
import com.example.calendertask.databinding.FragmentCalendarBinding
import com.example.calendertask.main.adapter.GridAdapter
import com.example.calendertask.main.adapter.ListAdapter
import com.example.calendertask.main.data.ListViewModel
import com.example.calendertask.main.dialog.TaskDialog
import com.example.calendertask.model.ItemDayTask
import com.example.calendertask.utils.extensions.dateToString
import com.example.calendertask.utils.extensions.monthToString
import com.example.calendertask.utils.extensions.replaceFragment
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDate


@AndroidEntryPoint
class CalendarFragment : Fragment() {

    private lateinit var _binding : FragmentCalendarBinding
    private val binding get() = _binding
    private val viewModel : ListViewModel by viewModels<ListViewModel>()
    private val today = LocalDate.now()
    private var currentDate = today
    private lateinit var currentSelectedDateItem : ItemDayTask
    private lateinit var datePickerDialog : DatePickerDialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCalendarBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObserver()
        initDataPicker()
        setupClicks()
        setupCalendar(currentDate)
        setupDailyTaskDate(today.year,today.monthValue,today.dayOfMonth)
    }

    private fun setupCalendar(date : LocalDate) {
        viewModel.getDaysFromRetrofit(date.year,date.monthValue)
        binding.apply {
            calendarLayout.textMonth.text = date.monthValue.monthToString()
            calendarLayout.textYear.text = date.year.toString()
        }
    }

    private fun initDataPicker() {
        val dateSetListener = OnDateSetListener { p0: DatePicker?, p1: Int, p2: Int, p3: Int ->
            val selectedDateFromPicker = LocalDate.of(p1,p2+1,p3)
            setupCalendar(selectedDateFromPicker)
            currentDate = selectedDateFromPicker
        }
        datePickerDialog = DatePickerDialog(requireContext(),AlertDialog.THEME_HOLO_LIGHT,dateSetListener,today.year,today
            .monthValue,today.dayOfMonth)
    }

    private fun setupDailyTaskDate(year : Int , month : Int, day : Int) {
        binding.apply {
            date.text = dateToString(year, month, day)
        }
    }

    private fun onItemLongClick(item : ItemDayTask) {
        showDialogFragment(item)
    }

    private fun onItemClick(item : ItemDayTask) {
        getDailyTask(item.year,item.month,item.day)
        setupDailyTaskDate(item.year,item.month,item.day)
        currentSelectedDateItem = item
    }

    private fun setupClicks() {
        binding.apply {
            myListIcon.setOnClickListener {
                parentFragmentManager.replaceFragment(TaskListFragment(), R.id.fragment_container)
            }
            dateSet.setOnClickListener {
                datePickerDialog.show()
            }
            addTaskIcon.setOnClickListener {
                showDialogFragment(currentSelectedDateItem)
            }
            calendarLayout.activityMainPagerButtonLeftArrow.setOnClickListener {
                val previous = currentDate.minusMonths(1)
                setupCalendar(previous)
                currentDate = previous
            }
            calendarLayout.activityMainPagerButtonRightArrow.setOnClickListener {
                val next = currentDate.plusMonths(1)
                setupCalendar(next)
                currentDate = next
            }
        }
    }

    private fun showDialogFragment(item : ItemDayTask) {
        val dialogFragment = TaskDialog(item ,::addTaskToDatabase)
        dialogFragment.show(
            parentFragmentManager,
            dialogFragment.tag
        )
    }

    private fun getDailyTask(year : Int , month : Int, day : Int) {
        viewModel.getTaskListFromRetrofit(year,month,day)
    }

    private fun addTaskToDatabase(item: ItemDayTask) {

    }

    private fun setupObserver() {
        viewModel.monthDaysLiveData.observe(viewLifecycleOwner) {
            setupCalendarRecyclerView(it)
            setupCurrentItem(it)
        }
        viewModel.taskListLiveData.observe(viewLifecycleOwner) {
            setupListRecyclerView(it)
        }
    }

    private fun setupCurrentItem(items : List<ItemDayTask>){
        for (item in items) {
            if(item.day == currentDate.dayOfMonth && item.month == currentDate.monthValue && item.year == currentDate.year){
                currentSelectedDateItem = item
                break
            }
        }
    }

    private fun setupListRecyclerView(item : ItemDayTask?) {
        if (item != null) {
            binding.dailyTaskRview.apply {
                adapter = ListAdapter(item.task)
                layoutManager = LinearLayoutManager(context)
            }
        }
    }

    private fun setupCalendarRecyclerView(list : List<ItemDayTask>?) {
        if (list != null) {
            binding.calendarLayout.dateRview.apply {
                adapter = GridAdapter(list, ::onItemClick, ::onItemLongClick)
                layoutManager = GridLayoutManager(context,7)
            }
        }
    }

}

