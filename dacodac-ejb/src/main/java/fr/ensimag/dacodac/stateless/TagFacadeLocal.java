/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensimag.dacodac.stateless;

import fr.ensimag.dacodac.Tag;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author roussena
 */
@Local
public interface TagFacadeLocal {

    void create(Tag tag);

    void edit(Tag tag);

    void remove(Tag tag);

    Tag find(Object id);

    List<Tag> findAll();

    List<Tag> findRange(int[] range);
    
    Tag getTagByName(String nom);

    int count();
    
}
