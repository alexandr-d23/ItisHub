package com.example.itishub.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.itishub.data.room.entities.Creator
import com.example.itishub.data.room.entities.Lesson
import com.example.itishub.data.room.entities.Subject
import com.example.itishub.data.room.entities.UsefulLink

@Database(
    entities = [
        Creator::class,
        Subject::class,
        Lesson::class,
        UsefulLink::class
    ],
    version = 1,
    exportSchema = false
)
abstract class MyDatabase : RoomDatabase() {
    abstract val contentDao: ContentDao

    companion object{
        @Volatile
        private var instance: MyDatabase? = null

        fun getInstance(context: Context): MyDatabase {
            return instance?: Room.databaseBuilder(
                context,
                MyDatabase::class.java,
                "db"
            ).build().also {
                instance = it
            }
        }
    }

}