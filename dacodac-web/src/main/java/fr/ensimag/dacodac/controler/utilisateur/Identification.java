/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensimag.dacodac.controler.utilisateur;

import fr.ensimag.dacodac.exceptions.NotConnectedException;
import fr.ensimag.dacodac.Utilisateur;
import fr.ensimag.dacodac.stateless.UtilisateurFacadeLocal;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
    
    public void update() {
        identite = utilisateurFacade.findByPseudo(identite.getPseudo());
    }

    public void setIdentite(String pseudo, String mdp) throws NotConnectedException, NoSuchAlgorithmException {
        Utilisateur u = null;
        u = utilisateurFacade.findByPseudo(pseudo);
        
        mdp = Crypting.crypt(mdp);
        
        if((u != null) && (u.getPassword().equals(mdp)))
        {
            identite = u;
        }
        else
        {
            u = null;
            throw new NotConnectedException();
        }
    }
    
    public void clearIdentite() {
        identite = null;
    }
}
