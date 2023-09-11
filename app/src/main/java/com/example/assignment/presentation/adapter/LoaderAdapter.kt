package com.example.assignment.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment.R

class LoaderAdapter : LoadStateAdapter<LoaderAdapter.LoaderViewModel>() {
    inner class LoaderViewModel(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val progressBar = itemView.findViewById<ProgressBar>(R.id.progressBar)
        fun bindTo(loadState: LoadState) {
            progressBar.isVisible = loadState is LoadState.Loading
        }
    }

    override fun onBindViewHolder(holder: LoaderViewModel, loadState: LoadState) {
        holder.bindTo(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoaderViewModel {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_loader, parent, false)
        return LoaderViewModel(view)
    }
}