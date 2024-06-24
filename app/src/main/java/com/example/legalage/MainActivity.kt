package com.example.legalage

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {




    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        val edtInputAge:EditText = findViewById(R.id.edtInputAge)
        val btnEnter:Button = findViewById(R.id.btnEnter)
        val tvMessage:TextView = findViewById(R.id.tvMessage)
        val btnSubtract:Button = findViewById(R.id.btnSubtract)
        val btnAdd:Button = findViewById(R.id.btnAdd)


        btnEnter.setOnClickListener {
            var age = edtInputAge.text.toString().toIntOrNull()
            updateMessageAge(age,tvMessage)
        }


        btnSubtract.setOnClickListener {
            var age = edtInputAge.text.toString().toIntOrNull()

           if(age == null) tvMessage.text = "Ingresa tu edad"
            else{
                age--
               updateMessageAge(age, tvMessage)
               edtInputAge.setText(age.toString())
           }
        }

        btnAdd.setOnClickListener {
            var age = edtInputAge.text.toString().toIntOrNull()
            if(age == null) tvMessage.text = "Ingresa tu edad"
            else {
                age++
                updateMessageAge(age, tvMessage)
                edtInputAge.setText(age.toString())
            }
        }

    }

    private fun updateMessageAge(age:Int?, tvMessage:TextView){

        var message = ""

        if(age==null){
            message = "Ingresa tu edad"
        }else if (age < 0 ||age > 150){
            message = "Ingresa una edad validad"
        }else {

             message = when {

                age in 19..30 -> "Eres mayor de edad Joven"
                age < 18 || age > 80 -> {
                    var msg: String = verifyPertissionTheCondution()
                    "$msg"
                }

                age > 30 -> "Eres mayor de edad madur@ "
                else -> "Tienes justo 18 años"
            }
        }

        tvMessage.text = message

    }

    private fun verifyPertissionTheCondution() :String{
        val msg:String = "Lo siento no pudemos darte la licencia de conducir no cumples con el criterio de edad segun la ley ... "
        return msg
    }


}