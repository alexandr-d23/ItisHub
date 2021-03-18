package com.example.itishub.presenter.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.itishub.BuildConfig
import com.example.itishub.data.room.entities.Creator
import com.example.itishub.databinding.ItemCreatorBinding
import com.squareup.picasso.Picasso

class CreatorAdapter(
    private val iconLinkClick : (url: String) -> Unit,
) : ListAdapter<Creator, CreatorAdapter.CreatorViewHolder>(object: DiffUtil.ItemCallback<Creator>(){

    override fun areItemsTheSame(oldItem: Creator, newItem: Creator): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Creator, newItem: Creator): Boolean = oldItem == newItem

}) {
    inner class CreatorViewHolder(
        private val binding: ItemCreatorBinding,
        private val iconClick: (url: String) -> Unit,
        ): RecyclerView.ViewHolder(binding.root){

        fun bind(creator: Creator){
            with(binding){
                ivGithub.setOnClickListener {
                    iconClick.invoke(creator.github)
                }
                ivTelegram.setOnClickListener {
                    iconClick.invoke(creator.telegram)
                }
                ivVk.setOnClickListener {
                    iconClick.invoke(creator.vk)
                }
                tvAbout.text = creator.about
                tvName.text = "${creator.name} ${creator.surname}"
            }
            Picasso.get()
                .load("${BuildConfig.API_URI_HOME}/${creator.avatar}")
                .fit()
                .into(binding.ivAvatar)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CreatorViewHolder =
        CreatorViewHolder(
            ItemCreatorBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            iconLinkClick
        )

    override fun onBindViewHolder(holder: CreatorViewHolder, position: Int) = holder.bind(getItem(position))

}