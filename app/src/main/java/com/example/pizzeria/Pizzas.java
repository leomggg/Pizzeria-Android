package com.example.pizzeria;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.pizzeria.DAO.DAOPizzas;
import com.example.pizzeria.POJO.Pizza;

public class Pizzas extends AppCompatActivity {

    ListView listaPizzas;
    DAOPizzas dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        setContentView(R.layout.activity_pizzas);

        dao = new DAOPizzas();
        final ImageButton btnHome = findViewById(R.id.btnHome);
        final ImageButton btnCarrito = findViewById(R.id.btnCarrito);
        final ImageButton btnPerfil = findViewById(R.id.btnPerfil);

        btnCarrito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Pizzas.this, Carrito.class);
                startActivity(intent);
            }
        });

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Pizzas.this, WebPrincipal.class);
                startActivity(intent);
            }
        });

        btnPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Pizzas.this, Perfil.class);
                startActivity(intent);
            }
        });

        listaPizzas = findViewById(R.id.listaPizzas);
        ArrayAdapter<Pizza> adaptadorPizzas = new ArrayAdapter<Pizza>(this, android.R.layout.simple_list_item_1, dao.obtenerPizzas());
        listaPizzas.setAdapter(adaptadorPizzas);
    }
}