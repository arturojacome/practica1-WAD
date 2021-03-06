/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.controlador.web.categoria;

import com.ipn.mx.modelo.dao.CategoriaDAO;
import com.ipn.mx.modelo.dto.CategoriaDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author AMJac
 */
@WebServlet(name = "MostrarDatosCategoria", urlPatterns = {"/MostrarDatosCategoria"})
public class MostrarDatosCategoria extends HttpServlet {

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
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Lista de Categorias</title>");
            out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css' rel='stylesheet'>");
            out.println("<script src='https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js'></script>");
            out.println("<script src='https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js\'></script>");
            out.println("<script src='https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.min.js\'></script>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class='container'>");
            
            out.println(
                            "<nav class='navbar navbar-expand-lg navbar-dark bg-dark'>"
                        +       "<div class='container-fluid'>"
                        +           "<a class='navbar-brand' href='#'>P1 - 3CM13</a>"
                        +           "<button class='navbar-toggler' type='button' data-bs-toggle='collapse' data-bs-target='#navbarNav' aria-controls='navbarNav' aria-expanded='false' aria-label='Toggle navigation'>"
                        +               "<span class='navbar-toggler-icon'></span>"
                        +           "</button>"

                        +               "<div class='collapse navbar-collapse' id='navbarNav'>"

                        +                   "<ul class='navbar-nav'>"
                        +                       "<li class='nav-item'>"
                        +                           "<a class='nav-link' aria-current='page' href='index.html'>Home</a>"
                        +                       "</li>"

                        +                       "<li class='nav-item'>"
                        +                           "<a class='nav-link' href='TablasDeMultiplicar'>Tablas de multiplicar</a>"
                        +                       "</li>"

                        +                       "<li class='nav-item'>"
                        +                           "<a class='nav-link active' href='MostrarDatosCategoria'>Mostrar datos Categor??a</a>"
                        +                       "</li>"

                        +                       "<li class='nav-item'>"
                        +                           "<a class='nav-link' href='categoriaForm.html'>Agregar Categor??a</a>"
                        +                       "</li>"

                        +                       "<li class='nav-item'>"
                        +                           "<a class='nav-link' href='ProductoController?accion=listaDeProductos'>Mostrar datos Productos</a>"
                        +                       "</li>"
                                                
                        +                        "<li class='nav-item'>"
                        +                            "<a class='nav-link' aria-current='page' href='ProductoController?accion=nuevo'>Crear Producto</a>"
                        +                        "</li>"
                                    

                        +                   "</ul>"
                        +               "</div>"
                        +           "</div>"
                        +       "</nav>"
                            );
            
            out.println("<div class='card text-white  mb-3' style='width: 80rem; margin: auto; margin-top: 20px;'>");
                out.println("<div class='card-header ' style=' background-color: black; text-align: center;'>");
                    out.println("<h1>LISTA DE CATEGOR??AS</h1>");
                out.println("</div>");
                
                out.println("<div class='card-body' style='background-color: #352b2b;'>");
                    out.println("<blockquote class='blockquote mb-2'>");                        
                        out.println("<a style='margin-bottom: 20px; 'href='categoriaForm.html' class='btn btn-primary'> Agregar Categor??a </a>");
                        out.println("<table style='color: white; text-align: center;' class='table table-bordered'>");
                        out.println("<tr >");
                        out.println("<th> Clave </th> ");
                        out.println("<th> Nombre </th> ");
                        out.println("<th> Descripci??n </th> ");
                        out.println("<th> Eliminar </th> ");
                        out.println("<th> Actualizar </th> ");
                        out.println("</tr>");
                        
                        CategoriaDAO dao = new CategoriaDAO();
                        try {
                            List lista = dao.readAll();
                            for (int i = 0; i < lista.size(); i++) {
                                CategoriaDTO dto = (CategoriaDTO) lista.get(i);
                                out.println("<tr>");
                                out.println("<td>" + "<a href='VerCategoria?id=" + dto.getEntidad().getIdCategoria() + "' class='btn btn-warning'>" + dto.getEntidad().getIdCategoria() + "</a>" + "</td>");
                                out.println("<td>" + dto.getEntidad().getNombreCategoria() + "</td>");
                                out.println("<td>" + dto.getEntidad().getDescripcionCategoria() + "</td>");
                                out.println("<td>" + "<a href='EliminarCategoria?id=" + dto.getEntidad().getIdCategoria() + "' class='btn btn-danger'>Eliminar</a>" + "</td>");
                                out.println("<td>" + "<a href='ActualizarCategoria?id=" + dto.getEntidad().getIdCategoria() + "' class='btn btn-success'>Actualizar</a>" + "</td>");
                            }
                        } catch (SQLException ex) {
                            Logger.getLogger(MostrarDatosCategoria.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        out.println("</table>");
                        
                        
                        
                        
                    out.println("<blockquote>");
                out.println("</div>"); 
            out.println("</div>");  
            
            
            
            
            

            
            
            out.println("</div>");
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
