package com.example.practicarecycleview;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        TextView detailTextView = findViewById(R.id.pokemonDetail);

        // Obtener datos del intent
        String name = getIntent().getStringExtra("name");
        String url = getIntent().getStringExtra("url");

        // Mostrar detalles
        detailTextView.setText("Name: " + name + "\nURL: " + url);
    }
}
