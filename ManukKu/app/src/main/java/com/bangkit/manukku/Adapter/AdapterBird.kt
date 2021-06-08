package com.bangkit.manukku.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.manukku.DataObject.Bird
import com.bangkit.manukku.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlin.collections.ArrayList


class AdapterBird(val listUser: ArrayList<Bird>) : RecyclerView.Adapter<AdapterBird.ListViewHolder>()  {

    private lateinit var onItemClickCallback: OnItemClickCallback
    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)  {
        var burungImg: ImageView = itemView.findViewById(R.id.iv_list)
        var burungNama: TextView = itemView.findViewById(R.id.tv_birdname)


    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterBird.ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_burung, parent, false)
        return ListViewHolder(view)
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Bird)
    }

    override fun getItemCount(): Int {
        return listUser.size
    }
    override fun onBindViewHolder(holder: AdapterBird.ListViewHolder, position: Int) {
        val bird = listUser[position]
        Glide.with(holder.itemView.context)
            .load(listUser[position].birdImg)
            .apply(RequestOptions().override(350, 550))
            .into(holder.burungImg)
        holder.burungNama.text = bird.birdName
        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(listUser[holder.adapterPosition]) }
    }
}
