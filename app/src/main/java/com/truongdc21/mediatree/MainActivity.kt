package com.truongdc21.mediatree

import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.truongdc21.mediatree.base.BaseActivity
import com.truongdc21.mediatree.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate){

    override fun initViews() {
        setBottomNavigation()
    }

    override fun initData() {

    }

    private fun setBottomNavigation() {
        val navController = findNavController(R.id.fragmentHost)
        binding.bottomNavigationView.setupWithNavController(navController)
    }
}
