/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensimag.dacodac.controler.annonce;

import fr.ensimag.dacodac.Annonce;
import fr.ensimag.dacodac.TypeAnnonce;
import fr.ensimag.dacodac.Utilisateur;
import fr.ensimag.dacodac.controler.utilisateur.Identification;
import fr.ensimag.dacodac.stateless.AnnonceFacadeLocal;
import fr.ensimag.dacodac.stateless.CommentaireFacadeLocal;
import fr.ensimag.dacodac.stateless.UtilisateurFacadeLocal;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author weschlel
 */
@Named(value = "annoncePublic")
@ManagedBean
@ViewScoped
public class AnnoncePublic implements Serializable {

    @Inject
    private Identification beanID;

    @EJB
    private AnnonceFacadeLocal annonceFacade;
    @EJB
    private UtilisateurFacadeLocal utilisateurFacade;


    private Annonce annonce = null;


    /**
     * Creates a new instance of AnnoncePublic
     */
    public AnnoncePublic() {
    }

    public void setIdentification(Identification identification) {
        this.beanID = identification;
    }

    public Identification getIdentification() {
        return beanID;
    }

    public Annonce getAnnonce(long id) {
        if (annonce == null) {
            annonce = annonceFacade.find(id);
        }
        return annonce;
    }

    public void validerAnnonce(Utilisateur postulant) {
        annonce.getPostulants().clear();
        annonce.getPostulants().add(postulant);
        annonce.setContracteur(postulant);
        annonce.setEstValidee(true);
        annonceFacade.edit(annonce);
    }

    public void refuserProposition(Utilisateur postulant) {
        annonce.getPostulants().remove(postulant);
        annonceFacade.edit(annonce);
    }

    public String serviceRendu() {
        if (beanID.getIdentite().equals(annonce.getAuteur())) {
            annonce.setServiceRendu_auteur(true);
        } else {
            annonce.setServiceRendu_contracteur(true);
        }
        annonceFacade.edit(annonce);
        if (annonce.getServiceRendu_auteur() && annonce.getServiceRendu_contracteur()) {
            int prix = annonce.getPrix();
            System.err.println("Auteur :");
            System.err.println(annonce.getAuteur());
            System.err.println("Contracteur :");
            System.err.println(annonce.getContracteur());
            if (annonce.getType() == TypeAnnonce.DEMANDE) {
                utilisateurFacade.addDakos(annonce.getAuteur(), -prix);
                utilisateurFacade.addDakos(annonce.getContracteur(), prix);
            } else {
                utilisateurFacade.addDakos(annonce.getAuteur(), prix);
                utilisateurFacade.addDakos(annonce.getContracteur(), -prix);
            }
            beanID.update();
        }
        return "laisserCommentaire.xhtml";
    }

}
