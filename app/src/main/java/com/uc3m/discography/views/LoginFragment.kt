package com.uc3m.discography.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import androidx.navigation.fragment.findNavController
import com.uc3m.discography.R
import com.uc3m.discography.databinding.FragmentLoginBinding
import com.uc3m.discography.viewModel.UserViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var userViewModel: UserViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        val view = binding.root



        binding.regbutton.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }

        binding.botonlogin.setOnClickListener {
            lifecycleScope.launch{
                login()
            }
        }

        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)


        return view
    }

    //Funcion para login
    private suspend fun login() {
        val email = binding.emaillog.text.toString()
        val pass= binding.passlog.text.toString()
        val validLog = withContext(Dispatchers.IO){
            userViewModel.logUser(email, pass)
        }

        if (validLog) {
            findNavController().navigate(R.id.action_loginFragment_to_selectArtistFragment)
        }
        else  {
            Toast.makeText(requireContext(), "El email o la contraseña no son correctos", Toast.LENGTH_LONG).show()
        }

    }



}