/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensimag.dacodac.servlet;

import fr.ensimag.dacodac.Utilisateur;
import fr.ensimag.dacodac.stateless.AnnonceFacadeLocal;
import fr.ensimag.dacodac.stateless.CommentaireFacadeLocal;
import fr.ensimag.dacodac.stateless.UtilisateurFacadeLocal;
import java.io.IOException;
import java.io.PrintWriter;
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
        Utilisateur utilisateur = new Utilisateur(1, "Danald", "AMortLesSombreros", "donald.trump@maison-blanche.gouv", "28312", 60, false);
        utilisateurFacade.create(utilisateur);
        utilisateur = new Utilisateur(4, "Halliry", "JAimePasLesEmails", "hillary.clinton@defaite.lol", "52147", 55, true);
        /*Annonce a = new Annonce(10, TypeAnnonce.OFFRE, utilisateur, new ArrayList<Utilisateur>(), 38000, "description", "titre", LocalDateTime.now());


        Utilisateur utilisateur = new Utilisateur(1, "Donald", "A mort les sombrero", "donald.trump@maison-blanche.gouv", 2, 3, false);
        utilisateurFacade.create(utilisateur);

        utilisateur = utilisateurFacade.findByPseudo("Donald");
        Annonce a = new Annonce(10, TypeAnnonce.OFFRE, utilisateur, 38000, "description", "Ceci est mon titre", LocalDateTime.now());
        annonceFacade.create(a);
        System.out.println("---------------------------------------------------------------------------------------");
        System.out.println(a.getAuteur().getId());
        System.out.println(utilisateur);
        System.out.println("---------------------------------------------------------------------------------------11111");
        a = annonceFacade.findByUtilAndTitre(a.getAuteur(), a.getTitre());
        System.out.println("---------------------------------------------------------------------------------------22222");

        System.out.println(a.getTitre());
        Commentaire c = new Commentaire(utilisateur, LocalDateTime.MIN, a, "description");
        commentaireFacade.create(c);
        Commentaire retournes = commentaireFacade.findByAuteurAndAnnonce(utilisateur, a);
        retournes.setDescription("NOUVELLE DESCRIPTION 245654222222");
        commentaireFacade.edit(retournes);
        utilisateur = new Utilisateur(4, "Hillary", "J'aime pas les emails", "hillary.clinton@defaite.sanders", 5, 6, true);

        utilisateurFacade.create(utilisateur);
        utilisateur = utilisateurFacade.findByPseudo("Halliry");
        //utilisateurFacade.modifyUser(); */
        utilisateur.setDakos(12);
        utilisateurFacade.edit(utilisateur);
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
