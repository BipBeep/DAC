/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensimag.dacodac.controler.annonce;

import fr.ensimag.dacodac.Annonce;
import fr.ensimag.dacodac.Utilisateur;
import fr.ensimag.dacodac.stateless.AnnonceFacadeLocal;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author weschlel
 */
@Named(value = "annoncePublic")
@RequestScoped
public class AnnoncePublic {

    
    @EJB
    private AnnonceFacadeLocal annonceFacade;
    
    private Annonce annonce = null;
    
    /**
     * Creates a new instance of AnnoncePublic
     */
    public AnnoncePublic() {
    }
   
    public Annonce getAnnonce(long id) {
        if (annonce == null) {
            annonce = annonceFacade.find(id);
        }
        return annonce;
    }
    
    public void validerAnnonce(){
        System.err.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%OK");
    }
    
    public String refuserProposition(Utilisateur postulant){
        System.err.println("NON");
        annonce.getPostulants().remove(postulant);
        annonceFacade.edit(annonce);
        return null;
    }
    
    public void hello(){
        System.err.println("hello");
        System.err.println("hello");
        System.err.println("hello");
        System.err.println("hello");
        System.err.println("hello");
        System.err.println("hello");
        System.err.println("hello");
        System.err.println("hello");
        
    }
    
    
    
}
