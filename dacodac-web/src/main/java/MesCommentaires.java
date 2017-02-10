/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import fr.ensimag.dacodac.Commentaire;
import fr.ensimag.dacodac.Utilisateur;
import fr.ensimag.dacodac.controler.utilisateur.Identification;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author roussena
 */
@Named(value = "mesCommentaires")
@RequestScoped
public class MesCommentaires {

    private List<Commentaire> mesCommentaires = null;
    boolean filled = false;
    
    @Inject
    private Identification beanID;
    
    /**
     * Creates a new instance of MesCommentaires
     */
    public MesCommentaires() {
    }
    
    public void setIdentification(Identification identification){
        this.beanID=identification;
    }
    
    public Identification getIdentification(){
        return beanID;
    }
    
    public List<Commentaire> getCommentaires() {
        verifyFilled();
        return mesCommentaires;
    }
    
    private void verifyFilled(){
        if(!filled){
            fill();
            filled=true;
        }
    }
    
    private void fill(){
        mesCommentaires = new ArrayList<>();
        Utilisateur u = beanID.getIdentite();
        mesCommentaires = u.getCommentairesDest();
        for(Commentaire c : mesCommentaires){
            System.err.println("\n \n \n c: "+ c.getId() + "\n \n \n");
        }
    }
}
