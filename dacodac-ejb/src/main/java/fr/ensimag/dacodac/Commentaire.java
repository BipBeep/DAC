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
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.validation.constraints.Size;

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
    
    @ManyToOne
    @JoinColumn(nullable=false)
    private Utilisateur auteur;
    
    @Column(nullable=false)
    private LocalDateTime dateCreation;
    
    @ManyToOne
    @JoinColumn(nullable=false)
    private Annonce annonce;
    
    @Column(nullable=false)
    @Size(min=1, max=1023)
    private String description;

    public Commentaire() {
    }
    
    public Commentaire(Utilisateur auteur, LocalDateTime dateCreation, Annonce annonce, String description) {
        this.auteur = auteur;
        this.dateCreation = dateCreation;
        this.annonce = annonce;
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

    public Annonce getAnnonce() {
        return annonce;
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

    public void setAnnonce(Annonce annonce) {
        this.annonce = annonce;
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
        return "Commentaire[id=" + id + ", ID-Auteur=" + auteur.getId() + ", DateCreation=" + dateCreation + ", TitreAnnonce=" + annonce.getTitre() + "]";
    }
}
