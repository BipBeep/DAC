/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensimag.dacodac.controler.commentaire;

import fr.ensimag.dacodac.Commentaire;
import fr.ensimag.dacodac.Annonce;
import fr.ensimag.dacodac.TypeAnnonce;
import fr.ensimag.dacodac.Utilisateur;
import fr.ensimag.dacodac.controler.utilisateur.Identification;
import fr.ensimag.dacodac.stateless.AnnonceFacadeLocal;
import fr.ensimag.dacodac.stateless.CommentaireFacadeLocal;
import fr.ensimag.dacodac.stateless.UtilisateurFacadeLocal;
import java.time.LocalDate;
import javax.annotation.PostConstruct;
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
    @EJB
    private AnnonceFacadeLocal annonceFacade;

    private String description = "Commentaire ...";
    private Utilisateur destinataire = null;
    private Annonce annonce = null;

    public LaisserCommentaire() {

    }

    public void setIdentification(Identification identification) {
        this.beanID = identification;
    }

    public Identification getIdentification() {
        return beanID;
    }

    /*public void setDescription(String description) {
        this.description = description;
    }*/
    public String getDescription() {
        return description;
    }

    public Utilisateur getDestinataire(String pseudo) {
        if (destinataire == null) {
            destinataire = utilisateurFacade.findByPseudo(pseudo);
        }
        return destinataire;
    }

    public Annonce getAnnonce(long id) {
        if (annonce == null) {
            annonce = annonceFacade.find(id);
        }
        return annonce;
    }

    public String save(long id, String description, String pseudoDest) {
        Utilisateur auteur = beanID.getIdentite();
        Utilisateur destCom = null;
        Annonce annonceCour = getAnnonce(id);

        System.err.println("auteurAnnonce : ");
        System.err.println(annonceCour.getAuteur());
        System.err.println("auteurCom : ");
        System.err.println(auteur.toString());
        System.err.println("--------------------------------------------");

        if (!auteur.equals(getAnnonce(id).getAuteur())) {
            destCom = getAnnonce(id).getAuteur();
        } else {
            destCom = utilisateurFacade.findByPseudo(pseudoDest);
        }

        System.err.println("auteurCom : ");
        System.err.println(auteur.toString());
        System.err.println("dest : ");
        System.err.println(destCom.toString());
        System.err.println("description : " + description);
        System.err.println("titre : " + annonceCour.getTitre());

        Commentaire commentaire = new Commentaire(auteur, destCom, LocalDate.now(), description, annonceCour.getTitre());
        commentaireFacade.create(commentaire);
        utilisateurFacade.addCommentaire(commentaire);
        validerannonce();
        if (annonceCour.getServiceRendu_auteur() && annonceCour.getServiceRendu_contracteur()) {
            annonceFacade.remove(annonceCour);
        }

        return "index.xhtml";
    }

    public void validerannonce() {
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
    }

    public String retourneAccueil(long id) {
        Annonce annonceCour = getAnnonce(id);
        validerannonce();
        if (annonceCour.getServiceRendu_auteur() && annonceCour.getServiceRendu_contracteur()) {
            annonceFacade.remove(annonceCour);
        }

        return "index.xhtml";
    }

}
