/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensimag.dacodac;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 *
 * @author roussena
 */
@Entity
public class Utilisateur implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int dakos;
    private String nom;
    private String prenom;
    private int codePostal;
    private int age;
    private boolean estAdmin;
    
    @OneToMany
    private List<Annonce> mesAnnonces;
    
    @OneToMany
    private List<Commentaire> commentaires;

    public Utilisateur() {
    }
    
    public Utilisateur(int dakos, String nom, String prenom, int codePostal, int age, boolean estAdmin) {
        this.dakos = dakos;
        this.nom = nom;
        this.prenom = prenom;
        this.codePostal = codePostal;
        this.age = age;
        this.estAdmin = estAdmin;
        this.mesAnnonces = new ArrayList<Annonce>();
        this.commentaires = new ArrayList<Commentaire>();
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getDakos() {
        return dakos;
    }

    public void setDakos(int dakos) {
        this.dakos = dakos;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(int codePostal) {
        this.codePostal = codePostal;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isEstAdmin() {
        return estAdmin;
    }

    public void setEstAdmin(boolean estAdmin) {
        this.estAdmin = estAdmin;
    }

    public List<Annonce> getMesAnnonces() {
        return mesAnnonces;
    }

    public void setMesAnnonces(List<Annonce> mesAnnonces) {
        this.mesAnnonces = mesAnnonces;
    }

    public List<Commentaire> getCommentaires() {
        return commentaires;
    }

    public void setCommentaires(List<Commentaire> commentaires) {
        this.commentaires = commentaires;
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
        if (!(object instanceof Utilisateur)) {
            return false;
        }
        Utilisateur other = (Utilisateur) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Utilisateur[ id=" + id + ", dakos=" + dakos +
                ", nom=" + nom + ", prenom=" + prenom + ", code postal=" +
                codePostal + ", age=" + age + ", estAdmin=" + estAdmin +
                ", a des annonces :" + !mesAnnonces.isEmpty() + 
                ", a des commentaires :" + !commentaires.isEmpty() + "]";
    }
    
}