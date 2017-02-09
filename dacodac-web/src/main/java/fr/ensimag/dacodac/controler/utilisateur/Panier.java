/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensimag.dacodac.controler.utilisateur;

import fr.ensimag.dacodac.Annonce;
import fr.ensimag.dacodac.Utilisateur;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.SessionScoped;

/**
 *
 * @author maubertj
 */
@Named(value = "panier")
@SessionScoped
public class Panier implements Serializable {

    private List<Annonce> annonces = null;

    /**
     * Creates a new instance of Panier
     */
    public Panier() {
        annonces = new ArrayList<>();
    }

    public List<Annonce> getAnnonces() {
        if (annonces == null) {
            annonces = new ArrayList<>();
        }
        return annonces;

    }

    public void addAnnonce(Annonce annonce) {
        annonces.add(annonce);
    }

    public void removeAnnonce(Annonce annonce) {
        annonces.remove(annonce);
    }

}
