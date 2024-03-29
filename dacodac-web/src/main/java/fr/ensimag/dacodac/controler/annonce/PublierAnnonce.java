/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensimag.dacodac.controler.annonce;

import fr.ensimag.dacodac.Annonce;
import fr.ensimag.dacodac.Tag;
import fr.ensimag.dacodac.TypeAnnonce;
import fr.ensimag.dacodac.Utilisateur;
import fr.ensimag.dacodac.controler.utilisateur.Identification;
import fr.ensimag.dacodac.stateless.AnnonceFacadeLocal;
import fr.ensimag.dacodac.stateless.TagFacadeLocal;
import fr.ensimag.dacodac.stateless.UtilisateurFacadeLocal;
import java.time.LocalDate;


import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author mignotju
 */
@Named(value = "publierAnnonce")
@RequestScoped
public class PublierAnnonce {

    @EJB
    private AnnonceFacadeLocal annonceFacade;

    @EJB
    private UtilisateurFacadeLocal utilisateurFacade;
    
    @EJB
    private TagFacadeLocal tagFacade;

    @Inject
    private Identification beanID;

    /**
     * Creates a new instance of publierAnnonce
     */
    public PublierAnnonce() {
    }

    private Annonce annonce = null; //new Annonce();
    private Utilisateur utilisateur = null; //new Utilisateur();
    private String tags;
    private String type;

    public Annonce getAnnonce() {
        if (annonce == null) {
            annonce = new Annonce();
        }
        return annonce;
    }

    public Utilisateur getUtilisateur() {
        if (utilisateur == null) {
            utilisateur = new Utilisateur();
        }
        return utilisateur;
    }

    public String save() {
        Utilisateur u = beanID.getIdentite();
        if (u == null) {
            //Utilisateur non connecté
            return "connexion.xhtml";
        }
        TypeAnnonce typeA = TypeAnnonce.DEMANDE;
        if (type.equals("Offre")) {
            typeA = TypeAnnonce.OFFRE;
        }
        annonce.setType(typeA);
        annonce.setDatePublication(LocalDate.now());
        annonce.setAuteur(u);
        annonceFacade.create(getAnnonce());
        if (!tags.isEmpty()) {
            String[] arrayTags = tags.split(" ");

            for (String s : arrayTags) {
                Tag t = tagFacade.getTagByName(s);
                if (t == null) {
                    tagFacade.create(new Tag(s));
                }
                Tag tag = tagFacade.getTagByName(s);
                annonceFacade.addTag(getAnnonce(), tag);
            }
        }

        annonceFacade.edit(getAnnonce());

        utilisateurFacade.addAnnonce(u, getAnnonce());
        utilisateurFacade.edit(u);
        return "index.xhtml";
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setIdentification(Identification identification) {
        this.beanID = identification;
    }

    public Identification getIdentification() {
        return beanID;
    }
}
