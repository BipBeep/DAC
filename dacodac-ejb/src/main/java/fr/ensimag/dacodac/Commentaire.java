/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensimag.dacodac;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

/**
 *
 * @author weschlel
 */
@Entity
public class Commentaire implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Utilisateur auteur;
    private LocalDateTime dateCreation;
    private String titreAnnonce;
    private String description;

    public Commentaire() {
    }
    
    public Commentaire(Utilisateur auteur, LocalDateTime dateCreation, String titreAnnonce, String description) {
        this.auteur = auteur;
        this.dateCreation = dateCreation;
        this.titreAnnonce = titreAnnonce;
        this.description = description;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Utilisateur getAuteur() {
        return auteur;
    }

    public LocalDateTime getDateCreation() {
        return dateCreation;
    }

    public String getTitreAnnonce() {
        return titreAnnonce;
    }

    public String getDescription() {
        return description;
    }
    
    public void setAuteur(Utilisateur auteur) {
        this.auteur = auteur;
    }

    public void setDateCreation(LocalDateTime dateCreation) {
        this.dateCreation = dateCreation;
    }

    public void setTitreAnnonce(String titreAnnonce) {
        this.titreAnnonce = titreAnnonce;
    }

    public void setDescription(String description) {
        this.description = description;
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
        if (!(object instanceof Commentaire)) {
            return false;
        }
        Commentaire other = (Commentaire) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Commentaire[id=" + id + ", ID-Auteur=" + auteur.getId() + ", DateCreation=" + dateCreation + ", TitreAnnonce=" + titreAnnonce + "]";
    }
}
