package com.example.taskapplication.presentation.main

import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.TooltipCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.forEach
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.taskapplication.R
import com.example.taskapplication.databinding.ActivityHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import androidx.core.view.size

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var navHostFragments: NavHostFragment
    private lateinit var navController: NavController
    private var selectedFragment: Int = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
        setNavigationGraph()
        setUpBottomNavigation()
    }

    private fun initViews() {
        with(binding) {
            onMainActivityBack()
        }
    }

    override fun onDestroy() {
        super.onDestroy()

    }

    private fun setNavigationGraph()  = with(binding) {
        navHostFragments = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragments.navController
        setUpBottomNavigation()
        navController.addOnDestinationChangedListener { _, destination, _ ->
            val menuView = bnv.getChildAt(0) as ViewGroup

            val index = when (destination.id) {
                R.id.homeFragment -> 0
                R.id.servicesFragment -> 1
                R.id.bookingFragment -> 2
                R.id.profileFragment -> 3
                else -> return@addOnDestinationChangedListener
            }

            val selectedItemView = menuView.getChildAt(index)

            navIndicator.animate()
                .x(selectedItemView.x)
                .setDuration(200)
                .start()
            selectedFragment = destination.id
            Log.e("pawanLogs","selectedFragment = $selectedFragment")

        }

    }

    private fun setUpBottomNavigation() {
        with(binding) {

            bnv.post {
                val menuView = bnv.getChildAt(0) as ViewGroup
                val firstItem = menuView.getChildAt(0)

                // Set indicator width same as first item
                navIndicator.layoutParams.width = firstItem.width
                navIndicator.requestLayout()

            }


            bnv.setupWithNavController(navController)
            bnv.menu.forEach { TooltipCompat.setTooltipText(findViewById(it.itemId), null) }
            bnv.menu.findItem(R.id.homeFragment)?.setOnMenuItemClickListener {
                return@setOnMenuItemClickListener selectedFragment == R.id.homeFragment
            }
            bnv.menu.findItem(R.id.servicesFragment)?.setOnMenuItemClickListener {
                return@setOnMenuItemClickListener selectedFragment == R.id.servicesFragment
            }
            bnv.menu.findItem(R.id.bookingFragment)?.setOnMenuItemClickListener {
                return@setOnMenuItemClickListener selectedFragment == R.id.bookingFragment
            }
            bnv.menu.findItem(R.id.profileFragment)?.setOnMenuItemClickListener {
                return@setOnMenuItemClickListener selectedFragment == R.id.profileFragment
            }

        }
    }

    private fun onMainActivityBack() {
        onBackPressedDispatcher.addCallback(this, // lifecycle owner
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    onBack()
                }
            })
    }

    private fun onBack() {
        when (selectedFragment) {
            R.id.homeFragment -> {
                finish()
            }

            R.id.servicesFragment -> {
                finish()
            }

            R.id.bookingFragment -> {
                finish()
            }

            R.id.profileFragment -> {
                finish()
            }
            else -> {
            navigationUp()
            }
        }
        }
    private fun navigationUp() {
        navController.navigateUp()
    }

}