/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensimag.dacodac.controler.annonce;

import fr.ensimag.dacodac.Commentaire;
import fr.ensimag.dacodac.Utilisateur;
import fr.ensimag.dacodac.stateless.UtilisateurFacadeLocal;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author maubertj
 */
@Named(value = "commentaireAnnonce")
@RequestScoped
public class CommentaireAnnonce {

    @EJB
    private UtilisateurFacadeLocal utilisateurFacade;

    private Commentaire commentaire = null;
    private Utilisateur destinataire = null;
    private Utilisateur auteur = null;

    /**
     * Creates a new instance of CommentaireAnnonce
     */
    public CommentaireAnnonce() {
    }

    public String laisserCommentaire() {
        utilisateurFacade.addCommentaire(getCommentaire());
        return "index.xhtml";
    }

    public Commentaire getCommentaire() {
        if (commentaire == null) {
            commentaire = new Commentaire();
        }
        return commentaire;
    }

    public Utilisateur getDestinataire() {
        if (destinataire == null) {
            destinataire = new Utilisateur();
        }
        return destinataire;
    }

    public Utilisateur getAuteur() {
        if (auteur == null) {
            auteur = new Utilisateur();
        }
        return auteur;
    }
}
