/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensimag.dacodac.controler.annonce;

import fr.ensimag.dacodac.Annonce;
import fr.ensimag.dacodac.Commentaire;
import fr.ensimag.dacodac.TypeAnnonce;
import fr.ensimag.dacodac.Utilisateur;
import fr.ensimag.dacodac.controler.commentaire.LaisserCommentaire;
import fr.ensimag.dacodac.controler.utilisateur.Identification;
import fr.ensimag.dacodac.stateless.AnnonceFacadeLocal;
import fr.ensimag.dacodac.stateless.CommentaireFacadeLocal;
import fr.ensimag.dacodac.stateless.UtilisateurFacadeLocal;
import java.io.Serializable;
import java.time.LocalDate;
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
    @EJB
    private CommentaireFacadeLocal commentaireFacade;

    private Utilisateur destinataire = null;
    private Annonce annonce = null;

    private String description = "Commentaire ...";

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

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDestinataire(Utilisateur destinataire) {
        this.destinataire = destinataire;
    }

    public Utilisateur getDestinataire() {
        return destinataire;
    }

    public void setAnnonce(Annonce annonce) {
        this.annonce = annonce;
    }

    public Annonce getAnnonce() {
        return annonce;
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
            destinataire = annonce.getAuteur();
        } else {
            annonce.setServiceRendu_contracteur(true);
            destinataire = annonce.getPostulants().get(0);
        }
        annonceFacade.edit(annonce);
        if (annonce.getServiceRendu_auteur() && annonce.getServiceRendu_contracteur()) {
            int prix = annonce.getPrix();
            if (annonce.getType() == TypeAnnonce.DEMANDE) {
                annonce.getAuteur().setDakos(annonce.getAuteur().getDakos() - prix);
                annonce.getPostulants().get(0).setDakos(annonce.getPostulants().get(0).getDakos() + prix);
            } else {
                annonce.getAuteur().setDakos(annonce.getAuteur().getDakos() + prix);
                annonce.getPostulants().get(0).setDakos(annonce.getPostulants().get(0).getDakos() - prix);
            }
            utilisateurFacade.edit(annonce.getAuteur());
            utilisateurFacade.edit(annonce.getPostulants().get(0));
            annonceFacade.remove(annonce);
        }
        return "laisserCommentaire.xhtml";
    }

    public String save() {
        Utilisateur auteur = beanID.getIdentite();

        Commentaire commentaire = new Commentaire(auteur, destinataire, LocalDate.now(), description, annonce.getTitre());
        commentaireFacade.create(commentaire);
        utilisateurFacade.addCommentaire(commentaire);

        return "index.xhtml";
    }

}
