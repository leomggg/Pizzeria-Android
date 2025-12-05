package com.example.pizzeria.PantallaPrincipal.WebMenuConfig.GeneralPersonalizadaUltima;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.example.pizzeria.Carrito;
import com.example.pizzeria.DAO.GestionCarrito;
import com.example.pizzeria.POJO.GestorTemas;
import com.example.pizzeria.POJO.Pizza;
import com.example.pizzeria.PantallaPrincipal.WebMenuConfig.Config;
import com.example.pizzeria.PantallaPrincipal.WebPrincipal;
import com.example.pizzeria.R;

import java.util.ArrayList;
import java.util.List;

public class PersonalizarPizza extends AppCompatActivity {

    private List<CheckBox> listaIngredientes = new ArrayList<>();
    private final int MAX_INGREDIENTES = 3;
    private final double PRECIO_FIJO = 12.00;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setAppTheme();
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_personalizar_pizza);

        ImageButton btnHome = findViewById(R.id.btnHome);
        ImageButton btnCarrito = findViewById(R.id.btnCarrito);
        ImageButton btnPerfil = findViewById(R.id.btnPerfil);

        btnHome.setOnClickListener(v -> {
            startActivity(new Intent(this, WebPrincipal.class));
            finish();
        });
        btnCarrito.setOnClickListener(v -> startActivity(new Intent(this, Carrito.class)));
        btnPerfil.setOnClickListener(v -> startActivity(new Intent(this, Config.class)));

        Button btnAnadir = findViewById(R.id.btnAnadirPersonalizada);

        CheckBox cbJamon = findViewById(R.id.cbJamon);
        CheckBox cbQueso = findViewById(R.id.cbQueso);
        CheckBox cbBacon = findViewById(R.id.cbBacon);
        CheckBox cbChampinon = findViewById(R.id.cbChampinon);
        CheckBox cbPimiento = findViewById(R.id.cbPimiento);
        CheckBox cbAceitunas = findViewById(R.id.cbAceitunas);
        CheckBox cbPina = findViewById(R.id.cbPina);
        CheckBox cbPollo = findViewById(R.id.cbPollo);

        CheckBox[] todosLosChecks = {cbJamon, cbQueso, cbBacon, cbChampinon, cbPimiento, cbAceitunas, cbPina, cbPollo};

        CompoundButton.OnCheckedChangeListener checkListener = (buttonView, isChecked) -> {
            if (isChecked) {
                if (listaIngredientes.size() >= MAX_INGREDIENTES) {
                    buttonView.setChecked(false);
                    Toast.makeText(this, "Solo puedes elegir 3 ingredientes", Toast.LENGTH_SHORT).show();
                } else {
                    listaIngredientes.add((CheckBox) buttonView);
                }
            } else {
                listaIngredientes.remove((CheckBox) buttonView);
            }
        };

        for (CheckBox cb : todosLosChecks) {
            cb.setOnCheckedChangeListener(checkListener);
        }

        btnAnadir.setOnClickListener(v -> {
            if (listaIngredientes.isEmpty()) {
                Toast.makeText(this, "Elige al menos 1 ingrediente", Toast.LENGTH_SHORT).show();
                return;
            }

            String ingr1 = (listaIngredientes.size() > 0) ? listaIngredientes.get(0).getText().toString() : "";
            String ingr2 = (listaIngredientes.size() > 1) ? listaIngredientes.get(1).getText().toString() : "";
            String ingr3 = (listaIngredientes.size() > 2) ? listaIngredientes.get(2).getText().toString() : "";

            Pizza pizzaCustom = new Pizza(
                    "Pizza Personalizada",
                    ingr1,
                    ingr2,
                    ingr3,
                    PRECIO_FIJO
            );

            GestionCarrito.getInstance().anadirPizzaDirecta(pizzaCustom, 1);

            Toast.makeText(this, "¡Pizza personalizada añadida!", Toast.LENGTH_SHORT).show();

            startActivity(new Intent(this, Carrito.class));
            finish();
        });
    }

    private void setAppTheme() {
        int savedMode = GestorTemas.getThemeMode(this);

        int themeMode = (savedMode == 1) ?
                AppCompatDelegate.MODE_NIGHT_YES :
                AppCompatDelegate.MODE_NIGHT_NO;

        AppCompatDelegate.setDefaultNightMode(themeMode);
    }
}