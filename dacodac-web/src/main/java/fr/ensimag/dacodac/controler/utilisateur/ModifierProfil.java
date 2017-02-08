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
import javax.enterprise.context.Dependent;

/**
 *
 * @author maubertj
 */
@Named(value = "modifierProfil")
@Dependent
public class ModifierProfil {

    
    @EJB
    private UtilisateurFacadeLocal utilisateurFacade;

    private Utilisateur utilisateur = null;
    
    /**
     * Creates a new instance of ModifierProfil
     */
    public ModifierProfil() {
    }
    
    public String modificationProfil() {
        utilisateurFacade.edit(utilisateur);
        return "index.xhtml";
    }
    
    public Utilisateur getUtilisateur() {
        if (utilisateur == null)
        {
            utilisateur = new Utilisateur();
        }
        return utilisateur;
    }
    
}
