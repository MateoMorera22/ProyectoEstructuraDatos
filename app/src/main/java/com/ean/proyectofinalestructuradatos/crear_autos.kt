package com.ean.proyectofinalestructuradatos

import Auto.Auto
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class crear_autos:AppCompatActivity(){

    val database = Firebase.database
    val myRef = database.getReference("cars")

    private lateinit var auth: FirebaseAuth;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_autos)


        // Initialize Firebase Auth
        auth = Firebase.auth

        val boton_regresar=findViewById<Button>(R.id.bn_volver_auto)
        boton_regresar.setOnClickListener {
            val intent= Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

        val txt_codigo=findViewById<EditText>(R.id.text_codigo_auto)
        val txt_nombre=findViewById<EditText>(R.id.text_nombre_auto)
        val txt_numero=findViewById<EditText>(R.id.text_numero_auto)
        val txt_fecha=findViewById<EditText>(R.id.text_ano_creacion_auto)


        val boton_registrar=findViewById<Button>(R.id.bn_registrar_auto)
        boton_registrar.setOnClickListener {
            try {
                val codigo=txt_codigo.text.toString().toInt()
                val nombre=txt_nombre.text.toString().lowercase()
                val numero=txt_numero.text.toString().lowercase()
                val fecha=txt_fecha.text.toString()
                if(nombre.isEmpty()||numero.isEmpty()||fecha.isEmpty()){
                    throw Exception("Los campos no pueden estar vacios!")
                }
                else{
                    if(auth.currentUser!=null){
                        var librito=Auto(codigo,nombre,numero,fecha)
                        val usuario=myRef.child(auth.uid!!).child(librito.getcodigo().toString())
                        usuario.child("Codigo").setValue(librito.getcodigo())
                        usuario.child("Nombre").setValue(librito.getnombre())
                        usuario.child("numero").setValue(librito.getnumero())
                        usuario.child("fecha").setValue(librito.getfecha())
                        throw java.lang.Exception("Auto registrado con exito.")
                    }

                }
            }
            catch(e: Exception){
                Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
            }

        }
    }
}


