package com.example.taskapplication.presentation.home.adapter

import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.example.taskapplication.databinding.RawFeedbackBinding
import com.example.taskapplication.presentation.common.extensions.layoutInflater

class FeedbackPagerAdapter() : PagerAdapter() {

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val binding = RawFeedbackBinding.inflate(container.context.layoutInflater, container, false)
        container.addView(binding.root)
        return binding.root
    }

    override fun getCount(): Int {
        return 3
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}