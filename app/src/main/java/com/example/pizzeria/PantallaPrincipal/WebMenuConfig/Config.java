package com.example.pizzeria.PantallaPrincipal.WebMenuConfig;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.example.pizzeria.Carrito;
import com.example.pizzeria.POJO.GestorTemas;
import com.example.pizzeria.PantallaPrincipal.WebMenuConfig.GeneralPersonalizadaUltima.Pizzas;
import com.example.pizzeria.PantallaPrincipal.WebPrincipal;
import com.example.pizzeria.R;

public class Config extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setAppTheme();
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_config);

        final ImageButton btnHome = findViewById(R.id.btnHome);
        final ImageButton btnCarrito = findViewById(R.id.btnCarrito);
        final Button btnTemaClaro = findViewById(R.id.btnClaro);
        final Button btnTemaOscuro = findViewById(R.id.btnOscuro);

        btnCarrito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Config.this, Carrito.class);
                startActivity(intent);
            }
        });

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Config.this, WebPrincipal.class);
                startActivity(intent);
            }
        });

        btnTemaClaro.setOnClickListener(v -> {
            GestorTemas.saveThemeMode(this, 0);
            recreateActivity();
        });

        btnTemaOscuro.setOnClickListener(v -> {
            GestorTemas.saveThemeMode(this, 1);
            recreateActivity();
        });

    }

    private void recreateActivity() {
        setAppTheme();

        recreate();
    }

    private void setAppTheme() {
        int savedMode = GestorTemas.getThemeMode(this);

        // 0: Modo Claro
        // 1: Modo Oscuro
        int themeMode = (savedMode == 1) ?
                AppCompatDelegate.MODE_NIGHT_YES :
                AppCompatDelegate.MODE_NIGHT_NO;

        AppCompatDelegate.setDefaultNightMode(themeMode);
    }
}