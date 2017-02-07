/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensimag.dacodac;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Date;


/**
 *
 * @author duclaur
 */
@Entity
public class Annonce implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int prix;    
    private TypeAnnonce type;

    //@Temporal(TemporalType.TIMESTAMP)
    //NEED HELP
    private LocalDateTime datePublication;
    
    @ManyToOne
    private Utilisateur auteur;
    
    @ManyToMany
    private List<Utilisateur> postulants;
    
    @OneToMany
    private List<Commentaire> commentaires;
    
    private int codePostal;
    private String description;
    private String titre;

    public Annonce() {}

    public Annonce(int prix, TypeAnnonce type, Utilisateur auteur, int codePostal, String description, String titre, LocalDateTime datePublication) {
        this.prix = prix;
        this.type = type;
        this.auteur = auteur;
        this.codePostal = codePostal;
        this.description = description;
        this.titre = titre;
        this.datePublication = datePublication;
        
        postulants = new ArrayList<>();
        commentaires = new ArrayList<>();
    }
    
    
    /**
     * Get the value of type
     *
     * @return the value of type
     */
    public TypeAnnonce getType() {
        return type;
    }

    /**
     * Set the value of type
     *
     * @param type new value of type
     */
    public void setType(TypeAnnonce type) {
        this.type = type;
    }
    
    /**
     * Get the value of titre
     *
     * @return the value of titre
     */
    public String getTitre() {
        return titre;
    }

    /**
     * Set the value of titre
     *
     * @param titre new value of titre
     */
    public void setTitre(String titre) {
        this.titre = titre;
    }


    /**
     * Get the value of description
     *
     * @return the value of description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set the value of description
     *
     * @param description new value of description
     */
    public void setDescription(String description) {
        this.description = description;
    }


    /**
     * Get the value of codePostal
     *
     * @return the value of codePostal
     */
    public int getCodePostal() {
        return codePostal;
    }

    /**
     * Set the value of codePostal
     *
     * @param codePostal new value of codePostal
     */
    public void setCodePostal(int codePostal) {
        this.codePostal = codePostal;
    }

    

    /**
     * Get the value of datePublication
     *
     * @return the value of datePublication
     */
    public LocalDateTime getDatePublication() {
        return datePublication;
    }

    /**
     * Set the value of datePublication
     *
     * @param datePublication new value of datePublication
     */
    public void setDatePublication(LocalDateTime datePublication) {
        this.datePublication = datePublication;
    }


    /**
     * Get the value of postulants
     *
     * @return the value of postulants
     */
    public List<Utilisateur> getPostulants() {
        return postulants;
    }

    /**
     * Set the value of postulants
     *
     * @param postulants new value of postulants
     */
    public void setPostulants(List<Utilisateur> postulants) {
        this.postulants = postulants;
    }
    
    /**
     * Get the value of commentaires
     *
     * @return the value of commentaires
     */
    public List<Commentaire> getCommentaires() {
        return commentaires;
    }

    /**
     * Set the value of commentaires
     *
     * @param commentaires new value of commentaires
     */
    public void setCommentaires(List<Commentaire> commentaires) {
        this.commentaires = commentaires;
    }


    /**
     * Get the value of auteur
     *
     * @return the value of auteur
     */
    public Utilisateur getAuteur() {
        return auteur;
    }

    /**
     * Set the value of auteur
     *
     * @param auteur new value of auteur
     */
    public void setAuteur(Utilisateur auteur) {
        this.auteur = auteur;
    }


    /**
     * Get the value of prix
     *
     * @return the value of prix
     */
    public int getPrix() {
        return prix;
    }

    /**
     * Set the value of prix
     *
     * @param prix new value of prix
     */
    public void setPrix(int prix) {
        this.prix = prix;
    }

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Annonce)) {
            return false;
        }
        Annonce other = (Annonce) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fr.ensimag.dacodac.Annonce[ id=" + id + " ]";
    }
    
}
