package com.example.pizzeria;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.example.pizzeria.DAO.DAOUsuarios;
import com.example.pizzeria.POJO.GestorTemas;
import com.example.pizzeria.POJO.Usuario;
import com.example.pizzeria.PantallaPrincipal.WebPrincipal;

public class Bienvenida extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setAppTheme();
        setContentView(R.layout.activity_bienvenida);

        final EditText textusuario = findViewById(R.id.usuario);
        final EditText textpassword = findViewById(R.id.password);
        final Button btnLogin = findViewById(R.id.button);
        final TextView txtRegistro = findViewById(R.id.txtviewRegistrarse);
        final TextView txtResetPassword = findViewById(R.id.txtviewResetPassword);

        final DAOUsuarios dao = DAOUsuarios.getInstance();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = textusuario.getText().toString().trim();
                String password = textpassword.getText().toString().trim();

                if (nombre.isEmpty() || password.isEmpty()) {
                    Toast.makeText(Bienvenida.this, "Rellene todos los datos", Toast.LENGTH_SHORT).show();
                    return;
                }

                Usuario usuario = dao.buscarUsuario(nombre);
                if (usuario != null) {
                    if (usuario.getPassword().equals(password)) {
                        Toast.makeText(Bienvenida.this, "Bienvenido " + nombre, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Bienvenida.this, WebPrincipal.class);
                        startActivity(intent);
                    } else Toast.makeText(Bienvenida.this, "Contraseña incorrecta", Toast.LENGTH_SHORT).show();
                } else Toast.makeText(Bienvenida.this, "Usuario no existente, por favor regístrese", Toast.LENGTH_SHORT).show();
            }
        });

        // Registro
        txtRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Bienvenida.this, Registro.class);
                startActivity(intent);
            }
        });

        // Reinicio de contraseña
        txtResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Bienvenida.this, ResetPassword.class);
                startActivity(intent);
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