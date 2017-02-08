/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensimag.dacodac.controler.index;

import java.util.List;
import fr.ensimag.dacodac.Annonce;
import fr.ensimag.dacodac.TypeAnnonce;
import fr.ensimag.dacodac.stateless.AnnonceFacadeLocal;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.ejb.EJB;

/**
 *
 * @author weschlel
 */
@Named(value = "index")
@RequestScoped
public class Index {

    @EJB(name = "annonceFacade")
    private AnnonceFacadeLocal annonceFacade;
    
    /**
     * Creates a new instance of Index
     */
    public Index() {
    }
    

    /* Renvoie les 3 dernières offres */
    public List<Annonce> dernieresOffres() {
        int nbAnnoncesAffichees = 3;
        return annonceFacade.findLatest(nbAnnoncesAffichees, TypeAnnonce.OFFRE);
    }

    /* Renvoie les 3 dernières demandes */
    public List<Annonce> dernieresDemandes() {
        int nbAnnoncesAffichees = 3;
        return annonceFacade.findLatest(nbAnnoncesAffichees, TypeAnnonce.DEMANDE);
    }
}
