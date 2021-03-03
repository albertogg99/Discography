package com.uc3m.discography.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.uc3m.discography.databinding.FragmentDiscographyBinding


class DiscographyFragment : Fragment() {

    private lateinit var binding: FragmentDiscographyBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentDiscographyBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        val view = binding.root

        binding.artistSelected.text = arguments?.getString("artist")

        return view
    }
}