package com.example.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView text;
    ImageView profilePic;
    CardView card;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = findViewById(R.id.name);
        profilePic = findViewById(R.id.profilePic);
        card = findViewById(R.id.card);

        card.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.card:
                startActivityForResult(new Intent(MainActivity.this, UpdateScreen.class), 1);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                String asdf = data.getStringExtra("UPDATED_NAME");
                text.setText(asdf);

                try {
                    final Uri imageUri = data.getParcelableExtra("UPDATED_IMAGE");
                    final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                    final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                    profilePic.setImageBitmap(selectedImage);
                } catch (FileNotFoundException e) {

                }
            }
        }
        else {
            Toast.makeText(this, "¯\\_(ツ)_/¯ ", Toast.LENGTH_SHORT).show();
        }
    }
}
