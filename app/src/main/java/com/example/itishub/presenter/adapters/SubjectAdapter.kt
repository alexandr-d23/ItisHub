package com.example.itishub.presenter.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.itishub.R
import com.example.itishub.data.room.entities.Subject
import com.example.itishub.databinding.ItemSubjectBinding

class SubjectAdapter(
    private val context: Context
) : ListAdapter<Subject, SubjectAdapter.SubjectViewHolder>(object : DiffUtil.ItemCallback<Subject>(){
    override fun areItemsTheSame(oldItem: Subject, newItem: Subject): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Subject, newItem: Subject): Boolean = oldItem == newItem
}) {

    inner class SubjectViewHolder(private val binding: ItemSubjectBinding, private val context: Context) : RecyclerView.ViewHolder(binding.root){
        fun bind(course: Subject){
            with(binding){
                tvName.text = course.title
                tvLessonsCount.text = "${context.resources.getString(R.string.item_lessons)}: ${course.lessonsCount}"
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectViewHolder =
        SubjectViewHolder(ItemSubjectBinding.inflate(LayoutInflater.from(parent.context), parent, false), context)

    override fun onBindViewHolder(holder: SubjectViewHolder, position: Int) = holder.bind(getItem(position))
}

