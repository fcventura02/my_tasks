package br.com.mytasks.data

import androidx.lifecycle.LiveData
import androidx.room.*
import br.com.mytasks.model.Task

@Dao
interface TaskDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addTask(task: Task)

    @Update
    suspend fun  updateTask(task: Task)

    @Delete
    suspend fun  deleteTask(task: Task)

    @Query("DELETE FROM task_table")
    suspend fun deleteAllTask()

    @Query("SELECT * FROM TASK_TABLE where not(complet) ORDER BY important desc")
    fun readAllData(): LiveData<List<Task>>
}