package com.uc3m.discography.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.uc3m.discography.R
import com.uc3m.discography.databinding.FragmentLoginBinding



class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        val view = binding.root


        binding.regbutton.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }

        binding.botonlogin.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_selectArtistFragment)
        }

        binding.usersButton.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_userlistFragment)
        }



        return view
    }

}