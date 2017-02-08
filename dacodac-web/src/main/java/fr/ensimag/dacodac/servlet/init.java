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

import fr.ensimag.dacodac.stateless.AnnonceFacadeLocal;
import fr.ensimag.dacodac.stateless.CommentaireFacadeLocal;
import fr.ensimag.dacodac.stateless.UtilisateurFacadeLocal;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
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

        Utilisateur utilisateur1 = new Utilisateur(1000000000, "Donald", "AMortLesSombreros", "donald.trump@maison-blanche.gouv", "28312", 60, false);
        Utilisateur utilisateur2 = new Utilisateur(100000, "Jonas", "secret00", "jonas.maubert@j-aime-elle.com", "38190", 22, true);
        Utilisateur utilisateur3 = new Utilisateur(100, "Leo", "secret00", "leo@gmail.com", "28300", 22, false);
        Utilisateur utilisateur4 = new Utilisateur(100, "Nico", "secret00", "kamo@gmail.com", "28301", 22, false);
        Utilisateur utilisateur5 = new Utilisateur(100, "Juju", "secret00", "juju@pl-s.com", "28302", 22, false);
        Utilisateur utilisateur6 = new Utilisateur(400000, "Hillary", "JAimePasLesEmails", "hillary.clinton@defaite.seum", "52147", 98, false);
        utilisateurFacade.create(utilisateur1);
        utilisateurFacade.create(utilisateur2);
        utilisateurFacade.create(utilisateur3);
        utilisateurFacade.create(utilisateur4);
        utilisateurFacade.create(utilisateur5);
        utilisateurFacade.create(utilisateur6);
        //public Annonce(int prix, TypeAnnonce type, Utilisateur auteur, int codePostal, String description, String titre, LocalDateTime datePublication)
        Annonce a1 = new Annonce(11, TypeAnnonce.OFFRE, utilisateur1, 38100, "description de l'offre numéro 1", "titre de l'offre 1", LocalDateTime.now());
        Annonce a2 = new Annonce(12, TypeAnnonce.OFFRE, utilisateur1, 38200, "description de l'offre numéro 2", "titre de l'offre 2", LocalDateTime.now());
        Annonce a3 = new Annonce(13, TypeAnnonce.OFFRE, utilisateur2, 38300, "description de l'offre numéro 3", "titre de l'offre 3", LocalDateTime.now());
        Annonce a4 = new Annonce(14, TypeAnnonce.OFFRE, utilisateur2, 38400, "description de l'offre numéro 4", "titre de l'offre 4", LocalDateTime.now());
        Annonce a5 = new Annonce(15, TypeAnnonce.OFFRE, utilisateur3, 38500, "description de l'offre numéro 5", "titre de l'offre 5", LocalDateTime.now());
        annonceFacade.create(a1);
        annonceFacade.create(a2);
        annonceFacade.create(a3);
        annonceFacade.create(a4);
        annonceFacade.create(a5);
        a1 = new Annonce(11, TypeAnnonce.DEMANDE, utilisateur1, 18100, "description de la demande numéro 1", "titre de la demande 1", LocalDateTime.now());
        a2 = new Annonce(12, TypeAnnonce.DEMANDE, utilisateur1, 18200, "description de la demande numéro 2", "titre de la demande 2", LocalDateTime.now());
        a3 = new Annonce(13, TypeAnnonce.DEMANDE, utilisateur4, 19300, "description de la demande numéro 3", "titre de la demande 3", LocalDateTime.now());
        a4 = new Annonce(14, TypeAnnonce.DEMANDE, utilisateur4, 18400, "description de la demande numéro 4", "titre de la demande 4", LocalDateTime.now());
        a5 = new Annonce(15, TypeAnnonce.DEMANDE, utilisateur5, 18500, "description de la demande numéro 5", "titre de la demande 5", LocalDateTime.now());
        annonceFacade.create(a1);
        annonceFacade.create(a2);
        annonceFacade.create(a3);
        annonceFacade.create(a4);
        annonceFacade.create(a5);

        /*Commentaire c = new Commentaire(utilisateur, LocalDateTime.MIN, a, "description");
        commentaireFacade.create(c);
        Commentaire retournes = commentaireFacade.findByAuteurAndAnnonce(utilisateur, a);
         */
        processRequest(request, response);
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
