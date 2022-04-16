package com.example.controle;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.Iterator;
import java.util.List;

public class modifier extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modifier);

        Button qui = findViewById(R.id.cancel);

        qui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent homme = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(homme);
            }
        });
        AfficherListEtd();
    }

    public void AfficherListEtd() {

        String[] etudt;
        ListView listEtd = (ListView) findViewById(R.id.ID_List);
        List<Etudiant> list_etd = new EtudiantsDAO(getApplicationContext()).getAllEtudiants();

        if(list_etd != null) {
            int i = 0;
            etudt = new String[list_etd.size()];
            for (Iterator it = list_etd.iterator(); it.hasNext();) {
                Etudiant E = (Etudiant) it.next();
                etudt[i] = E.getCode()+" "+E.getNom()+" "+E.getPrenom()+" "+E.getDt_n()+" "+E.getville();
                i++;
            }
            ArrayAdapter<Etudiant> adapter = new ArrayAdapter<Etudiant>(this, android.R.layout.simple_list_item_1, list_etd);
            listEtd.setAdapter(adapter);
        }
    }

}