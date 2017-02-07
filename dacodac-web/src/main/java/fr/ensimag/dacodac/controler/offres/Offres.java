/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensimag.dacodac.controler.offres;

import java.util.LinkedList;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.Dependent;

/**
 *
 * @author mignotju
 */
@Named(value = "offres")
@Dependent
public class Offres {

    List<Departement> departements;
    String depart;
    String tri;
    
    String tab_depart[] = {"ain","aisne","allier","hautes-alpes","alpes-de-haute-provence","alpes-maritimes","ardeche","ardennes","ariege","aube","aude","aveyron","bouches-du-rhone","calvados","cantal","charente","charente-maritime","cher","correze","corse-du-sud","haute-corse","cote-dor","cotes-darmor","creuse","dordogne","doubs","drome","eure","eure-et-loir","finistere","gard","haute-garonne","gers","gironde","herault","ile-et-vilaine","indre","indre-et-loire","isere","jura","landes","loir-et-cher","loire","haute-loire","loire-atlantique","loiret","lot","lot-et-garonne","lozere","maine-et-loire","manche","marne","haute-marne","mayenne","meurthe-et-moselle","meuse","morbihan","moselle","nievre","nord","oise","orne","pas-de-calais","puy-de-dome","pyrenees-atlantiques","hautes-pyrenees","pyrenees-orientales","bas-rhin","haut-rhin","rhone","haute-saone","saone-et-loire","sarthe","savoie","haute-savoie","paris","seine-maritime","seine-et-marne","yvelines","deux-sevres","somme","tarn","tarn-et-garonne","var","vaucluse","vendee","vienne","haute-vienne","vosges","yonne","territoire-de-belfort","essonne","hauts-de-seine","seine-saint-denis","val-de-marne","val-doise","mayotte","guadeloupe","guyane","martinique","reunion"};

    /**
     * Creates a new instance of Offres
     */
    public Offres() {
        departements = new LinkedList<Departement>();
        for (int i = 1; i < 95; i++) {
            Departement d = new Departement(i, tab_depart[i]);
            departements.add(d);
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
    
}
