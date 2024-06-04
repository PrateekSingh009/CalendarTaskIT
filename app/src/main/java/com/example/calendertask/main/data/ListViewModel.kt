package com.example.calendertask.main.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.calendertask.model.ItemDayTask
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(private val repository: ListRepository) : ViewModel() {

    val _monthDaysLiveData = MutableLiveData<List<ItemDayTask>>()
    val monthDaysLiveData: LiveData<List<ItemDayTask>> = _monthDaysLiveData

    val _taskListLiveData = MutableLiveData<ItemDayTask>()
    val taskListLiveData: LiveData<ItemDayTask> = _taskListLiveData

    val _myTaskListLiveData = MutableLiveData<List<ItemDayTask>>()
    val myTaskListLiveData: LiveData<List<ItemDayTask>> = _myTaskListLiveData

    fun getDaysFromRetrofit(year : Int , month : Int) {
        viewModelScope.launch {
            _monthDaysLiveData.postValue(repository.getDaysFromRetrofit(year,month))
        }
    }

    fun getTaskListFromRetrofit(year : Int , month : Int, day : Int) {
        viewModelScope.launch {
            _taskListLiveData.postValue(repository.getTaskListFromRetrofit(year,month,day))
        }
    }

    fun getMyTaskListFromRetrofit() {
        viewModelScope.launch {
            _myTaskListLiveData.postValue(repository.getMyTaskListFromRetrofit())
        }
    }
}