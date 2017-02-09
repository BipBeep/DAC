/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensimag.dacodac.controler.annonce;

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
@Named(value = "postulerAnnonce")
@Dependent
public class PostulerAnnonce {
    private Annonce annonce = null;
    private Utilisateur utilisateur = null;
    
    
    
    @EJB
    private AnnonceFacadeLocal annonceFacade;
    
    /**
     * Creates a new instance of PostulerAnnonce
     */
    public PostulerAnnonce() {
    }
    
    public String Postuler() {
        annonceFacade.addPostulant(getAnnonce(), getUtilisateur());
        annonceFacade.edit(getAnnonce());
        return "index.xhtml";
    }

    /**
     * @return the annonce
     */
    public Annonce getAnnonce() {
        if (annonce == null)
        {
            annonce = new Annonce();
        }
        return annonce;
    }

    /**
     * @return the utilisateur
     */
    public Utilisateur getUtilisateur() {
        if (utilisateur == null)
        {
            utilisateur = new Utilisateur();
        }
        return utilisateur;
    }
}
