package com.example.pizzeria.PantallaPrincipal.WebMenuConfig.GeneralPersonalizadaUltima;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pizzeria.R;

public class PersonalizarPizza extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_personalizar_pizza);



    }
}