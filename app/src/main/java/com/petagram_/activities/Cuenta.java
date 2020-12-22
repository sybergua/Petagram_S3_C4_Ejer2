package com.petagram_.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.petagram_.R;
import com.petagram_.db.ConstructorBD;
import com.petagram_.restApi.ConstantesRestApi;

import static android.app.ActionBar.DISPLAY_SHOW_CUSTOM;

public class Cuenta extends AppCompatActivity {

    private Toolbar toolbar;
    private String cuenta = "";

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuenta);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if(toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayOptions(DISPLAY_SHOW_CUSTOM);
            getSupportActionBar().setCustomView(R.layout.app_bar);
        }

        ImageButton btnFavorito = (ImageButton) findViewById(R.id.btnFavorito2);
        btnFavorito.setVisibility(View.INVISIBLE);

        ImageButton btnAtras = (ImageButton) findViewById(R.id.btnAtras);
        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Cuenta.this, MainActivity.class);
                intent.putExtra("cuenta", cuenta);
                startActivity(intent);
                finish();
            }
        });

        Button btnActualizar = (Button)findViewById(R.id.btnActualizar);

        btnActualizar .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText etCuenta = (EditText) findViewById(R.id.etCuenta);
                cuenta = etCuenta.getText().toString();
                if(ConstantesRestApi.existeCuenta(cuenta)){
                    Toast.makeText(Cuenta.this, "Cuenta Guardada", Toast.LENGTH_LONG).show();
                }else{
                    cuenta = "";
                    etCuenta.setText("");
                    Toast.makeText(Cuenta.this, "Cuenta no registrada para la aplicación", Toast.LENGTH_LONG).show();
                }

            }
        });
    }

}