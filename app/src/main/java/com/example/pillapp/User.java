package com.example.pillapp;

public class User {
    private String email;
    private String age;
    private String nom;
    private String Occupation;
    private String relation;


    public String getSpinner() {
        return spinner;
    }

    public void setSpinner(String spinner) {
        this.spinner = spinner;
    }

    private String spinner;
    public User(){
    }



    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getRelation() {
        return this.relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public String getEmail() {
        return email;
    }

    public String getOccupation() {
        return Occupation;
    }



    public void setAge(String age) {
        this.age = age;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    public void setOccupation(String Occupation) {
        this.Occupation = Occupation;
    }

    public String getAge() {
        return age;
    }
}
