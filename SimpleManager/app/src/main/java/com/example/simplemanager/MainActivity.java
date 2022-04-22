package com.example.simplemanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity {

//    ListView List;
//    String[] data = {"Mouna Lemir: 14.75", "Ahmed Rachik: 15.00", "Omar Falahi: 10.50",
//            "Maria Doghmi: 16.25", "Yemmna Baladi: 13.00"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button nv = findViewById(R.id.nouv);
        Button re = findViewById(R.id.rech);
        Button su = findViewById(R.id.supp);

//        List = (ListView) findViewById(R.id.ID_List);
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data);
//        List.setAdapter(adapter);

        nv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ajout = new Intent(getApplicationContext(), ajouter.class);
                startActivity(ajout);

            }
        });

        re.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent rech = new Intent(getApplicationContext(), rechercher.class);
                startActivity(rech);

            }
        });

        su.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent supp = new Intent(getApplicationContext(), supprimer.class);
                startActivity(supp);

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
                    etudt[i] = E.getCode() + " " + E.getNom() + E.getPrenom() + ", "+E.getNote();
                    i++;
                }
                ArrayAdapter<Etudiant> adapter = new ArrayAdapter<Etudiant>(this, android.R.layout.simple_list_item_1, list_etd);
                listEtd.setAdapter(adapter);
                }
            }

}
