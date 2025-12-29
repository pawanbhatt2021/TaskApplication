package com.example.taskapplication.presentation.home.adapter


import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.taskapplication.databinding.RawLookingForBinding
import com.example.taskapplication.domain.model.Category
import com.example.taskapplication.presentation.common.extensions.layoutInflater

class LookingForAdapter(): ListAdapter<Category, LookingForAdapter.ViewHolder>(DiffCallBack()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ViewHolder {
        val binding = RawLookingForBinding.inflate(parent.context.layoutInflater,parent,false)
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


    class DiffCallBack: DiffUtil.ItemCallback<Category>(){
        override fun areItemsTheSame(
            oldItem: Category,
            newItem: Category,
        ): Boolean {
          return  true
        }

        override fun areContentsTheSame(
            oldItem: Category,
            newItem: Category,
        ): Boolean {
           return true
        }

    }
    inner class ViewHolder(val binding: RawLookingForBinding): RecyclerView.ViewHolder(binding.root)

}