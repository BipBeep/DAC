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
 * @author duclaur
 */
@Named(value = "creationCompte")
@RequestScoped
public class CreationCompte {

    @EJB
    private UtilisateurFacadeLocal utilisateurFacade;
    
    private Utilisateur newUtil = null;
    
    /**
     * Creates a new instance of CreationCompte
     */
    public CreationCompte() {
    }
    
    public Utilisateur getNewUtil() {
        if (newUtil == null)
        {
            newUtil = new Utilisateur();
        }
        return newUtil;
    }

    public String creerCompte(String testPass)
    {
        /*//tester si le pseudo existe
        if (utilisateurFacade.findByPseudo(newUtil.getPseudo()) != null)
        {
            return "index.xhtml";
        }
        else */
        if (newUtil.getPassword().equals(testPass))
        {
            System.err.println("TU ME FAIS PEUR NETBEANS");
            utilisateurFacade.create(newUtil);
            return "index.xhtml";
        }
        
        System.err.println("TA MERE SUCE DES BITES EN ENFER");
        
        return "index.xhtml";
    }
}
