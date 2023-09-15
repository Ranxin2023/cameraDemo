package com.example.camerademo2

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import android.Manifest
import android.content.Intent
import android.graphics.Bitmap
import android.provider.MediaStore

class MainActivity : AppCompatActivity() {
    lateinit var button:Button
    lateinit var imageView:ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.button=findViewById(R.id.photoButton)
        this.imageView=findViewById(R.id.photoView)
        if(ContextCompat.checkSelfPermission(this@MainActivity, Manifest.permission.CAMERA)!=
                PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this@MainActivity, arrayOf(
                Manifest.permission.CAMERA
            ), 100)
        }
        this.button.setOnClickListener{
            val intent=Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(intent, 100)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==100 && resultCode == RESULT_OK && data!=null){
            val bundle:Bundle?=data.extras
            val finalPhoto: Bitmap = bundle?.get("data") as Bitmap
            if(finalPhoto!=null){
                imageView.setImageBitmap(finalPhoto)
            }

        }
    }
}