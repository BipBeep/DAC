/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensimag.dacodac.controler.utilisateur;

import fr.ensimag.dacodac.Utilisateur;
import fr.ensimag.dacodac.stateless.UtilisateurFacadeLocal;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author maubertj
 */
@Named(value = "modifierProfil")
@ViewScoped
public class ModifierProfil {

    @EJB
    private UtilisateurFacadeLocal utilisateurFacade;
    
    private String pseudo;
    
    private Utilisateur utilisateur = null;

    private String nouveauMotDePasse = null;
    
    private String ancienMotDePasse = null;

    /**
     * Creates a new instance of ModifierProfil
     */
    public ModifierProfil() {
    }

    @PostConstruct
    public void postBuild() {
        pseudo = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("pseudo");
        utilisateur = utilisateurFacade.findByPseudo(pseudo);
    }

    public String modificationProfil() {
        //Gestion du mot de passe
        /*if ((!nouveauMotDePasse.equals(null)) && ancienMotDePasse.equals(utilisateur.getPassword())) {
            System.err.println("I'm in!");
            utilisateur.setPassword(nouveauMotDePasse);
        }*/
        utilisateurFacade.edit(utilisateur);
        return "index.xhtml";
    }

    public String getNouveauMotDePasse() {
        if (nouveauMotDePasse == null) {
            nouveauMotDePasse = new String();
        }
        return nouveauMotDePasse;
    }

    public void setNouveauMotDePasse(String nouveauMotDePasse) {
        this.nouveauMotDePasse = nouveauMotDePasse;
    }

    public void setAncienMotDePasse(String ancienMotDePasse) {
        this.ancienMotDePasse = ancienMotDePasse;
    }

    public String getAncienMotDePasse() {
        if (ancienMotDePasse == null) {
            ancienMotDePasse = new String();
        }
        return ancienMotDePasse;
    }

    public Utilisateur getUtilisateur() {
        if (utilisateur == null) {
            utilisateur = new Utilisateur();
            utilisateur.setPseudo(pseudo);
        }
        return utilisateur;
    }

}
