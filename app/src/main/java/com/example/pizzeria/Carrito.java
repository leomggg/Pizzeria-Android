package com.example.pizzeria;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.pizzeria.DAO.GestionCarrito;
import com.example.pizzeria.DAO.ItemCarrito;

public class Carrito extends AppCompatActivity {

    private ListView listaCarrito;
    private TextView txtPrecioTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_carrito);

        listaCarrito = findViewById(R.id.listaCarrito);
        txtPrecioTotal = findViewById(R.id.txtPrecioTotal);

        final ImageButton btnHome = findViewById(R.id.btnHome);
        final ImageButton btnCarrito = findViewById(R.id.btnCarrito);
        final ImageButton btnPerfil = findViewById(R.id.btnPerfil);

        btnCarrito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Carrito.this, Carrito.class);
                startActivity(intent);
            }
        });

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Carrito.this, WebPrincipal.class);
                startActivity(intent);
            }
        });

        btnPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Carrito.this, Perfil.class);
                startActivity(intent);
            }
        });


    }
}