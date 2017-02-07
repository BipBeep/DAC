/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensimag.dacodac.controler.offres;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author mignotju
 */
@Named(value = "offres")
@RequestScoped
public class Offres {

    String depart;
    String tri;
    
    String tab_depart[] = {"ain","aisne","allier","hautes-alpes","alpes-de-haute-provence","alpes-maritimes","ardeche","ardennes","ariege","aube","aude","aveyron","bouches-du-rhone","calvados","cantal","charente","charente-maritime","cher","correze","corse-du-sud","haute-corse","cote-dor","cotes-darmor","creuse","dordogne","doubs","drome","eure","eure-et-loir","finistere","gard","haute-garonne","gers","gironde","herault","ile-et-vilaine","indre","indre-et-loire","isere","jura","landes","loir-et-cher","loire","haute-loire","loire-atlantique","loiret","lot","lot-et-garonne","lozere","maine-et-loire","manche","marne","haute-marne","mayenne","meurthe-et-moselle","meuse","morbihan","moselle","nievre","nord","oise","orne","pas-de-calais","puy-de-dome","pyrenees-atlantiques","hautes-pyrenees","pyrenees-orientales","bas-rhin","haut-rhin","rhone","haute-saone","saone-et-loire","sarthe","savoie","haute-savoie","paris","seine-maritime","seine-et-marne","yvelines","deux-sevres","somme","tarn","tarn-et-garonne","var","vaucluse","vendee","vienne","haute-vienne","vosges","yonne","territoire-de-belfort","essonne","hauts-de-seine","seine-saint-denis","val-de-marne","val-doise","mayotte","guadeloupe","guyane","martinique","reunion"};
    private Map<Integer,String> liste_departs;
    
    /**
     * Creates a new instance of Offres
     */
    public Offres() {
        liste_departs = new HashMap<Integer, String>();
        for (int i = 1; i < 101; i++) {
            liste_departs.put(i,tab_depart[i]);
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
    
    public Map<Integer,String> getDepartements() {
        return liste_departs;
    }
    public void setDepartements(Map<Integer,String> departs) {
        this.liste_departs = departs;
    }
    
}
