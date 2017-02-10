/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensimag.dacodac.controler.utilisateur;

import fr.ensimag.dacodac.Annonce;
import fr.ensimag.dacodac.TypeAnnonce;
import fr.ensimag.dacodac.Utilisateur;
import fr.ensimag.dacodac.stateless.AnnonceFacadeLocal;
import java.io.Serializable;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
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
    private int prixTotal = 0;

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

    public List<Annonce> getOffres() {
        List<Annonce> list = new LinkedList<>();
        for (Annonce a : annonces) {
            if (a.getType() == TypeAnnonce.OFFRE) {
                list.add(a);
            }
        }
        return list;
    }

    public List<Annonce> getDemandes() {
        List<Annonce> list = new LinkedList<>();
        for (Annonce a : annonces) {
            if (a.getType() == TypeAnnonce.DEMANDE) {
                list.add(a);
            }
        }
        return list;
    }

    public void addAnnonce(Annonce annonce) {
        System.out.println("-------------------------------------------------");
        System.err.println(annonce);
        String msg;
        if (annonces.add(annonce)) {
            if (annonce.getType() == TypeAnnonce.OFFRE) {
                prixTotal += annonce.getPrix();
            }
            msg = "Cette annonce a été ajouté à votre panier";
        } else {
            msg = "Cette annonce est déjà présente dans votre panier";
        }

        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void test() {
        String msg="ca marche";

        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    public void removeAnnonce(Annonce annonce) {
        System.err.println("On rentre dans remove Annonce");
        String msg;
        if (annonces.remove(annonce)) {
            if (annonce.getType() == TypeAnnonce.OFFRE) {
                prixTotal -= annonce.getPrix();
            }
            msg = "Cette annonce a été supprimée de votre panier";
        } else {
            msg = "[ERROR] Suppression invalide !";
        }

        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
        //return "monPanier.xhtml";
    }

    public String validerPanier() throws InterruptedException {
        Utilisateur u = beanID.getIdentite();
        if (u == null) {
            //Utilisateur non connecté
            return "connexion.xhtml";
        } else if (u.getDakos() < prixTotal) {
            String msg = "Vous ne possédez pas assez de Dakos !";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
            return null;
        } else {
            for (Annonce a : annonces) {
                if (!a.getPostulants().contains(u)) {
                    annonceFacade.addPostulant(a, u);
                    annonceFacade.edit(a);
                }
            }
            annonces.clear();
            prixTotal = 0;
            /*String msg = "Votre panier a été validé";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);*/
            return "index.xhtml";
        }
    }

    public int getPrixTotal() {
        return prixTotal;
    }
}
