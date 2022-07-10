package com.picpay.desafio.android

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class UserListAdapter : ListAdapter<User, UserListItemViewHolder>(USER_LIST_DIFF_UTIL) {

    companion object {
        val USER_LIST_DIFF_UTIL = object : ItemCallback<User>() {
            override fun areItemsTheSame(oldItem: User, newItem: User): Boolean =
                oldItem == newItem
            override fun areContentsTheSame(oldItem: User, newItem: User): Boolean =
                oldItem.id == newItem.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListItemViewHolder =
        UserListItemViewHolder.createViewHolder(parent)

    override fun onBindViewHolder(holder: UserListItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}