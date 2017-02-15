/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensimag.dacodac.controler.utilisateur;

import fr.ensimag.dacodac.Annonce;
import fr.ensimag.dacodac.Utilisateur;
import fr.ensimag.dacodac.stateless.AnnonceFacadeLocal;
import fr.ensimag.dacodac.stateless.UtilisateurFacadeLocal;
import java.security.NoSuchAlgorithmException;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author roussena
 */
@Named(value = "modifierAnnonce")
@RequestScoped
public class ModifierAnnonce {

    @EJB
    private AnnonceFacadeLocal annonceFacade;

    private Annonce annonce = null;

    @Inject
    private Identification beanID;

    /**
     * Creates a new instance of ModifierAnnonce
     */
    public ModifierAnnonce() {
    }
    
    public Annonce getAnnonce(long id) {
        if (annonce == null)
        {
            annonce = annonceFacade.find(id);
        }
        return annonce;
    }
    
    public void modificationAnnonce() {
        //System.err.println("Annonce :");
        //System.err.println(annonce);
        String msg = "L'annonce a été éditée";
        annonceFacade.edit(annonce);
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
