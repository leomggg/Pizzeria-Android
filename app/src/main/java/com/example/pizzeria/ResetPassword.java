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

public class ResetPassword extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        final EditText textNewPassword = findViewById(R.id.edtxtEmailRstPassword);
        final Button btnResetPassword = findViewById(R.id.btnRstPassw);

        btnResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (textNewPassword != null) {
                    Intent intent = new Intent(ResetPassword.this, Bienvenida.class);
                    startActivity(intent);
                    Toast.makeText(ResetPassword.this, "Correo electrónico de recuperación de cuenta enviado", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}