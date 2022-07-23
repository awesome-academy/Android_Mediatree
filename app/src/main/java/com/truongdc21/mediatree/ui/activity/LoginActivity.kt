package com.truongdc21.mediatree.ui.activity

import android.app.ProgressDialog
import android.content.Intent
import androidx.activity.viewModels
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.truongdc21.mediatree.MainActivity
import com.truongdc21.mediatree.R
import com.truongdc21.mediatree.base.BaseActivity
import com.truongdc21.mediatree.databinding.ActivityLoginBinding
import com.truongdc21.mediatree.utils.Constant
import com.truongdc21.mediatree.utils.showToast
import com.truongdc21.mediatree.utils.switchActivity
import com.truongdc21.mediatree.viewmodel.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoginActivity : BaseActivity<ActivityLoginBinding>(ActivityLoginBinding::inflate){

    private val mViewModel : AuthViewModel by viewModels()
    private var mProgressDialog : ProgressDialog? = null

    override fun initViews() {
        binding.apply {
            tvRegisteNewAccounts.setOnClickListener {
                this@LoginActivity.switchActivity(RegisterActivity.newInstance())
            }

            imgBack.setOnClickListener {
                finish()
            }
            btnLogin.setOnClickListener {
                signInWithEmailAndPass()
            }
            btnSignInGoogle.setOnClickListener {
                signInWithGoogle()
            }
        }
    }

    override fun initData() {
        signInSuccess()
        showToastError()
        signInWithGoogleSuccess()
        showLoading()
    }

    override fun onStart() {
        super.onStart()
        binding.apply {
            edtEmail.setText("")
            edtPassword.setText("")
        }
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

    private fun signInWithEmailAndPass() {
        binding.apply {
            val srtEmail = edtEmail.text.toString().trim()
            val srtPass = edtPassword.text.toString().trim()
            if (srtEmail.isEmpty()) {
                showToast(resources.getString(R.string.email_invalid))
                return
            }
            if (srtPass.isEmpty()) {
                showToast(resources.getString(R.string.pass_invalid))
                return
            }
            mViewModel.signInAuth(srtEmail, srtPass)
        }
    }

    private fun signInWithGoogle(){
        val options = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.client_id))
            .requestEmail()
            .build()
        val signInClient = GoogleSignIn.getClient(this@LoginActivity, options)
        signInClient.signInIntent.also {
            startActivityForResult(it, Constant.REQUEST_CODE_SIGN_IN)
        }
    }

    private fun signInSuccess() {
        mViewModel.signInAuthLiveData.observe(this) { isCheck ->
           this@LoginActivity.switchActivity(MainActivity.newInstance())
        }
    }

    private fun showToastError() {
        mViewModel.errorExceptionFirebase.observe(this) { message ->
            this@LoginActivity.showToast(message)
        }
    }

    private fun signInWithGoogleSuccess() {
        mViewModel.signInWithGoogleLiveData.observe(this) { isCheck ->
            this@LoginActivity.switchActivity(MainActivity.newInstance())
            finishAffinity()
        }
    }

    private fun showLoading() {
        mViewModel.isLoading.observe(this) { isCheck ->
            if (isCheck){
                mProgressDialog = ProgressDialog(this@LoginActivity)
                mProgressDialog?.let {
                    it.setCancelable(false)
                    it.show()
                }
            }else{
                mProgressDialog?.dismiss()
            }
        }
    }

    companion object {
        private var instance : LoginActivity? = null

        fun newInstance() = synchronized(this){
            instance ?: LoginActivity().also { instance = it }
        }
    }
}
