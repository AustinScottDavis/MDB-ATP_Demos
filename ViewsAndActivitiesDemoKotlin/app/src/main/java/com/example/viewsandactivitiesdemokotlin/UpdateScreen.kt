package com.example.viewsandactivitiesdemokotlin

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_update_screen.*
import java.io.InputStream

class UpdateScreen : AppCompatActivity(), View.OnClickListener {
    var uri : Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_screen)

        submit.setOnClickListener(this)
        selectPic.setOnClickListener(this)
    }

    override fun onClick(v : View?) {
        // Basically works like a switch function in Java.
        when (v?.id) {
            // If the view's id is that of the submit button, start a new activity
            R.id.submit -> {
                // If the image hasn't been selected, or the name hasn't been inserted, prompt the user
                if (uri == null || editText.text.toString().isEmpty()) {
                    Toast.makeText(this, "Please select an image and enter your name", Toast.LENGTH_SHORT).show()
                } else {
                    // Create a new intent, and put in the extra data of the image Uri and the name
                    val intent: Intent = Intent(applicationContext, MainActivity::class.java)
                    intent.putExtra("NAME", editText.text.toString())
                    intent.putExtra("IMAGE", uri)
                    setResult(Activity.RESULT_OK, intent)
                    // Finish takes you back to whatever activity created this activity with
                    // the appropriate resultCode (1 in this case)
                    finish()
                }
            }
            R.id.selectPic -> {
                // Create a new content selector intent to let the user select an image
                val intent : Intent = Intent()
                intent.setType("image/*")
                intent.setAction(Intent.ACTION_GET_CONTENT)
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // If you successfully came back from the intent to select an image
        if (requestCode == 1) {
            // Serialize the image into an InputStream to pass to MainActivity
            val imageUri : Uri? = data?.data
            val imageStream : InputStream? = contentResolver.openInputStream(imageUri!!)
            val selectedImage : Bitmap = BitmapFactory.decodeStream(imageStream)
            selectPic.setImageBitmap(selectedImage)
            // Save the imageUri to a public variable so that we can pass it into the intent later.
            uri = imageUri
        }
    }
}
