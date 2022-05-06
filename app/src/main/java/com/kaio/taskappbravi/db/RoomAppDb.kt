package com.kaio.taskappbravi.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [TaskEntity::class], version = 1)
abstract class RoomAppDb : RoomDatabase() {
    abstract fun taskDao(): TaskDao?

    companion object {
        private var INSTANCE: RoomAppDb? = null

        val migration_1_2: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE taskinfo ADD COLUMN activity TEXT DEFAULT ''")
            }
        }

        fun getAppDatabase(context: Context): RoomAppDb? {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder<RoomAppDb>(
                    context.applicationContext, RoomAppDb::class.java, "AppDBB"
                ).addMigrations(migration_1_2)
                    .allowMainThreadQueries()
                    .build()
            }
            return INSTANCE
        }
        fun destroyInstance() {
            INSTANCE = null
        }
    }
}