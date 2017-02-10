/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensimag.dacodac.controler.utilisateur;

import fr.ensimag.dacodac.Annonce;
<<<<<<< HEAD
import fr.ensimag.dacodac.Commentaire;
import fr.ensimag.dacodac.Utilisateur;
import fr.ensimag.dacodac.stateless.AnnonceFacadeLocal;
import fr.ensimag.dacodac.stateless.CommentaireFacadeLocal;
=======
import fr.ensimag.dacodac.TypeAnnonce;
import fr.ensimag.dacodac.Utilisateur;
import fr.ensimag.dacodac.controler.annonce.DernieresAnnonces;
import fr.ensimag.dacodac.stateless.AnnonceFacadeLocal;
>>>>>>> a0309802df3b70d76f3c8d4fa593fbad72e96ecf
import fr.ensimag.dacodac.stateless.UtilisateurFacadeLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
<<<<<<< HEAD
import javax.inject.Inject;
=======
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
>>>>>>> a0309802df3b70d76f3c8d4fa593fbad72e96ecf

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

    public List<Utilisateur> getUtilisateurs() {
        return utilisateurFacade.findAll();
    }

    public void removeAnnonce(Annonce annonce) {
        annonceFacade.remove(annonce);
    }
}
