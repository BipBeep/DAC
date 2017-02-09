/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensimag.dacodac.stateless;

import fr.ensimag.dacodac.Tag;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author roussena
 */
@Stateless
public class TagFacade extends AbstractFacade<Tag> implements TagFacadeLocal {

    @PersistenceContext(unitName = "Dacodac_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TagFacade() {
        super(Tag.class);
    }
    
    @Override
    public Tag getTagByName(String nom) {
        List<Tag> liste = getEntityManager().createQuery("Select t FROM Tag t WHERE t.nom = :nom").setParameter("nom", nom).getResultList();
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
