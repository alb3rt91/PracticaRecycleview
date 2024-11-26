package com.example.practicarecycleview;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private PokemonAdapter adapter;
    private List<Pokemon> pokemonList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Cargar datos desde JSON
        try {
            InputStreamReader reader = new InputStreamReader(getResources().openRawResource(R.raw.pokemons));
            JsonObject jsonObject = new Gson().fromJson(reader, JsonObject.class);
            String resultsJson = jsonObject.getAsJsonArray("results").toString();

            // Convertir a una lista de objetos Pokemon
            Type listType = new TypeToken<List<Pokemon>>() {}.getType();
            pokemonList = new Gson().fromJson(resultsJson, listType);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        adapter = new PokemonAdapter(pokemonList, pokemon -> {
            // Navegar a la actividad de detalles
            Intent intent = new Intent(MainActivity.this, DetailActivity.class);
            intent.putExtra("name", pokemon.getName());
            intent.putExtra("url", pokemon.getUrl());
            startActivity(intent);
        });

        recyclerView.setAdapter(adapter);
    }
}
