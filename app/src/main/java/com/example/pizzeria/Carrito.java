package com.example.pizzeria;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.example.pizzeria.DAO.GestionCarrito;
import com.example.pizzeria.DAO.ItemCarrito;
import com.example.pizzeria.POJO.GestorTemas;
import com.example.pizzeria.POJO.Pizza;
import com.example.pizzeria.PantallaPrincipal.WebMenuConfig.Config;
import com.example.pizzeria.PantallaPrincipal.WebMenuConfig.GeneralPersonalizadaUltima.Pizzas;
import com.example.pizzeria.PantallaPrincipal.WebPrincipal;

import java.util.List;

public class Carrito extends AppCompatActivity {

    private ListView listaCarrito;
    private TextView txtPrecioTotal;
    private ImageButton btnCarrito, btnPerfil, btnHome;
    private Button btnPagar, btnLimpiarCarrito;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setAppTheme();
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
                Intent intent = new Intent(Carrito.this, Config.class);
                startActivity(intent);
            }
        });

        // Si el botón fuera funcional y la app real esto nos llevaría a la pasarela de pago
        btnPagar.setOnClickListener(v -> {
            List<ItemCarrito> items = GestionCarrito.getInstance().getItemCarrito();

            if (items.isEmpty()) {
                Toast.makeText(this, "El carrito está vacío", Toast.LENGTH_SHORT).show();
                return;
            }

            SharedPreferences prefs = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();

            ItemCarrito ultimoItem = items.get(items.size() - 1);
            Pizza p = ultimoItem.getPizza();

            editor.putString("ultima_nombre", p.getNombre());
            editor.putString("ultima_ingr1", p.getIngr1());
            editor.putString("ultima_ingr2", p.getIngr2());
            editor.putString("ultima_ingr3", p.getIngr3());
            editor.putFloat("ultima_precio", (float) p.getPrecio());

            editor.apply();

            Toast.makeText(this, "¡Pedido realizado con éxito!", Toast.LENGTH_LONG).show();

            GestionCarrito.getInstance().limpiarCarrito();
            cargarCarrito();
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
        ) {
            @Override
            public View getView(int position, View convertView, android.view.ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView text = view.findViewById(android.R.id.text1);
                text.setTextColor(getResources().getColor(R.color.texto_dinamico));
                return view;
            }
        };
        listaCarrito.setAdapter(carritoAdapter);

        if (txtPrecioTotal != null) txtPrecioTotal.setText((String.format("%.2f", totalGlobal) + "€"));
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

