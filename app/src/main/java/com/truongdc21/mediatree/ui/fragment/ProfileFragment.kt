package com.truongdc21.mediatree.ui.fragment

import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.truongdc21.mediatree.base.BaseFragment
import com.truongdc21.mediatree.databinding.FragmentProfileBinding
import com.truongdc21.mediatree.ui.activity.LoginActivity
import com.truongdc21.mediatree.utils.extension.setAlphaAnimation
import com.truongdc21.mediatree.utils.extension.showImageGlideWithURI
import com.truongdc21.mediatree.utils.switchActivity
import com.truongdc21.mediatree.viewmodel.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding>(FragmentProfileBinding::inflate) {

    private val mViewModel : AuthViewModel by viewModels()

    override fun initView() {
        binding.apply {
            viewGrSignOut.setOnClickListener {
                mViewModel.authFirebase.signOut()
                showAuthProfile()
                it.setAlphaAnimation()
            }
            btnSwithToLogin.setOnClickListener {
                it.setAlphaAnimation()
                this@ProfileFragment.activity?.switchActivity(LoginActivity.newInstance())
            }
        }
    }

    override fun initData() {
        showAuthProfile()
    }

    private fun showAuthProfile() {
        mViewModel.getAuthProfile()
        binding.apply {
            if (mViewModel.authFirebase.currentUser != null){
                viewNotLogin.isVisible = false
                viewLoged.isVisible = true
                mViewModel.authProfileLiveData.observe(this@ProfileFragment) { user ->
                    tvEmailUser.text = user.userEmail
                    user.userName?.let {
                        tvNameUser.text = it
                    }
                    user.userImageUri?.let { uri ->
                        imgAvtProfile.showImageGlideWithURI(
                            uri
                        )
                    }
                }
            }else {
                viewNotLogin.isVisible = true
                viewLoged.isVisible = false
            }
        }
    }
}
