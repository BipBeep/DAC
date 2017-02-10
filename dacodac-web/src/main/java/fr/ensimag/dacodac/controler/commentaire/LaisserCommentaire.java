/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensimag.dacodac.controler.commentaire;

import fr.ensimag.dacodac.Commentaire;
import fr.ensimag.dacodac.Annonce;
import fr.ensimag.dacodac.Utilisateur;
import fr.ensimag.dacodac.controler.utilisateur.Identification;
import fr.ensimag.dacodac.stateless.CommentaireFacadeLocal;
import fr.ensimag.dacodac.stateless.UtilisateurFacadeLocal;
import java.time.LocalDate;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;

/**
 *
 * @author weschlel
 */
@Named(value = "laisserCommentaire")
@Dependent
public class LaisserCommentaire {

    @Inject
    private Identification beanID;
    @EJB
    private UtilisateurFacadeLocal utilisateurFacade;
    @EJB
    private CommentaireFacadeLocal commentaireFacade;

    private String description = "Commentaire ...";
    private Utilisateur destinataire = null;
    private Annonce annonce = null;
    
    public LaisserCommentaire() {
    }

    public String afficherVue(Utilisateur destinataire, Annonce annonce) {
        this.destinataire = destinataire;
        this.annonce = annonce;
        return "laisserCommentaire.xhtml";
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

    public String save() {
        Utilisateur auteur = beanID.getIdentite();

        Commentaire commentaire = new Commentaire(auteur, destinataire, LocalDate.now(), description, annonce.getTitre());
        commentaireFacade.create(commentaire);
        utilisateurFacade.addCommentaire(commentaire);

        return "index.xhtml";
    }

}
