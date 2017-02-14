/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensimag.dacodac.controler.utilisateur;

import fr.ensimag.dacodac.Annonce;
import fr.ensimag.dacodac.Commentaire;
import fr.ensimag.dacodac.Tag;
import fr.ensimag.dacodac.TypeAnnonce;
import fr.ensimag.dacodac.stateless.CommentaireFacadeLocal;
import fr.ensimag.dacodac.Utilisateur;
import fr.ensimag.dacodac.stateless.AnnonceFacadeLocal;
import fr.ensimag.dacodac.stateless.TagFacadeLocal;
import fr.ensimag.dacodac.stateless.UtilisateurFacadeLocal;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author weschlel
 */
@Named(value = "administrateur")
@RequestScoped
public class Administrateur {

    @EJB
    private UtilisateurFacadeLocal utilisateurFacade;
    @EJB
    private AnnonceFacadeLocal annonceFacade;
    @EJB
    private CommentaireFacadeLocal commentaireFacade;

    @Inject
    private Identification beanID;

    @Inject
    private Connecteur connecteur;

    @EJB
    private TagFacadeLocal tagFacade;

    private List<Annonce> offres = null;

    private List<Annonce> demandes = null;

    private List<Utilisateur> utilisateurs = null;

    private String tags;

    private String pseudo;

    public Administrateur() {
    }

    public void setIdentification(Identification identification) {
        this.beanID = identification;
    }

    public Identification getIdentification() {
        return beanID;
    }

    public void setConnecteur(Connecteur connecteur) {
        this.connecteur = connecteur;
    }

    public Connecteur getConnecteur() {
        return connecteur;
    }

    public String supprimerUtilisateur(Utilisateur user) {

        List<Annonce> listAnnonce = annonceFacade.findAll();
        for (Annonce a : listAnnonce) {
            if (a.getPostulants().contains(user)) {
                annonceFacade.removePostulant(a, user);
            }
        }

        List<Commentaire> listComm = commentaireFacade.findAll();
        for (Commentaire c : listComm) {
            if (c.getAuteur().equals(user) || c.getDestinataire().equals(user)) {
                commentaireFacade.remove(c);
            }
        }

        List<Commentaire> listCommAuteur = user.getCommentairesAuteur();
        for (Commentaire c : listCommAuteur) {
            c.getDestinataire().getCommentairesDest().remove(c);
            utilisateurFacade.edit(c.getDestinataire());
        }

        List<Commentaire> listCommDest = user.getCommentairesDest();
        for (Commentaire c : listCommDest) {
            c.getAuteur().getCommentairesAuteur().remove(c);
            utilisateurFacade.edit(c.getDestinataire());
        }

        utilisateurFacade.remove(user);

        // Auto-Banned
        if (user.equals(beanID.getIdentite())) {
            return connecteur.disconnect(beanID);
        }

        return "index.xhtml";
    }

    public List<Utilisateur> getUtilisateurs() {
        if (utilisateurs == null) {
            utilisateurs = utilisateurFacade.findAll();
        }
        return utilisateurs;
    }

    public void removeAnnonce(Annonce annonce) {
        if (annonce.getType() == TypeAnnonce.DEMANDE) {
            demandes.remove(annonce);
        } else {
            offres.remove(annonce);
        }
        annonceFacade.remove(annonce);

    }

    /* Renvoie les dernières offres */
    public List<Annonce> getOffres() {
        if (offres == null) {
            offres = annonceFacade.getOffres();
        }
        return offres;

    }

    /* Renvoie les dernières demandes */
    public List<Annonce> getDemandes() {
        if (demandes == null) {
            demandes = annonceFacade.getDemandes();
        }
        return demandes;
    }

    public String effectuer(String type) {
        TypeAnnonce typeA = TypeAnnonce.OFFRE;
        if (type.equals("Demandes")) {
            typeA = TypeAnnonce.DEMANDE;
        }
        String[] arrayTags = getTags().split(" ");
        List<Tag> listTags = new ArrayList<>();

        for (String s : arrayTags) {
            Tag t = tagFacade.getTagByName(s);
            if (t != null) {
                listTags.add(t);
            } else {
                // la personne cherche au moins un tag qui n'existe pas, aucune annonce ne correspond
                listTags = new ArrayList<>();
                break;
            }
        }

        if (getTags().equals("")) {
            listTags = null;
        }

        if (type.equals("Demandes")) {
            demandes = annonceFacade.findDemandesByTagsAndDepartement(listTags, "");
        } else {
            offres = annonceFacade.findOffresByTagsAndDepartement(listTags, "");
        }

        return "administrateur.xhtml";

    }

    public String findByPseudo() {
        Utilisateur u = utilisateurFacade.findByPseudo(pseudo);
        utilisateurs = new ArrayList<>();
        utilisateurs.add(u);
        return "administrateur.xhtml";
    }

    /**
     * @return the tags
     */
    public String getTags() {
        return tags;
    }

    /**
     * @param tags the tags to set
     */
    public void setTags(String tags) {
        this.tags = tags;
    }

    /**
     * @return the pseudo
     */
    public String getPseudo() {
        return pseudo;
    }

    /**
     * @param pseudo the pseudo to set
     */
    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }
}
