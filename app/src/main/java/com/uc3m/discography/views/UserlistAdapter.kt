package com.uc3m.discography.views

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.uc3m.discography.databinding.RecyclerViewItemBinding
import com.uc3m.discography.model.User

class UserlistAdapter: RecyclerView.Adapter<UserlistAdapter.MyViewHolder>() {

    private var userList = emptyList<User>()

    class MyViewHolder(val binding: RecyclerViewItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserlistAdapter.MyViewHolder {
        val binding = RecyclerViewItemBinding.inflate(LayoutInflater.from(parent.context), parent,
            false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserlistAdapter.MyViewHolder, position: Int) {
        val currentItem = userList[position]
        with(holder){
            binding.nameList.text = currentItem.name
            binding.surnameList.text = currentItem.surname
            binding.emailList.text = currentItem.email
            binding.passList.text = currentItem.password
        }
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    fun setData(userList: List<User>){
        this.userList = userList
        notifyDataSetChanged()
    }
}