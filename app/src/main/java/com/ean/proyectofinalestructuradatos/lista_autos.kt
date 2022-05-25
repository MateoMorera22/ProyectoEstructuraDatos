package com.ean.proyectofinalestructuradatos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
//import com.google.firebase.database.DatabaseReference
//import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

class lista_autos : AppCompatActivity() {

    val storage= Firebase.storage
    //private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_autos)


        //val conecDatabase = Firebase.database.reference


        val autos_a = ArrayList<String>()
        val listRef = storage.reference.child("autos")
        listRef.listAll()
            .addOnSuccessListener {
                for (i in it.items) {
                    autos_a.add(i.name + "")
                }
                Log.d("Firebase", "files $autos_a")
            }
            .addOnFailureListener {
                Log.d("Firebase", "error $it")
            }

        val boton_refrescar=findViewById<Button>(R.id.bn_actualizar_a)
        boton_refrescar.setOnClickListener {
            val lista_autos=findViewById<ListView>(R.id.list_view_autos_a)
            val adapautos= ArrayAdapter(this,android.R.layout.simple_list_item_1,autos_a)
            lista_autos.adapter=adapautos
            lista_autos.setOnItemClickListener(){parent,view,position,id->
                val nombre_auto=parent.getItemAtPosition(position).toString()
                Toast.makeText(this,parent.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show()
                val intent= Intent(this,leer_autos::class.java)
                intent.putExtra("TITULO AUTO",nombre_auto)//enviando nombre del autos a leer auto
                //enviar informacion extra  a leer_autos, nombre auto
                startActivity(intent)

            }
        }
    }
}