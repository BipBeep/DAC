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
public interface CommentaireFacadeLocal {

    void create(Commentaire commentaire);

    void edit(Commentaire commentaire);

    void remove(Commentaire commentaire);

    Commentaire find(Object id);

    List<Commentaire> findAll();

    List<Commentaire> findRange(int[] range);

    int count();
}
