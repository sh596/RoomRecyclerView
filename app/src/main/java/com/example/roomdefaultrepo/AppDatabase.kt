package com.example.roomdefaultrepo

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Memo::class], version = 2)
abstract class AppDatabase() : RoomDatabase() {
    abstract fun memoDao(): MemoDao

    companion object {
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase? {
            instance = Room.databaseBuilder(
                context,

                AppDatabase::class.java,
                "memoTable"
            ).fallbackToDestructiveMigration()
                .build()

            return instance
        }

    }
}