package com.example.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URI;

public class UpdateScreen extends AppCompatActivity implements View.OnClickListener {
    EditText name;
    ImageView image;
    Button button;
    Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_screen);

        name = findViewById(R.id.editText);
        image = findViewById(R.id.imageView);
        button = findViewById(R.id.button);

        image.setOnClickListener(this);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.imageView:
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1);
                break;
            case R.id.button:
                if (name.getText().equals("")) {
                    Toast.makeText(this, "¯Please enter a name", Toast.LENGTH_SHORT).show();
                } else {
                    Intent goBack = new Intent(this, MainActivity.class);
                    goBack.putExtra("UPDATED_IMAGE", uri);
                    goBack.putExtra("UPDATED_NAME", name.getText().toString());
                    setResult(RESULT_OK, goBack);
                    finish();
                }
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            try {
                final Uri imageUri = data.getData();
                final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                uri = imageUri;
                image.setImageBitmap(selectedImage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(this, "¯\\_(ツ)_/¯ ", Toast.LENGTH_SHORT).show();
            }
            
        } else {
            Toast.makeText(this, "¯\\_(ツ)_/¯ ", Toast.LENGTH_SHORT).show();
        }
    }
}
