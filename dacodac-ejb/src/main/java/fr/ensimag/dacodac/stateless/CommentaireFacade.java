/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensimag.dacodac.stateless;

import fr.ensimag.dacodac.Annonce;
import fr.ensimag.dacodac.Commentaire;
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
    public Commentaire findByAnnonce(Annonce annonce)
    {
        return (Commentaire) getEntityManager().createQuery("SELECT c FROM Commentaire c WHERE c.annonce = :annonce")
                .setParameter("annonce", annonce).getResultList().get(0);
    }
    
}
