package com.example.thetatechnolabassignment.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.thetatechnolabassignment.R
import com.example.thetatechnotest.home.model.Data
import kotlinx.android.synthetic.main.item_user_details.view.*

class UserAdapter : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private val differCallBack = object : DiffUtil.ItemCallback<Data>() {
        override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem.id == newItem.id


        }

        override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem == newItem
        }

    }
    val differ = AsyncListDiffer(this, differCallBack)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAdapter.UserViewHolder {
        return UserViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_user_details, parent, false)
        )
    }

    override fun onBindViewHolder(holder: UserAdapter.UserViewHolder, position: Int) {
        val data = differ.currentList[position]
        holder.itemView.apply {
            Glide.with(this)
                .load(data.avatar)
                .into(userImage)
            textviewName.text = data.first_name
            textViewEmailId.text = data.email
            textViewLastName.text = data.last_name
//            setOnClickListener {
//                listener.onItemClicked(data)
//            }


        }


    }

    override fun getItemCount(): Int {
        return differ.currentList.size


    }
}