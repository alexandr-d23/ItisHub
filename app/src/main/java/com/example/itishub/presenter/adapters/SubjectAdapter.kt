package com.example.itishub.presenter.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.itishub.BuildConfig
import com.example.itishub.R
import com.example.itishub.data.room.entities.Subject
import com.example.itishub.databinding.ItemSubjectBinding
import com.squareup.picasso.Picasso

class SubjectAdapter(
    private val context: Context,
    private val itemClick: (subjectId: Int, extras: FragmentNavigator.Extras, imageUri: String) -> Unit
) : ListAdapter<Subject, SubjectAdapter.SubjectViewHolder>(object : DiffUtil.ItemCallback<Subject>() {
    override fun areItemsTheSame(oldItem: Subject, newItem: Subject): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Subject, newItem: Subject): Boolean =
        oldItem == newItem
}) {

    class SubjectViewHolder(
        private val binding: ItemSubjectBinding,
        private val context: Context,
        private val itemClick: (subjectId: Int, extras: FragmentNavigator.Extras, imageUri: String) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(course: Subject) {
            with(binding) {
                tvName.text = course.title
                tvLessonsCount.text =
                    "${context.resources.getString(R.string.item_lessons)}: ${course.lessonsCount}"
                val transitionName = "${course.id}"
                ivImage.transitionName = transitionName
                root.setOnClickListener {
                    val extras = FragmentNavigatorExtras(binding.ivImage to transitionName)
                    itemClick.invoke(course.id, extras, course.image ?: "")
                }
            }
            Picasso.get()
                .load("${BuildConfig.API_URI_HOME}/${course.image}")
                .fit()
                .into(binding.ivImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectViewHolder =
        SubjectViewHolder(
            ItemSubjectBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            ),
            context,
            itemClick
        )

    override fun onBindViewHolder(holder: SubjectViewHolder, position: Int) =
        holder.bind(getItem(position))
}

