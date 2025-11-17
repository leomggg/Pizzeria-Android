package com.example.pizzeria;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.pizzeria.DAO.DAOPizzas;
import com.example.pizzeria.POJO.Pizza;

import java.util.ArrayList;
import java.util.List;

public class Favoritas extends AppCompatActivity {

    private ListView listaFavoritas;
    private DAOPizzas dao;
    private static final String PREFS_NAME = "PIZZA_FAVORITAS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_favoritas);

        dao = new DAOPizzas();
        listaFavoritas = findViewById(R.id.listaFavoritas);

        final ImageButton btnHome = findViewById(R.id.btnHome);
        final ImageButton btnCarrito = findViewById(R.id.btnCarrito);
        final ImageButton btnPerfil = findViewById(R.id.btnPerfil);

        btnCarrito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Favoritas.this, Carrito.class);
                startActivity(intent);
            }
        });

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Favoritas.this, WebPrincipal.class);
                startActivity(intent);
            }
        });

        btnPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Favoritas.this, Perfil.class);
                startActivity(intent);
            }
        });

        cargarPizzasFavoritas();
    }

    @Override
    protected void onResume() {
     super.onResume();
     cargarPizzasFavoritas();
    }

    private void cargarPizzasFavoritas() {
        List<Pizza> todasPizzas = dao.obtenerPizzas();

        List<Pizza> pizzasFavoritas = new ArrayList<>();

        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

        for (Pizza p: todasPizzas) {
            String nombrePizza = p.getNombre();
            boolean esFavorita = prefs.getBoolean(nombrePizza, false);
            if (esFavorita) pizzasFavoritas.add(p);
        }

        ArrayAdapter<Pizza> adaptadorFavoritas = new ArrayAdapter<Pizza>(this, android.R.layout.simple_list_item_1, pizzasFavoritas) {
            @Override
            public View getView(int position, View convertView, android.view.ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView texto = view.findViewById(android.R.id.text1);
                texto.setTextColor(ContextCompat.getColor(getContext(), android.R.color.white));
                return view;
            }
        };

        listaFavoritas.setAdapter(adaptadorFavoritas);
    }
}