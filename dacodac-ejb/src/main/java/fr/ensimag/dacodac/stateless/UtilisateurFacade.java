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
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author weschlel
 */

@Stateless
public class UtilisateurFacade extends AbstractFacade<Utilisateur> implements UtilisateurFacadeLocal {

    @PersistenceContext(unitName = "Dacodac_PU")
    private EntityManager em;
    

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UtilisateurFacade() {
        super(Utilisateur.class);
    }
    
    @Override
    public Utilisateur findByPseudo(String pseudo)
    {
        List<Utilisateur> liste = getEntityManager().createQuery("SELECT u FROM Utilisateur u WHERE u.pseudo LIKE :pseudo")
                .setParameter("pseudo", pseudo).getResultList();
        if (liste.isEmpty())
        {
            return null;
        }
        else
        {
            return liste.get(0);
        }
    }

    @Override
    public void addCommentaire(Utilisateur utilisateur, Commentaire commentaire) {
        List<Commentaire> commentaires = utilisateur.getCommentaires();
        commentaires.add(commentaire);
        utilisateur.setCommentaires(commentaires);
        edit(utilisateur);
    }

    @Override
    public void addAnnonce(Utilisateur utilisateur, Annonce annonce) {
        List<Annonce> annonces = utilisateur.getAnnonces();
        annonces.add(annonce);
        utilisateur.setAnnonces(annonces);
        edit(utilisateur);
    }
    
}
