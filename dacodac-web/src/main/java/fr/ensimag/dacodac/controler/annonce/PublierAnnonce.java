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
import fr.ensimag.dacodac.stateless.UtilisateurFacadeLocal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
        System.out.println("----------------------------------------------");
        System.out.println("annonce : " + getAnnonce());
        Utilisateur u = beanID.getIdentite();
        
        TypeAnnonce typeA = TypeAnnonce.DEMANDE;
        if (type.equals("Offre")) {
            typeA = TypeAnnonce.OFFRE;
        }
        annonce.setType(typeA);
        annonce.setDatePublication(LocalDate.now());
        annonce.setAuteur(u);
        annonceFacade.create(getAnnonce());
        String[] arrayTags = tags.split(" ");
        for (String s : arrayTags) {
            annonceFacade.addTag(getAnnonce(), new Tag(s));
        }
        
        annonceFacade.edit(getAnnonce());
        
        utilisateurFacade.addAnnonce(u, getAnnonce());
        utilisateurFacade.edit(u);
        return "index.xhtml";
    }
    
    public String getTags() 
    {
        return tags;
    }
    public void setTags(String tags) 
    {
        this.tags = tags;
    }
    
    public String getType() 
    {
        return type;
    }
    
    public void setType(String type) 
    {
        this.type = type;
    }
    
}
