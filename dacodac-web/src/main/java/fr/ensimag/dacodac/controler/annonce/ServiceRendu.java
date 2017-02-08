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
@Named(value = "serviceRendu")
@Dependent
public class ServiceRendu {
   
    private Annonce annonce = null;
    private Utilisateur utilisateur = null;
    
    @EJB
    private AnnonceFacadeLocal annonceFacade;
    
    public ServiceRendu() {
    }
    
    String validerService() {
        annonceFacade.serviceRendu(true, getAnnonce(), getUtilisateur());
        annonceFacade.edit(getAnnonce());
        return "index.xhtml";
    }
    
    String inValiderService() {
        annonceFacade.serviceRendu(false, getAnnonce(), getUtilisateur());
        annonceFacade.edit(getAnnonce());
        return "index.xhtml";
    }

    public Annonce getAnnonce() {
        if (annonce == null)
        {
            annonce = new Annonce();
        }
        return annonce;
    }

    public Utilisateur getUtilisateur() {
        if (utilisateur == null)
        {
            utilisateur = new Utilisateur();
        }
        return utilisateur;
    }
}
