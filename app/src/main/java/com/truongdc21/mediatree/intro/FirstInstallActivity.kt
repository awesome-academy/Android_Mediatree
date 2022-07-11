package com.truongdc21.mediatree.intro

import com.truongdc21.mediatree.MainActivity
import com.truongdc21.mediatree.base.BaseActivity
import com.truongdc21.mediatree.databinding.ActivityFirstInstallBinding
import com.truongdc21.mediatree.utils.switchActivity
class FirstInstallActivity : BaseActivity<ActivityFirstInstallBinding>(ActivityFirstInstallBinding::inflate){
    override fun initViews() {
        binding.apply {
            btnSkips.setOnClickListener {
                this@FirstInstallActivity.switchActivity(MainActivity())
                finish()
            }
        }
    }

    override fun initData() {}
}
