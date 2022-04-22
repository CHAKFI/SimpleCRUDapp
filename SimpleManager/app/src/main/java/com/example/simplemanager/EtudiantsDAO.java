package com.example.simplemanager;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class EtudiantsDAO {
    private SQLiteDatabase database;
    private MaBaseSQLite My_database;
    private String[] allColumns = {MaBaseSQLite.Col_CODE, MaBaseSQLite.Col_NOM, MaBaseSQLite.Col_Prenom,
            MaBaseSQLite.Col_Moyenne_notes};

    public EtudiantsDAO(Context context) {
        My_database = new MaBaseSQLite(context);
        database = My_database.getWritableDatabase();
        My_database.onCreate(database);
    }

    public Etudiant Ajouter_Etudiants(Etudiant e){
        database = My_database.getWritableDatabase();
        ContentValues valeurs = new ContentValues();

        valeurs.put(MaBaseSQLite.Col_NOM, e.getNom());
        valeurs.put(MaBaseSQLite.Col_Prenom, e.getPrenom());
        valeurs.put(MaBaseSQLite.Col_Moyenne_notes, e.getNote());

        try {database.insert(MaBaseSQLite.TABLE_ETUDIANTS, null, valeurs);}
        catch (SQLiteException ex) {Log.e("InsertError", ex.getMessage());}
        return e;
    }

    public void supprimerEtudiant (){
        database = My_database.getWritableDatabase();
        ContentValues valeurs = new ContentValues();

        valeurs.remove(MaBaseSQLite.Col_NOM);
        valeurs.remove(MaBaseSQLite.Col_Prenom);
        valeurs.remove(MaBaseSQLite.Col_Moyenne_notes);
        
    }

    public List <Etudiant> getAllEtudiants(){
        database = My_database.getReadableDatabase();
        List<Etudiant> listEtudiants = new ArrayList<Etudiant>();
        Cursor cursor = database.rawQuery("SELECT * FROM "+ MaBaseSQLite.TABLE_ETUDIANTS, null);
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
        etd.setNote(cursor.getInt(3));
        return etd;
    }

}
