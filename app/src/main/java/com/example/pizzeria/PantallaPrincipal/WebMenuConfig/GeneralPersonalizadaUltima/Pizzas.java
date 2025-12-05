package com.example.pizzeria.PantallaPrincipal.WebMenuConfig.GeneralPersonalizadaUltima;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
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

import com.example.pizzeria.Carrito;
import com.example.pizzeria.DAO.DAOPizzas;
import com.example.pizzeria.DAO.GestionCarrito;
import com.example.pizzeria.Favoritas;
import com.example.pizzeria.POJO.GestorTemas;
import com.example.pizzeria.POJO.Pizza;
import com.example.pizzeria.PantallaPrincipal.WebMenuConfig.Config;
import com.example.pizzeria.PantallaPrincipal.WebPrincipal;
import com.example.pizzeria.R;

public class Pizzas extends AppCompatActivity {

    ListView listaPizzas;
    DAOPizzas dao;
    GestionCarrito carrito;

    private static final String PREFS_NAME = "PIZZA_FAVORITAS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setAppTheme();
        EdgeToEdge.enable(this);

        setContentView(R.layout.activity_pizzas);

        dao = new DAOPizzas();
        listaPizzas = findViewById(R.id.listaPizzas);
        carrito = GestionCarrito.getInstance();
        final ImageButton btnHome = findViewById(R.id.btnHome);
        final ImageButton btnCarrito = findViewById(R.id.btnCarrito);
        final ImageButton btnPerfil = findViewById(R.id.btnPerfil);
        final Button btnFavoritos = findViewById(R.id.btnFavoritos);

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
                Intent intent = new Intent(Pizzas.this, Config.class);
                startActivity(intent);
            }
        });

        btnFavoritos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Pizzas.this, Favoritas.class);
                startActivity(intent);
            }
        });

        ArrayAdapter<Pizza> adaptadorPizzas = new ArrayAdapter<Pizza>(this, android.R.layout.simple_list_item_1, dao.obtenerPizzas()) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView text = view.findViewById(android.R.id.text1);
                text.setTextColor(getResources().getColor(R.color.texto_dinamico));
                return view;
            }
        };
        listaPizzas.setAdapter(adaptadorPizzas);

        listaPizzas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Pizza pizzaSelec = (Pizza) parent.getItemAtPosition(position);
                seleccionarCantidad(pizzaSelec);
            }
        });
    }

    private void seleccionarCantidad(Pizza pizza) {
        final int[] cantidad = {1};
        final boolean[] favorita = {esFavorita(pizza.getNombre())};

        TextView txtCantidad = new TextView(this);
        txtCantidad.setText(String.valueOf(cantidad[0]));
        txtCantidad.setTextSize(24f);
        txtCantidad.setPadding(30, 30, 30, 30);
        txtCantidad.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        ImageButton btnFavorito = new ImageButton(this);
        btnFavorito.setBackground(null);
        marcarFavorita(btnFavorito, favorita[0]);

        btnFavorito.setOnClickListener(v -> {
            favorita[0] = !favorita[0];
            guardarFavorita(pizza.getNombre(), favorita[0]);
            marcarFavorita(btnFavorito, favorita[0]);
        });

        LinearLayout lay = new LinearLayout(this);
        lay.setOrientation(LinearLayout.HORIZONTAL);
        lay.setGravity(Gravity.CENTER);

        Button btnMenos = new Button(this);
        btnMenos.setText("-");
        Button btnMas = new Button(this);
        btnMas.setText("+");

        lay.addView(btnFavorito);
        lay.addView(btnMenos);
        lay.addView(txtCantidad);
        lay.addView(btnMas);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(pizza.getNombre()).setView(lay).setPositiveButton("Confirmar", (dialog, which) -> {
            carrito.anadirPizzaCarrito(pizza.getNombre(), cantidad[0]);
        }).setNegativeButton("Cancelar", (dialog, which) -> dialog.dismiss());

        AlertDialog dialog = builder.create();
        dialog.show();

        btnMas.setOnClickListener(v -> {
            cantidad[0]++;
            txtCantidad.setText(String.valueOf(cantidad[0]));
        });

        btnMenos.setOnClickListener(v -> {
            if (cantidad[0] > 1) {
                cantidad[0]--;
                txtCantidad.setText((String.valueOf(cantidad[0])));
            }
        });
    }

    private void marcarFavorita(ImageButton btnFavorito, boolean b) {
        int iconResource = b ? android.R.drawable.btn_star_big_on : android.R.drawable.btn_star_big_off;
        int colorTint = b ?
                ContextCompat.getColor(this, android.R.color.holo_orange_light) :
                ContextCompat.getColor(this, android.R.color.darker_gray);

        btnFavorito.setImageResource(iconResource);
        btnFavorito.setColorFilter(colorTint);
    };

    private boolean esFavorita(String nombre) {
        return getSharedPreferences(PREFS_NAME, MODE_PRIVATE)
                .getBoolean(nombre, false);
    };

    private void guardarFavorita(String nombrePizza, boolean favorita) {
        getSharedPreferences(PREFS_NAME, MODE_PRIVATE)
                .edit()
                .putBoolean(nombrePizza, favorita)
                .apply();
    };

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