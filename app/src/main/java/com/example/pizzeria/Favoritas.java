package com.example.pizzeria;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.content.ContextCompat;

import com.example.pizzeria.DAO.DAOPizzas;
import com.example.pizzeria.DAO.GestionCarrito;
import com.example.pizzeria.POJO.GestorTemas;
import com.example.pizzeria.POJO.Pizza;
import com.example.pizzeria.PantallaPrincipal.WebMenuConfig.Config;
import com.example.pizzeria.PantallaPrincipal.WebPrincipal;

import java.util.ArrayList;
import java.util.List;

public class Favoritas extends AppCompatActivity {

    private ListView listaFavoritas;
    private DAOPizzas dao;
    private GestionCarrito carrito;
    private static final String PREFS_NAME = "PIZZA_FAVORITAS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setAppTheme();
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_favoritas);

        dao = new DAOPizzas();
        carrito = GestionCarrito.getInstance();
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
                Intent intent = new Intent(Favoritas.this, Config.class);
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
                texto.setTextColor(ContextCompat.getColor(getContext(), R.color.texto_dinamico));
                return view;
            }
        };
        listaFavoritas.setAdapter(adaptadorFavoritas);

        listaFavoritas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Pizza pizzaSelec = (Pizza) parent.getItemAtPosition(position);
                seleccionarCant(pizzaSelec);
            }
        });
    }

    private void seleccionarCant(Pizza pizza) {
        final int[] cant = {1};

        TextView txtCantidad = new TextView(this);
        txtCantidad.setText(String.valueOf(cant[0]));
        txtCantidad.setTextSize(24f);
        txtCantidad.setPadding(30, 30, 30, 30);
        txtCantidad.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        LinearLayout lay = new LinearLayout(this);
        lay.setOrientation(LinearLayout.HORIZONTAL);
        lay.setGravity(Gravity.CENTER);

        Button btnMenos = new Button(this);
        btnMenos.setText("-");
        Button btnMas = new Button(this);
        btnMas.setText("+");

        lay.addView(btnMenos);
        lay.addView(txtCantidad);
        lay.addView(btnMas);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(pizza.getNombre()).setView(lay).setPositiveButton("Confirmar", (dialog, which) -> {
            carrito.anadirPizzaCarrito(pizza.getNombre(), cant[0]);
        }).setNegativeButton("Cancelar", (dialog, which) -> dialog.dismiss());

        AlertDialog dialog = builder.create();
        dialog.show();

        btnMas.setOnClickListener(v -> {
            cant[0]++;
            txtCantidad.setText(String.valueOf(cant[0]));
        });

        btnMenos.setOnClickListener(v -> {
            if (cant[0] > 1) {
                cant[0]--;
                txtCantidad.setText((String.valueOf(cant[0])));
            }
        });
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