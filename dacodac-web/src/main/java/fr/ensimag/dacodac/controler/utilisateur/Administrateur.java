/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensimag.dacodac.controler.utilisateur;

import fr.ensimag.dacodac.Utilisateur;
import fr.ensimag.dacodac.stateless.UtilisateurFacadeLocal;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author weschlel
 */
@Named(value = "administrateur")
@RequestScoped
public class Administrateur {

    @EJB
    private UtilisateurFacadeLocal utilisateurFacade;
    
    /**
     * Creates a new instance of SupprimerProfil
     */
    public Administrateur() {
    }
    
    public String supprimerUtilisateur(Utilisateur user) {
        return "index.xhtml";
    }
}
