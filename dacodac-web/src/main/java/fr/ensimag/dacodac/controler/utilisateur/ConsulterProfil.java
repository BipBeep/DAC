/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensimag.dacodac.controler.utilisateur;

import javax.inject.Named;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author roussena
 */
@Named(value = "consulterProfil")
@RequestScoped
public class ConsulterProfil implements Serializable {

    @Inject
    private Identification beanID;
    
    /**
     * Creates a new instance of ConsulterProfil
     */
    public ConsulterProfil() {
    }
    
    public void setIdentification(Identification identification){
        this.beanID=identification;
    }
    
    public Identification getIdentification(){
        return beanID;
    }
    
    public String getPseudoUtilisateur() {
        return beanID.getIdentite().getPseudo();
    }
    
    public String getEmailUtilisateur() {
        return beanID.getIdentite().getEmail();
    }
    
    public String getDescriptionUtilisateur() {
        return beanID.getIdentite().getDescription();
    }
    
    public String getCodePostalUtilisateur() {
        return beanID.getIdentite().getCodePostal();
    }
        
    public String getAgeUtilisateur() {
        int age =  beanID.getIdentite().getAge();
        return String.valueOf(age);
    }
}
