package com.example.itishub.data.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.itishub.data.room.entities.Creator
import com.example.itishub.data.room.entities.Lesson
import com.example.itishub.data.room.entities.Subject
import com.example.itishub.data.room.entities.UsefulLink

@Dao
interface ContentDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCreators(list: List<Creator>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSubjects(subjects: List<Subject>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLessons(lessons: List<Lesson>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLinks(links: List<UsefulLink>)

    @Query("SELECT * FROM Creator")
    fun getCreators(): LiveData<List<Creator>>

    @Query("SELECT * FROM Subject")
    fun getSubjects(): LiveData<List<Subject>>

}