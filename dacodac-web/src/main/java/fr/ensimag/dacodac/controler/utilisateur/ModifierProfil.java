/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensimag.dacodac.controler.utilisateur;

import fr.ensimag.dacodac.Utilisateur;
import fr.ensimag.dacodac.stateless.UtilisateurFacadeLocal;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author maubertj
 */
@Named(value = "modifierProfil")
@RequestScoped
public class ModifierProfil {

    @EJB
    private UtilisateurFacadeLocal utilisateurFacade;

    private Utilisateur utilisateur = null;

    private String nouveauMotDePasse;
    private String ancienMotDePasse;

    /**
     * Creates a new instance of ModifierProfil
     */
    public ModifierProfil() {
    }

    public String modificationProfil() {
        //Gestion du mot de passe
        if ((!nouveauMotDePasse.equals("")) && ancienMotDePasse.equals(utilisateur.getPassword())) {
            utilisateur.setPassword(nouveauMotDePasse);
        }
        utilisateurFacade.edit(utilisateur);
        return "index.xhtml";
    }

    public String getNouveauMotDePasse() {
        return nouveauMotDePasse;
    }

    public String getAncienMotDePasse() {
        return ancienMotDePasse;
    }

    public Utilisateur getUtilisateur(String pseudo) {
        if (utilisateur == null) {
            utilisateur = utilisateurFacade.findByPseudo(pseudo);
        }
        return utilisateur;
    }

}
