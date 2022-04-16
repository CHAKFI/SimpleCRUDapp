package com.example.controle;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button nv = findViewById(R.id.nouv);
        Button md = findViewById(R.id.mod);
        Button su = findViewById(R.id.supp);
        ListView listEtd = (ListView) findViewById(R.id.ID_List);

        nv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCustomDialog();
            }
        });

        md.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent modifier = new Intent(getApplicationContext(), modifier.class);
                startActivity(modifier);

            }
        });

        su.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent supprimer = new Intent(getApplicationContext(), supprimer.class);
                startActivity(supprimer);

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

    //Function to display the custom dialog.
    void showCustomDialog() {
        final Dialog dialog = new Dialog(MainActivity.this);
        //We have added a title in the custom layout. So let's disable the default title.
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //The user will be able to cancel the dialog bu clicking anywhere outside the dialog.
        dialog.setCancelable(true);
        //Mention the name of the layout of your custom dialog.
        dialog.setContentView(R.layout.db_dialog);

        //Initializing the views of the dialog.
        final EditText nom = dialog.findViewById(R.id.nomLaout);
        String nm = nom.getText().toString();
        final EditText pre = dialog.findViewById(R.id.prenomLayout);
        String pr = pre.getText().toString();
        final EditText dte = dialog.findViewById(R.id.dateLayout);
        String dt = dte.getText().toString();
        final EditText vll = dialog.findViewById(R.id.villeLayout);
        String vi = vll.getText().toString();

        Button submitButton = dialog.findViewById(R.id.nouv);
        Button ccl = dialog.findViewById(R.id.cancel);

        Etudiant e = new Etudiant();
        e.setNom(nm);
        e.setPrenom(pr);
        e.setDt_n(dt);
        e.setville(vi);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Etudiant etd = new EtudiantsDAO(getApplicationContext()).Ajouter_Etudiants(e);
                dialog.dismiss();
            }
        });

        ccl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        dialog.show();
    }

}