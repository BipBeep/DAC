/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensimag.dacodac.stateless;

import fr.ensimag.dacodac.Annonce;
import fr.ensimag.dacodac.Commentaire;
import fr.ensimag.dacodac.Tag;
import fr.ensimag.dacodac.Utilisateur;
import fr.ensimag.dacodac.TypeAnnonce;

import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author weschlel
 */
@Local
public interface AnnonceFacadeLocal {

    void create(Annonce annonce);

    void edit(Annonce annonce);

    void remove(Annonce annonce);

    void addCommentaire(Annonce annonce, Commentaire com);

    Annonce findByUtilAndTitre(Utilisateur u, String titre);

    List<Annonce> findLatest(int nbAnnoncesAffichees, TypeAnnonce type);

    void serviceRendu(boolean realise, Annonce annonce, Utilisateur utilisateur);

    void addPostulant(Annonce annonce, Utilisateur utilisateur);

    void addTag(Annonce annonce, Tag tag);

    void removePostulant(Annonce annonce, Utilisateur utilisateur);

    Annonce find(Object id);

    void accepterPostulant(Annonce annonce, Utilisateur utilisateur);

    List<Annonce> findAll();
    
    List<Annonce> findByTag(Tag tag);

    List<Annonce> findRange(int[] range);

    int count();

}
