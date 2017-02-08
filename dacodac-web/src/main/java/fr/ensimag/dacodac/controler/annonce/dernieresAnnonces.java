/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensimag.dacodac.controler.annonce;

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
@Named(value = "dernieresAnnonces")
@RequestScoped
public class dernieresAnnonces {

    @EJB(name = "annonceFacade")
    private AnnonceFacadeLocal annonceFacade;
    
    /**
     * Creates a new instance of Index
     */
    public dernieresAnnonces() {
    }
    

    /* Renvoie les dernières offres */
    public List<Annonce> dernieresOffres() {
        int nbAnnoncesAffichees = 5;
        List<Annonce> list = annonceFacade.findLatest(nbAnnoncesAffichees, TypeAnnonce.OFFRE);
     //   System.out.println("----------------------****----------------------------");
     //   list.stream().forEach(e -> e.getTags().stream().forEach(t -> System.out.println("TAG : " + t)));
    //    System.out.println("----------------------////----------------------------");
        return list;
    }

    /* Renvoie les dernières demandes */
    public List<Annonce> dernieresDemandes() {
        int nbAnnoncesAffichees = 5;
        List<Annonce> list = annonceFacade.findLatest(nbAnnoncesAffichees, TypeAnnonce.DEMANDE);
        return list;
    }
}
