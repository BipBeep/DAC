/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensimag.dacodac.stateless;

import fr.ensimag.dacodac.Annonce;
import fr.ensimag.dacodac.Commentaire;
import fr.ensimag.dacodac.Utilisateur;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author weschlel
 */
@Local
public interface UtilisateurFacadeLocal {

    void create(Utilisateur utilisateur);
    
    void addDakos(Utilisateur u, int dakos);

    void edit(Utilisateur utilisateur);

    void remove(Utilisateur utilisateur);
    
    void addCommentaire(Commentaire commentaire);
       
    void addAnnonce(Utilisateur utilisateur, Annonce annonce);

    Utilisateur find(Object id);
    
    Utilisateur findByPseudo(String pseudo);

    List<Utilisateur> findAll();

    List<Utilisateur> findRange(int[] range);

    int count();
    
}
