/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensimag.dacodac.controler.utilisateur;

import fr.ensimag.dacodac.Utilisateur;
import fr.ensimag.dacodac.stateless.UtilisateurFacadeLocal;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;

/**
 *
 * @author kamowskn
 */
@Named(value = "identification")
@SessionScoped
public class Identification implements Serializable {

    @EJB
    private UtilisateurFacadeLocal utilisateurFacade;

    private Utilisateur identite = null;

    /**
     * Creates a new instance of Identification
     */
    public Identification() {
    }

    public Utilisateur getIdentite() {
        return identite;
    }

    public void setIdentite(String pseudo, String mdp) {
        Utilisateur u = null;
        u = utilisateurFacade.findByPseudo(pseudo);
        if(u==null){
            //Pseudo pas bon
        }
        if(u.getPassword().equals(mdp)){
            //Ok
            identite = u;
            return;
        }
        //MDP pas bon
    }
    
    public void clearIdentite(){
        identite = null;
    }
}
