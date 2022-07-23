package com.truongdc21.mediatree

import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.truongdc21.mediatree.base.BaseActivity
import com.truongdc21.mediatree.databinding.ActivityMainBinding
import com.truongdc21.mediatree.ui.fragment.MediaPlayBottomSheetFragment
import com.truongdc21.mediatree.utils.extension.setAlphaAnimation
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate){

    override fun initViews() {
        setBottomNavigation()
        binding.apply {
            viewMedia.setOnClickListener {
                it.setAlphaAnimation()
                val bottomSheetDialog = MediaPlayBottomSheetFragment()
                bottomSheetDialog.show(supportFragmentManager, bottomSheetDialog.tag)
            }
        }
    }

    override fun initData() {
    }

    private fun setBottomNavigation() {
        val navController = findNavController(R.id.fragmentHost)
        binding.bottomNavigationView.setupWithNavController(navController)
    }


    companion object {
        private var instance : MainActivity? = null

        fun newInstance() = synchronized(this){
            instance ?: MainActivity().also { instance = it }
        }
    }
}
