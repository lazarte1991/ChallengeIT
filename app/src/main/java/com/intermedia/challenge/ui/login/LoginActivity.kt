package com.intermedia.challenge.ui.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.facebook.CallbackManager
import com.google.firebase.auth.FirebaseAuth
import com.intermedia.challenge.ui.main.MainScreenActivity
import com.intermedia.challenge.R
import com.intermedia.challenge.ui.main.ProviderType
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    override fun onResume() {
        super.onResume()
        startFirebaseAuth()
        session()
    }

    override fun onStart(){
        super.onStart()
        loginLayout.visibility = View.VISIBLE
    }

    private fun session(){
        val prefs = getSharedPreferences(getString(R.string.prefs_files), Context.MODE_PRIVATE)
        val email = prefs.getString("email", null)
        val provider = prefs.getString("provider", null)

        if (email != null && provider !=null){
            loginLayout.visibility = View.INVISIBLE
            showMainScreen(email, ProviderType.valueOf(provider))
        }
    }

    private fun startFirebaseAuth() {

        singUpButton.setOnClickListener{
            if (emailEditText.text.isNotEmpty() && passwordEditText.text.isNotEmpty()){
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(emailEditText.text.toString(),
                    passwordEditText.text.toString()).addOnCompleteListener{
                    if (it.isSuccessful){
                        showMainScreen(it.result?.user?.email ?:"", ProviderType.BASIC)
                    }else{
                        showAlert()
                    }
                }
            }
        }

        singInButton.setOnClickListener{
            if (emailEditText.text.isNotEmpty() && passwordEditText.text.isNotEmpty()){
                FirebaseAuth.getInstance().signInWithEmailAndPassword(emailEditText.text.toString(),
                    passwordEditText.text.toString()).addOnCompleteListener{
                    if (it.isSuccessful){
                        showMainScreen(it.result?.user?.email ?:"", ProviderType.BASIC)
                    }else{
                        showAlert()
                    }
                }
            }
        }
    }

    private fun showMainScreen(email: String, provider: ProviderType){

       val showMain = Intent(this, MainScreenActivity::class.java).apply {
           putExtra("email", email)
           putExtra("provider", provider.name)
       }
       startActivity(showMain)
            // addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            // addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)})


    }

    private fun showAlert() {
        val msj = "La combinación de correo electrónico y contraseña no es correcta. Por favor, intenta de nuevo."
        Toast.makeText(this, msj, Toast.LENGTH_LONG).show()

    }

}