package com.kaio.taskappbravi.db

import androidx.room.*

@Dao
interface TaskDao {
    @Query("SELECT * FROM taskinfo ORDER BY id DESC")
    fun getAllTaskInfo(): List<TaskEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTask(task: TaskEntity?)
}