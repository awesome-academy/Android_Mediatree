package com.truongdc21.mediatree.intro

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.lifecycleScope
import com.truongdc21.mediatree.MainActivity
import com.truongdc21.mediatree.R
import com.truongdc21.mediatree.base.BaseActivity
import com.truongdc21.mediatree.databinding.ActivitySplashBinding
import com.truongdc21.mediatree.utils.*
import kotlinx.coroutines.delay

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = ConstantPreferences.dataStore)
class SplashActivity : BaseActivity<ActivitySplashBinding>(ActivitySplashBinding::inflate) {

    override fun initViews() {
        checkFirstInstall()
    }


    override fun initData() {
    }
    private fun checkFirstInstall() = lifecycleScope.launchWhenCreated {
        val isCheck : Boolean? = dataStore.readPreferencesFirstInstall()
        if (isCheck != null){
            showAnimation()
            this@SplashActivity.switchActivity(MainActivity())
            finish()

        }else {
            showAnimation()
            dataStore.savePreferencesFirstInstall(true)
            this@SplashActivity.switchActivity(FirstInstallActivity())
            finish()
        }
    }

    private suspend fun showAnimation(){
        binding.apply {
            delay(Constant.TIME_DELAY_SPLASH)
            loadingONE.setBackgroundResource(R.drawable.bgr_radius)
            delay(Constant.TIME_DELAY_SPLASH)
            loadingTWO.setBackgroundResource(R.drawable.bgr_radius)
            delay(Constant.TIME_DELAY_SPLASH)
            loadingTHREE.setBackgroundResource(R.drawable.bgr_radius)
        }
    }
}
