package com.example.itishub.data.room.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.itishub.data.room.entities.Lesson
import com.example.itishub.data.room.entities.UsefulLink


data class LessonWithLinks(
    @Embedded
    var lesson: Lesson,
    @Relation(parentColumn = "id", entityColumn = "lessonId")
    var links: List<UsefulLink>
)