/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensimag.dacodac.controler.utilisateur;

import fr.ensimag.dacodac.Annonce;
import fr.ensimag.dacodac.stateless.AnnonceFacadeLocal;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author roussena
 */
@Named(value = "modifierAnnonce")
@ManagedBean
@ViewScoped
public class ModifierAnnonce implements Serializable {

    @EJB
    private AnnonceFacadeLocal annonceFacade;

    private Annonce annonce = null;

    @Inject
    private Identification beanID;

    /**
     * Creates a new instance of ModifierAnnonce
     */
    public ModifierAnnonce() {
    }

    public Annonce getAnnonce(long id) {
        if (annonce == null) {
            annonce = annonceFacade.find(id);
        }
        return annonce;
    }

    public String modificationAnnonce() {
        annonceFacade.edit(annonce);
        return "index.html";
    }
}
