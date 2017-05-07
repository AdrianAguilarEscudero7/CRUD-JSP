<%-- 
    Document   : view
    Created on : 25-abr-2017, 22:55:54
    Author     : adri
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="author" content="Adrián Aguilar">
    <title>Skynet | View</title>
    <script src="js/bootstrap.js"></script>
    <script src="js/scripts.js"></script>
    <link rel="stylesheet" href="css/bootstrap.css">
    <link rel="stylesheet" href="css/styles.css">
  </head>
  <body>
    <div id="wrapper">
      <div id="navbar">
        <span>ID</span>
        <span>Cantidad</span>
        <input id="marca" type="text" placeholder="Marca" maxlength="30">
        <input id="modelo" type="text" placeholder="Modelo" maxlength="30">
        <input id="conect" type="text" placeholder="Conectividad" maxlength="30">
        <input id="tipo" type="text" placeholder="Tipo" maxlength="30">
        <input id="precio" type="text" placeholder="Precio" maxlength="30">
        <button class="add-btn btn-styles">AÑADIR</button>
      </div>
      <div id="body">
        <table border="0">
        <%
          Class.forName("com.mysql.jdbc.Driver");
          Connection conex = DriverManager.getConnection("jdbc:mysql://localhost:3306/skynet","root","");
          Statement stmt = conex.createStatement();

          ResultSet rs = stmt.executeQuery("SELECT * FROM teclado");

          while (rs.next()) {
            out.println("<tr><td id='id-box'>"+rs.getInt(1)+"</td>");
            out.println("<td id='cntd-box'>"+rs.getInt(2)+"</td>");
            out.println("<td id='marca-box'>"+rs.getString(3)+"</td>");
            out.println("<td id='model-box'>"+rs.getString(4)+"</td>");
            out.println("<td id='conect-box'>"+rs.getString(5)+"</td>");
            out.println("<td id='type-box'>"+rs.getString(6)+"</td>");
            out.println("<td id='price-box'>"+String.format("%.2f",rs.getFloat(7))+" €</td>"); 
            out.println("<td><button class='modify-btn btn-styles' value='"+rs.getInt(1)+"'>MODIFICAR</button></td>");
            out.println("<td><button class='delete-btn btn-styles' value='"+rs.getInt(1)+"'>ELIMINAR</button></td></tr>");
          } // end while
          conex.close(); // cerramos conexión con BBDD
        %>
        </table>
        <div id="myModal" class="modal fade" role="dialog">
          <div class="modal-dialog">
            <!-- Modal content-->
            <div class="modal-content">
              <div class="modal-header">
                <h4 class="modal-title"></h4>
              </div>
              <div class="modal-body">
                <p></p>
              </div>
              <div class="modal-footer">
                <button id="close-modal" type="button" class="btn btn-default btn-styles" data-dismiss="modal">Cerrar</button>
              </div>
            </div>
          </div>
        </div>
        <div id="modify-modal" class="modal fade" role="dialog">
          <div class="modal-dialog modal-lg">
            <!-- Modal content-->
            <div class="modal-content">
              <div class="modal-header">
                <h4 class="modal-title"></h4>
              </div>
              <div class="modal-body">
                <input id="cantidad-modify" type="text" placeholder="Cantidad">
                <input id="marca-modify" type="text" placeholder="Marca">
                <input id="modelo-modify" type="text" placeholder="Modelo"><br>
                <input id="conect-modify" type="text" placeholder="Conectividad">
                <input id="tipo-modify" type="text" placeholder="Tipo">
                <input id="precio-modify" type="text" placeholder="Precio">
              </div>
              <div class="modal-footer">
                <button id="cancel-modify" type="button" class="btn btn-default btn-styles" data-dismiss="modal">Cancelar</button>
                <button id="accept-modify" type="button" class="btn btn-default btn-styles">Aceptar</button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </body> 
</html>
