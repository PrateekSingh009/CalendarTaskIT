package com.example.calendertask.main.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.calendertask.R
import com.example.calendertask.databinding.FragmentTaskListBinding
import com.example.calendertask.main.adapter.ListAdapter
import com.example.calendertask.main.adapter.MyListAdapter
import com.example.calendertask.main.data.ListViewModel
import com.example.calendertask.model.ItemDayTask
import com.example.calendertask.model.ItemTask
import com.example.calendertask.utils.extensions.replaceFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TaskListFragment: Fragment() {

    private lateinit var _binding : FragmentTaskListBinding
    private val binding get() = _binding
    private val viewModel : ListViewModel by viewModels<ListViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTaskListBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObserver()
        setupClicks()
        viewModel.getMyTaskListFromRetrofit()
    }

    private fun setupClicks() {
        binding.apply {
            backIcon.setOnClickListener {
                parentFragmentManager.replaceFragment(CalendarFragment(), R.id.fragment_container)
            }
        }
    }

    private fun setupObserver() {
        viewModel.myTaskListLiveData.observe(viewLifecycleOwner) {
            setupRecyclerView(it)
        }
    }

    private fun onItemClick(item : ItemDayTask) {
    }

    private fun setupRecyclerView(list : List<Pair<Int, List<ItemTask>>>?) {
        if (list != null) {
            binding.taskRview.apply {
                adapter = MyListAdapter(list)
                layoutManager = LinearLayoutManager(context)
            }
        }
    }
}