package com.petagram_.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.petagram_.models.Mascota;

import java.util.ArrayList;

import androidx.annotation.Nullable;

public class BaseDatos extends SQLiteOpenHelper {
    private Context context;

    public BaseDatos(@Nullable Context context) {
        super(context, ConstantesBaseDatos.DATABASE_NAME, null, ConstantesBaseDatos.DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryCrearTablaMascotasFavoritas =
                "CREATE TABLE " + ConstantesBaseDatos.TABLA_MASCOTAS_FAVORITAS + "(" +
                        ConstantesBaseDatos.TABLA_MASCOTAS_FAVORITAS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        ConstantesBaseDatos.TABLA_MASCOTAS_FAVORITAS_NOMBRE + " TEXT," +
                        ConstantesBaseDatos.TABLA_MASCOTAS_FAVORITAS_FOTO + " INTEGER," +
                        ConstantesBaseDatos.TABLA_MASCOTAS_FAVORITAS_RAITING + " INTEGER," +
                        ConstantesBaseDatos.TABLA_MASCOTAS_FAVORITAS_SEXO + " INTEGER" +
                        ")";

        db.execSQL(queryCrearTablaMascotasFavoritas);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + ConstantesBaseDatos.TABLA_MASCOTAS_FAVORITAS);
        onCreate(db);
    }

    public ArrayList<Mascota> obtenerMasCotasFavoritas(){
        ArrayList<Mascota> mascotas = new ArrayList<>();

        String query = "SELECT * FROM " + ConstantesBaseDatos.TABLA_MASCOTAS_FAVORITAS + " ORDER BY " + ConstantesBaseDatos.TABLA_MASCOTAS_FAVORITAS_ID + " DESC";
        SQLiteDatabase db = getWritableDatabase();
        Cursor registros = db.rawQuery(query,null);

        while (registros.moveToNext()){
            Mascota mascota = new Mascota();
            mascota.setId(registros.getInt(0));
            mascota.setNombreMascota(registros.getString(1));
            mascota.setFotoMascota(registros.getInt(2));
            mascota.setValorRaiting(registros.getInt(3));
            mascota.setSexo(registros.getInt(4) == 0 ? false:true);

            mascotas.add(mascota);
        }

        db.close();
        return mascotas;
    }

    public void insertarMascotaFavorita(ContentValues contentValues){
        eliminarMascotaFavoritaExistente(contentValues.getAsString(ConstantesBaseDatos.TABLA_MASCOTAS_FAVORITAS_NOMBRE));

        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ConstantesBaseDatos.TABLA_MASCOTAS_FAVORITAS,null, contentValues);
        if(cantidadMascotasFavoritas() > 5){
            eliminarMascotaFavorita();
        }

        db.close();
    }

    public int cantidadMascotasFavoritas() {
        int cantidad = 0;
        String query =
                "SELECT COUNT(" + ConstantesBaseDatos.TABLA_MASCOTAS_FAVORITAS_ID + ")" +
                        " FROM " + ConstantesBaseDatos.TABLA_MASCOTAS_FAVORITAS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query, null);

        if (registros.moveToNext()) {
            cantidad = registros.getInt(0);
        }

        db.close();

        return cantidad;
    }

    public void eliminarMascotaFavorita(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(ConstantesBaseDatos.TABLA_MASCOTAS_FAVORITAS, ConstantesBaseDatos.TABLA_MASCOTAS_FAVORITAS_ID + "=?", new String[]{obtenerIdMasAntiguo()});
        db.close();
    }

    public String obtenerIdMasAntiguo(){
        String idMin="";
        String query = "SELECT MIN(" + ConstantesBaseDatos.TABLA_MASCOTAS_FAVORITAS_ID + ") FROM " + ConstantesBaseDatos.TABLA_MASCOTAS_FAVORITAS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query, null);

        if (registros.moveToNext()) {
            idMin = registros.getString(0);
        }

        return idMin;
    }

    public void eliminarMascotaFavoritaExistente(String nombreMascota){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(ConstantesBaseDatos.TABLA_MASCOTAS_FAVORITAS,ConstantesBaseDatos.TABLA_MASCOTAS_FAVORITAS_NOMBRE + "=?",new String[]{nombreMascota});
        db.close();
    }
}
