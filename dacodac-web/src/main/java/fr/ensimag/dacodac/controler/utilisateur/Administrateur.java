/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensimag.dacodac.controler.utilisateur;

import fr.ensimag.dacodac.Annonce;
import fr.ensimag.dacodac.Commentaire;
import fr.ensimag.dacodac.Commentaire_;
import fr.ensimag.dacodac.Utilisateur;
import fr.ensimag.dacodac.stateless.AnnonceFacadeLocal;
import fr.ensimag.dacodac.stateless.CommentaireFacadeLocal;
import fr.ensimag.dacodac.stateless.UtilisateurFacadeLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author weschlel
 */
@Named(value = "administrateur")
@RequestScoped
public class Administrateur {

    @EJB
    private UtilisateurFacadeLocal utilisateurFacade;
    
    @EJB
    private AnnonceFacadeLocal annonceFacade;
    
    @EJB
    private CommentaireFacadeLocal commentaireFacade;
    
    /**
     * Creates a new instance of SupprimerProfil
     */
    public Administrateur() {
    }
    
    public String supprimerUtilisateur(Utilisateur user) {
        System.out.println("---------------------------------");
        System.out.println(user.toString());
        System.out.println("---------------------------------");
        
        
        //remove user de annonpostulant
        List<Annonce> listAnnonce = annonceFacade.findAll();
        for (Annonce a : listAnnonce) {
            if (a.getPostulants().contains(user)) {
                System.out.println("-**** : " + a.getId());
                System.out.println("---HERE : " + user.toString());
                annonceFacade.removePostulant(a, user);
            }
        }

                      System.out.println("----------------------Commentaires ! --------------------");
        //remove les commentaires qui vont etre delete dans la liste des comms des autres user
        List<Utilisateur> listUser = utilisateurFacade.findAll();
        for (Utilisateur u : listUser) {
            List<Commentaire> listComm = u.getCommentaires();
                for (Commentaire c : listComm) {
                    if (c.getAuteur().equals(u)) {
                        System.out.println("[SUPPPPPPPPR]" + u.getPseudo() + "   " + c.getId());
                        commentaireFacade.remove(c);
                    }
                }
        }
        
        
        
        //peut etre vider la liste des postulant de la liste des annonces de user
        // autoban -> deconnection
        utilisateurFacade.remove(user);
        return "index.xhtml";
    }
}
