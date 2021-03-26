package com.example.itishub.presenter.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.itishub.data.room.entities.Lesson
import com.example.itishub.databinding.ItemLessonBinding

class LessonAdapter(
    private val itemClick: (Int) -> Unit
) : ListAdapter<Lesson, LessonAdapter.LessonHolder>(object : DiffUtil.ItemCallback<Lesson>() {
    override fun areItemsTheSame(oldItem: Lesson, newItem: Lesson): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Lesson, newItem: Lesson): Boolean = oldItem == newItem

}) {

    class LessonHolder(
        private val binding: ItemLessonBinding,
        private val itemClick: (Int) -> Unit
    ) : RecyclerView.ViewHolder(
        binding.root
    ) {
        fun bind(lesson: Lesson) {
            with(binding) {
                tvTitle.text = lesson.title
                root.setOnClickListener {
                    itemClick.invoke(lesson.id)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonHolder =
        LessonHolder(
            ItemLessonBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            itemClick
        )

    override fun onBindViewHolder(holder: LessonHolder, position: Int) =
        holder.bind(getItem(position))

}