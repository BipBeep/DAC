/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package fr.ensimag.dacodac.controler.utilisateur;

import fr.ensimag.dacodac.Utilisateur;
import fr.ensimag.dacodac.stateless.UtilisateurFacadeLocal;
import java.security.NoSuchAlgorithmException;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;

import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author maubertj
 */
@Named(value = "modifierProfil")
@RequestScoped
public class ModifierProfil {

    @EJB
    private UtilisateurFacadeLocal utilisateurFacade;

    private String pseudo;

    private Utilisateur utilisateur = null;

    private String nouveauMotDePasse = "";

    private String nouveauMotDePasse2 = "";

    private String ancienMotDePasse = "";

    @Inject
    private Identification beanID;

    /**
     * Creates a new instance of ModifierProfil
     */
    public ModifierProfil() {
    }

// @PostConstruct
// public void postBuild() {
// pseudo = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("pseudo");
// utilisateur = utilisateurFacade.findByPseudo(pseudo);
// }
    public void modificationProfil() throws NoSuchAlgorithmException {
//Gestion du mot de passe
/*if ((!nouveauMotDePasse.equals(null)) && ancienMotDePasse.equals(utilisateur.getPassword())) {
System.err.println("I'm in!");
utilisateur.setPassword(nouveauMotDePasse);
}*/
        String msg = "";
        if (ancienMotDePasse.equals("") && nouveauMotDePasse.equals("") && nouveauMotDePasse2.equals("")) {
//Pas besoin de toucher aux mdp. mais on edit le reste.
            msg = "Le profil a été édité";
            utilisateurFacade.edit(getUtilisateur());
        } else if (ancienMotDePasse.equals("") || nouveauMotDePasse.equals("") || nouveauMotDePasse2.equals("")) {
//1 est null, on lui dit de recommencer.
            msg = "Il faut entrer les 3 mots de passe. Profil non modifié";
        } else //les 3 sont remplis. on edit peut etre le mdp.
        if (nouveauMotDePasse.length() < 8) {
            msg = "Le nouveau mot de passe entré est trop court (8 caracteres minimium). Profil non modifié";
        } else {
            ancienMotDePasse = Crypting.crypt(ancienMotDePasse);
            if (nouveauMotDePasse.equals(getNouveauMotDePasse2()) && (ancienMotDePasse.equals(getUtilisateur().getPassword()))) {
                nouveauMotDePasse = Crypting.crypt(nouveauMotDePasse);
                System.err.println("je set le mdp");
                getUtilisateur().setPassword(nouveauMotDePasse);
                msg = "Le profil a été édité";
                utilisateurFacade.edit(getUtilisateur());
            } else {
                msg = "Un des mots de passe entrés est mauvais. Profil non modifié";
            }
        }
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
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
        return beanID.getIdentite();
    }

    /**
     * @return the pseudo
     */
    public String getPseudo() {
        return pseudo;
    }

    /**
     * @param pseudo the pseudo to set
     */
    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    /**
     * @return the beanID
     */
    public Identification getBeanID() {
        return beanID;
    }

    /**
     * @param beanID the beanID to set
     */
    public void setBeanID(Identification beanID) {
        this.beanID = beanID;
    }

    /**
     * @return the nouveauMotDePasse2
     */
    public String getNouveauMotDePasse2() {
        return nouveauMotDePasse2;
    }

    /**
     * @param nouveauMotDePasse2 the nouveauMotDePasse2 to set
     */
    public void setNouveauMotDePasse2(String nouveauMotDePasse2) {
        this.nouveauMotDePasse2 = nouveauMotDePasse2;
    }

}
