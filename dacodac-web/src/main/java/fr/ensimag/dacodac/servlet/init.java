/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensimag.dacodac.servlet;

import fr.ensimag.dacodac.Utilisateur;
import fr.ensimag.dacodac.Annonce;
import fr.ensimag.dacodac.TypeAnnonce;
import fr.ensimag.dacodac.Commentaire;
import fr.ensimag.dacodac.Tag;

import fr.ensimag.dacodac.stateless.AnnonceFacadeLocal;
import fr.ensimag.dacodac.stateless.CommentaireFacadeLocal;
import fr.ensimag.dacodac.stateless.UtilisateurFacadeLocal;
import fr.ensimag.dacodac.stateless.TagFacadeLocal;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author roussena
 */


@WebServlet(name = "init", urlPatterns = {"/init"})
public class init extends HttpServlet {

    @EJB(name = "utilisateurFacade")
    private UtilisateurFacadeLocal utilisateurFacade;

    @EJB(name = "annonceFacade")
    private AnnonceFacadeLocal annonceFacade;

    @EJB(name = "commentaireFacade")
    private CommentaireFacadeLocal commentaireFacade;

    @EJB(name = "tagFacade")
    private TagFacadeLocal tagFacade;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet init</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet init at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            Utilisateur utilisateur1 = new Utilisateur(10000, "Remi", "secret00", "remi.duclau@gmail.com", "73317", 60, false);
            Utilisateur utilisateur2 = new Utilisateur(250, "Jonas", "secret00", "jonas.maubert@hotmail.fr", "38190", 22, true);
            Utilisateur utilisateur3 = new Utilisateur(34, "Leo", "secret00", "leo@gmail.com", "28700", 22, false);
            Utilisateur utilisateur4 = new Utilisateur(80, "Nicolas", "secret00", "kamo@gmail.com", "65789", 23, false);
            Utilisateur utilisateur5 = new Utilisateur(50, "Justine", "secret00", "juju@wanadoo.com", "18302", 22, true);
            Utilisateur utilisateur6 = new Utilisateur(654, "Nathan", "secret00", "nathan@gmail.com", "41587", 21, false);
            Utilisateur utilisateur7 = new Utilisateur(214, "Baptiste", "secret00", "baptiste@bbox.fr", "24598", 60, false);
            utilisateur1.setDescription("Description du compte de Remi.");
            utilisateur2.setDescription("Description du compte de Jonas. Ce compte est administrateur.");
            utilisateur3.setDescription("Description du compte de Léo.");
            utilisateur4.setDescription("Description du compte de Nicolas.");
            utilisateur5.setDescription("Description du compte de Justine. Ce compte est administrateur.");
            utilisateur6.setDescription("Description du compte de Nathan.");
            utilisateur7.setDescription("Description du compte de Baptiste.");

            utilisateurFacade.create(utilisateur1);
            utilisateurFacade.create(utilisateur2);
            utilisateurFacade.create(utilisateur3);
            utilisateurFacade.create(utilisateur4);
            utilisateurFacade.create(utilisateur5);
            utilisateurFacade.create(utilisateur6);
            utilisateurFacade.create(utilisateur7);

            LocalDate now = LocalDate.now();

            //public Annonce(int prix, TypeAnnonce type, Utilisateur auteur, int codePostal, String description, String titre, LocalDateTime datePublication)
            Annonce a1 = new Annonce(11, TypeAnnonce.OFFRE, utilisateur1, "38100", "description de l'offre numéro 1", "titre de l'offre 1", now);
            Annonce a2 = new Annonce(120, TypeAnnonce.OFFRE, utilisateur1, "38200", "description de l'offre numéro 2", "titre de l'offre 2", now.plusDays(1));
            Annonce a3 = new Annonce(13, TypeAnnonce.OFFRE, utilisateur2, "38300", "description de l'offre numéro 3", "titre de l'offre 3", now.plusDays(2));
            Annonce a4 = new Annonce(14, TypeAnnonce.OFFRE, utilisateur2, "38400", "description de l'offre numéro 4", "titre de l'offre 4", now.plusDays(5));
            Annonce a5 = new Annonce(15, TypeAnnonce.OFFRE, utilisateur3, "38500", "description de l'offre numéro 5", "titre de l'offre 5", now.plusDays(3));
            Annonce a6 = new Annonce(6, TypeAnnonce.DEMANDE, utilisateur1, "18100", "description de la demande numéro 6", "titre de la demande 6", now);
            Annonce a7 = new Annonce(7, TypeAnnonce.DEMANDE, utilisateur1, "18200", "description de la demande numéro 7", "titre de la demande 7", now.plusDays(8));
            Annonce a8 = new Annonce(8, TypeAnnonce.DEMANDE, utilisateur4, "19300", "description de la demande numéro 8", "titre de la demande 8", now.plusDays(4));
            Annonce a9 = new Annonce(9, TypeAnnonce.DEMANDE, utilisateur4, "18400", "description de la demande numéro 9", "titre de la demande 9", now.plusDays(10));
            Annonce a10 = new Annonce(10, TypeAnnonce.DEMANDE, utilisateur5, "18500", "description de la demande numéro 10", "titre de la demande 10", now.plusDays(15));
            
            createAnnonce(a1.getAuteur(), a1);
            createAnnonce(a2.getAuteur(), a2);
            createAnnonce(a3.getAuteur(), a3);
            createAnnonce(a4.getAuteur(), a4);
            createAnnonce(a5.getAuteur(), a5);
            createAnnonce(a6.getAuteur(), a6);
            createAnnonce(a7.getAuteur(), a7);
            createAnnonce(a8.getAuteur(), a8);
            createAnnonce(a9.getAuteur(), a9);
            createAnnonce(a10.getAuteur(), a10);
            
            Tag tag1 = new Tag("tag1");
            Tag tag2 = new Tag("tag2");
            Tag tag3 = new Tag("tag3");
            Tag tag4 = new Tag("tag4");
            Tag tag5 = new Tag("tag5");
            Tag tag6 = new Tag("tag6");
            Tag tag7 = new Tag("tag7");
            Tag tag8 = new Tag("tag8");
            Tag tag9 = new Tag("tag9");
            Tag tag10 = new Tag("tag10");

            tagFacade.create(tag1);
            tagFacade.create(tag2);
            tagFacade.create(tag3);
            tagFacade.create(tag4);
            tagFacade.create(tag5);
            tagFacade.create(tag6);
            tagFacade.create(tag7);
            tagFacade.create(tag8);
            tagFacade.create(tag9);
            tagFacade.create(tag10);

            annonceFacade.addTag(a1, tag1);
            annonceFacade.addTag(a1, tag2);
            annonceFacade.addTag(a1, tag3);
            annonceFacade.addTag(a2, tag4);
            annonceFacade.addTag(a2, tag5);
            annonceFacade.addTag(a3, tag6);
            annonceFacade.addTag(a4, tag2);
            annonceFacade.addTag(a4, tag3);
            annonceFacade.addTag(a4, tag5);
            annonceFacade.addTag(a4, tag7);
            annonceFacade.addTag(a4, tag8);
            annonceFacade.addTag(a5, tag5);
            annonceFacade.addTag(a5, tag9);
            annonceFacade.addTag(a5, tag10);
            annonceFacade.addTag(a6, tag1);
            annonceFacade.addTag(a6, tag6);
            annonceFacade.addTag(a7, tag8);
            annonceFacade.addTag(a7, tag9);
            annonceFacade.addTag(a8, tag10);
            annonceFacade.addTag(a9, tag2);
            annonceFacade.addTag(a9, tag4);
            annonceFacade.addTag(a9, tag6);
            annonceFacade.addTag(a10, tag5);
            annonceFacade.addTag(a10, tag7);
            
            annonceFacade.addPostulant(a1, utilisateur2);
            annonceFacade.addPostulant(a1, utilisateur3); //Des personnes ont postulé à a1
            annonceFacade.addPostulant(a2, utilisateur6);
            annonceFacade.accepterPostulant(a2, utilisateur6); //Une personne est validé pour a2
            annonceFacade.addPostulant(a3, utilisateur1); //U1 postule a a3 et a4
            annonceFacade.addPostulant(a4, utilisateur1);
            annonceFacade.accepterPostulant(a3, utilisateur1); // U1 sera validé pour a3
            annonceFacade.addPostulant(a6, utilisateur2);
            annonceFacade.accepterPostulant(a6, utilisateur2);//U2 sera validé pour a6
            //a7 n'aura aucun postulants
            annonceFacade.addPostulant(a8, utilisateur1);//U1 ne sera pas encore validé pour a8
            annonceFacade.addPostulant(a9, utilisateur1);
            annonceFacade.accepterPostulant(a9, utilisateur1);//U1 sera validé pour a9

            // ANNONCES TERMINEES !!! NON PRESENTES EN BASE !!!!
            Annonce old1 = new Annonce(85, TypeAnnonce.DEMANDE, utilisateur2, "18800", "description de la demande numéro old1", "titre de la demande old1", now.plusDays(18));
            Annonce old2 = new Annonce(81, TypeAnnonce.OFFRE, utilisateur4, "18900", "description de l'offre numéro old2", "titre de l'offre old2", now.plusDays(19));

            Commentaire commentaire1 = new Commentaire(utilisateur1, old1.getAuteur(), LocalDate.now(), "Je suis " + utilisateur1.getPseudo() + " auteur de ce commentaire et il est destiné à " + old1.getAuteur().getPseudo(), old1.getTitre());
            commentaireFacade.create(commentaire1);
            utilisateurFacade.addCommentaire(commentaire1);

            Commentaire commentaire2 = new Commentaire(utilisateur3, old2.getAuteur(), LocalDate.now(), "Je suis " + utilisateur3.getPseudo() + " auteur de ce commentaire et il est destiné à " + old2.getAuteur().getPseudo(), old2.getTitre());
            commentaireFacade.create(commentaire2);
            utilisateurFacade.addCommentaire(commentaire2);

            annonceFacade.serviceRendu(true, a2, utilisateur1);
            annonceFacade.serviceRendu(true, a2, utilisateur6);

            /*Commentaire c = new Commentaire(utilisateur, LocalDateTime.MIN, a, "description");
        commentaireFacade.create(c);
        Commentaire retournes = commentaireFacade.findByAuteurAndAnnonce(utilisateur, a);
             */
            processRequest(request, response);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(init.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void createAnnonce(Utilisateur u, Annonce a) {
        annonceFacade.create(a);
        utilisateurFacade.addAnnonce(u, a);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
