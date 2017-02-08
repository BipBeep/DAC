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
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

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

    @Min(0)
    @Column(nullable = false)
    private int prix;

    @Column(nullable = false)
    private TypeAnnonce type;

    //@Temporal(TemporalType.TIMESTAMP)
    //NEED HELP
    private LocalDateTime datePublication;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Utilisateur auteur;

    @Pattern(regexp = "^(0[1-9]|[1-9][0-9])[0-9]{3}$")
    @Column(nullable = false)
    private String codePostal;

    @Column(nullable = false)
    @Size(min = 1, max = 1023)
    private String description;

    @Column(nullable = false)
    @Size(min = 1, max = 255)
    private String titre;

    @ManyToMany
    private List<Utilisateur> postulants;

    @Column(nullable = false)
    private boolean estValidee;

    @Column(nullable = false)
    private boolean serviceRendu_auteur;
    
    @Column(nullable = false)
    private boolean serviceRendu_contracteur;
    
    @OneToMany
    private List<Commentaire> commentaires;
    
    // LIMITER A 5 TAGS
    @ManyToMany
    private List<Tag> tags;

    public Annonce() {
    }

    public Annonce(int prix, TypeAnnonce type, Utilisateur auteur, String codePostal, String description, String titre, LocalDateTime datePublication) {
        this.prix = prix;
        this.type = type;
        this.auteur = auteur;
        this.codePostal = codePostal;
        this.description = description;
        this.titre = titre;
        this.datePublication = datePublication;
        estValidee = false;
        postulants = new ArrayList<>();
        commentaires = new ArrayList<>();
        tags = new ArrayList<>();
    }  
  
    public TypeAnnonce getType() {
        return type;
    }

    public void setType(TypeAnnonce type) {
        this.type = type;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public LocalDateTime getDatePublication() {
        return datePublication;
    }

    public void setDatePublication(LocalDateTime datePublication) {
        this.datePublication = datePublication;
    }

    public List<Utilisateur> getPostulants() {
        return postulants;
    }

    public void setPostulants(List<Utilisateur> postulants) {
        this.postulants = postulants;
    }

    public List<Commentaire> getCommentaires() {
        return commentaires;
    }

    public void setCommentaires(List<Commentaire> commentaires) {
        this.commentaires = commentaires;
    }

    public Utilisateur getAuteur() {
        return auteur;
    }

    public void setAuteur(Utilisateur auteur) {
        this.auteur = auteur;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
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

    public boolean isEstValidee() {
        return estValidee;
    }

    public void setEstValidee(boolean estValidee) {
        this.estValidee = estValidee;
    }

    public boolean getServiceRendu_auteur() {
        return serviceRendu_auteur;
    }

    public void setServiceRendu_auteur(boolean serviceRendu_auteur) {
        this.serviceRendu_auteur = serviceRendu_auteur;
    }

    public boolean getServiceRendu_contracteur() {
        return serviceRendu_contracteur;
    }

    public void setServiceRendu_contracteur(boolean serviceRendu_contracteur) {
        this.serviceRendu_contracteur = serviceRendu_contracteur;
    }

}
