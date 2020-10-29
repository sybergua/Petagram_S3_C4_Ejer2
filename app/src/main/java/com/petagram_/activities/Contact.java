package com.petagram_.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.petagram_.services.Email;
import com.petagram_.R;

import javax.mail.MessagingException;

import static android.app.ActionBar.DISPLAY_SHOW_CUSTOM;

public class Contact extends AppCompatActivity {

    private Toolbar toolbar;

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

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
                Intent intent = new Intent(Contact.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        Button btnEnviarMensaje = (Button) findViewById(R.id.btnEnviarMensaje);
        btnEnviarMensaje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new EnviarMail().execute();
            }
        });
    }

    private class EnviarMail extends AsyncTask<String, Integer, Void> {

        @Override
        protected Void doInBackground(String... strings) {
            try {
                String mensaje = "Mensaje de: ";

                TextInputEditText tietNombre = (TextInputEditText) findViewById(R.id.tietNombre);
                mensaje += tietNombre.getText();

                TextInputEditText tietEmail = (TextInputEditText) findViewById(R.id.tietEmail);
                mensaje += "\n\ne-mail: " + tietEmail.getText();

                TextInputEditText tietMensaje = (TextInputEditText) findViewById(R.id.tietMensaje);
                mensaje += "\n\nMensaje: " + tietMensaje.getText();

                Email email = new Email(mensaje);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            posEnvioCorreo();
        }
    }

    private void posEnvioCorreo(){
        Toast.makeText(this,"Mensaje Enviado",Toast.LENGTH_LONG).show();
        TextInputEditText tietNombre = (TextInputEditText) findViewById(R.id.tietNombre);
        TextInputEditText tietEmail = (TextInputEditText) findViewById(R.id.tietEmail);
        TextInputEditText tietMensaje = (TextInputEditText) findViewById(R.id.tietMensaje);

        tietEmail.setText("");
        tietMensaje.setText("");
        tietNombre.setText("");
        tietNombre.requestFocus();
    }
}