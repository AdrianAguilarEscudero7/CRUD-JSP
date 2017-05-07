/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function() {
  
  $("#video-content").html("<video autoplay loop>\n\
    <source src='videos/SkntVideo.mp4' type='video/mp4'>\n\
    <source src='videos/SkntVideo.webm' type='video/webm'>\n\
    Su navegador no admite la etiqueta video de HTML5</video>"); 
  
// ----- INDEX -----
  $("#enter").click(function() {
    $("#enter").attr("disabled", true);
    $("#video-content").slideUp(2000);
    $("#view-content").load("view.jsp");
  });
  
  $("#exit").click(function() {
    window.location.href='https://www.google.es';
  });
// ----- VIEW -----
// ------------------- ADD OPTION -------------------------------- //  
  $(".add-btn").click(function() {
    
    var datos = {
      zona : 'add',
      marca : $("#marca").val(),
      modelo : $("#modelo").val(),
      conect : $("#conect").val(),
      tipo : $("#tipo").val(),
      precio : $("#precio").val()
    };

    $.post("SknServlet",datos,addResponse);
    
    $("#marca").val("");
    $("#modelo").val("");
    $("#conect").val("");
    $("#tipo").val("");
    $("#precio").val(null);
  });

  function addResponse(response) {
    if (response === 'ok') {
      $("#myModal .modal-body").text("Teclado introducido correctamente.");
      $("#myModal").modal({
        backdrop: 'static'
      });
      $("#close-modal").click(function() {
        $("#view-content").load("view.jsp");
        $(".modal-backdrop").remove();
      });
    } else if (response === 'existe') {
      $("#myModal .modal-body").text("Ya dispone de un teclado igual, se acumulará con el existente.");
      $("#myModal").modal({
        backdrop: 'static'
      });
      $("#close-modal").click(function() {
        $("#view-content").load("view.jsp");
        $(".modal-backdrop").remove();
      });
    } else if (response === 'no') {
      $("#myModal .modal-body").text("Introduzca al menos la marca, el modelo y el precio.");
      $("#myModal").modal();
    } else {
      $("#myModal .modal-body").text("Se produjo un error al intentar introducir el teclado.");
      $("#myModal").modal();
    }
  }

// ------------------- MODIFY OPTION -------------------------------- //
  $(".modify-btn").click(function() {
    var idVal = $(this).val();
    $("#modify-modal .modal-title").text("Modificando teclado con ID, "+idVal);
    $("#modify-modal").modal({
      backdrop: 'static'
    });
    
    $("#cancel-modify").click(function() {
        $("#cantidad-modify").val("");
        $("#marca-modify").val("");
        $("#modelo-modify").val("");
        $("#conect-modify").val("");
        $("#tipo-modify").val("");
        $("#precio-modify").val("");
      });
    
    $("#accept-modify").click(function() {
      var datos = {
        zona : 'modify', 
        id : idVal,
        cantidad : $("#cantidad-modify").val(),
        marca : $("#marca-modify").val(),
        modelo : $("#modelo-modify").val(),
        conect : $("#conect-modify").val(),
        tipo : $("#tipo-modify").val(),
        precio : $("#precio-modify").val()  
      };
      $.post("SknServlet",datos,modifyResponse);
      
      $("#modify-modal").modal('hide');
      
      $("#cantidad-modify").val("");
      $("#marca-modify").val("");
      $("#modelo-modify").val("");
      $("#conect-modify").val("");
      $("#tipo-modify").val("");
      $("#precio-modify").val("");
    });
  });

  function modifyResponse(response) {
    if (response === 'ok') {
      $("#myModal .modal-body").text("Teclado modificado correctamente.");
      $("#myModal").modal({
        backdrop: 'static'
      });
      $("#close-modal").click(function() {
        $("#view-content").load("view.jsp");
        $(".modal-backdrop").remove();
      });
    } else if (response === 'no') {
      $("#myModal .modal-body").text("Introduzca al menos la marca, el modelo y el precio.");
      $("#myModal").modal();
    } else {
      $("#myModal .modal-body").text("Se produjo un error al intentar modificar el teclado.");
      $("#myModal").modal();
    }
  }

// ------------------- DELETE OPTION -------------------------------- //
  $(".delete-btn").click(function() {

    if (confirm("¿Seguro/a que desea eliminar el teclado con ID, "
            +$(this).val()+"?.")) {

      var datos = {zona : 'delete', id : $(this).val()};
      $.post("SknServlet",datos,deleteResponse);
    }

    function deleteResponse(response) {
      if (response === 'ok') {
        $("#myModal .modal-body").text("Teclado eliminado correctamente.");
        $("#myModal").modal({
          backdrop: 'static'
        });
        $("#close-modal").click(function() {
          $("#view-content").load("view.jsp");
          $(".modal-backdrop").remove();
        });
      } else if (response === 'existe') {
        $("#myModal .modal-body").text("Teclado eliminado correctamente.");
        $("#myModal").modal({
          backdrop: 'static'
        });
        $("#close-modal").click(function() {
          $("#view-content").load("view.jsp");
          $(".modal-backdrop").remove();
        });
      } else {
        $("#myModal .modal-body").text("Se produjo un error al intentar eliminar el teclado.");
        $("#myModal").modal();
      }
    }
  });
});