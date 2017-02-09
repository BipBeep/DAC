/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensimag.dacodac.controler.annonce;

import fr.ensimag.dacodac.Annonce;
import fr.ensimag.dacodac.stateless.AnnonceFacadeLocal;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author kamowskn
 */
@Named(value = "offres")
@RequestScoped
public class Offres {

    @EJB
    private AnnonceFacadeLocal annonceFacade;

    private List<Annonce> offres = null;
    
    
    
    /**
     * Creates a new instance of Offres
     */
    public Offres() {
    }
    
    public List<Annonce> getOffres(){
        if(offres==null){
            offres = ensembleOffres();
        }
        return offres;
    }
    public List<Annonce> ensembleOffres() {
        return annonceFacade.getOffres();
    }
    
}
