package com.muhammaddayyanahmad.i210772

//import SignupActivity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
//import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
//import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var signup = findViewById<TextView>(R.id.signup)
        var email1 = findViewById<EditText>(R.id.email1)
        var pass1= findViewById<EditText>(R.id.editPW)
        var login = findViewById<Button>(R.id.login)

        signup.setOnClickListener{
            val intent = Intent(this,SignupActivity::class.java)
            startActivity(intent)
            finish()
        }


        login.setOnClickListener {
            var email = email1.text.toString()
            var pass = pass1.text.toString()

            login(email,pass)

        }




    }



    private fun login(email: String, password: String) {
        val url = "http://10.0.2.2/login.php"

        val requestQueue = Volley.newRequestQueue(this)

        val stringRequest = object : StringRequest(Request.Method.POST, url,
            Response.Listener<String> { response ->
                // Handle response
                if (response.trim() == "success") {
                    Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show()
                    // Redirect to next activity or perform desired action
                    val intent = Intent(this,Home::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this, "Invalid credentials", Toast.LENGTH_SHORT).show()
                }
            },
            Response.ErrorListener { error ->
                // Handle error
                Toast.makeText(this, "Error occurred: ${error.message}", Toast.LENGTH_SHORT).show()
            }) {
            override fun getParams(): MutableMap<String, String> {
                val params = HashMap<String, String>()
                params["Email"] = email
                params["Password"] = password
                return params
            }
        }

        requestQueue.add(stringRequest)
    }
}