package com.uc3m.discography.views

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.uc3m.discography.databinding.RecyclerViewAlbumsBinding
import com.uc3m.discography.model.Album

class DiscographyListAdapter: RecyclerView.Adapter<DiscographyListAdapter.MyViewHolder>() {

    private var discographyList = emptyList<Album>()

    class MyViewHolder(val binding: RecyclerViewAlbumsBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = RecyclerViewAlbumsBinding.inflate(LayoutInflater.from(parent.context),
                parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentAlbum = discographyList[position]
        with(holder){
            binding.albumName.text = currentAlbum.strAlbum
            binding.albumYear.text = currentAlbum.intYearReleased
        }
    }

    override fun getItemCount(): Int {
        return discographyList.size
    }

    fun setData(discographyList: List<Album>){
        this.discographyList = discographyList
        notifyDataSetChanged()
    }

}