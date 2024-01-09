package com.example.plantapp.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.plantapp.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var db : FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        auth = Firebase.auth
        db = FirebaseFirestore.getInstance()

        binding.signUpClicked.setOnClickListener{

            val email = binding.signupEmail.text.toString()
            val password = binding.signupPassword.text.toString()
            val username = binding.signupUsername.text.toString()
            val phone = binding.signupPhone.text.toString()

            val confirmPassword = binding.signupConfirm.text.toString()
            if (email.isNotEmpty() && password.isNotEmpty() && confirmPassword.isNotEmpty() && username.isNotEmpty() && phone.isNotEmpty()){
                if (password == confirmPassword){
                    auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener{
                        if (it.isSuccessful){
                            //kullanıcı adı kayıt ediliyor
                            saveUserNameToFirestore()

                            val intent = Intent(this, LoginActivity::class.java)
                            startActivity(intent)
                        } else {
                            Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                        }
                    }
                } else {
                    Toast.makeText(this, "Password does not matched", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Fields cannot be empty", Toast.LENGTH_SHORT).show()
            }
        }
        binding.alreadyHaveAccount.setOnClickListener {
            val loginIntent = Intent(this, LoginActivity::class.java)
            startActivity(loginIntent)
            finish()
        }
        binding.floatingActionButton.setOnClickListener{
            val loginIntent = Intent(this, LoginActivity::class.java)
            startActivity(loginIntent)
            finish()
        }
    }

    private fun saveUserNameToFirestore() {
        val user = auth.currentUser
        try {
            if (user != null && user.uid != null) {
                val userId = user.uid
                val userHashMap = hashMapOf<String, Any>()
                userHashMap["username"] = binding.signupUsername.text.toString()
                userHashMap["email"] = binding.signupEmail.text.toString()
                userHashMap["phone"] = binding.signupPhone.text.toString()

                // Kullanıcının UID'sini kullanarak belgeyi oluştur
                db.collection("Users").document(userId).set(userHashMap)
                    .addOnCompleteListener { task ->
                        try {
                            if (task.isSuccessful) {
                                Toast.makeText(this, "User saved to firestore", Toast.LENGTH_SHORT).show()
                            } else {
                                Toast.makeText(this, "Failed to save user to firestore: ${task.exception}", Toast.LENGTH_SHORT).show()
                            }
                        } catch (e: Exception) {
                            Log.e("PlantApp", "Unexpected error occurred", e)
                        }
                    }
            } else {
                Toast.makeText(this, "User or UID is null", Toast.LENGTH_SHORT).show()
            }
        } catch (e: Exception) {
            Log.e("MyApp", "Unexpected error occurred", e)
        }
    }
/*
    private fun saveUserNameToFirestore() {
        val user = auth.currentUser
        if (user != null) {
            val userId = user.uid
            val userHashMap = hashMapOf<String, Any>()
            userHashMap["username"] = binding.signupUsername.text.toString()
            userHashMap["email"] = binding.signupEmail.text.toString()
            userHashMap["phone"] = binding.signupPhone.text.toString()

            // Kullanıcının UID'sini kullanarak belgeyi oluştur
            db.collection("Users").document(userId).set(userHashMap)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "User saved to firestore", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "Failed to save user to firestore", Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }

 */
}