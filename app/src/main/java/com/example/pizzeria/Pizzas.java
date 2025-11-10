package com.example.pizzeria;

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
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.pizzeria.DAO.DAOPizzas;
import com.example.pizzeria.DAO.gestionCarrito;
import com.example.pizzeria.POJO.Pizza;

public class Pizzas extends AppCompatActivity {

    ListView listaPizzas;
    DAOPizzas dao;

    gestionCarrito carrito;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        setContentView(R.layout.activity_pizzas);

        dao = new DAOPizzas();
        listaPizzas = findViewById(R.id.listaPizzas);
        carrito = new gestionCarrito(gestionCarrito.getCarrito());
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

        ArrayAdapter<Pizza> adaptadorPizzas = new ArrayAdapter<Pizza>(this, android.R.layout.simple_list_item_1, dao.obtenerPizzas()) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView text = view.findViewById(android.R.id.text1);
                text.setTextColor(getResources().getColor(android.R.color.white));
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

        TextView txtCantidad = new TextView(this);
        txtCantidad.setText(String.valueOf(cantidad[0]));
        txtCantidad.setTextSize(24f);
        txtCantidad.setPadding(50, 50, 50, 50);
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

}