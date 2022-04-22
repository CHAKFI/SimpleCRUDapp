package com.example.simplemanager;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ajouter extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ajouter);

        Button ajout = findViewById(R.id.btnAjouter);
        Button ccl = findViewById(R.id.cancelAjouter);

        ajout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText n = findViewById(R.id.nom);
                String nom = n.getText().toString();

                EditText prn = findViewById(R.id.prenom);
                String prenom = prn.getText().toString();

                EditText nt = findViewById(R.id.note);
                String ne = nt.getText().toString();

                if( TextUtils.isEmpty(nom) || TextUtils.isEmpty(prenom) || TextUtils.isEmpty(ne) ) {
                    n.setError("SVP, saisir un nom ");
                    prn.setError("SVP, saisir un prenom");
                    nt.setError("SVP, saisir une note");
                }else {

                    Float note = Float.parseFloat(ne);

                    Etudiant e = new Etudiant();
                    e.setNom(nom);
                    e.setPrenom(prenom);
                    e.setNote(note);

                    Etudiant etd = new EtudiantsDAO(getApplicationContext()).Ajouter_Etudiants(e);

                    if (e.getNom().isEmpty() && e.getPrenom().isEmpty()){
                        Toast.makeText(ajouter.this, "Informations non ajoutées", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(ajouter.this, "Informations ajoutées", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        ccl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent home = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(home);
            }
        });
    }
}
