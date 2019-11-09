package com.example.viewsandactivitiesdemokotlin

import android.app.Activity
import kotlinx.android.synthetic.main.activity_main.*
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import java.io.InputStream

class MainActivity : AppCompatActivity(),  View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Can call the CardView directly instead of using findViewById(R.id.card)
        card.setOnClickListener(this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Came back from intent to UpdateScreen successfully
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                name.text = data?.getStringExtra("NAME")

                // Serialize the data and convert into a Bitmap. Set the imageView to this
                val imageUri : Uri? = data?.getParcelableExtra("IMAGE")
                val imageStream : InputStream? = contentResolver.openInputStream(imageUri!!)
                val selectedImage : Bitmap = BitmapFactory.decodeStream(imageStream)
                pic.setImageBitmap(selectedImage)
            }
        }
    }

    override fun onClick(v : View?) {
        // Basically works like a switch function in Java.
        when (v?.id) {
            // If the view's id is that of the card, start a new activity
            R.id.card -> startActivityForResult(Intent(applicationContext, UpdateScreen::class.java), 1)
        }
    }
}
