/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensimag.dacodac;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.*;

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

    @Min(0)
    @Column(nullable = false)
    private int dakos = 100;

    @Pattern(regexp = "^[A-Za-z0-9]+$")
    @Size(min = 1, max = 63)
    @Column(unique = true, nullable = false)
    private String pseudo;

    @Pattern(regexp = "^[A-Za-z0-9]+$")
    @Size(min = 8, max = 63)
    @Column(nullable = false)
    private String password;

    @Pattern(regexp = "^[a-z0-9._-]+@[a-z0-9._-]{2,}\\.[a-z]{2,4}$")
    @Column(unique = true, nullable = false)
    private String email;

    @Pattern(regexp = "^(0[1-9]|[1-8][0-9]|9[1-5])[0-9]{3}$")
    @Column(nullable = false)
    private String codePostal;
    
    @Min(12)
    @Max(150)
    @Column(nullable=false)
    private int age = 20;

    @Column(nullable = false)
    private boolean estAdmin = false;

    private String description;

    @OneToMany(mappedBy = "auteur", cascade = CascadeType.ALL)
    private List<Annonce> annonces;

    @OneToMany(mappedBy = "destinataire", cascade = CascadeType.ALL)
    private List<Commentaire> commentairesDest;

    @OneToMany(mappedBy = "auteur", cascade = CascadeType.ALL)
    private List<Commentaire> commentairesAuteur;

    public Utilisateur() {
    }
    
    public Utilisateur(int dakos, String pseudo, String password, String email, String codePostal, int age, boolean estAdmin) throws NoSuchAlgorithmException {
        this.dakos = dakos;
        this.pseudo = pseudo;
        //this.password= password;

        this.email = email;
        this.codePostal = codePostal;
        this.age = age;
        this.estAdmin = estAdmin;
        
        MessageDigest md = MessageDigest.getInstance("MD5");
        this.password = crypt(md, password);

        this.description = "";
        this.annonces = new ArrayList<>();
        this.commentairesDest = new ArrayList<>();
        this.commentairesAuteur = new ArrayList<>();
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

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Annonce> getAnnonces() {
        return annonces;
    }

    public void setAnnonces(List<Annonce> annonces) {
        this.annonces = annonces;
    }

    public List<Commentaire> getCommentairesDest() {
        return commentairesDest;
    }

    public void setCommentairesDest(List<Commentaire> commentairesDest) {
        this.commentairesDest = commentairesDest;
    }

    public List<Commentaire> getCommentairesAuteur() {
        return commentairesAuteur;
    }

    public void setCommentairesAuteur(List<Commentaire> commentairesAuteur) {
        this.commentairesAuteur = commentairesAuteur;
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
        return "Utilisateur[ id=" + id + ", dakos=" + dakos
                + ", pseudo=" + pseudo + ", email=" + email + ", code postal="
                + codePostal + ", age=" + age + ", estAdmin=" + estAdmin
                + ", a des annonces :" + !annonces.isEmpty() + "]";
    }

    public static String crypt(MessageDigest digester, String str) {
        if (str == null || str.length() == 0) {
            throw new IllegalArgumentException("String to encript cannot be null or zero length");
        }

        digester.update(str.getBytes());
        byte[] hash = digester.digest();
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < hash.length; i++) {
            if ((0xff & hash[i]) < 0x10) {
                hexString.append("0" + Integer.toHexString((0xFF & hash[i])));
            }
            else
            {
                hexString.append(Integer.toHexString(0xFF & hash[i]));
            }
        }
        return hexString.toString();
    }

}
