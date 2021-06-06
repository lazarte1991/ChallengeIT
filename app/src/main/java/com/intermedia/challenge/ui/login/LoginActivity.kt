package com.intermedia.challenge.ui.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.facebook.*
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.intermedia.challenge.ui.main.MainScreenActivity
import com.intermedia.challenge.R
import com.intermedia.challenge.ui.main.ProviderType
import kotlinx.android.synthetic.main.activity_login.*
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.AccessToken
import com.facebook.login.LoginManager


class LoginActivity : AppCompatActivity() {

    private val callbackManager = CallbackManager.Factory.create()
    //private lateinit var auth: FirebaseAuth
    //private lateinit var buttonFacebookLogin: LoginButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
       /* auth = Firebase.auth
        buttonFacebookLogin = findViewById(R.id.login_button);
        callbackManager = CallbackManager.Factory.create()
        Log.e("AFUERA", "FACEBOOK...................")
        buttonFacebookLogin.setPermissions("email", "public_profile")
        buttonFacebookLogin.registerCallback(
            callbackManager,
            object : FacebookCallback<LoginResult> {
                override fun onSuccess(loginResult: LoginResult) {
                    Log.d(TAG, "facebook:onSuccess:$loginResult")
                    Log.e("ONSUCCESS", "FACEBOOK...................")
                    handleFacebookAccessToken(loginResult.accessToken)
                }

                override fun onCancel() {
                    Log.d(TAG, "facebook:onCancel")
                }

                override fun onError(error: FacebookException) {
                    Log.d(TAG, "facebook:onError", error)
                    Log.e("ON ERROR", "FACEBOOK...................")
                }
            })*/
    }

    override fun onResume() {
        super.onResume()
        startFirebaseAuth()
        //session()
    }
    public override fun onStart() {
        super.onStart()
        loginLayout.visibility = View.VISIBLE
    }


   /* private fun handleFacebookAccessToken(token: AccessToken) {
        Log.d(TAG, "handleFacebookAccessToken:$token")

        val credential = FacebookAuthProvider.getCredential(token.token)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithCredential:success")
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                    Toast.makeText(
                        baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                    updateUI(null)
                }
            }
    }

    private fun updateUI(user: FirebaseUser?) {
        val showMain = Intent(this, MainScreenActivity::class.java).apply {
           // putExtra("email", email)
          //  putExtra("provider", provider.name)
        }
        startActivity(showMain)

    }

    companion object {
        private const val TAG = "FacebookLogin"
    }*/


    private fun session() {
        val prefs = getSharedPreferences(getString(R.string.prefs_files), Context.MODE_PRIVATE)
        val email = prefs.getString("email", null)
        val provider = prefs.getString("provider", null)

        if (email != null && provider != null) {
            loginLayout.visibility = View.INVISIBLE
            showMainScreen(email, ProviderType.valueOf(provider))
        }
    }

    private fun startFirebaseAuth() {

        singUpButton.setOnClickListener {
            if (emailEditText.text.isNotEmpty() && passwordEditText.text.isNotEmpty()) {
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(
                    emailEditText.text.toString(),
                    passwordEditText.text.toString()
                ).addOnCompleteListener {
                    if (it.isSuccessful) {
                        showMainScreen(it.result?.user?.email ?: "", ProviderType.BASIC)
                    } else {
                        showAlert()
                    }
                }
            }
        }

        singInButton.setOnClickListener {
            if (emailEditText.text.isNotEmpty() && passwordEditText.text.isNotEmpty()) {
                FirebaseAuth.getInstance().signInWithEmailAndPassword(
                    emailEditText.text.toString(),
                    passwordEditText.text.toString()
                ).addOnCompleteListener {
                    if (it.isSuccessful) {
                        showMainScreen(it.result?.user?.email ?: "", ProviderType.BASIC)
                    } else {
                        showAlert()
                    }
                }
            }
        }
        login_button.setOnClickListener{
            LoginManager.getInstance().logInWithReadPermissions(this, listOf("email"))
            LoginManager.getInstance().registerCallback(callbackManager,
                object  : FacebookCallback<LoginResult>{

                    override fun onSuccess(result: LoginResult?) {
                        result?.let {
                            val token = it.accessToken
                            val credential = FacebookAuthProvider.getCredential(token.token)
                            FirebaseAuth.getInstance().signInWithCredential(credential).addOnCompleteListener{

                                if (it.isSuccessful) {
                                    showMainScreen(it.result?.user?.email?: "", ProviderType.FACEBOOK)
                                }else{
                                    showAlert()
                                }
                            }
                        }
                    }

                    override fun onCancel() {

                    }

                    override fun onError(error: FacebookException?) {
                        showAlert()
                    }
                })


        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun showMainScreen(email: String, provider: ProviderType) {

        val showMain = Intent(this, MainScreenActivity::class.java).apply {
            putExtra("email", email)
            putExtra("provider", provider.name)
        }

        startActivity(showMain)
        //  addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        //  addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)})
    }

        private fun showAlert() {
            val msj =
                "La combinación de correo electrónico y contraseña no es correcta. Por favor, intenta de nuevo."
            Toast.makeText(this, msj, Toast.LENGTH_LONG).show()
        }
}