package com.example.pizzeria.PantallaPrincipal.WebMenuConfig;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pizzeria.PantallaPrincipal.WebMenuConfig.GeneralPersonalizadaUltima.PersonalizarPizza;
import com.example.pizzeria.PantallaPrincipal.WebMenuConfig.GeneralPersonalizadaUltima.Pizzas;
import com.example.pizzeria.PantallaPrincipal.WebMenuConfig.GeneralPersonalizadaUltima.UltimaPizza;
import com.example.pizzeria.R;

public class PantallaMenuPizzas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pantalla_menu_pizzas);

        final ImageButton btnPizzas = findViewById(R.id.btnPizzasGenerales);
        final ImageButton btnPersonalizar = findViewById(R.id.btnPizzaPersonalizada);
        final ImageButton btnUltimaPizza = findViewById(R.id.btnUltimaPizza);

        btnPizzas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PantallaMenuPizzas.this, Pizzas.class);
                startActivity(intent);
            }
        });

        btnPersonalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PantallaMenuPizzas.this, PersonalizarPizza.class);
                startActivity(intent);
            }
        });

        btnUltimaPizza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PantallaMenuPizzas.this, UltimaPizza.class);
                startActivity(intent);
            }
        });
    }
}