package com.example.taskapplication.presentation.home.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.taskapplication.databinding.RawAcServicesBinding
import com.example.taskapplication.presentation.common.extensions.layoutInflater
import com.example.taskapplication.presentation.home.model.AcServices

class AcServicesAdapter(): ListAdapter<AcServices, AcServicesAdapter.ViewHolder>(DiffCallBack()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ViewHolder {
        val binding = RawAcServicesBinding.inflate(parent.context.layoutInflater,parent,false)
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


    class DiffCallBack: DiffUtil.ItemCallback<AcServices>(){
        override fun areItemsTheSame(
            oldItem: AcServices,
            newItem: AcServices,
        ): Boolean {
            return  true
        }

        override fun areContentsTheSame(
            oldItem: AcServices,
            newItem: AcServices,
        ): Boolean {
            return true
        }

    }
    inner class ViewHolder(val binding: RawAcServicesBinding): RecyclerView.ViewHolder(binding.root)

}