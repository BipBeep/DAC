/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensimag.dacodac.controler.publicationAnnonce;

import fr.ensimag.dacodac.Annonce;
import fr.ensimag.dacodac.Utilisateur;
import fr.ensimag.dacodac.stateless.AnnonceFacadeLocal;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;

/**
 *
 * @author maubertj
 */
@Named(value = "managePostulantsAnnonce")
@Dependent
public class ManagePostulantsAnnonce {

    @EJB
    private AnnonceFacadeLocal annonceFacade;
    
    private Annonce annonce = null;
    private Utilisateur utilisateur = null;
    

    /**
     * Creates a new instance of ManagePostulantsAnnonce
     */
    public ManagePostulantsAnnonce() {
    }

    String accepterPostulant() {
        annonceFacade.accepterPostulant(getAnnonce(), getUtilisateur());
        annonceFacade.edit(getAnnonce());
        return "index.xhtml";
    }
    
    String refuserPostulant() {
        annonceFacade.removePostulant(getAnnonce(), getUtilisateur());
        annonceFacade.edit(getAnnonce());
        return "index.xhtml";
    }

    /**
     * @return the annonce
     */
    public Annonce getAnnonce() {
        return annonce;
    }

    /**
     * @return the utilisateur
     */
    public Utilisateur getUtilisateur() {
        return utilisateur;
    }
}

