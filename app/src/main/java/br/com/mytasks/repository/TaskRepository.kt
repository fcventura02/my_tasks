package br.com.mytasks.repository

import androidx.lifecycle.LiveData
import br.com.mytasks.data.TaskDao
import br.com.mytasks.model.Task

class TaskRepository(private val taskDao: TaskDao) {
    val readAllData: LiveData<List<Task>> = taskDao.readAllData()

    suspend fun  addTask(task: Task){
        taskDao.addTask(task)
    }

    suspend fun  updateTask(task: Task){
        taskDao.updateTask(task)
    }

    suspend fun  deleteTask(task: Task){
        taskDao.deleteTask(task)
    }

    suspend fun  deleteAllTask(){
        taskDao.deleteAllTask()
    }

}