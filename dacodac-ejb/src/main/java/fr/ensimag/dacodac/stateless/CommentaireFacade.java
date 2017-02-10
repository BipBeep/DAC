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
public class CommentaireFacade extends AbstractFacade<Commentaire> implements CommentaireFacadeLocal {

    @PersistenceContext(unitName = "Dacodac_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CommentaireFacade() {
        super(Commentaire.class);
    }
    
    @Override
    public List<Commentaire> findByAnnonce(Annonce annonce)
    {
        return getEntityManager().createQuery("SELECT c FROM Commentaire c WHERE c.annonce = :annonce")
                .setParameter("annonce", annonce).getResultList();
    }
    
    @Override
    public List<Commentaire> findByAuteur(Utilisateur utilisateur)
    {
        return getEntityManager().createQuery("SELECT c FROM Commentaire c WHERE c.auteur= :utilisateur")
                .setParameter("utilisateur", utilisateur).getResultList();
    }
    
     @Override
    public Commentaire findByAuteurAndAnnonce(Utilisateur utilisateur, Annonce annonce)
    {
        List<Commentaire> liste = getEntityManager().createQuery("SELECT c FROM Commentaire c WHERE c.auteur= :utilisateur and c.annonce = :annonce")
                .setParameter("utilisateur", utilisateur).setParameter("annonce", annonce).getResultList();
        if (liste.isEmpty())
        {
            return null;
        }
        else
        {
            return liste.get(0);
        }
    }
}
