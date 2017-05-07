/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author adri
 */
@WebServlet(name = "SknServlet", urlPatterns = {"/SknServlet"})
public class SknServlet extends HttpServlet {

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
      Connection conex = null;
      try {
        Class.forName("com.mysql.jdbc.Driver");
        
        conex = DriverManager.getConnection("jdbc:mysql://localhost:3306/skynet","root","");
        PreparedStatement ps;
        
        // Zona add -----------------------------------------------------------
        if (request.getParameter("zona").equals("add")) {
          ps = conex.prepareStatement("SELECT * FROM teclado WHERE marca=? AND modelo=? AND precio=?");
          
          ps.setString(1, request.getParameter("marca"));
          ps.setString(2, request.getParameter("modelo"));
          ps.setFloat(3, Float.parseFloat(request.getParameter("precio")));
          
          ResultSet rs = ps.executeQuery();
          
          if (rs.next()) {
            int contador = rs.getInt(2);
            contador++;
            
            ps = conex.prepareStatement("UPDATE teclado SET cantidad=? WHERE marca=?"
                    + " AND modelo=? AND precio=?");
            ps.setInt(1, contador);
            ps.setString(2, request.getParameter("marca"));
            ps.setString(3, request.getParameter("modelo"));
            ps.setFloat(4, Float.parseFloat(request.getParameter("precio")));
            
            ps.executeUpdate();
            out.print("existe");
          } else if (request.getParameter("marca").isEmpty() || 
                  request.getParameter("modelo").isEmpty()) {
            out.print("no");
          } else {
            int cantidad = 1;
            ps = conex.prepareStatement("INSERT INTO teclado"
                    + " (cantidad,marca,modelo,conectividad,tipo,precio)"
                    + " values(?,?,?,?,?,?)");
            ps.setInt(1, cantidad);
            ps.setString(2, request.getParameter("marca"));
            ps.setString(3, request.getParameter("modelo"));
            ps.setString(4, request.getParameter("conect"));
            ps.setString(5, request.getParameter("tipo"));
            ps.setFloat(6, Float.parseFloat(request.getParameter("precio")));
            ps.executeUpdate();
            out.print("ok");
          }
        }
        
        // Zona Modify --------------------------------------------------------
        if (request.getParameter("zona").equals("modify")) {
          
          if (request.getParameter("marca").isEmpty() || 
                  request.getParameter("modelo").isEmpty()) {
            out.print("no");
          } else {
            ps = conex.prepareStatement("UPDATE teclado SET cantidad=?, marca=?,"
                    + " modelo=?, conectividad=?, tipo=?, precio=? WHERE id=?");
            ps.setInt(1, Integer.parseInt(request.getParameter("cantidad")));
            ps.setString(2, request.getParameter("marca"));
            ps.setString(3, request.getParameter("modelo"));
            ps.setString(4, request.getParameter("conect"));
            ps.setString(5, request.getParameter("tipo"));
            ps.setFloat(6, Float.parseFloat(request.getParameter("precio")));
            ps.setInt(7, Integer.parseInt(request.getParameter("id")));
            ps.executeUpdate();
            out.print("ok");
          }
        }
        
        // Zona Delete --------------------------------------------------------
        if (request.getParameter("zona").equals("delete")) {
          
          ps = conex.prepareStatement("SELECT * FROM teclado WHERE id=?");
          
          ps.setString(1, request.getParameter("id"));
          
          ResultSet rs = ps.executeQuery();
          
          if (rs.next() && rs.getInt(2) > 1) {
            int contador = rs.getInt(2);
            contador--;
            
            ps = conex.prepareStatement("UPDATE teclado SET cantidad=? WHERE id=?");
            
            ps.setInt(1, contador);
            ps.setString(2, request.getParameter("id"));
            
            ps.executeUpdate();
            out.print("existe");
          } else {
            ps = conex.prepareStatement("DELETE FROM teclado WHERE id=?");
            ps.setInt(1, Integer.parseInt(request.getParameter("id")));
            ps.executeUpdate();
            out.print("ok");
          }
        }
      } catch (SQLException | ClassNotFoundException ex) {
        Logger.getLogger(SknServlet.class.getName()).log(Level.SEVERE, null, ex);
      } finally {
        if (conex != null) {
          try {
            conex.close();
          } catch (SQLException ex) {
            Logger.getLogger(SknServlet.class.getName()).log(Level.SEVERE, null, ex);
          }
        }
      }
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
