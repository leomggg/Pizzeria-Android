package com.example.pizzeria;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.pizzeria.DAO.DAOUsuarios;
import com.example.pizzeria.POJO.Usuario;

public class Registro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        final Button btnRegistro = findViewById(R.id.btnRegistro);
        final EditText edtxtNewUser = findViewById(R.id.edtxtNewUser);
        final EditText edtxtNewPassword = findViewById(R.id.edtxtNewPassword);

        final DAOUsuarios dao = DAOUsuarios.getInstance();

        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtxtNewUser != null && edtxtNewPassword != null) {
                    Usuario nuevoUsuario = new Usuario(edtxtNewUser.getText().toString().trim(), edtxtNewPassword.getText().toString().trim());
                    dao.agregarUsuario(nuevoUsuario);

                    Intent intent = new Intent(Registro.this, Bienvenida.class);
                    startActivity(intent);
                    Toast.makeText(Registro.this, "Usuario registrado, inicie sesión", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}