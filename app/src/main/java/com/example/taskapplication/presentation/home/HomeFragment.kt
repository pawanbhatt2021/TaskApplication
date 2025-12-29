package com.example.taskapplication.presentation.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.example.taskapplication.R
import com.example.taskapplication.data.local.DataProvider
import com.example.taskapplication.databinding.FragmentHomeBinding
import com.example.taskapplication.domain.model.ApiResult
import com.example.taskapplication.domain.model.Category
import com.example.taskapplication.presentation.common.extensions.dp
import com.example.taskapplication.presentation.home.adapter.AcServicesAdapter
import com.example.taskapplication.presentation.home.adapter.FeedbackAdapter
import com.example.taskapplication.presentation.home.adapter.FeedbackPagerAdapter
import com.example.taskapplication.presentation.home.adapter.LookingForAdapter
import com.example.taskapplication.presentation.home.adapter.OfferAdapter
import com.example.taskapplication.presentation.home.adapter.TopServicesAdapter
import com.example.taskapplication.presentation.viewmodel.CategoryViewModel
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import kotlin.text.toInt

@AndroidEntryPoint
class HomeFragment : Fragment() {
private lateinit var binding: FragmentHomeBinding
    private val viewModel: CategoryViewModel by viewModels()
private lateinit var lookingForAdapter: LookingForAdapter
private lateinit var topServicesAdapter: TopServicesAdapter
private lateinit var acServicesAdapter: AcServicesAdapter
private lateinit var offerAdapter: OfferAdapter
private lateinit var feedbackAdapter: FeedbackAdapter
private lateinit var feedbackPagerAdapter: FeedbackPagerAdapter
    private val originalList = listOf(
        "Feedback 1",
        "Feedback 2",
        "Feedback 3"
    )

    private val infiniteList = ArrayList<String>()
    private val sliderHandler = Handler(Looper.getMainLooper())

    private val sliderRunnable = Runnable {
       binding. pagerFeedBack.currentItem = binding.pagerFeedBack.currentItem + 1
    }
    private lateinit var handler: Handler
    private lateinit var runnable: Runnable


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
       binding = FragmentHomeBinding.inflate(inflater,container,false)

        initViews()
//        initListener()
        observeCategories()
        viewModel.loadCategories()
        prepareInfiniteList()
        setupViewPager()
        startAutoSlider()
        setupTouchListener()

        return binding.root
    }

    private fun observeCategories() {
        viewModel.categories.observe(viewLifecycleOwner) { result ->
            when (result) {
                is ApiResult.Loading -> {
                    showLoading()
                }

                is ApiResult.Success -> {
                    lookingForAdapter.submitList(result.data)
                    showCategoryList(result.data)
                }

                is ApiResult.Error -> {
                    showError(result.message)
                }
            }
        }
    }
    private fun showLoading() {
        Log.e("pawanLogs","loading  = ")
    }

    private fun showCategoryList(list: List<Category>) {
        Log.e("pawanLogs","list  = ${Gson().toJson(list)}")
    }

    private fun showError(message: String) {
        Log.e("pawanLogs","message   = $message")
    }

    private fun initViews(){
        with(binding){
            topServicesAdapter = TopServicesAdapter()
            topServicesAdapter.submitList(DataProvider.getTopServicesDataList(requireContext()))
            rvTopServices.adapter = topServicesAdapter

            lookingForAdapter = LookingForAdapter()
            rvLookingFor.adapter = lookingForAdapter

            acServicesAdapter = AcServicesAdapter()
            acServicesAdapter.submitList(DataProvider.getAcServicesDataList(requireContext()))
            rvAcServices.adapter = acServicesAdapter

            offerAdapter = OfferAdapter()
            offerAdapter.submitList(listOf("a","b","c"))
            rvOffers.adapter = offerAdapter

//            feedbackAdapter = FeedbackAdapter()
//            feedbackAdapter.submitList(listOf("a","b","c"))
//            rvFeedBack.adapter = feedbackAdapter


            pagerFeedBack.adapter = FeedbackAdapter()
            pagerFeedBack.offscreenPageLimit = 3

            // RecyclerView internal fix
//            val rv = pagerFeedBack.getChildAt(0) as RecyclerView
//            rv.clipToPadding = false
//            rv.clipChildren = false
//            rv.overScrollMode = RecyclerView.OVER_SCROLL_NEVER

            // Page transformer (peek + scale)
            pagerFeedBack.setPageTransformer { page, position ->
                page.translationX = position * -40
                val scale = 0.9f + (1 - kotlin.math.abs(position)) * 0.1f
                page.scaleY = scale
            }

            dotsIndicator.attachTo(pagerFeedBack)
//            pagerFeedBack.setCurrentItem(0, false)

            // AUTO SCROLL
            handler = Handler(Looper.getMainLooper())
            runnable = Runnable {
                val next = (pagerFeedBack.currentItem + 1) % 3
                pagerFeedBack.setCurrentItem(next, true)
            }

            handler.postDelayed(runnable, 3000)
        }
    }

    private fun initListener(){
        with(binding){
            pagerFeedBack.registerOnPageChangeCallback(
                object : ViewPager2.OnPageChangeCallback() {

                    override fun onPageScrolled(
                        position: Int,
                        positionOffset: Float,
                        positionOffsetPixels: Int
                    ) {
                        super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                    }

                    override fun onPageSelected(position: Int) {
                        super.onPageSelected(position)
                    }

                    override fun onPageScrollStateChanged(state: Int) {
                        if (state == ViewPager2.SCROLL_STATE_IDLE) {
                            val pos = pagerFeedBack.currentItem

                            when {
                                pos < 3-> {
                                    pagerFeedBack.setCurrentItem(
                                        pos + 3,
                                        false
                                    )
                                }

                                pos >= 3 * 2 -> {
                                    pagerFeedBack.setCurrentItem(
                                        pos - 3,
                                        false
                                    )
                                }
                            }
                        }
                    }
                }
            )


        }
    }

    private fun prepareInfiniteList() {
        // Repeat list 3 times for infinite feel
        repeat(3) { infiniteList.addAll(originalList) }
    }

    private fun setupViewPager() {
        val adapter = FeedbackAdapter()
        binding.pagerFeedBack.adapter = adapter
        binding.pagerFeedBack.offscreenPageLimit = 3

        // Start from middle to allow infinite scroll both sides
        val startPosition = originalList.size
        binding.pagerFeedBack.setCurrentItem(startPosition, false)

        // DotsIndicator attach
        binding.dotsIndicator.attachTo(binding.pagerFeedBack)

        // Infinite scroll logic
        binding.pagerFeedBack.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrollStateChanged(state: Int) {
                if (state == ViewPager2.SCROLL_STATE_IDLE) {
                    val pos = binding.pagerFeedBack.currentItem
                    when {
                        pos < originalList.size -> {
                            binding.pagerFeedBack.setCurrentItem(pos + originalList.size, false)
                        }
                        pos >= originalList.size * 2 -> {
                            binding.pagerFeedBack.setCurrentItem(pos - originalList.size, false)
                        }
                    }
                }
            }

            override fun onPageSelected(position: Int) {
                stopAutoSlider()
                startAutoSlider()
            }
        })
    }


    @SuppressLint("ClickableViewAccessibility")
    private fun setupTouchListener() {
        binding.pagerFeedBack.getChildAt(0).setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> stopAutoSlider()
                MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> startAutoSlider()
            }
            false
        }
    }

    private fun startAutoSlider() {
        sliderHandler.postDelayed(sliderRunnable, 3000)
    }

    private fun stopAutoSlider() {
        sliderHandler.removeCallbacks(sliderRunnable)
    }


    override fun onResume() {
        super.onResume()
        startAutoSlider()
    }

    override fun onPause() {
        super.onPause()
        stopAutoSlider()
    }

}