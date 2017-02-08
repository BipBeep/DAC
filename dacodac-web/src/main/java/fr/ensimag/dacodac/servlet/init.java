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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
        Annonce a1 = new Annonce(11, TypeAnnonce.OFFRE, utilisateur1, "38100", "description de l'offre numéro 1", "titre de l'offre 1", new Date());
        Annonce a2 = new Annonce(12, TypeAnnonce.OFFRE, utilisateur1, "38200", "description de l'offre numéro 2", "titre de l'offre 2", new Date());
        Annonce a3 = new Annonce(13, TypeAnnonce.OFFRE, utilisateur2, "38300", "description de l'offre numéro 3", "titre de l'offre 3", new Date());
        Annonce a4 = new Annonce(14, TypeAnnonce.OFFRE, utilisateur2, "38400", "description de l'offre numéro 4", "titre de l'offre 4", new Date());
        Annonce a5 = new Annonce(15, TypeAnnonce.OFFRE, utilisateur3, "38500", "description de l'offre numéro 5", "titre de l'offre 5", new Date());
        
        List<Tag> listeTags = new ArrayList<Tag>();
        Tag tag1 = new Tag("Premier tag");
        Tag tag2 = new Tag("Deuxième tag");
        Tag tag3 = new Tag("Troisième tag");
        
        tagFacade.create(tag1);
        tagFacade.create(tag2);
        tagFacade.create(tag3);
        
        listeTags.add(tag1);
        listeTags.add(tag2);
        listeTags.add(tag3);
        a1.setTags(listeTags);
        
        Annonce a6 = new Annonce(6, TypeAnnonce.DEMANDE, utilisateur1, "18100", "description de la demande numéro 6", "titre de la demande 6", new Date());
        Annonce a7 = new Annonce(7, TypeAnnonce.DEMANDE, utilisateur1, "18200", "description de la demande numéro 7", "titre de la demande 7", new Date());
        Annonce a8 = new Annonce(8, TypeAnnonce.DEMANDE, utilisateur4, "19300", "description de la demande numéro 8", "titre de la demande 8", new Date());
        Annonce a9 = new Annonce(9, TypeAnnonce.DEMANDE, utilisateur4, "18400", "description de la demande numéro 9", "titre de la demande 9", new Date());
        Annonce a10 = new Annonce(10, TypeAnnonce.DEMANDE, utilisateur5, "18500", "description de la demande numéro 10", "titre de la demande 10", new Date());

        annonceFacade.create(a1);
        annonceFacade.create(a2);
        annonceFacade.create(a3);
        annonceFacade.create(a4);
        annonceFacade.create(a5);
        annonceFacade.create(a6);
        annonceFacade.create(a7);
        annonceFacade.create(a8);
        annonceFacade.create(a9);
        annonceFacade.create(a10);
        annonceFacade.addPostulant(a1, utilisateur2);
        annonceFacade.addPostulant(a1, utilisateur3); //Des personnes ont postulé à a1
        annonceFacade.edit(a1);
        annonceFacade.addPostulant(a2, utilisateur6);
        annonceFacade.accepterPostulant(a2, utilisateur6); //Une personne est validé pour a2
        annonceFacade.addPostulant(a3, utilisateur1); //U1 postule a a3 et a4
        annonceFacade.addPostulant(a4, utilisateur1);
        annonceFacade.accepterPostulant(a3, utilisateur1); // U1 sera validé pour a3
        annonceFacade.edit(a2);
        annonceFacade.edit(a3);
        annonceFacade.edit(a4);
        annonceFacade.addPostulant(a6, utilisateur2);
        annonceFacade.accepterPostulant(a6, utilisateur2);//U2 sera validé pour a6
        //a7 n'aura aucun postulants
        annonceFacade.addPostulant(a8, utilisateur1);//U1 ne sera pas encore validé pour a8
        annonceFacade.addPostulant(a9, utilisateur1);
        annonceFacade.accepterPostulant(a9, utilisateur1);//U1 sera validé pour a9
        annonceFacade.edit(a6);
        annonceFacade.edit(a8);
        annonceFacade.edit(a9);
        Commentaire commentaire = new Commentaire(utilisateur2, LocalDateTime.now(), a5, "Je suis U2. U1 m'a rendu service.");
        commentaireFacade.create(commentaire);
        Commentaire commentaire2 = new Commentaire(utilisateur3, LocalDateTime.now(), a6, "Je suis U3. U1 m'a rendu service.");
        commentaireFacade.create(commentaire2);
        utilisateurFacade.addCommentaire(utilisateur1, commentaire);
        utilisateurFacade.addCommentaire(utilisateur1, commentaire2);
        utilisateurFacade.edit(utilisateur1);
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
