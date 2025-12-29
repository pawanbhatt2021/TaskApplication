package com.example.taskapplication.presentation.home.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.taskapplication.databinding.RawFeedbackBinding
import com.example.taskapplication.presentation.common.extensions.layoutInflater

class FeedbackAdapter(): RecyclerView.Adapter<FeedbackAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ViewHolder {
        val binding = RawFeedbackBinding.inflate(parent.context.layoutInflater,parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int,
    ) {
        with(holder.binding){

        }
    }

    override fun getItemCount(): Int = 3


    inner class  ViewHolder(val binding: RawFeedbackBinding): RecyclerView.ViewHolder(binding.root)

}