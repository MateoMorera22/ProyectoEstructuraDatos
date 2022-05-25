package com.ean.proyectofinalestructuradatos


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.MediaController
import android.widget.VideoView
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

class leer_autos : AppCompatActivity() {
    val storage = Firebase.storage

    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_leer_autos)

        //Leer el video de storageRef
        var nombre_auto=intent.getStringExtra("TITULO AUTO").toString()

        var video=findViewById<VideoView>(R.id.videoView)
        val Ref = storage.reference.child("autos")

        Log.d("Firebase", "Files $nombre_auto")

        Ref.child(nombre_auto).downloadUrl.addOnSuccessListener {
            Log.d("item", "$it")
            val mediaController = MediaController(this)



            mediaController.setAnchorView(video)

            video.setMediaController(mediaController)

            //video.setVideoPath(nombre_auto)
            video.requestFocus()
            video.start()
        }





    }

}