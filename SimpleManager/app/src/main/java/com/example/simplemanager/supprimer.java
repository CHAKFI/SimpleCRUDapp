package com.example.simplemanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Iterator;
import java.util.List;

public class supprimer extends AppCompatActivity {

    ArrayAdapter<Etudiant> adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.supprimer);

        List<Etudiant> list_etd = new EtudiantsDAO(getApplicationContext()).getAllEtudiants();
        adapter = new ArrayAdapter<Etudiant>(this, android.R.layout.simple_list_item_1, list_etd);
        ListView listEtd = findViewById(R.id.ID_List);


        Button ccl = findViewById(R.id.cancelSupp);

        String[] etudt;
        if(list_etd != null) {
            int i = 0;
            etudt = new String[list_etd.size()];
            for (Iterator it = list_etd.iterator(); it.hasNext();) {
                Etudiant E = (Etudiant) it.next();
                etudt[i] = E.getCode() + " " + E.getNom() + E.getPrenom() + ", "+E.getNote();
                i++;
            }
            listEtd.setAdapter(adapter);
        }

//        ListView listEtd = (ListView) findViewById(R.id.ID_List);
//
//        List = (ListView) findViewById(R.id.ID_List);
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data);
//        List.setAdapter(adapter);

        listEtd.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {

                AlertDialog.Builder builder = new AlertDialog.Builder(supprimer.this);
                builder.setTitle("Etes-vous Sûre ?");

                builder.setNeutralButton("Annuler", (dialog, which) -> dialog.cancel());
                builder.setPositiveButton("Supprimer", (dialog, which) -> {

                    new EtudiantsDAO(getApplicationContext()).supprimerEtudiant();
                    Toast.makeText(supprimer.this, "Suppression Validée", Toast.LENGTH_SHORT).show();

                });

                builder.show();

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
