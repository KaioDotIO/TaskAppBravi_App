package com.kaio.taskappbravi;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.kaio.taskappbravi.db.RoomAppDb
import com.kaio.taskappbravi.db.TaskEntity

class MainActivityViewModel(app: Application) : AndroidViewModel(app) {
    lateinit var allTasks: MutableLiveData<List<TaskEntity>>
    init {
        allTasks = MutableLiveData()
        getAllTasks()
    }

    fun getAllTasksObservers(): MutableLiveData<List<TaskEntity>>{
        return allTasks
    }

    fun getAllTasks(){
        val taskDao = RoomAppDb.getAppDatabase((getApplication()))?.taskDao()
        val list = taskDao?.getAllTaskInfo()
        allTasks.postValue(list)
    }

    fun insertTaskInfo(entity: TaskEntity){
        val taskDao = RoomAppDb.getAppDatabase((getApplication()))?.taskDao()
        taskDao?.insertTask(entity)
        getAllTasks()
    }
}
