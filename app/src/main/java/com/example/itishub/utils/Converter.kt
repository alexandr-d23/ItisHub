package com.example.itishub.utils

import com.example.itishub.data.retrofit.entities.CourseResponse
import com.example.itishub.data.retrofit.entities.CreatorResponse
import com.example.itishub.data.room.entities.Creator
import com.example.itishub.data.room.entities.Lesson
import com.example.itishub.data.room.entities.Subject
import com.example.itishub.data.room.entities.UsefulLink

class Converter {
    companion object{
        fun getLessonsFromResponse(list: List<CourseResponse>): List<Lesson> = list
            .flatMap { response ->
                response.lessons
                    .map { l ->
                        Lesson(
                            id = l.id,
                            videoUrl = l.videoUrl,
                            title = l.title,
                            description = l.description,
                            pdfFile = l.pdfFile,
                            subjectId = response.id
                        )
                    }
            }

        fun getSubjectsFromResponse(list: List<CourseResponse>): List<Subject> = list
            .map { response ->
                Subject(
                    id = response.id,
                    title = response.title,
                    url = response.url,
                    lessonsCount = response.lessons.size
                )
            }

        fun getLinksFromResponse(list: List<CourseResponse>): List<UsefulLink> = list
            .flatMap { response ->
                response.lessons
            }
            .flatMap { lesson ->
                lesson.usefulLinks
                    .map { link ->
                        UsefulLink(
                            title = link.title,
                            lessonId = lesson.id,
                            url = link.url
                        )
                    }
            }

        fun getCreatorsFromResponse(list: List<CreatorResponse>) : List<Creator> = list.map { creatorResponse ->
            Creator(
                id = creatorResponse.id,
                avatar = creatorResponse.avatar,
                about = creatorResponse.about,
                name = creatorResponse.name,
                github = creatorResponse.github,
                surname = creatorResponse.surname,
                telegram = creatorResponse.telegram,
                vk = creatorResponse.vk
            )
        }
    }
}