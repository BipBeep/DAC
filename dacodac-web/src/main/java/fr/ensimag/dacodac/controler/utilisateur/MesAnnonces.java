/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensimag.dacodac.controler.utilisateur;

import fr.ensimag.dacodac.Annonce;
import fr.ensimag.dacodac.TypeAnnonce;
import fr.ensimag.dacodac.Utilisateur;
import fr.ensimag.dacodac.stateless.AnnonceFacadeLocal;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedProperty;
import javax.inject.Inject;

/**
 *
 * @author kamowskn
 */
@Named(value = "mesAnnonces")
@RequestScoped
public class MesAnnonces {

    private List<Annonce> offres_soumises = null;
    private List<Annonce> demandes_soumises = null;
    private List<Annonce> offres_repondues = null;
    private List<Annonce> demandes_repondues = null;
    private boolean filled = false;
    
    @EJB(name = "annonceFacade")
    private AnnonceFacadeLocal annonceFacade;

    @Inject
    private Identification beanID;

    /**
     * Creates a new instance of MesAnnonces
     */
    public MesAnnonces() {
        
    }

    public void setIdentification(Identification identification){
        this.beanID=identification;
    }
    
    public Identification getIdentification(){
        return beanID;
    }
    
    public List<Annonce> getOffresSoumises() {
        verifyFilled();
        return offres_soumises;
    }

    public List<Annonce> getOffresRepondues() {
        verifyFilled();
        return offres_repondues;
    }

    public List<Annonce> getDemandesSoumises() {
        verifyFilled();
        return demandes_soumises;
    }

    public List<Annonce> getDemandesRepondues() {
        verifyFilled();
        return demandes_repondues;
    }
    
    private void verifyFilled(){
        if(!filled){
            fill();
            filled=true;
        }
    }
    
    private void fill(){
        offres_soumises = new ArrayList<>();
        demandes_soumises = new ArrayList<>();
        offres_repondues = new ArrayList<>();
        demandes_repondues = new ArrayList<>();
        List<Annonce> allAnonces, annoncesPostees;
        allAnonces = annonceFacade.findAll();
        Utilisateur u = beanID.getIdentite();
        annoncesPostees = u.getAnnonces();
        System.err.println("Size: " + allAnonces.size()+", user: " + u.getPseudo() +", post: "+ annoncesPostees.size());
        for (Annonce a : allAnonces) {
            if (annoncesPostees.contains(a)) {
                if (a.getType().equals(TypeAnnonce.DEMANDE)) {
                    demandes_soumises.add(a);
                } else {
                    offres_soumises.add(a);
                }
            } else if (a.getPostulants().contains(u)) {
                if (a.getType().equals(TypeAnnonce.DEMANDE)) {
                    demandes_repondues.add(a);
                } else {
                    offres_repondues.add(a);
                }
            } else {
                System.err.println("nope");
            }

        }
        for(Annonce a: annoncesPostees){
            System.err.println("a: "+ a.getId());
        }
    }

}
