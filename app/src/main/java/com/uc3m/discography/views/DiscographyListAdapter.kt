package com.uc3m.discography.views

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.uc3m.discography.databinding.RecyclerViewAlbumsBinding
import com.uc3m.discography.model.Album

class DiscographyListAdapter: RecyclerView.Adapter<DiscographyListAdapter.MyViewHolder>() {

    private var discographyList = emptyList<Album>()
    private var artist = String()


    class MyViewHolder(private val mContext : Context, val binding: RecyclerViewAlbumsBinding): RecyclerView.ViewHolder(binding.root){
        init {
            binding.albumName.setOnClickListener{
                val vector : Array<String> = binding.albumName.text.toString().split(" ").toTypedArray()
                var url = "https://youtube.com/results?search_query=${binding.artist.text}+"
                for (i in vector.indices){
                    url += if (i!=vector.size-1){
                        "${vector[i]}+"
                    } else {
                        vector[i]
                    }
                }
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                mContext.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = RecyclerViewAlbumsBinding.inflate(LayoutInflater.from(parent.context) , parent,
                false)
        return MyViewHolder(parent.context, binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentAlbum = discographyList[position]
        with(holder){
            binding.albumName.text = currentAlbum.strAlbum
            binding.albumYear.text = currentAlbum.intYearReleased
            binding.artist.text = artist
        }

    }

    override fun getItemCount(): Int {
        return discographyList.size
    }

    fun setData(discographyList: List<Album>, artist: String){
        this.discographyList = discographyList
        this.artist = artist

        notifyDataSetChanged()
    }



}