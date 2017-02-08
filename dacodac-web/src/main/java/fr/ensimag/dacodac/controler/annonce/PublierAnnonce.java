/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensimag.dacodac.controler.annonce;

import fr.ensimag.dacodac.Annonce;
import fr.ensimag.dacodac.TypeAnnonce;
import fr.ensimag.dacodac.Utilisateur;
import fr.ensimag.dacodac.stateless.AnnonceFacadeLocal;
import fr.ensimag.dacodac.stateless.UtilisateurFacadeLocal;
import java.time.LocalDate;
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
    
    public Annonce getAnnonce() {
        return annonce;
    }
    
    public String save(String descr, String tags, String titre, String type, String prix, String codePostal) {
        int prixI = Integer.parseInt(prix);
        TypeAnnonce typeA = TypeAnnonce.DEMANDE;
        if (type.equals("Offre")) {
            typeA = TypeAnnonce.OFFRE;
        }        
        
        Utilisateur user = utilisateurFacade.findByPseudo("Nico");
        
        Annonce a = new Annonce(prixI, typeA, user, codePostal, descr, titre, LocalDate.now());
        
        annonceFacade.create(a);
        //if not connected, renvoie vers connexion.xhtml
        //récupérer utilisateur via fonction de javaee security
        utilisateurFacade.addAnnonce(user, a);
        return "index.xhtml";
    }
}
