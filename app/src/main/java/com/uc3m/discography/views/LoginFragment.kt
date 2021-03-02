package com.uc3m.discography.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.uc3m.discography.R
import com.uc3m.discography.databinding.FragmentLoginBinding
import com.uc3m.discography.model.User
import com.uc3m.discography.viewModel.UserViewModel
import com.uc3m.discography.views.UserlistAdapter
import java.security.MessageDigest


class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var userViewModel: UserViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        val view = binding.root



        binding.regbutton.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }

        binding.botonlogin.setOnClickListener {
            log(binding.emaillog.toString(), binding.passlog.toString())
        }

        binding.usersButton.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_userlistFragment)
        }

        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)


        return view
    }

    //Funcion hasString que realiza el hasheo dandole un String y un algoritmo
    private fun hashString(input: String, algorithm: String): String {
        return MessageDigest
                .getInstance(algorithm)
                .digest(input.toByteArray())
                .fold("", { str, it -> str + "%02x".format(it) })
    }

    //Funcion para llamar a hashString con el algoritmo MD5
    fun String.md5(): String {
        return hashString(this, "MD5")
    }

    //Funcion para llamar a hashString con el algoritmo SHA-256
    fun String.sha256(): String {
        return hashString(this, "SHA-256")
    }

    //Funcion log llamada por
    private fun log(email: String, pass: String) {
        val email = binding.emaillog.toString()
        val pass= binding.passlog.toString()
        val u: User


        //val adapter = UserlistAdapter()
        u = userViewModel.getUserByEmailAndPassword(email,pass)
        //userViewModel.login(email,pass)

        //pass.toString().sha256()

        //AQUI HABRIA QUE CAMBIAR YA QUE HE INTENTADO COSAS PERO NADA

        //u = userViewModel.login(email, pass) : LiveData<List<User?>>



        if (u.email==email && u.password==pass) {
            findNavController().navigate(R.id.action_loginFragment_to_selectArtistFragment)
        }
        else  {
            Toast.makeText(requireContext(), "Wrong email or password", Toast.LENGTH_LONG).show()
        }

    }



}