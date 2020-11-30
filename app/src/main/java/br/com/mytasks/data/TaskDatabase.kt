package br.com.mytasks.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.mytasks.model.Task
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized

@Database(entities =  [Task::class], version = 1, exportSchema = false)
abstract class TaskDatabase: RoomDatabase() {

    //Retorna o objeto de acesso aos dados que estarão criados
    abstract fun taskDao(): TaskDao

    //Fará com q a classe TaskDatabase vire singleton do BD
    //Ou seja a classe terá so uma instância
    companion object{
        @Volatile
        private var INSTANCE: TaskDatabase? = null

        @OptIn(InternalCoroutinesApi::class)
        fun getDatabase(context: Context): TaskDatabase{
            val tempInstance = INSTANCE
            if (tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TaskDatabase::class.java,
                    "task_database"
                ).build()

                INSTANCE = instance
                return instance
            }
        }
    }

}
