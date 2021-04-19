package com.uc3m.discography.views

import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.uc3m.discography.R
import com.uc3m.discography.databinding.FragmentRegisterBinding
import com.uc3m.discography.viewModel.UserViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding
    private lateinit var userViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        val view = binding.root


        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        binding.buttonconfirmarreg.setOnClickListener{
            lifecycleScope.launch{
                insertDataToDatabase()
            }
        }



        binding.buttonbacklogin.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }


        return view
    }

    private suspend fun insertDataToDatabase() {
        val firstName = binding.namereg.text.toString()
        val lastName = binding.surnamereg.text.toString()
        val email = binding.emailreg.text.toString()
        val pass= binding.passreg.text.toString()
        val cpass = binding.cpassreg.text.toString()


        if(inputCheck(firstName, lastName, email, pass, cpass)){
            if (email.isValidEmail()) {
                if (passCheck(pass,cpass)){
                    val us = withContext(Dispatchers.IO){
                        userViewModel.findUser(email)
                    }
                    if (us!=null) {
                        Toast.makeText(requireContext(), "Email already registered", Toast.LENGTH_LONG).show()
                    }
                    else {
                        userViewModel.addUser(email, firstName, lastName, pass)
                        Toast.makeText(requireContext(), "Successfully registered", Toast.LENGTH_LONG).show()
                        findNavController().navigate(R.id.action_registerFragment_to_selectArtistFragment)
                    }
                }
                else {
                    Toast.makeText(requireContext(), "Passwords are not the same", Toast.LENGTH_LONG).show()
                }
            }
            else{
                Toast.makeText(requireContext(), "Invalid Email", Toast.LENGTH_LONG).show()
            }

        }else{
            Toast.makeText(requireContext(), "Fill all the fields to register", Toast.LENGTH_LONG).show()
        }
    }

    private fun CharSequence?.isValidEmail() = !isNullOrEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()

    private fun passCheck(pass1: String, pass2: String): Boolean {
        if (pass1 == pass2){
            return true
        }
        return false
    }

    private fun inputCheck(firstName: String, lastName: String, email: String, pass: String, cpass : String ): Boolean {


        return !( TextUtils.isEmpty(firstName) || TextUtils.isEmpty(lastName) || TextUtils.isEmpty(email) || TextUtils.isEmpty(pass) || TextUtils.isEmpty(cpass))
    }

}