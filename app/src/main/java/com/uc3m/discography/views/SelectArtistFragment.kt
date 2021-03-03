package com.uc3m.discography.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.uc3m.discography.R
import com.uc3m.discography.databinding.FragmentSelectArtistBinding



class SelectArtistFragment : Fragment() {

    private lateinit var binding: FragmentSelectArtistBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentSelectArtistBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        val view = binding.root



        binding.botonSelectArtist.setOnClickListener {
            val artist = binding.cajaSelectArtist.text.toString()
            if(artist.isNotBlank()){
                val action = SelectArtistFragmentDirections.actionSelectArtistFragmentToDiscographyFragment(artist)
                findNavController().navigate(action)
            }
            else  {
                Toast.makeText(requireContext(), "Por favor, escribe el nombre de un artista", Toast.LENGTH_LONG).show()
                //Toast.makeText(requireContext(), "Wrong email or password", Toast.LENGTH_LONG).show()
            }
        }

        return view
    }
}