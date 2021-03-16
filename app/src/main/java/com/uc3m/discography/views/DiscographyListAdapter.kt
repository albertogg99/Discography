package com.uc3m.discography.views

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.uc3m.discography.databinding.RecyclerViewAlbumsBinding
import com.uc3m.discography.model.Album

class DiscographyListAdapter: RecyclerView.Adapter<DiscographyListAdapter.MyViewHolder>() {

    private var discographyList = emptyList<Album>()


    class MyViewHolder(val mContext : Context, val binding: RecyclerViewAlbumsBinding): RecyclerView.ViewHolder(binding.root){
        init {
            binding.albumName.setOnClickListener{
                var vectoralbum : Array<String> = binding.albumName.text.toString().split(" ").toTypedArray()
                //var vectorartista = binding.albumName.toString().split(" ")
                var vector = vectoralbum //+ vectorartista
                var url = "https://youtube.com/results?search_query="
                for (i in 0..vector.size-1){
                    if (i!=vector.size-1){
                        url=url+"${vector[i]}+"
                    }
                    else {
                        url=url+"${vector[i]}"
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