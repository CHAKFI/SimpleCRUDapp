package com.example.simplemanager;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.text.TextWatcher;
import android.widget.Toast;

import java.util.Iterator;
import java.util.List;

public class rechercher extends AppCompatActivity {

    ArrayAdapter<Etudiant> adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rechercher);

        List<Etudiant> list_etd = new EtudiantsDAO(getApplicationContext()).getAllEtudiants();
        adapter = new ArrayAdapter<Etudiant>(this, android.R.layout.simple_list_item_1, list_etd);

        Button rech = findViewById(R.id.btnRech);
        Button ccl = findViewById(R.id.cancelRech);
        ListView listEtd = findViewById(R.id.ID_List);

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


        rech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText textRech = findViewById(R.id.textRech);

                textRech.addTextChangedListener(new TextWatcher() {

                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    }

                    @Override
                    public void onTextChanged(CharSequence cs, int i, int i1, int i2) {
                             rechercher.this.adapter.getFilter().filter(cs);
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }
                });

                Toast.makeText(rechercher.this, "Resultat de recherche", Toast.LENGTH_SHORT).show();

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
