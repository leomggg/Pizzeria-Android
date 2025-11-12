package com.example.pizzeria;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
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

import java.util.ArrayList;
import java.util.List;

public class Carrito extends AppCompatActivity {

    private ListView listaCarrito;
    private TextView txtPrecioTotal;

    private ImageButton btnHome;
    private ImageButton btnCarrito;
    private ImageButton btnPerfil;
    private Button btnPagar;
    private Button btnLimpiarCarrito;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_carrito);

        listaCarrito = findViewById(R.id.listaCarrito);
        txtPrecioTotal = findViewById(R.id.txtPrecioTotal);

        btnHome = findViewById(R.id.btnHome);
        btnCarrito = findViewById(R.id.btnCarrito);
        btnPerfil = findViewById(R.id.btnPerfil);
        btnLimpiarCarrito = findViewById(R.id.btnLimpiarCarrito);
        btnPagar = findViewById(R.id.btnPagar);

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

        // LLevar a pasarela de pago
        btnPagar.setOnClickListener(v -> {

        });

        btnLimpiarCarrito.setOnClickListener(v-> {
            GestionCarrito.getInstance().limpiarCarrito();
            cargarCarrito();
        });

        cargarCarrito();
    }

    @Override
    protected void onResume() {
        super.onResume();
        cargarCarrito();
    }

    private void cargarCarrito() {
        List<ItemCarrito> itemsCarrito = GestionCarrito.getInstance().getItemCarrito();
        double totalGlobal = 0.0;

        for (ItemCarrito item: itemsCarrito) {
            double precioUnitario = item.getPizza().getPrecio();
            double subtotal = item.getCant() * precioUnitario;

            totalGlobal += subtotal;
        }

        ArrayAdapter<ItemCarrito> carritoAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                itemsCarrito
        );
        listaCarrito.setAdapter(carritoAdapter);

        if (txtPrecioTotal != null) txtPrecioTotal.setText((String.format("%.2f", totalGlobal) + "€"));
    }
}

