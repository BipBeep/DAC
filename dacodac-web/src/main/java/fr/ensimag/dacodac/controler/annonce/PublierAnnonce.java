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
import fr.ensimag.dacodac.stateless.AnnonceFacadeLocal;
import fr.ensimag.dacodac.stateless.UtilisateurFacadeLocal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;

/**
 *
 * @author mignotju
 */
@Named(value = "publierAnnonce")
@Dependent
public class PublierAnnonce {

    @EJB
    private AnnonceFacadeLocal annonceFacade;
    
    @EJB
    private UtilisateurFacadeLocal utilisateurFacade;
    
    /**
     * Creates a new instance of publierAnnonce
     */
    public PublierAnnonce() {
    }
    
    private Annonce annonce;
    private Utilisateur utilisateur;
    private String tags;
    
    public Annonce getAnnonce() {
        return annonce;
    }
    
    public String save() {
        annonceFacade.create(annonce);
        String[] arrayTags = tags.split(" ");
        for (String s : arrayTags) {
            annonceFacade.addTag(annonce, new Tag(s));
        }
        annonceFacade.edit(annonce);
        utilisateurFacade.addAnnonce(utilisateur, annonce);
        utilisateurFacade.edit(utilisateur);
        return "index.xhtml";
    }
}
