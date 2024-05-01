package com.muhammaddayyanahmad.i210772

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class SignupActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        val nameEditText = findViewById<EditText>(R.id.name)
        val emailEditText = findViewById<EditText>(R.id.email2)
        val numberEditText = findViewById<EditText>(R.id.number)
        val countryEditText = findViewById<EditText>(R.id.country1)
        val cityEditText = findViewById<EditText>(R.id.city1)
        val passwordEditText = findViewById<EditText>(R.id.pass2)
        val signupButton = findViewById<Button>(R.id.signup1)

        signupButton.setOnClickListener {
            val name = nameEditText.text.toString()
            val email = emailEditText.text.toString()
            val number = numberEditText.text.toString()
            val country = countryEditText.text.toString()
            val city = cityEditText.text.toString()
            val password = passwordEditText.text.toString()

            signup(name, email, number, country, city, password)
        }
    }

    private fun signup(name: String, email: String, number: String, country: String, city: String, password: String) {
        val url = "http://10.0.2.2/POST.php"

        val requestQueue = Volley.newRequestQueue(this)

        val stringRequest = object : StringRequest(Request.Method.POST, url,
            Response.Listener<String> { response ->
                // Handle response
                // For example, show a toast
                Toast.makeText(this,"success",Toast.LENGTH_LONG).show()
                val intent = Intent(this,Home::class.java)
                startActivity(intent)
                finish()
            },
            Response.ErrorListener { error ->
                // Handle error
                // For example, show a toast
                Toast.makeText(this, "Error occurred: ${error.message}", Toast.LENGTH_SHORT).show()
            }) {
            override fun getParams(): MutableMap<String, String> {
                val params = HashMap<String, String>()
                params["Name"] = name
                params["Email"] = email
                params["Number"] = number
                params["Country"] = country
                params["City"] = city
                params["Password"] = password
                return params
            }
        }

        requestQueue.add(stringRequest)
    }
}

