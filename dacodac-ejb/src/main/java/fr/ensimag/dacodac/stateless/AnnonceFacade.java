/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensimag.dacodac.stateless;

import fr.ensimag.dacodac.Annonce;
import fr.ensimag.dacodac.Commentaire;
import fr.ensimag.dacodac.Tag;
import fr.ensimag.dacodac.Utilisateur;
import fr.ensimag.dacodac.TypeAnnonce;
import java.time.LocalDate;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author weschlel
 */
@Stateless
public class AnnonceFacade extends AbstractFacade<Annonce> implements AnnonceFacadeLocal {

    @PersistenceContext(unitName = "Dacodac_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AnnonceFacade() {
        super(Annonce.class);
    }

    @Override
    public void addCommentaire(Annonce annonce, Commentaire com) {
        List<Commentaire> commentaires = annonce.getCommentaires();
        commentaires.add(com);
        annonce.setCommentaires(commentaires);
        edit(annonce);
    }

    @Override
    public void addPostulant(Annonce annonce, Utilisateur utilisateur) {
        List<Utilisateur> postulants = annonce.getPostulants();
        postulants.add(utilisateur);
        annonce.setPostulants(postulants);
        edit(annonce);
    }

    @Override
    public void addTag(Annonce annonce, Tag tag) {
        List<Tag> tags = annonce.getTags();
        tags.add(tag);
        annonce.setTags(tags);
        edit(annonce);
    }

    @Override
    public void modifierType(Annonce annonce, TypeAnnonce type) {
        annonce.setType(type);
        edit(annonce);
    }

    @Override
    public void removePostulant(Annonce annonce, Utilisateur utilisateur) {
        List<Utilisateur> postulants = annonce.getPostulants();
        postulants.remove(utilisateur);
        annonce.setPostulants(postulants);
        edit(annonce);
    }

    @Override
    public void accepterPostulant(Annonce annonce, Utilisateur utilisateur) {
        List<Utilisateur> postulants = annonce.getPostulants();

        if (postulants.contains(utilisateur)) {
            postulants = new ArrayList<>();
            postulants.add(utilisateur);
            annonce.setPostulants(postulants);
            annonce.setEstValidee(true);
        } else {
            //L'utilisateur rentré n'est pas dans la liste des postulants.
        }
        edit(annonce);
    }

    public boolean containsTag(Annonce annonce, List<Tag> tags) {
        boolean trouve = true;
        if (tags.isEmpty()) {
            return trouve;
        } else if (annonce.getTags().isEmpty()) {
            return false;
        } else {
            for (Tag tag : tags) {
                for (Iterator<Tag> it = annonce.getTags().iterator(); it.hasNext();) {
                    Tag t = it.next();
                    if (t.getNom().equals(tag.getNom())) {
                        break;
                    } else if (!it.hasNext()) {
                        trouve = false;
                        break;
                    }
                }
            }
            return trouve;
        }
    }

    @Override
    public List<Annonce> findByTags(List<Tag> tags) {
        List<Annonce> annonces = findAll();
        List<Annonce> annoncesCorrespondantes = new ArrayList<>();
        if (annonces.isEmpty()) {
            return null;
        } else {
            boolean trouve = true;
            for (Annonce annonce : annonces) {
                trouve = containsTag(annonce, tags);
                if (trouve) {
                    annoncesCorrespondantes.add(annonce);
                }
            }
            if (annoncesCorrespondantes.isEmpty()) {
                return null;
            }
            return annoncesCorrespondantes;
        }
    }

    @Override
    public List<Annonce> findOffresByTags(List<Tag> tags) {
        List<Annonce> offres = getOffres();
        List<Annonce> offresCorrespondantes = new ArrayList<>();
        if (offres.isEmpty()) {
            System.err.println("offres empty");
            return null;
        } else if (tags.isEmpty()) {
            System.err.println("tags empty");
            return null;
        } else {
            System.err.println("offres not empty");
            boolean trouve = true;
            for (Annonce offre : offres) {
                trouve = containsTag(offre, tags);
                if (trouve) {
                    offresCorrespondantes.add(offre);
                }
            }
            if (offresCorrespondantes.isEmpty()) {
                return null;
            }
            return offresCorrespondantes;
        }
    }

    @Override
    public List<Annonce> findDemandesByTags(List<Tag> tags) {
        List<Annonce> demandes = getDemandes();
        List<Annonce> demandesCorrespondantes = new ArrayList<>();
        if (demandes.isEmpty()) {
            return null;
        } else {
            boolean trouve = true;
            for (Annonce demande : demandes) {
                trouve = containsTag(demande, tags);
                if (trouve) {
                    demandesCorrespondantes.add(demande);
                }
            }
            if (demandesCorrespondantes.isEmpty()) {
                return null;
            }
            return demandesCorrespondantes;
        }
    }

    @Override
    public List<Annonce> findByTitle(String titreRecherche) {
        List<Annonce> annonces = findAll();
        if (annonces.isEmpty()) {
            return null;
        } else {
            String[] separeRecherche = titreRecherche.toLowerCase().split(" ");
            boolean trouve = true;
            boolean trouve_tmp;
            for (Annonce annonce : annonces) {
                String[] separeTitre = annonce.getTitre().toLowerCase().split(" ");
                for (String subRecherche : separeRecherche) {
                    for (String subTitre : separeTitre) {
                        trouve_tmp = false;
                        if (subTitre.equals(subRecherche)) {
                            trouve_tmp = true;
                            if (trouve_tmp == false) {
                                trouve = false;
                                break;
                            }
                        }
                    }
                    if (!trouve) {
                        break;
                    }
                }
                if (!trouve) {
                    annonces.remove(annonce);
                }
            }
            return annonces;
        }
    }

    @Override
    public void serviceRendu(boolean realise, Annonce annonce, Utilisateur utilisateur) {
        if (annonce.getAuteur().equals(utilisateur)) {
            annonce.setServiceRendu_auteur(realise);
        } else {
            annonce.setServiceRendu_contracteur(realise);
        }
        edit(annonce);
    }

    @Override
    public List<Annonce> getOffres() {
        List<Annonce> offres = getEntityManager().createQuery("Select a FROM Annonce a WHERE a.type = :type").setParameter("type", TypeAnnonce.OFFRE).getResultList();
        return offres;
    }

    @Override
    public List<Annonce> getDemandes() {
        List<Annonce> demandes = getEntityManager().createQuery("Select a FROM Annonce a WHERE a.type = :type").setParameter("type", TypeAnnonce.DEMANDE).getResultList();
        return demandes;
    }

    @Override
    public Annonce findByUtilAndTitre(Utilisateur u, String titre) {
        return (Annonce) getEntityManager().createQuery("SELECT a FROM Annonce a WHERE a.titre LIKE :titre and a.auteur = :auteur")
                .setParameter("titre", titre).setParameter("auteur", u).getResultList().get(0);
    }

    @Override
    public List<Annonce> findLatest(int nbAnnoncesAffichees, TypeAnnonce type) {
        List<Annonce> result = (List<Annonce>) getEntityManager().createQuery("SELECT a FROM Annonce a WHERE a.type = :type ORDER BY a.datePublication")
                .setParameter("type", type).getResultList();

        if (!result.isEmpty()) {
            return result.subList(0, nbAnnoncesAffichees);
        }
        return result;
    }

}
