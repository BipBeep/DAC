/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensimag.dacodac.controler.utilisateur;

import fr.ensimag.dacodac.Utilisateur;
import fr.ensimag.dacodac.stateless.AnnonceFacadeLocal;
import fr.ensimag.dacodac.stateless.UtilisateurFacadeLocal;
import javax.inject.Named;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author roussena
 */
@Named(value = "consulterProfil")
@RequestScoped
public class ConsulterProfil implements Serializable {

    Utilisateur utilisateur = null;

    @EJB(name = "utilisateurFacade")
    private UtilisateurFacadeLocal utilisateurFacade;

    /**
     * Creates a new instance of ConsulterProfil
     */
    public ConsulterProfil() {
    }

    
    
    public Utilisateur getUtilisateur(String pseudo) {
        if (utilisateur == null) {
            utilisateur = utilisateurFacade.findByPseudo(pseudo);
        }
        return utilisateur;
    }

}
