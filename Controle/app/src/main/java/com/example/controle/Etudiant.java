package com.example.controle;

public class Etudiant {

    private int Code;
    private String Nom;
    private String Prenom;
    private String dt_n;
    private String ville;

    // getters et setters

    // code etudiant
    public long getCode() {
        return Code;
    }
    public void setCode(int Code) {
        this.Code = Code;
    }

    // nom
    public String getNom() {
        return Nom;
    }
    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    // prenom
    public String getPrenom() {
        return Prenom;
    }
    public void setPrenom(String Prenom) {
        this.Prenom = Prenom;
    }

    // date
    public String getDt_n() { return dt_n; }
    public void setDt_n(String dt_n) { this.dt_n = dt_n; }

    // ville
    public String getville() {
        return ville;
    }
    public void setville(String vi) {
        this.ville = vi;
    }

    @Override
    public String toString() {
        return "Etudiant " + Code + " : " + Nom + " " + Prenom + " " + dt_n + " " + ville;
    }
}
