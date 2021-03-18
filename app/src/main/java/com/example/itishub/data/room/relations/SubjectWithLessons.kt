package com.example.itishub.data.room.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.itishub.data.room.entities.Lesson
import com.example.itishub.data.room.entities.Subject

data class SubjectWithLessons (
    @Embedded
    var subject: Subject,
    @Relation(parentColumn = "id", entityColumn = "subjectId")
    var lessons: List<Lesson>
)