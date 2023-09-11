package com.example.assignment.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.assignment.Constants
import com.example.assignment.R
import com.example.assignment.databinding.ItemUserBinding
import com.example.assignment.domain.model.User

class UserPagingAdapter(private val clickListener: (String, User) -> Unit) :
    PagingDataAdapter<User, UserPagingAdapter.UserViewHolder>(COMPARATOR) {

    inner class UserViewHolder(private val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root) {
        val name = itemView.findViewById<TextView>(R.id.textView_name)
        fun bindTo(item: User?, position: Int) {
            binding.apply {

                textViewName.text = item?.name

                Glide.with(binding.root).load(item?.image)
                    .timeout(6000).placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_launcher_background).centerInside()
                    .into(imageViewUser)

                textViewPhoneNumber.text = item?.phone
                textViewAddress.text = item?.address

                if (item?.status == "none") {
                    accept.visibility = View.VISIBLE
                    decline.visibility = View.VISIBLE
                    textViewStatus.visibility = View.GONE
                } else {
                    accept.visibility = View.GONE
                    decline.visibility = View.GONE
                    textViewStatus.visibility = View.VISIBLE
                    textViewStatus.text = item?.status
                }
                accept.setOnClickListener {
                    item?.let { it1 -> clickListener(Constants.ACCEPTED, it1) }
                    notifyItemChanged(position)
                }
                decline.setOnClickListener {
                    item?.let { it1 -> clickListener(Constants.DECLINED, it1) }
                    notifyItemChanged(position)
                }
            }
        }
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bindTo(getItem(position), position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(view)
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<User>() {
            override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem == newItem
            }
        }
    }

}