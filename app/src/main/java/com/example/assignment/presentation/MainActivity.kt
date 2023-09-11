package com.example.assignment.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.assignment.core.BaseActivity
import com.example.assignment.databinding.ActivityMainBinding
import com.example.assignment.domain.model.User
import com.example.assignment.presentation.adapter.LoaderAdapter
import com.example.assignment.presentation.adapter.UserPagingAdapter
import com.example.assignment.presentation.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    override val bindingInflater: (LayoutInflater) -> ActivityMainBinding
        get() = ActivityMainBinding::inflate

    private lateinit var viewModel: UserViewModel
    private lateinit var adapter: UserPagingAdapter

    override fun onViewBindingCreated(savedInstanceState: Bundle?) {
        super.onViewBindingCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        adapter = UserPagingAdapter { status, user ->
            userResponse(status, user)
        }
        views.recyclerView.adapter = adapter.withLoadStateHeaderAndFooter(
            header = LoaderAdapter(),
            footer = LoaderAdapter()
        )
        viewModel.list.observe(this, Observer {
            adapter.submitData(lifecycle, it)
        })
    }

    private fun userResponse(status: String, user: User) {
        user.status = status
        viewModel.updateUser(user)
        Toast.makeText(this, "${user.name} $status", Toast.LENGTH_SHORT).show()
    }

}