package com.picpay.desafio.android.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.picpay.desafio.android.databinding.FragmentContactsListBinding
import com.picpay.desafio.android.view.adapter.UserItemsDecorator
import com.picpay.desafio.android.view.adapter.UserListAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class ContactsListFragment : Fragment() {

    private var _binding: FragmentContactsListBinding? = null
    private val binding get() = _binding!!

    private val _viewModel by viewModel<ContactsListViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentContactsListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = setupAdapter()
        createObservers(adapter)
    }

    private fun setupAdapter(): UserListAdapter {
        val selfAdapter = UserListAdapter()

        with(binding.recyclerView) {
            setHasFixedSize(true)
            addItemDecoration(UserItemsDecorator())
            adapter = selfAdapter
        }

        return selfAdapter
    }

    private fun createObservers(adapter: UserListAdapter) = with(_viewModel) {
        getData().observe(viewLifecycleOwner) { usersData ->
            adapter.submitList(usersData) {
                binding.userListProgressBar.isVisible = false
            }
        }
    }

}