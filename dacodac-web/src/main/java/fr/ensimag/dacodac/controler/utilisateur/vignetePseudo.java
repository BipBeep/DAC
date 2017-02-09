/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensimag.dacodac.controler.utilisateur;

import fr.ensimag.dacodac.Commentaire;
import fr.ensimag.dacodac.Utilisateur;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author kamowskn
 */
@Named(value = "vignetePseudo")
@RequestScoped
public class vignetePseudo {

    /**
     * Creates a new instance of vignetePseudo
     */
    public vignetePseudo() {
    }
    
    public List<Commentaire> dernierCommentaire(Utilisateur u){
        int size = 3;
        if (u.getCommentaires().size()<3){
            size = u.getCommentaires().size();
        }
        System.err.println(u.getCommentaires().size());
        return u.getCommentaires().subList(0, size);
    }
    
}
