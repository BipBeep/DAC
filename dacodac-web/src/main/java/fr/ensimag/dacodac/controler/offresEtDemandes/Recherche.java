/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensimag.dacodac.controler.offresEtDemandes;

import fr.ensimag.dacodac.stateless.AnnonceFacadeLocal;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author mignotju
 */
@Named(value = "recherche")
@RequestScoped
public class Recherche {
    
    @EJB
    private AnnonceFacadeLocal annonceFacade;

    String depart;
    String tri;
    
    String tab_depart[] = {"ain","aisne","allier","hautes-alpes","alpes-de-haute-provence","alpes-maritimes","ardeche","ardennes","ariege","aube","aude","aveyron","bouches-du-rhone","calvados","cantal","charente","charente-maritime","cher","correze","corse-du-sud","haute-corse","cote-dor","cotes-darmor","creuse","dordogne","doubs","drome","eure","eure-et-loir","finistere","gard","haute-garonne","gers","gironde","herault","ile-et-vilaine","indre","indre-et-loire","isere","jura","landes","loir-et-cher","loire","haute-loire","loire-atlantique","loiret","lot","lot-et-garonne","lozere","maine-et-loire","manche","marne","haute-marne","mayenne","meurthe-et-moselle","meuse","morbihan","moselle","nievre","nord","oise","orne","pas-de-calais","puy-de-dome","pyrenees-atlantiques","hautes-pyrenees","pyrenees-orientales","bas-rhin","haut-rhin","rhone","haute-saone","saone-et-loire","sarthe","savoie","haute-savoie","paris","seine-maritime","seine-et-marne","yvelines","deux-sevres","somme","tarn","tarn-et-garonne","var","vaucluse","vendee","vienne","haute-vienne","vosges","yonne","territoire-de-belfort","essonne","hauts-de-seine","seine-saint-denis","val-de-marne","val-doise","mayotte","guadeloupe","guyane","martinique","reunion"};
    private Map<String,String> liste_departs;
    
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
    public Recherche() {
        //Création de la liste des départements
        liste_departs = new TreeMap<String, String>(keyComparator);
        String num, code, num_dep;
        for (int i = 1; i < 101; i++) {
            num = Integer.toString(i);
            code = num.concat(" - ");
            num_dep = code.concat(tab_depart[i]);
            liste_departs.put(num_dep,num_dep);
        }
    }
        
    
    public String getDepartement() {
        return depart;
    }
    public void setDepartement(String depart ) {
        this.depart = depart;
    }
    
    public String getTri() {
        return tri;
    }
    public void setTri(String tri) {
        this.tri = tri;
    }
    
    public Map<String,String> getDepartements() {
        return liste_departs;
    }
    public void setDepartements(Map<String,String> departs) {
        this.liste_departs = departs;
    }
    
    public void effectuer() {
        
    }
}
