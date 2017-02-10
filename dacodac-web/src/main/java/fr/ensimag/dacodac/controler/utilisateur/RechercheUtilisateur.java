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
    
    private String nom;
    
    Comparator<String> keyComparator = new Comparator<String>() {
        @Override
        public int compare(String s1, String s2) {
            String[] sInt1 = s1.split(" ");
            String[] sInt2 = s2.split(" ");
            int int1 = Integer.parseInt(sInt1[0]);
            int int2 = Integer.parseInt(sInt2[0]);
            if (int1 < int2) {
                return -1;
            } else if (int1 == int2) {
                return 0;
            } else {
                return 1;
            }
        }
    };

    /**
     * Creates a new instance of Recherche
     */
    public RechercheUtilisateur() {
        //Création de la liste des départements
        //liste_departs = new TreeMap<String, String>(keyComparator);
     
    }
    
    public String getNom(){
        return nom;
    }
    
   Utilisateur findByPseudo(){
      return utilisateurFacade.findByPseudo(nom);
   }

}
