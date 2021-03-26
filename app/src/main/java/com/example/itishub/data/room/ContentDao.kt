package com.example.itishub.data.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.itishub.data.room.entities.Creator
import com.example.itishub.data.room.entities.Lesson
import com.example.itishub.data.room.entities.Subject
import com.example.itishub.data.room.entities.UsefulLink
import com.example.itishub.data.room.relations.LessonWithLinks
import com.example.itishub.data.room.relations.SubjectWithLessons

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

    @Transaction
    @Query("SELECT * FROM Subject WHERE id = :id LIMIT 1")
    fun getSubjectWithLessons(id: Int): LiveData<SubjectWithLessons>

    @Transaction
    @Query("SELECT * FROM Lesson WHERE id = :id LIMIT 1")
    fun getLessonWithLinks(id: Int): LiveData<LessonWithLinks>

}