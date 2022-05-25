package com.ean.proyectofinalestructuradatos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize Firebase Auth
        auth = Firebase.auth

        //Este boton me lleva a la actividad de Registrar un usuario
        val boton_registrar_usuario=findViewById<Button>(R.id.bn_registra_am)
        boton_registrar_usuario.setOnClickListener {
            val intent= Intent(this,registrar::class.java)
            startActivity(intent)
        }

        //Este boton me lleva a la actividad de login
        val boton_login=findViewById<Button>(R.id.bn_login_am)
        boton_login.setOnClickListener {
            val intent= Intent(this,log_in::class.java)
            startActivity(intent)
        }

        //solo puede entrar a ver los autos si esta logeado
        val boton_ver=findViewById<Button>(R.id.bn_autos_am)
        boton_ver.setOnClickListener {
            if(auth.currentUser!=null){
                val intent= Intent(this,lista_autos::class.java)
                startActivity(intent)
            }else{
                Toast.makeText(baseContext,"Debe iniciar sesion para consultar los autos disponibles", Toast.LENGTH_SHORT).show()
            }
        }

        //Este boton me lleva a la actividad de crear solo logeado
        val boton_crear_auto = findViewById<Button>(R.id.bn_create_autos)
       boton_crear_auto.setOnClickListener {
            if(auth.currentUser!=null){
                val intent= Intent(this,crear_autos::class.java)
                startActivity(intent)
            }else{
                Toast.makeText(baseContext,"Debe iniciar sesion para crear autos...", Toast.LENGTH_SHORT).show()
            }
        }

        val boton_cerrar_sesion=findViewById<Button>(R.id.bn_cerrar_sesion_am)
        boton_cerrar_sesion.setOnClickListener {
            auth.signOut()//cierra sesion
            if(auth.currentUser==null){
                boton_cerrar_sesion.visibility= View.INVISIBLE
                boton_login.visibility= View.VISIBLE
                boton_registrar_usuario.visibility= View.VISIBLE
            }
        }

        if(auth.currentUser!=null){
            boton_cerrar_sesion.visibility= View.VISIBLE
            boton_login.visibility= View.GONE
            boton_registrar_usuario.visibility= View.GONE
        }

    }
}