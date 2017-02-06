/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensimag.dacodac.stateless;

import fr.ensimag.dacodac.Utilisateur;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NamedQuery;
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
    public Utilisateur findByName(String nom)
    {
        return (Utilisateur) getEntityManager().createQuery("SELECT u FROM Utilisateur u WHERE u.nom LIKE :nom").setParameter("nom", nom).getResultList().get(0);
    }
    
}
