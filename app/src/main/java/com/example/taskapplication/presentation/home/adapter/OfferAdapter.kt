package com.example.taskapplication.presentation.home.adapter


import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.taskapplication.databinding.RawOfferBannerBinding
import com.example.taskapplication.presentation.common.extensions.layoutInflater

class OfferAdapter(): ListAdapter<String, OfferAdapter.ViewHolder>(DiffCallBack()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ViewHolder {
        val binding = RawOfferBannerBinding.inflate(parent.context.layoutInflater,parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int,
    ) {
        val item  = getItem(position)
        with(holder.binding){
        }
    }


    inner class  ViewHolder(val binding: RawOfferBannerBinding): RecyclerView.ViewHolder(binding.root)

    class DiffCallBack: DiffUtil.ItemCallback<String>(){
        override fun areItemsTheSame(
            oldItem: String,
            newItem: String,
        ): Boolean {
            return true
        }

        override fun areContentsTheSame(
            oldItem: String,
            newItem: String,
        ): Boolean {
            return true
        }

    }
}