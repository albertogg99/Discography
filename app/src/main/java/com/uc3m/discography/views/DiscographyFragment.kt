package com.uc3m.discography.views

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.uc3m.discography.R
import com.uc3m.discography.databinding.FragmentDiscographyBinding
import com.uc3m.discography.model.DiscographyRepository
import com.uc3m.discography.viewModel.DiscographyViewModel
import com.uc3m.discography.viewModel.DiscographyViewModelFactory


class DiscographyFragment : Fragment() {

    private lateinit var binding: FragmentDiscographyBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = FragmentDiscographyBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        val view = binding.root

        binding.buttonbackselectartist.setOnClickListener{
            findNavController().navigate(R.id.action_discographyFragment_to_selectArtistFragment)
        }

        //Para inicializar el discographyViewModel necesitamos el factory (que a su vez necesita
        // el repositorio)
        val discographyRepository = DiscographyRepository()
        val discographyViewModelFactory = DiscographyViewModelFactory(discographyRepository)
        val discographyViewModel = ViewModelProvider(this, discographyViewModelFactory).get(DiscographyViewModel::class.java)

        // Artista que se va a buscar
        val artist = arguments?.getString("artist")
        if (artist != null) {
            Log.d("artist", artist)
            discographyViewModel.getDiscography(artist)
        }

        // Añadiendo el adapter
        val adapter = DiscographyListAdapter()
        val recyclerView = binding.albumList
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Respuesta
        discographyViewModel.discography.observe(viewLifecycleOwner, { response ->
            Log.d("Response", response.toString())
            if (response.isSuccessful){
                val albums = response.body()?.album
                if (albums != null) {
                    if (artist != null) {
                        adapter.setData(albums, artist)
                    }
                }
                else{
                    Toast.makeText(requireContext(), "No hemos encontrado el artista. Prueba con otro :)", Toast.LENGTH_LONG).show()
                    findNavController().navigate(R.id.action_discographyFragment_to_selectArtistFragment)
                }
            }
            else{
                Toast.makeText(requireContext(), "Ha habido un error con la API. Inténtalo más tarde :S", Toast.LENGTH_LONG).show()
                findNavController().navigate(R.id.action_discographyFragment_to_selectArtistFragment)
            }
        }
        )



        return view
    }
}