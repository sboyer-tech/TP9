/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import simplejdbc.*;


/**
 *
 * @author pedago
 */
public class Servlet1 extends HttpServlet {

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
            throws ServletException, IOException, DAOException {
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Servlet1</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Servlet1 at " + request.getContextPath() + "</h1>");
            
            /*Partie 2*/
            States myDAOStates = new States(DataSourceFactory.getDataSource());
            ArrayList ListStates = myDAOStates.StatesList();
            
            /*Création du formulaire*/
            out.println("<form method = 'post'>");
            out.println("<select name = 'form1'>");
            
            for (int i=0; i<ListStates.size(); i++){
                out.println("<option value="+ListStates.get(i)+">"+ListStates.get(i)+"</option>");
            }
            out.println("</select>");
            
            out.println("<button type='submit' name='button1'>Valider</button>");
            out.println("<form>");
            
            /*Partie 1*/
            DAO myDAO = new DAO(DataSourceFactory.getDataSource());
            String etatactuel = request.getParameter("form1");
            List<CustomerEntity> result = myDAO.customersInState(etatactuel);
            
            /*Création de la table*/
            out.println("<table style = 'border : 1px solid black'>");
            
            out.println("<tr>");
            out.println("<th>Nom</th>");
            out.println("<th>Âge</th>");
            out.println("<th>Pays</th>");
            out.println("</tr>");
                    
            for (int i=0; i<result.size(); i++){
                out.println("<tr>");
                out.println("<td>"+result.get(i).getCustomerId()+"</td>");
                out.println("<td>"+result.get(i).getName()+"</td>");
                out.println("<td>"+result.get(i).getAddressLine1()+"</td>");
                out.println("</tr>");
            }
            
            out.println("</table>");
            
            /*Style de la table*/
            out.println("<style>");
            out.println("table, th, td {\n" +"    border: 1px solid black;\n" +"}");
            out.println("table {\n" +"    border-spacing: 5px;\n" +"}");
            out.println("</style>");
            
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
            
            processRequest(request, response);
        } catch (DAOException ex) {
            Logger.getLogger(Servlet1.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (DAOException ex) {
            Logger.getLogger(Servlet1.class.getName()).log(Level.SEVERE, null, ex);
        }
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
