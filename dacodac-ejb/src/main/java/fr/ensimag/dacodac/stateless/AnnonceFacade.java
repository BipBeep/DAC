/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensimag.dacodac.stateless;

import fr.ensimag.dacodac.Annonce;
import fr.ensimag.dacodac.Commentaire;
import fr.ensimag.dacodac.Utilisateur;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author weschlel
 */
@Stateless
public class AnnonceFacade extends AbstractFacade<Annonce> implements AnnonceFacadeLocal {

    @PersistenceContext(unitName = "Dacodac_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AnnonceFacade() {
        super(Annonce.class);
    }

    @Override
    public void addCommentaire(Annonce annonce, Commentaire com) {
        List<Commentaire> commentaires = annonce.getCommentaires();
        commentaires.add(com);
        annonce.setCommentaires(commentaires);
    }

    @Override
    public void addPostulant(Annonce annonce, Utilisateur utilisateur) {
        List<Utilisateur> postulants = annonce.getPostulants();
        postulants.add(utilisateur);
        annonce.setPostulants(postulants);
    }

    @Override
    public void removePostulant(Annonce annonce, Utilisateur utilisateur) {
        List<Utilisateur> postulants = annonce.getPostulants();
        postulants.remove(utilisateur);
        annonce.setPostulants(postulants);
    }

    @Override
    public void accepterPostulant(Annonce annonce, Utilisateur utilisateur) {
        List<Utilisateur> postulants = annonce.getPostulants();
        
        if (postulants.contains(utilisateur)) {
            postulants = new ArrayList<>();
            postulants.add(utilisateur);
            annonce.setPostulants(postulants);
            annonce.setEstValidee(true);
        } else {
            //L'utilisateur rentré n'est pas dans la liste des postulants.
        }
    }

    @Override
    public Annonce findByUtilAndTitre(Utilisateur u, String titre) {
        return (Annonce) getEntityManager().createQuery("SELECT a FROM Annonce a WHERE a.titre LIKE :titre and a.auteur = :auteur")
                .setParameter("titre", titre).setParameter("auteur", u).getResultList().get(0);
    }
}
