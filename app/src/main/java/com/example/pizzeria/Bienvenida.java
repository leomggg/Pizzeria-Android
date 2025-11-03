package com.example.pizzeria;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.pizzeria.DAO.DAOPizzas;
import com.example.pizzeria.DAO.DAOUsuarios;
import com.example.pizzeria.POJO.Usuario;

public class Bienvenida extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bienvenida);

        final EditText textusuario = findViewById(R.id.usuario);
        final EditText textpassword = findViewById(R.id.password);
        final Button btnLogin = findViewById(R.id.button);
        final TextView txtRegistro = findViewById(R.id.txtviewRegistrarse);
        final TextView txtResetPassword = findViewById(R.id.txtviewResetPassword);


        final DAOUsuarios dao = DAOUsuarios.getInstance();

        //Prueba de funcinamiento del login
        final String adminUser = "admin";
        final String adminPass = "admin";

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = textusuario.getText().toString().trim();
                String password = textpassword.getText().toString().trim();

                for (Usuario u : dao.obtenerUsuarios()) {
                    if (nombre.equals(u.getNombre()) && password.equals(u.getPassword())) {
                        Intent intent = new Intent(Bienvenida.this, WebPrincipal.class);
                        startActivity(intent);
                        Toast.makeText(Bienvenida.this, "Bienvenido " + nombre, Toast.LENGTH_SHORT).show();
                    } else if (nombre.equals(u.getNombre()) && !password.equals(u.getPassword()) || !dao.encontrarUsuario(nombre)) Toast.makeText(Bienvenida.this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
                }
            }
        });

        txtRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Bienvenida.this, Registro.class);
                startActivity(intent);
            }
        });

        txtResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Bienvenida.this, ResetPassword.class);
                startActivity(intent);
            }
        });

    }
}