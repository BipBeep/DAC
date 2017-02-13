/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensimag.dacodac.controler.utilisateur;

import fr.ensimag.dacodac.Annonce;
import fr.ensimag.dacodac.Tag;
import fr.ensimag.dacodac.TypeAnnonce;
import fr.ensimag.dacodac.Utilisateur;
import fr.ensimag.dacodac.stateless.AnnonceFacadeLocal;
import fr.ensimag.dacodac.stateless.TagFacadeLocal;
import fr.ensimag.dacodac.stateless.UtilisateurFacadeLocal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author mignotju
 */
@Named(value = "rechercheUtilisateur")
@RequestScoped
public class RechercheUtilisateur {

    
    @EJB
    private UtilisateurFacadeLocal utilisateurFacade;
    
    private List<Utilisateur> utilisateurs = null;
    
    private String nom;
    

    /**
     * Creates a new instance of Recherche
     */
    public RechercheUtilisateur() {
     
    }
    
    public String getNom(){
        return nom;
    }
    
   public String findByPseudo(){
      Utilisateur u = utilisateurFacade.findByPseudo(nom);
      utilisateurs = new ArrayList<>();
      utilisateurs.add(u);
      return "utilisateurs.xhtml";
   }

    /**
     * @return the utilisateurs
     */
    public List<Utilisateur> getUtilisateurs() {
        if (utilisateurs == null) {
            return utilisateurFacade.findAll();
        }
        return utilisateurs;
    }

    /**
     * @param utilisateurs the utilisateurs to set
     */
    public void setUtilisateurs(List<Utilisateur> utilisateurs) {
        this.utilisateurs = utilisateurs;
    }

}
