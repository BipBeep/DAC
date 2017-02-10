/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensimag.dacodac.controler.utilisateur;

import fr.ensimag.dacodac.Annonce;
import fr.ensimag.dacodac.TypeAnnonce;
import fr.ensimag.dacodac.Utilisateur;
import fr.ensimag.dacodac.controler.annonce.DernieresAnnonces;
import fr.ensimag.dacodac.stateless.AnnonceFacadeLocal;
import fr.ensimag.dacodac.stateless.UtilisateurFacadeLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

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
    /**
     * Creates a new instance of SupprimerProfil
     */
    public Administrateur() {
    }



    public String supprimerUtilisateur(Utilisateur user) {
        return "index.xhtml";
    }

    public List<Utilisateur> getUtilisateurs() {
        return utilisateurFacade.findAll();
    }

    public void removeAnnonce(Annonce annonce) {
        annonceFacade.remove(annonce);
    }
}
