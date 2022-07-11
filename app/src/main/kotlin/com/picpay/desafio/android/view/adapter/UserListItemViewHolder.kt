package com.picpay.desafio.android.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.ViewSizeResolver
import coil.transform.CircleCropTransformation
import com.picpay.desafio.android.data.model.User
import com.picpay.desafio.android.databinding.ListItemUserBinding

class UserListItemViewHolder(
    private val binding: ListItemUserBinding
) : RecyclerView.ViewHolder(binding.root) {

    companion object{
        fun createViewHolder(parent:ViewGroup): UserListItemViewHolder =
            UserListItemViewHolder(
                ListItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
    }

    fun bind(user: User) = with(binding) {
        name.text = user.name
        username.text = user.username
        progressBar.visibility = View.VISIBLE
        picture.load(user.img) {
            size(ViewSizeResolver(picture))
            transformations(CircleCropTransformation())
            listener(
                onError = {_, _ ->
                    progressBar.isVisible = false
                },
                onSuccess = { _, _ ->
                    progressBar.isVisible = false
                }
            )
        }
    }
}