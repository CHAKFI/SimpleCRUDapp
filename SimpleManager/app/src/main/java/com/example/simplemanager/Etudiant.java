package com.example.simplemanager;
public class Etudiant {

    private int Code;
    private String Nom;
    private String Prenom;
    private float Moyenne_Notes;

    //getters et setters
    public long getCode() {
        return Code;
    }

    public void setCode(int Code) {
        this.Code = Code;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    public String getPrenom() {
        return Prenom;
    }

    public void setPrenom(String Prenom) {
        this.Prenom = Prenom;
    }

    public float getNote() {
        return Moyenne_Notes;
    }

    public void setNote(float Note) {
        this.Moyenne_Notes = Note;
    }

    @Override
    public String toString() {
        return "Etudiant " + Code + " : " + Nom + " " + Prenom + ", " + Moyenne_Notes;
    }}
