package com.example.itishub.presenter.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.itishub.data.room.entities.UsefulLink
import com.example.itishub.databinding.ItemLinkBinding

class UsefulLinkAdapter(

) : ListAdapter<UsefulLink, UsefulLinkAdapter.LinkViewHolder>(object : DiffUtil.ItemCallback<UsefulLink>() {
    override fun areItemsTheSame(oldItem: UsefulLink, newItem: UsefulLink): Boolean =
        oldItem.title == newItem.title && oldItem.url == newItem.url

    override fun areContentsTheSame(oldItem: UsefulLink, newItem: UsefulLink): Boolean =
        oldItem == newItem
}
) {

    class LinkViewHolder(
        private val binding: ItemLinkBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(link: UsefulLink) {
            with(binding) {
                tvUrl.text = link.url
                Log.d("MYTAG", "LinkViewHolder_bind: Забиндил")
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LinkViewHolder =
        LinkViewHolder(
            ItemLinkBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: LinkViewHolder, position: Int) =
        holder.bind(getItem(position))

}