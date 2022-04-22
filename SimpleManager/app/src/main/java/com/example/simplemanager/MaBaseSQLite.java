package com.example.simplemanager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MaBaseSQLite extends SQLiteOpenHelper {

    public static final int databaseVersion = 1;
    public static final String databaseName = "DB_Etudiants.db";

    // Définition des tables
    public static final String TABLE_ETUDIANTS = "Etudiants";

    // les noms des colonnes
    public static final String Col_CODE = "Code";
    public static final String Col_NOM = "Nom";
    public static final String Col_Prenom = "Prenom";
    public static final String Col_Moyenne_notes = "Moyenne_notes";

    //req de creation de la table
    private static final String SQLCreate_TablesEtudiants = "CREATE TABLE IF NOT EXISTS " +
            TABLE_ETUDIANTS + " ("
            + Col_CODE + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + Col_NOM + " TEXT NOT NULL, "
            + Col_Prenom + " TEXT NOT NULL, "
            + Col_Moyenne_notes + " FLOAT NOT NULL);";

    private static final String SQLDeleteTableEtudiants ="DROP TABLE IF EXISTS "+TABLE_ETUDIANTS+";" ;
    public MaBaseSQLite (Context context) {
        super(context, databaseName, null, databaseVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(SQLCreate_TablesEtudiants);
        }
        catch (Exception ex) {
            Log.e("Creation Table Error", ex.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        database.execSQL(SQLDeleteTableEtudiants);
        onCreate(database);
    }
}
