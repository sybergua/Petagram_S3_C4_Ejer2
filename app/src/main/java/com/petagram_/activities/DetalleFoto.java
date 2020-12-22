package com.petagram_.activities;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.petagram_.R;
import com.petagram_.db.ConstructorBD;
import com.petagram_.restApi.EndpointRegistroFireBase;
import com.petagram_.restApi.EndpointsApi;
import com.petagram_.restApi.adapter.RegistroFireBaseAdapter;
import com.petagram_.restApi.adapter.RestApiAdapter;
import com.petagram_.restApi.model.ComentarioResponse;
import com.petagram_.restApi.model.RegistroComentarioResponse;
import com.petagram_.restApi.model.UsuarioResponse;
import com.squareup.picasso.Picasso;

public class DetalleFoto extends AppCompatActivity {
    private ImageView imgFotoDetalle;
    private TextView tvLikesDetalle;
    private TextView tvCantComentariosDetalle;
    private TextInputEditText tieComentario;
    private String id;
    private String mensaje;
    private String cuenta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_foto);

        Bundle parametros = getIntent().getExtras();
        String url = parametros.getString("url");
        int likes = parametros.getInt("like");
        int comments_count = parametros.getInt("comments_count");
        id = parametros.getString("id");
        cuenta = parametros.getString("cuenta");


        tvLikesDetalle = (TextView) findViewById(R.id.tvLikesDetalle);
        tvLikesDetalle.setText(String.valueOf(likes));
        imgFotoDetalle = (ImageView) findViewById(R.id.imgFotoDetalle);
        tvCantComentariosDetalle = (TextView) findViewById(R.id.tvCantComentariosDetalle);
        tvCantComentariosDetalle.setText(String.valueOf(comments_count));
        tieComentario = (TextInputEditText) findViewById(R.id.tieComentario);

        Picasso.get()
                .load(url)
                .placeholder(R.drawable.boxer)
                .into(imgFotoDetalle);
    }

    public void enviarComentario(View v){
        mensaje = "" + tieComentario.getText();
        if(mensaje.trim().isEmpty()){
            Toast.makeText(this, "Debe de especificar un comentario", Toast.LENGTH_SHORT).show();
        }else {
            RestApiAdapter restApiAdapter = new RestApiAdapter();
            EndpointsApi endpointsApi = restApiAdapter.establecerConexionRestApiInstagram();
            Call<ComentarioResponse> stringCall = endpointsApi.enviarComentario(id, mensaje);
            stringCall.enqueue(new Callback<ComentarioResponse>() {
                @Override
                public void onResponse(Call<ComentarioResponse> call, Response<ComentarioResponse> response) {

                    Log.d("ID_COMENTARIO", response.body().getId());
                    tieComentario.setText("");

                    RegistroFireBaseAdapter registroFireBaseAdapter = new RegistroFireBaseAdapter();
                    EndpointRegistroFireBase endpointRegistroFireBase = registroFireBaseAdapter.establecerConexion();
                    ConstructorBD db = new ConstructorBD(getApplicationContext());
                    Call<RegistroComentarioResponse> registroComentarioResponseCall = endpointRegistroFireBase.registrarComentario(id, cuenta, db.obtenerIdNotificacion(), mensaje);
                    registroComentarioResponseCall.enqueue(new Callback<RegistroComentarioResponse>() {
                        @Override
                        public void onResponse(Call<RegistroComentarioResponse> call, Response<RegistroComentarioResponse> response) {
                            Log.d("Res Registro Comentario", response.body().getRespuesta());
                            Toast.makeText(DetalleFoto.this, "Comentario Enviado Existosamente", Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onFailure(Call<RegistroComentarioResponse> call, Throwable t) {
                            Toast.makeText(DetalleFoto.this, "No fue posible enviar la notificación", Toast.LENGTH_LONG).show();
                        }
                    });
                    Intent intent = new Intent(DetalleFoto.this, MainActivity.class);
                    intent.putExtra("cuenta", cuenta);
                    startActivity(intent);
                    finish();
                }

                @Override
                public void onFailure(Call<ComentarioResponse> call, Throwable t) {
                    Toast.makeText(DetalleFoto.this, "No fue posible enviar el comentario", Toast.LENGTH_LONG).show();
                }
            });
        }

    }
}