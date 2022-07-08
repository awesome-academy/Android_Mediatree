package com.truongdc21.mediatree.intro

import com.truongdc21.mediatree.MainActivity
import com.truongdc21.mediatree.base.BaseActivity
import com.truongdc21.mediatree.databinding.ActivityFisrtInstallBinding
import com.truongdc21.mediatree.utils.switchActivity

class FisrtInstallActivity : BaseActivity<ActivityFisrtInstallBinding>(ActivityFisrtInstallBinding::inflate){
    override fun initViews() {
        binding.apply {
            btnSkips.setOnClickListener {
                this@FisrtInstallActivity.switchActivity(MainActivity())
                finish()
            }
        }
    }

    override fun initData() {}
}
