package com.uc3m.discography.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.uc3m.discography.databinding.FragmentUserlistBinding
import com.uc3m.discography.viewModel.UserViewModel

class UserlistFragment : Fragment() {

    private lateinit var binding: FragmentUserlistBinding
    private lateinit var userViewModel: UserViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentUserlistBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        val view = binding.root

        val adapter = UserlistAdapter()
        val recyclerView = binding.userlist
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        userViewModel.readAll.observe(viewLifecycleOwner, {
            user -> adapter.setData(user)
        })

        return view
    }

}