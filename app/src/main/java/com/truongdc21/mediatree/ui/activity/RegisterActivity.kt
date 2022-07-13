package com.truongdc21.mediatree.ui.activity

import android.content.Intent
import androidx.activity.viewModels
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.truongdc21.mediatree.MainActivity
import com.truongdc21.mediatree.R
import com.truongdc21.mediatree.base.BaseActivity
import com.truongdc21.mediatree.databinding.ActivityRegisterBinding
import com.truongdc21.mediatree.utils.Constant
import com.truongdc21.mediatree.utils.showToast
import com.truongdc21.mediatree.utils.switchActivity
import com.truongdc21.mediatree.viewmodel.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterActivity : BaseActivity<ActivityRegisterBinding>(ActivityRegisterBinding::inflate) {

    private val mViewModel: AuthViewModel by viewModels()

    override fun initViews() {
        binding.apply {
            imgBack.setOnClickListener {
                finish()
            }
            btnRegister.setOnClickListener {
                signUpWithEmailAndPass()
            }
            btnSignInGoogle.setOnClickListener {
                signInWithGoogle()
            }
        }
    }

    override fun initData() {
        registerSuccess()
        showToastError()
        signInWithGoogleSuccess()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == Constant.REQUEST_CODE_SIGN_IN) {
            val account = GoogleSignIn.getSignedInAccountFromIntent(data).result
            account?.let {
                mViewModel.googleAuthForFirebase(it)
            }
        }
    }

    private fun signUpWithEmailAndPass() {
        binding.apply {
            val srtEmail = edtEmail.text.toString().trim()
            val srtPass = edtPassword.text.toString().trim()
            val srtComfirmPass = edtComfirmPassword.text.toString().trim()
            if (srtEmail.isEmpty()) {
                showToast(resources.getString(R.string.email_invalid))
                return
            }
            if (srtPass.isEmpty() || srtComfirmPass.isEmpty()) {
                showToast(resources.getString(R.string.pass_invalid))
                return
            }
            if (srtPass != srtComfirmPass) {
                this@RegisterActivity.showToast(resources.getString(R.string.password_incorrect))
                return
            }
            mViewModel.signUpAuth(srtEmail, srtPass)
        }
    }

    private fun signInWithGoogle(){
        val options = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.client_id))
            .requestEmail()
            .build()
        val signInClient = GoogleSignIn.getClient(this@RegisterActivity, options)
        signInClient.signInIntent.also {
            startActivityForResult(it, Constant.REQUEST_CODE_SIGN_IN)
        }
    }

    private fun registerSuccess() {
        mViewModel.signUpAuthLiveData.observe(this) { isCheck ->
            this.showToast(resources.getString(R.string.register_success))
            finish()
        }
    }

    private fun showToastError() {
        mViewModel.errorExceptionFirebase.observe(this) { message ->
            this@RegisterActivity.showToast(message)
        }
    }

    private fun signInWithGoogleSuccess() {
        mViewModel.signInWithGoogleLiveData.observe(this) { isCheck ->
            this@RegisterActivity.switchActivity(MainActivity.newInstance())
            finishAffinity()
        }
    }
    companion object {
        private var instance : RegisterActivity? = null

        fun newInstance() = synchronized(this){
            instance ?: RegisterActivity().also { instance = it }
        }
    }
}
