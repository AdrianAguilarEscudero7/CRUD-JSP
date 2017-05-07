package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.Connection;

public final class view_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("  <head>\n");
      out.write("    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("    <meta name=\"author\" content=\"Adrián Aguilar\">\n");
      out.write("    <title>Skynet | View</title>\n");
      out.write("    <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js\"></script>\n");
      out.write("    <script src=\"js/bootstrap.js\"></script>\n");
      out.write("    <script src=\"js/scripts.js\"></script>\n");
      out.write("    <link rel=\"stylesheet\" href=\"css/bootstrap.css\">\n");
      out.write("  </head>\n");
      out.write("  <body>\n");
      out.write("    <div id=\"wrapper\">\n");
      out.write("      <div id=\"navbar\">\n");
      out.write("        <span>ID</span>\n");
      out.write("        <span>Cantidad</span>\n");
      out.write("        <input id=\"marca\" type=\"text\" placeholder=\"Marca\">\n");
      out.write("        <input id=\"modelo\" type=\"text\" placeholder=\"Modelo\">\n");
      out.write("        <input id=\"conect\" type=\"text\" placeholder=\"Conectividad\">\n");
      out.write("        <input id=\"tipo\" type=\"text\" placeholder=\"Tipo\">\n");
      out.write("        <input id=\"precio\" type=\"text\" placeholder=\"Precio\">\n");
      out.write("        <button class=\"add-btn\">AÑADIR</button>\n");
      out.write("      </div>\n");
      out.write("      <div id=\"body\">\n");
      out.write("        <table border=\"1\">\n");
      out.write("        ");

          Class.forName("com.mysql.jdbc.Driver");
          Connection conex = DriverManager.getConnection("jdbc:mysql://localhost:3306/skynet","root","");
          Statement stmt = conex.createStatement();

          ResultSet rs = stmt.executeQuery("SELECT * FROM teclado");

          while (rs.next()) {
            out.println("<tr><td>"+rs.getInt(1)+"</td>");
            out.println("<td>"+rs.getInt(2)+"</td>");
            out.println("<td>"+rs.getString(3)+"</td>");
            out.println("<td>"+rs.getString(4)+"</td>");
            out.println("<td>"+rs.getString(5)+"</td>");
            out.println("<td>"+rs.getString(6)+"</td>");
            out.println("<td>"+rs.getFloat(7)+" €</td>"); 
            out.println("<td><button class='modify-btn' value='"+rs.getInt(1)+"'>MODIFICAR</button></td>");
            out.println("<td><button class='delete-btn' value='"+rs.getInt(1)+"'>ELIMINAR</button></td></tr>");
          } // end while
          conex.close(); // cerramos conexión con BBDD
        
      out.write("\n");
      out.write("        </table>\n");
      out.write("        <div id=\"myModal\" class=\"modal fade\" role=\"dialog\">\n");
      out.write("          <div class=\"modal-dialog\">\n");
      out.write("            <!-- Modal content-->\n");
      out.write("            <div class=\"modal-content\">\n");
      out.write("              <div class=\"modal-header\">\n");
      out.write("                <h4 class=\"modal-title\"></h4>\n");
      out.write("              </div>\n");
      out.write("              <div class=\"modal-body\">\n");
      out.write("                <p></p>\n");
      out.write("              </div>\n");
      out.write("              <div class=\"modal-footer\">\n");
      out.write("                <button id=\"close-modal\" type=\"button\" class=\"btn btn-default\" data-dismiss=\"modal\">Cerrar</button>\n");
      out.write("              </div>\n");
      out.write("            </div>\n");
      out.write("          </div>\n");
      out.write("        </div>\n");
      out.write("        <div id=\"modify-modal\" class=\"modal fade\" role=\"dialog\">\n");
      out.write("          <div class=\"modal-dialog modal-lg\">\n");
      out.write("            <!-- Modal content-->\n");
      out.write("            <div class=\"modal-content\">\n");
      out.write("              <div class=\"modal-header\">\n");
      out.write("                <h4 class=\"modal-title\"></h4>\n");
      out.write("              </div>\n");
      out.write("              <div class=\"modal-body\">\n");
      out.write("                <span>Cantidad</span>\n");
      out.write("                <span>Marca</span>\n");
      out.write("                <span>Modelo</span><br>\n");
      out.write("                <input id=\"cantidad-modify\" type=\"text\" placeholder=\"1º\">\n");
      out.write("                <input id=\"marca-modify\" type=\"text\" placeholder=\"2º\">\n");
      out.write("                <input id=\"modelo-modify\" type=\"text\" placeholder=\"3º\"><br>\n");
      out.write("                <span>Conectividad</span>\n");
      out.write("                <span>Tipo</span>\n");
      out.write("                <span>Precio</span><br>\n");
      out.write("                <input id=\"conect-modify\" type=\"text\" placeholder=\"4º\">\n");
      out.write("                <input id=\"tipo-modify\" type=\"text\" placeholder=\"5º\">\n");
      out.write("                <input id=\"precio-modify\" type=\"text\" placeholder=\"6º\">\n");
      out.write("              </div>\n");
      out.write("              <div class=\"modal-footer\">\n");
      out.write("                <button id=\"cancel-modify\" type=\"button\" class=\"btn btn-default\" data-dismiss=\"modal\">Cancelar</button>\n");
      out.write("                <button id=\"accept-modify\" type=\"button\" class=\"btn btn-default\">Aceptar</button>\n");
      out.write("              </div>\n");
      out.write("            </div>\n");
      out.write("          </div>\n");
      out.write("        </div>\n");
      out.write("      </div>\n");
      out.write("    </div>\n");
      out.write("  </body> \n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
