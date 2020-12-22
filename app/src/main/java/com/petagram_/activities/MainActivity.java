package com.petagram_.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.messaging.FirebaseMessaging;
import com.petagram_.db.ConstructorBD;
import com.petagram_.fragment.PerfilFragment;
import com.petagram_.fragment.MascotasFragment;
import com.petagram_.adapters.PageAdapter;
import com.petagram_.R;
import com.petagram_.restApi.EndpointRegistroFireBase;
import com.petagram_.restApi.adapter.RegistroFireBaseAdapter;
import com.petagram_.restApi.model.UsuarioResponse;

import java.util.ArrayList;

import static android.app.ActionBar.*;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private String cuenta = "";

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent =getIntent();
        String cuentaNueva = intent.getStringExtra("cuenta");

        if(cuentaNueva != null){
            cuenta = cuentaNueva;
        }
        //getSupportActionBar().setDisplayOptions(DISPLAY_SHOW_CUSTOM);
        //getSupportActionBar().setCustomView(R.layout.app_bar);


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if(toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayOptions(DISPLAY_SHOW_CUSTOM);
            getSupportActionBar().setCustomView(R.layout.app_bar);
            toolbar.setOverflowIcon(ContextCompat.getDrawable(getApplicationContext(), R.drawable.icons8_menu_2_24));
        }

        ImageButton btnAtras = (ImageButton) findViewById(R.id.btnAtras);
        btnAtras.setVisibility(View.INVISIBLE);

        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);

        setUpViewPager();



        ImageButton btnFavorito = (ImageButton) findViewById(R.id.btnFavorito2);
        btnFavorito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                irFavoritos();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent;
        switch (item.getItemId()){
            case R.id.mContacto:
                intent = new Intent(this, Contact.class);
                startActivity(intent);
                finish();
                break;
            case R.id.mAcerdaDe:
                intent = new Intent(this, Biografia.class);
                startActivity(intent);
                finish();
                break;
            case R.id.mCuenta:
                intent = new Intent(this, Cuenta.class);
                startActivity(intent);
                finish();
                break;
            case R.id.mNotificaciones:
                if(!cuenta.isEmpty()) {
                    ConstructorBD db = new ConstructorBD(getApplicationContext());
                    String idNotificacion = db.obtenerIdNotificacion();
                    if(!idNotificacion.isEmpty()){
                        Toast.makeText(this, "El Dispositivo ya se encuentra registrado para recibir notificaciones", Toast.LENGTH_LONG).show();
                    }else {
                        FirebaseMessaging.getInstance().getToken()
                                .addOnCompleteListener(new OnCompleteListener<String>() {
                                    @Override
                                    public void onComplete(@NonNull Task<String> task) {
                                        if (!task.isSuccessful()) {
                                            Log.w("Token: ", "Fetching FCM registration token failed", task.getException());
                                            return;
                                        }

                                        // Get new FCM registration token
                                        String token = task.getResult();

                                        enviarToken(token, cuenta);
                                        Log.d("Token solicitado: ", token);
                                    }
                                });
                    }
                }else{
                    Toast.makeText(this, "Debe de Configurar una cuenta", Toast.LENGTH_LONG).show();
                }
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    public void irFavoritos(){
        Intent intent = new Intent(MainActivity.this, MascotasFavoritas.class);
        startActivity(intent);
        finish();


    }

    private void setUpViewPager(){
        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager(), agregarFragments()));

        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_mascotas);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_perfil);
    }

    private ArrayList<Fragment> agregarFragments(){
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new MascotasFragment());
        fragments.add(new PerfilFragment(cuenta));

        return fragments;
    }

    private void enviarToken(String idDispositivo, String usuarioInstagram){
        RegistroFireBaseAdapter registroFireBaseAdapter = new RegistroFireBaseAdapter();
        EndpointRegistroFireBase endpointRegistroFireBase = registroFireBaseAdapter.establecerConexion();
        Call<UsuarioResponse> usuarioResponseCall = endpointRegistroFireBase.registrarUsuario(idDispositivo, usuarioInstagram);
        usuarioResponseCall.enqueue(new Callback<UsuarioResponse>() {
            @Override
            public void onResponse(Call<UsuarioResponse> call, Response<UsuarioResponse> response) {
                UsuarioResponse usuarioResponse = response.body();
                ConstructorBD db = new ConstructorBD(getApplicationContext());
                db.agregarIdNotificacion(usuarioResponse.getId());
                Toast.makeText(MainActivity.this, "Usuario " + usuarioResponse.getUsuarioInstagram() + " registrado" , Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<UsuarioResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "No fue posible conectarse al servicio de registro de usuario" , Toast.LENGTH_LONG).show();
            }
        });
    }
}