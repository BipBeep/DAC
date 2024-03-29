/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensimag.dacodac.controler.offresEtDemandes;

import fr.ensimag.dacodac.Annonce;
import fr.ensimag.dacodac.Tag;
import fr.ensimag.dacodac.TypeAnnonce;
import fr.ensimag.dacodac.stateless.AnnonceFacadeLocal;
import fr.ensimag.dacodac.stateless.TagFacadeLocal;
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
@Named(value = "recherche")
@RequestScoped
public class Recherche {

    @EJB
    private AnnonceFacadeLocal annonceFacade;

    @EJB
    private TagFacadeLocal tagFacade;

    String depart;
    String tri;
    String tags;
    private List<Annonce> offres = null;
    private List<Annonce> demandes = null;

    String tab_depart[] = {"ain", "aisne", "allier", "hautes-alpes", "alpes-de-haute-provence", "alpes-maritimes", "ardeche", "ardennes", "ariege", "aube", "aude", "aveyron", "bouches-du-rhone", "calvados", "cantal", "charente", "charente-maritime", "cher", "correze", "corse-du-sud", "haute-corse", "cote-dor", "cotes-darmor", "creuse", "dordogne", "doubs", "drome", "eure", "eure-et-loir", "finistere", "gard", "haute-garonne", "gers", "gironde", "herault", "ile-et-vilaine", "indre", "indre-et-loire", "isere", "jura", "landes", "loir-et-cher", "loire", "haute-loire", "loire-atlantique", "loiret", "lot", "lot-et-garonne", "lozere", "maine-et-loire", "manche", "marne", "haute-marne", "mayenne", "meurthe-et-moselle", "meuse", "morbihan", "moselle", "nievre", "nord", "oise", "orne", "pas-de-calais", "puy-de-dome", "pyrenees-atlantiques", "hautes-pyrenees", "pyrenees-orientales", "bas-rhin", "haut-rhin", "rhone", "haute-saone", "saone-et-loire", "sarthe", "savoie", "haute-savoie", "paris", "seine-maritime", "seine-et-marne", "yvelines", "deux-sevres", "somme", "tarn", "tarn-et-garonne", "var", "vaucluse", "vendee", "vienne", "haute-vienne", "vosges", "yonne", "territoire-de-belfort", "essonne", "hauts-de-seine", "seine-saint-denis", "val-de-marne", "val-doise"};
    private Map<String, String> liste_departs;

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
        for (int i = 1; i < 96; i++) {
            num = Integer.toString(i);
            code = num.concat(" - ");
            num_dep = code.concat(tab_depart[i]);
            liste_departs.put(num_dep, num_dep);
        }
    }

    public String effectuer(String type) {
        TypeAnnonce typeA = TypeAnnonce.OFFRE;
        if (type.equals("Demandes")) {
            typeA = TypeAnnonce.DEMANDE;
        }
        String[] arrayTags = tags.split(" ");
        List<Tag> listTags = new ArrayList<>();

        
        for (String s : arrayTags) {
            Tag t = tagFacade.getTagByName(s);
            if (t != null) {
                listTags.add(t);
            } else {
                // la personne cherche au moins un tag qui n'existe pas, aucune annonce ne correspond
                listTags = new ArrayList<>();
                break;
            }
        }

        if (tags.equals("")) {
            listTags = null;
        }

        //Departement
        String departChoisi = "";
        if (depart != null) {
            departChoisi = depart.split(" ")[0];
        }

        //tri
        Comparator<Annonce> comparateurAnnonce = new Comparator<Annonce>() {
            @Override
            public int compare(Annonce a1, Annonce a2) {
                // si aucun tri sélectionné, prix croissants par défaut
                if (tri == null || tri.equals("prix_croissants")) {
                    if (a1.getPrix() < a2.getPrix()) {
                        return -1;
                    } else if (a1.getPrix() == a2.getPrix()) {
                        return 0;
                    } else {
                        return 1;
                    }
                } else if (tri.equals("prix_decroissants")) {
                    if (a1.getPrix() > a2.getPrix()) {
                        return -1;
                    } else if (a1.getPrix() == a2.getPrix()) {
                        return 0;
                    } else {
                        return 1;
                    }
                } else if (tri.equals("dates_croissantes")) {
                    return -a1.getDatePublication().compareTo(a2.getDatePublication());
                } else if (tri.equals("dates_decroissantes")) {
                    return a1.getDatePublication().compareTo(a2.getDatePublication());
                } else {
                    // never
                    return 0;
                }
            }
        };

        if (type.equals("Demandes")) {
            demandes = annonceFacade.findDemandesByTagsAndDepartement(listTags, departChoisi);
            if (demandes != null) {
                demandes.sort(comparateurAnnonce);
            }
            
            return "demandes.xhtml";
        } else {
            offres = annonceFacade.findOffresByTagsAndDepartement(listTags, departChoisi);
            if (offres != null) {
                offres.sort(comparateurAnnonce);
            }
            return "offres.xhtml";
        }

    }

    public String getDepartement() {
        return depart;
    }

    public void setDepartement(String depart) {
        this.depart = depart;
    }

    public String getTri() {
        return tri;
    }

    public void setTri(String tri) {
        this.tri = tri;
    }

    public Map<String, String> getDepartements() {
        return liste_departs;
    }

    public void setDepartements(Map<String, String> departs) {
        this.liste_departs = departs;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public List<Annonce> getOffres() {
        if (offres == null) {
            offres = ensembleOffres();
        }
        return offres;
    }

    public List<Annonce> ensembleOffres() {
        return annonceFacade.findLatest(5, TypeAnnonce.OFFRE);
    }

    public List<Annonce> getDemandes() {
        if (demandes == null) {
            demandes = ensembleDemandes();
        }
        return demandes;
    }

    public List<Annonce> ensembleDemandes() {
        return annonceFacade.findLatest(5, TypeAnnonce.DEMANDE);
    }

}
