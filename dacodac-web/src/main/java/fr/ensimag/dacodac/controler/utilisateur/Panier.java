/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensimag.dacodac.controler.utilisateur;

import fr.ensimag.dacodac.Annonce;
import fr.ensimag.dacodac.Utilisateur;
import fr.ensimag.dacodac.stateless.AnnonceFacadeLocal;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author maubertj
 */
@Named(value = "panier")
@SessionScoped
public class Panier implements Serializable {

    @EJB(name = "annonceFacade")
    private AnnonceFacadeLocal annonceFacade;

    @Inject
    private Identification beanID;

    private Set<Annonce> annonces = null;

    public void setIdentification(Identification identification) {
        this.beanID = identification;
    }

    public Identification getIdentification() {
        return beanID;
    }

    /**
     * Creates a new instance of Panier
     */
    public Panier() {
        annonces = new HashSet<>();
    }

    public Set<Annonce> getAnnonces() {
        if (annonces == null) {
            annonces = new HashSet<>();
        }
        return annonces;

    }

    public void addAnnonce(Annonce annonce) {
        String msg;
        if(annonces.add(annonce)) {
            msg = "Cette annonce a été ajouté à votre panier";
        } else {
            msg = "Cette annonce est déjà présente dans votre panier";
        }
        
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
        System.out.println("-----------------------------------------------------------------");
        System.out.println(annonces.toString());
    }

    public void removeAnnonce(Annonce annonce) {
        annonces.remove(annonce);
    }

    public String validerPanier() {
        Utilisateur u = beanID.getIdentite();
        for (Annonce a : annonces) {
            annonceFacade.addPostulant(a, u);
            annonceFacade.edit(a);
        }
        return "index.xhtml";
    }

}
