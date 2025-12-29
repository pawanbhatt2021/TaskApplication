package com.example.taskapplication.presentation.home.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.taskapplication.databinding.RawServicesBinding
import com.example.taskapplication.presentation.common.extensions.layoutInflater
import com.example.taskapplication.presentation.home.model.TopServices

class TopServicesAdapter(): ListAdapter<TopServices, TopServicesAdapter.ViewHolder>(DiffCallBack()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ViewHolder {
      val binding = RawServicesBinding.inflate(parent.context.layoutInflater,parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int,
    ) {
     val item  = getItem(position)
        with(holder.binding){
            data = item
        }
    }


    inner class  ViewHolder(val binding: RawServicesBinding): RecyclerView.ViewHolder(binding.root)

    class DiffCallBack: DiffUtil.ItemCallback<TopServices>(){
        override fun areItemsTheSame(
            oldItem: TopServices,
            newItem: TopServices,
        ): Boolean {
            return true
        }

        override fun areContentsTheSame(
            oldItem: TopServices,
            newItem: TopServices,
        ): Boolean {
           return true
        }

    }
}