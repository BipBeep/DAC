/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensimag.dacodac.controler.utilisateur;

import fr.ensimag.dacodac.Utilisateur;
import fr.ensimag.dacodac.exceptions.NotConnectedException;
import fr.ensimag.dacodac.stateless.UtilisateurFacadeLocal;
import java.security.NoSuchAlgorithmException;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author kamowskn
 */
@Named(value = "connecteur")
@RequestScoped
public class Connecteur {

    private Utilisateur utilisateur = null;

    /**
     * Creates a new instance of Connecteur
     */
    public Connecteur() {
    }

    public Utilisateur getUtilisateur() {
        if (utilisateur == null) {
            utilisateur = new Utilisateur();
        }
        return utilisateur;
    }

    public String connect(Identification idBean) throws NoSuchAlgorithmException {
        try
        {
            idBean.setIdentite(utilisateur.getPseudo(), utilisateur.getPassword());
        }
        catch (NotConnectedException e)
        {
            //rattraper
        }
        return "index.xhtml";
    }

    public String disconnect(Identification idBean) {
        idBean.clearIdentite();
        return "deconnexion.xhtml";
    }

}
