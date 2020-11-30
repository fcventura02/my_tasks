package br.com.mytasks.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import br.com.mytasks.data.TaskDatabase
import br.com.mytasks.repository.TaskRepository
import br.com.mytasks.model.Task
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TaskViewModel(application: Application): AndroidViewModel(application) {
    val readAllData: LiveData<List<Task>>
    private val repository: TaskRepository

    init {
        val  taskDao = TaskDatabase.getDatabase(application).taskDao()
        repository = TaskRepository(taskDao)
        readAllData = repository.readAllData
    }

    fun addTask(task: Task){
        viewModelScope.launch (Dispatchers.IO){
            repository.addTask(task)
        }
    }

    fun updateTask(task: Task){
        viewModelScope.launch (Dispatchers.IO){
            repository.updateTask(task)
        }
    }

    fun deleteTask(task: Task){
        viewModelScope.launch (Dispatchers.IO){
            repository.deleteTask(task)
        }
    }

    fun deleteAllTask(){
        viewModelScope.launch (Dispatchers.IO){
            repository.deleteAllTask()
        }
    }
}
