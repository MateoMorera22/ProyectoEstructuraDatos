package com.ean.proyectofinalestructuradatos


import android.app.ProgressDialog
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.ean.proyectofinalestructuradatos.databinding.ActivityMainBinding
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import java.io.File

class leer_autos : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    val storage = Firebase.storage

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)

        setContentView(R.layout.activity_leer_autos)

        //setContentView(binding.root)
        var mostrar=findViewById<Button>(R.id.getImage)


        mostrar.setOnClickListener {
            /*val progressDialog=ProgressDialog(this)
            progressDialog.setMessage("Cargando imagen...")
            progressDialog.setCancelable(false)
            progressDialog.show()*/


            //Leer el video de storageRef
            var nombre_auto=intent.getStringExtra("TITULO AUTO").toString()

            var imagen=findViewById<ImageView>(R.id.imageview)
            val storageRef = storage.reference.child("autos/$nombre_auto")
            //Log.d("Firebase", "$nombre_auto")

            val localfile= File.createTempFile("tempImage","jpg")
            storageRef.getFile(localfile).addOnSuccessListener {

                /*if (progressDialog.isShowing){
                    progressDialog.dismiss()
                }*/

                val bitmap=BitmapFactory.decodeFile(localfile.absolutePath)
                imagen.setImageBitmap(bitmap)
            }.addOnCanceledListener {
                /*if (progressDialog.isShowing){
                    progressDialog.dismiss()
                }*/
                Toast.makeText(this,"Fallo al cargar la imagen",Toast.LENGTH_SHORT).show()
            }





        }
    }

}