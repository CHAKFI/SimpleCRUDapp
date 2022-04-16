package com.example.controle;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class EtudiantsDAO {
    private SQLiteDatabase database;
    private com.example.controle.MaBaseSQLite My_database;
    private String[] allColumns = {com.example.controle.MaBaseSQLite.Col_CODE, com.example.controle.MaBaseSQLite.Col_NOM,
            com.example.controle.MaBaseSQLite.Col_Prenom, com.example.controle.MaBaseSQLite.Col_Date_n,
            com.example.controle.MaBaseSQLite.Col_ville };

    public EtudiantsDAO(Context context) {
        My_database = new com.example.controle.MaBaseSQLite(context);
        database = My_database.getWritableDatabase();
        My_database.onCreate(database);
    }

    public Etudiant Ajouter_Etudiants(Etudiant e){
        database = My_database.getWritableDatabase();
        ContentValues valeurs = new ContentValues();

        valeurs.put(com.example.controle.MaBaseSQLite.Col_NOM, e.getNom());
        valeurs.put(com.example.controle.MaBaseSQLite.Col_Prenom, e.getPrenom());
        valeurs.put(com.example.controle.MaBaseSQLite.Col_Date_n, e.getDt_n());
        valeurs.put(com.example.controle.MaBaseSQLite.Col_ville, e.getville());


        try {
            database.insert(com.example.controle.MaBaseSQLite.TABLE_ETUDIANTS, null, valeurs);
        }

        catch (SQLiteException ex) {
            Log.e("InsertError", ex.getMessage());
        }
        return e;
    }

    public List <Etudiant> getAllEtudiants(){
        database = My_database.getReadableDatabase();
        List<Etudiant> listEtudiants = new ArrayList<Etudiant>();
        Cursor cursor = database.rawQuery("SELECT * FROM "+ com.example.controle.MaBaseSQLite.TABLE_ETUDIANTS,
                null);
            if (cursor != null) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()){
                    Etudiant etudiant = cursorToEtudiant(cursor);
                    listEtudiants.add(etudiant);
                    cursor.moveToNext();
                }
                cursor.close();
            }
     return listEtudiants;
    }

    private Etudiant cursorToEtudiant(Cursor cursor) {
        Etudiant etd = new Etudiant();
        etd.setCode(cursor.getInt(0));
        etd.setNom(cursor.getString(1));
        etd.setPrenom(cursor.getString(2));
        etd.setDt_n(cursor.getString(3));
        etd.setville(cursor.getString(4));
        return etd;
    }

}
