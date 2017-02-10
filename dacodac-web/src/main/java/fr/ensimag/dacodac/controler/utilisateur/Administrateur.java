/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensimag.dacodac.controler.utilisateur;

import fr.ensimag.dacodac.Annonce;
import fr.ensimag.dacodac.Commentaire;
import fr.ensimag.dacodac.Utilisateur;
import fr.ensimag.dacodac.stateless.AnnonceFacadeLocal;
import fr.ensimag.dacodac.stateless.CommentaireFacadeLocal;
import fr.ensimag.dacodac.stateless.UtilisateurFacadeLocal;
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

    /**
     * Creates a new instance of SupprimerProfil
     */
    public Administrateur() {
    }

    public void setIdentification(Identification identification) {
        this.beanID = identification;
    }
    
    public Identification getIdentification(){
        return beanID;
    }
    
    public void setConnecteur(Connecteur connecteur) {
        this.connecteur = connecteur;
    }
    
    public Connecteur getConnecteur(){
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

        utilisateurFacade.remove(user);
        
        // Auto-Banned
        if (user.equals(beanID.getIdentite())) {
            return connecteur.disconnect(beanID);
        }

        return "index.xhtml";
    }
}
