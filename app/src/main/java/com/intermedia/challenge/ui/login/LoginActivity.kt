package com.intermedia.challenge.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
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
    }

    private fun startFirebaseAuth() {

        singUpButton.setOnClickListener{
            if (emailEditText.text.isNotEmpty() && passwordEditText.text.isNotEmpty()){
                FirebaseAuth.getInstance()
                    .signInWithEmailAndPassword(emailEditText.text.toString(),
                        passwordEditText.text.toString()).addOnCompleteListener{
                            if (it.isSuccessful){
                                showMainScreen(it.result?.user?.email ?:"", ProviderType.BASIC)
                            }else{
                                showAlert()
                            }
                    }
            }

        }


        // TODO complete using Firebase Auth UI

        // TODO provisional

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
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Se ha producido un error de autenticación")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

}