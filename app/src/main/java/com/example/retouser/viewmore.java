package com.example.retouser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.retouser.model.Persona;
import com.squareup.picasso.Picasso;

public class viewmore extends AppCompatActivity {

    TextView titleTextView,textdescripcion, textgenero;
    ImageView imagen;
    Persona persona;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewmore);

        titleTextView = findViewById(R.id.titleTextView);
        textdescripcion = findViewById(R.id.textdescripcion);
        textgenero = findViewById(R.id.textgenero);
        imagen = findViewById(R.id.imagenview);

        Intent inputText = getIntent();
        persona = (Persona) inputText.getSerializableExtra("persona");
        titleTextView.setText(persona.getName());
        textdescripcion.setText(persona.getDescripcion());
        textgenero.setText(persona.getGenero());
        // se carga la imagen
        Picasso.with(this).load(persona.getImagenURL()).into(imagen);
    }
}