/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensimag.dacodac.controler.publicationAnnonce;

import fr.ensimag.dacodac.Annonce;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author mignotju
 */
@Named(value = "publierAnnonce")
@Dependent
public class publierAnnonce {

    /**
     * Creates a new instance of publierAnnonce
     */
    public publierAnnonce() {
    }
    
    private Annonce annonce;
    
    public Annonce getAnnonce() {
        return annonce;
    }
    
}
