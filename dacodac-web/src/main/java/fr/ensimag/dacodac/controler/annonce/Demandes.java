/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensimag.dacodac.controler.annonce;

import fr.ensimag.dacodac.Annonce;
import fr.ensimag.dacodac.TypeAnnonce;
import fr.ensimag.dacodac.stateless.AnnonceFacadeLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;

/**
 *
 * @author kamowskn
 */
@Named(value = "demandes")
@Dependent
public class Demandes {

    @EJB
    private AnnonceFacadeLocal annonceFacade;

    private List<Annonce> demandes = null;
    
    
    
    /**
     * Creates a new instance of Offres
     */
    public Demandes() {
    }
    
    public List<Annonce> getDemandes(){
        if(demandes==null){
            demandes = ensembleDemandes();
        }
        return demandes;
    }
    public List<Annonce> ensembleDemandes() {
        return annonceFacade.findLatest(5, TypeAnnonce.DEMANDE);
    }
    
}
