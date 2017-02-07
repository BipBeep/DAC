/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensimag.dacodac.controler.offres;

/**
 *
 * @author mignotju
 */
public class Departement {
    public int numero;
    public String nom;
    
    public Departement(int numero, String nom) {
        this.numero = numero;
        this.nom = nom;
    }
    
    public int getNumero() {
        return this.numero;
    }
    
    public void setNumero(int numero) {
        this.numero = numero;
    }
    
    public String getNom() {
        return this.nom;
    }
    
    public void setNom(String nom) {
        this.nom = nom;
    }
    
    
}
