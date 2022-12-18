/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Student;

/**
 *
 * @author grammii
 */
public class StudentServlet extends HttpServlet {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("StudentAppPU");

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

        Long id = Long.parseLong(request.getParameter("id"));
        String name = request.getParameter("name");
        double gpa = Double.parseDouble(request.getParameter("gpa"));

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            List<Student> all = allStudent();

            Student stu = new Student(id, name, gpa);

            request.setAttribute("id", id);
            request.setAttribute("name", name);
            request.setAttribute("gpa", gpa);
            request.setAttribute("all", all);

            try {
                persist(stu);
                all = allStudent();
                request.setAttribute("all", all);
                request.getRequestDispatcher("/Added.jsp").forward(request, response);
            } catch (Exception e) {
                request.getRequestDispatcher("/Error.jsp").forward(request, response);
            }

        }
    }

    public static List<Student> allStudent() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("StudentAppPU");
        EntityManager em = emf.createEntityManager();
        List<Student> addrList = (List<Student>) em.createNamedQuery("Student.findAll").getResultList();
        return addrList;
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

    public void persist(Object object) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

}
