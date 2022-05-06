package com.kaio.taskappbravi.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "taskinfo")
data class TaskEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "activity") val activity: String,
    @ColumnInfo(name = "type") val type: String,
    @ColumnInfo(name = "participants") val participants: Int,
    @ColumnInfo(name = "price") val price: Double,
    @ColumnInfo(name = "link") val link: String,
    @ColumnInfo(name = "key") val key: String,
    @ColumnInfo(name = "accessibility") val accessibility: Double
)