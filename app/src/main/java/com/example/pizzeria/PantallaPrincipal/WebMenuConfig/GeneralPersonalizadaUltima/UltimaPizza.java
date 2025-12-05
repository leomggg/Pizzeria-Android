package com.example.pizzeria.PantallaPrincipal.WebMenuConfig.GeneralPersonalizadaUltima;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.pizzeria.Carrito;
import com.example.pizzeria.POJO.GestorTemas;
import com.example.pizzeria.PantallaPrincipal.WebMenuConfig.Config;
import com.example.pizzeria.PantallaPrincipal.WebPrincipal;
import com.example.pizzeria.R;

public class UltimaPizza extends AppCompatActivity {

    private TextView tvNombre, tvIngredientes, tvPrecio, tvSinDatos;
    private LinearLayout layoutInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setAppTheme();
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ultima_pizza);

        tvNombre = findViewById(R.id.tvNombrePizza);
        tvIngredientes = findViewById(R.id.tvIngredientesPizza);
        tvPrecio = findViewById(R.id.tvPrecioPizza);
        tvSinDatos = findViewById(R.id.tvSinDatos);
        layoutInfo = findViewById(R.id.layoutInfoPizza);

        ImageButton btnHome = findViewById(R.id.btnHome);
        ImageButton btnCarrito = findViewById(R.id.btnCarrito);
        ImageButton btnPerfil = findViewById(R.id.btnPerfil);

        btnCarrito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UltimaPizza.this, Carrito.class);
                startActivity(intent);
            }
        });

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UltimaPizza.this, WebPrincipal.class);
                startActivity(intent);
            }
        });

        btnPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UltimaPizza.this, Config.class);
                startActivity(intent);
            }
        });

        cargarUltimaPizza();
    }

    private void cargarUltimaPizza() {
        SharedPreferences prefs = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);

        // Intentamos recuperar el nombre. Si devuelve "null", es que no hay pizza guardada.
        String nombre = prefs.getString("ultima_nombre", null);

        if (nombre != null) {
            // ¡HAY DATOS! Los leemos todos
            String ingr1 = prefs.getString("ultima_ingr1", "");
            String ingr2 = prefs.getString("ultima_ingr2", "");
            String ingr3 = prefs.getString("ultima_ingr3", "");
            // El precio lo guardamos como float porque SharedPreferences no soporta double directo
            float precio = prefs.getFloat("ultima_precio", 0.0f);

            // Mostramos la info
            tvNombre.setText(nombre);
            tvIngredientes.setText(ingr1 + ", " + ingr2 + ", " + ingr3);
            tvPrecio.setText(precio + " €");

            layoutInfo.setVisibility(View.VISIBLE);
            tvSinDatos.setVisibility(View.GONE);
        } else {
            // NO HAY DATOS
            layoutInfo.setVisibility(View.GONE);
            tvSinDatos.setVisibility(View.VISIBLE);
        }
    }

    private void setAppTheme() {
        int savedMode = GestorTemas.getThemeMode(this);

        // 0: Modo Claro (por defecto)
        // 1: Modo Oscuro
        int themeMode = (savedMode == 1) ?
                AppCompatDelegate.MODE_NIGHT_YES :
                AppCompatDelegate.MODE_NIGHT_NO;

        AppCompatDelegate.setDefaultNightMode(themeMode);
    }
}