/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.controlador.web.producto;

import com.ipn.mx.modelo.dao.ProductoDAO;
import com.ipn.mx.modelo.dto.ProductoDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author AMJac
 */
@WebServlet(name = "ProductoController", urlPatterns = {"/ProductoController"})
public class ProductoController extends HttpServlet {

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

        String accion = request.getParameter("accion");

        if (accion.equals("listaDeProductos")) {
            listaDeProductos(request, response);
        } else {
            if (accion.equals("nuevo")) {
                nuevoProducto(request, response);
            } else {
                if (accion.equals("eliminar")) {
                    eliminarProducto(request, response);
                } else {
                    if (accion.equals("actualizar")) {
                        actualizarProducto(request, response);
                    } else {
                        if (accion.equals("ver")) {
                            verProducto(request, response);
                        } else {
                            if (accion.equals("guardar")) {
                                almacenarProducto(request, response);
                            } else {
                                if (accion.equals("actualiza")) {
                                    actualizaProducto(request, response);
                                }
                            }
                        }
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

    private void listaDeProductos(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");

        try ( PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>ProductoController</title>");

            out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css' rel='stylesheet'>");
            out.println("<script src='https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js'></script>");

            out.println("<script src='https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js'></script>");
            out.println("<script src='https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.min.js'></script>");

            out.println("</head>");
            out.println("<body>");

            out.println("<div class='container'>");
            out.println(
                    "<nav class='navbar navbar-expand-lg navbar-dark bg-dark'>"
                    + "<div class='container-fluid'>"
                    + "<a class='navbar-brand' href='#'>P1 - 3CM13</a>"
                    + "<button class='navbar-toggler' type='button' data-bs-toggle='collapse' data-bs-target='#navbarNav' aria-controls='navbarNav' aria-expanded='false' aria-label='Toggle navigation'>"
                    + "<span class='navbar-toggler-icon'></span>"
                    + "</button>"
                    + "<div class='collapse navbar-collapse' id='navbarNav'>"
                    + "<ul class='navbar-nav'>"
                    + "<li class='nav-item'>"
                    + "<a class='nav-link' aria-current='page' href='index.html'>Home</a>"
                    + "</li>"
                    + "<li class='nav-item'>"
                    + "<a class='nav-link' href='TablasDeMultiplicar'>Tablas de multiplicar</a>"
                    + "</li>"
                    + "<li class='nav-item'>"
                    + "<a class='nav-link' href='MostrarDatosCategoria'>Mostrar datos Categoria</a>"
                    + "</li>"
                    + "<li class='nav-item'>"
                    + "<a class='nav-link' href='categoriaForm.html'>Agragar Categor??a</a>"
                    + "</li>"
                    + "<li class='nav-item'>"
                    + "<a class='nav-link active' href='ProductoController?accion=listaDeProductos'>Mostrar datos Productos</a>"
                    + "</li>"
                    + "<li class='nav-item'>"
                    + "<a class='nav-link' aria-current='page' href='ProductoController?accion=nuevo'>Crear Producto</a>"
                    + "</li>"
                    + "</ul>"
                    + "</div>"
                    + "</div>"
                    + "</nav>"
            );

            out.println("<div class='card text-white  mb-3' style='width: 80rem; margin: auto; margin-top: 20px;'>");
            out.println("<div class='card-header ' style=' background-color: black; text-align: center;'>");
            out.println("<h1>LISTA DE PRODUCTOS</h1>");
            out.println("</div>");

            out.println("<div class='card-body' style='background-color: #352b2b;'>");
            out.println("<blockquote class='blockquote mb-2'>");
            out.println("<a style='margin-bottom: 20px;' href='productoForm.html' class='btn btn-primary'> Crear Producto </a>");
            out.println("<table style='color: white; text-align: center;' class='table table-bordered'>");
            out.println("<thead>");
            out.println("<tr>");
            out.println("<td> Clave Producto </td>");
            out.println("<td> Nombre Producto </td>");
            out.println("<td> Descripcion  </td>");
            out.println("<td> Precio  </td>");
            out.println("<td> Existencias </td>");
            out.println("<td> Stock </td>");
            out.println("<td> Categoria </td>");
            out.println("<td> Eliminar </td>");
            out.println("<td> Actualizar </td>");
            out.println("</tr>");
            out.println("</thead>");

            out.println("<tbody>");

            ProductoDAO dao = new ProductoDAO();

            try {
                List lista = dao.readAll();

                for (int i = 0; i < lista.size(); i++) {
                    ProductoDTO dto = (ProductoDTO) lista.get(i);

                    out.println("<tr>");
                    out.println("<td><a href='ProductoController?accion=ver&id=" + dto.getEntidad().getIdProducto()
                            + "' class='btn btn-warning'>" + dto.getEntidad().getIdProducto() + "</a></td>");
                    out.println("<td>" + dto.getEntidad().getNombreProducto() + "</td>");
                    out.println("<td>" + dto.getEntidad().getDescripcionProducto() + "</td>");
                    out.println("<td>" + dto.getEntidad().getPrecio() + "</td>");
                    out.println("<td>" + dto.getEntidad().getExistencia() + "</td>");
                    out.println("<td>" + dto.getEntidad().getStockMinimo() + "</td>");
                    out.println("<td>" + dto.getEntidad().getClaveCategoria() + "</td>");

                    out.println("<td><a href='ProductoController?accion=eliminar&id=" + dto.getEntidad().getIdProducto()
                            + "' class='btn btn-danger'>Eliminar</a></td>");
                    out.println("<td><a href='ProductoController?accion=actualizar&id=" + dto.getEntidad().getIdProducto()
                            + "' class='btn btn-success'>Actualizar</a></td>");
                    out.println("</tr>");
                }

            } catch (SQLException ex) {
                out.println("<h4>Ocurrio un error...</h4>");
                Logger.getLogger(ProductoController.class.getName()).log(Level.SEVERE, null, ex);
            }
                        out.println("</tbody>");
            out.println("</table>");

           


            out.println("</div>");
            
            out.println("</div>");



            
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    private void nuevoProducto(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher rd = request.getRequestDispatcher("productoForm.html");
        try {
            rd.forward(request, response);

        } catch (ServletException | IOException ex) {
            Logger.getLogger(ProductoController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void actualizarProducto(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");

        try ( PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Actualizar Producto</title>");

            out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css' rel='stylesheet'>");
            out.println("<script src='https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js'></script>");

            out.println("<script src='https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js'></script>");
            out.println("<script src='https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.min.js'></script>");

            out.println("</head>");
            out.println("<body>");

            out.println("<div class='container'>");
            out.println(
                    "<nav class='navbar navbar-expand-lg navbar-dark bg-dark'>"
                    + "<div class='container-fluid'>"
                    + "<a class='navbar-brand' href='#'>P1 - 3CM13</a>"
                    + "<button class='navbar-toggler' type='button' data-bs-toggle='collapse' data-bs-target='#navbarNav' aria-controls='navbarNav' aria-expanded='false' aria-label='Toggle navigation'>"
                    + "<span class='navbar-toggler-icon'></span>"
                    + "</button>"
                    + "<div class='collapse navbar-collapse' id='navbarNav'>"
                    + "<ul class='navbar-nav'>"
                    + "<li class='nav-item'>"
                    + "<a class='nav-link' aria-current='page' href='index.html'>Home</a>"
                    + "</li>"
                    + "<li class='nav-item'>"
                    + "<a class='nav-link' href='TablasDeMultiplicar'>Tablas de multiplicar</a>"
                    + "</li>"
                    + "<li class='nav-item'>"
                    + "<a class='nav-link' href='MostrarDatosCategoria'>Mostrar datos Categoria</a>"
                    + "</li>"
                    + "<li class='nav-item'>"
                    + "<a class='nav-link' href='categoriaForm.html'>Agragar Categor??a</a>"
                    + "</li>"
                    + "<li class='nav-item'>"
                    + "<a class='nav-link active' href='ProductoController?accion=listaDeProductos'>Mostrar datos Productos</a>"
                    + "</li>"
                    + "<li class='nav-item'>"
                    + "<a class='nav-link' aria-current='page' href='ProductoController?accion=nuevo'>Crear Producto</a>"
                    + "</li>"
                    + "</ul>"
                    + "</div>"
                    + "</div>"
                    + "</nav>"
            );

            String msg = "";
            ProductoDAO dao = new ProductoDAO();
            ProductoDTO dto = new ProductoDTO();

            dto.getEntidad().setIdProducto(Integer.parseInt(request.getParameter("id")));

            try {
                dto = dao.read(dto);
                Logger.getLogger(ProductoController.class.getName()).log(Level.SEVERE, null, "Producto encontrado: " + dto.getEntidad().toString());
            } catch (SQLException ex) {
                Logger.getLogger(ProductoController.class.getName()).log(Level.SEVERE, null, ex);
            }

            out.println("<div class='card text-white  mb-3' style='width: 30rem; margin: auto; margin-top: 20px;'>");
            out.println("<div class='card-header ' style=' background-color: black; text-align: center;'>");
            out.println("<h1>" + "Actualizar Producto" + "</h1>");
            out.println("</div>");

            out.println("<div class='card-body' style='background-color: #352b2b; text-align: center;'>");
            
            if (dto != null) {
                
                out.println("<form name='frmDataAct' method='post' action='ProductoController?accion=actualiza'>");
                
                out.println("<table style='color: white'class='table table-striped>'>");
                out.println("<thead>");
                out.println("<tr>");
                out.println("<th> Clave Producto</th>");
                out.println("<td><input class='form-control' type='number' name='txtIdProducto' placeholder='Clave del producto' value = '" + dto.getEntidad().getIdProducto() + "' required readonly='true'/></td>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("<th> Nombre Producto </th>");
                out.println("<td><input class='form-control' type='text' name='txtNombre' placeholder='Nombre del producto' value = '" + dto.getEntidad().getNombreProducto() + "'required/></td>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("<th> Dscripcion  </th>");
                out.println("<td><input class='form-control' type='text' name='txtDescripcion' placeholder='Descripcion del producto' value = '" + dto.getEntidad().getDescripcionProducto() + "'required/></td>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("<th> Precio  </th>");
                out.println("<td><input class='form-control' type='number' name='txtPrecio' placeholder='Precio del producto' value = '" + dto.getEntidad().getPrecio() + "'required/></td>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("<th> Existencias </th>");
                out.println("<td><input class='form-control' type='number' name='txtExistencia' placeholder='Precio del producto' value = '" + dto.getEntidad().getExistencia() + "'required/></td>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("<th> Stock </th>");
                out.println("<td><input class='form-control' type='number' name='txtStock' placeholder='Precio del producto' value = '" + dto.getEntidad().getStockMinimo() + "'required/></td>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("<th> Categoria </th>");
                out.println("<td><input class='form-control' type='number' name='txtClaveCategoria' placeholder='Precio del producto' value = '" + dto.getEntidad().getClaveCategoria() + "'required/></td>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("</table>");
                out.println("<br/>");
                out.println("<input class='btn btn-primary' type='submit' value='Actualizar' name='btnEnviar'/>");
                out.println("</form>");
            }
            
            out.println("<a style='margin-top: 20px;' class='btn btn-success' href='ProductoController?accion=listaDeProductos'>Lista de Productos</a>");

            out.println("</div>");
                
            out.println("</div>");
                
            out.println("</div>");

            out.println("</body>");
            out.println("</html>");
        }
    }

    private void actualizaProducto(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Actualizacion Producto</title>");

            out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css' rel='stylesheet'>");
            out.println("<script src='https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js'></script>");

            out.println("<script src='https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js'></script>");
            out.println("<script src='https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.min.js'></script>");

            out.println("</head>");
            out.println("<body>");

            out.println("<div class='container'>");

            out.println(
                    "<nav class='navbar navbar-expand-lg navbar-dark bg-dark'>"
                    + "<div class='container-fluid'>"
                    + "<a class='navbar-brand' href='#'>P1 - 3CM13</a>"
                    + "<button class='navbar-toggler' type='button' data-bs-toggle='collapse' data-bs-target='#navbarNav' aria-controls='navbarNav' aria-expanded='false' aria-label='Toggle navigation'>"
                    + "<span class='navbar-toggler-icon'></span>"
                    + "</button>"
                    + "<div class='collapse navbar-collapse' id='navbarNav'>"
                    + "<ul class='navbar-nav'>"
                    + "<li class='nav-item'>"
                    + "<a class='nav-link' aria-current='page' href='index.html'>Home</a>"
                    + "</li>"
                    + "<li class='nav-item'>"
                    + "<a class='nav-link' href='TablasDeMultiplicar'>Tablas de multiplicar</a>"
                    + "</li>"
                    + "<li class='nav-item'>"
                    + "<a class='nav-link' href='MostrarDatosCategoria'>Mostrar datos Categoria</a>"
                    + "</li>"
                    + "<li class='nav-item'>"
                    + "<a class='nav-link' href='categoriaForm.html'>Agragar Categor??a</a>"
                    + "</li>"
                    + "<li class='nav-item'>"
                    + "<a class='nav-link active' href='ProductoController?accion=listaDeProductos'>Mostrar datos Productos</a>"
                    + "</li>"
                    + "<li class='nav-item'>"
                    + "<a class='nav-link' aria-current='page' href='ProductoController?accion=nuevo'>Crear Producto</a>"
                    + "</li>"
                    + "</ul>"
                    + "</div>"
                    + "</div>"
                    + "</nav>"
            );

            ProductoDAO dao = new ProductoDAO();
            ProductoDTO dto = new ProductoDTO();
            String msg = "";

            dto.getEntidad().setIdProducto(Integer.parseInt(request.getParameter("txtIdProducto")));
            dto.getEntidad().setNombreProducto(request.getParameter("txtNombre"));
            dto.getEntidad().setDescripcionProducto(request.getParameter("txtDescripcion"));
            dto.getEntidad().setPrecio(Float.parseFloat(request.getParameter("txtPrecio")));
            dto.getEntidad().setExistencia(Integer.parseInt(request.getParameter("txtExistencia")));
            dto.getEntidad().setStockMinimo(Integer.parseInt(request.getParameter("txtStock")));
            dto.getEntidad().setClaveCategoria(Integer.parseInt(request.getParameter("txtClaveCategoria")));

            try {
                dao.update(dto);
                msg = "Registro actualizado";
            } catch (SQLException ex) {
                Logger.getLogger(ProductoController.class.getName()).log(Level.SEVERE, null, ex);
            }

           
            
            
            
            out.println("<div class='card text-white  mb-3' style='width: 35rem; margin: auto; margin-top: 20px;'>");
                out.println("<div class='card-header ' style=' background-color: black; text-align: center;'>");
                    out.println("<h1>Registro actualizado Satisfactoriamente</h1>");
                out.println("</div>");
                
                out.println("<div class='card-body' style='background-color: #352b2b; text-align: center;'>");
                    out.println("<b>" + msg + "</b>");
                    out.println("<br/><a style='margin-top:15px;' class='btn btn-primary' href='ProductoController?accion=listaDeProductos'>Listado de Productos</a>");                    
                out.println("</div>"); 
               
            out.println("</div>");  

            out.println("</div>");

            out.println("</body>");
            out.println("</html>");
        }

    }

    private void verProducto(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");

        try ( PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Ver Producto</title>");

            out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css' rel='stylesheet'>");
            out.println("<script src='https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js'></script>");

            out.println("<script src='https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js'></script>");
            out.println("<script src='https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.min.js'></script>");

            out.println("</head>");
            out.println("<body>");

            out.println("<div class='container'>");

            out.println(
                    "<nav class='navbar navbar-expand-lg navbar-dark bg-dark'>"
                    + "<div class='container-fluid'>"
                    + "<a class='navbar-brand' href='#'>P1 - 3CM13</a>"
                    + "<button class='navbar-toggler' type='button' data-bs-toggle='collapse' data-bs-target='#navbarNav' aria-controls='navbarNav' aria-expanded='false' aria-label='Toggle navigation'>"
                    + "<span class='navbar-toggler-icon'></span>"
                    + "</button>"
                    + "<div class='collapse navbar-collapse' id='navbarNav'>"
                    + "<ul class='navbar-nav'>"
                    + "<li class='nav-item'>"
                    + "<a class='nav-link' aria-current='page' href='index.html'>Home</a>"
                    + "</li>"
                    + "<li class='nav-item'>"
                    + "<a class='nav-link' href='TablasDeMultiplicar'>Tablas de multiplicar</a>"
                    + "</li>"
                    + "<li class='nav-item'>"
                    + "<a class='nav-link' href='MostrarDatosCategoria'>Mostrar datos Categoria</a>"
                    + "</li>"
                    + "<li class='nav-item'>"
                    + "<a class='nav-link' href='categoriaForm.html'>Agragar Categor??a</a>"
                    + "</li>"
                    + "<li class='nav-item'>"
                    + "<a class='nav-link active' href='ProductoController?accion=listaDeProductos'>Mostrar datos Productos</a>"
                    + "</li>"
                    + "<li class='nav-item'>"
                    + "<a class='nav-link' aria-current='page' href='ProductoController?accion=nuevo'>Crear Producto</a>"
                    + "</li>"
                    + "</ul>"
                    + "</div>"
                    + "</div>"
                    + "</nav>"
            );

            String msg = "";
            ProductoDAO dao = new ProductoDAO();
            ProductoDTO dto = new ProductoDTO();
            dto.getEntidad().setIdProducto(Integer.parseInt(request.getParameter("id")));

            try {
                dto = dao.read(dto);
            } catch (SQLException ex) {
                Logger.getLogger(ProductoController.class.getName()).log(Level.SEVERE, null, ex);
            }

            out.println("<div class='card text-white  mb-3' style='width: 25rem; margin: auto; margin-top: 20px;'>");
            out.println("<div class='card-header ' style=' background-color: black; text-align: center;'>");
            out.println("<h1>VER CATEGOR??A</h1>");
            out.println("</div>");

            out.println("<div class='card-body' style='background-color: #352b2b; text-align: center;'>");
            if (dto != null) {
                out.println("<table  style='color: white;' class='table table-striped'>");
                out.println("<thead>");
                out.println("<tr>");
                out.println("<th> Clave Producto</th>");
                out.println("<td>" + dto.getEntidad().getIdProducto() + "</td>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("<th> Nombre Producto </th>");
                out.println("<td>" + dto.getEntidad().getNombreProducto() + "</td>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("<th> Dscripcion  </th>");
                out.println("<td>" + dto.getEntidad().getDescripcionProducto() + "</td>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("<th> Precio  </th>");
                out.println("<td>" + dto.getEntidad().getPrecio() + "</td>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("<th> Existencias </th>");
                out.println("<td>" + dto.getEntidad().getExistencia() + "</td>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("<th> Stock </th>");
                out.println("<td>" + dto.getEntidad().getStockMinimo() + "</td>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("<th> Categoria </th>");
                out.println("<td>" + dto.getEntidad().getClaveCategoria() + "</td>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("</table>");
            }

            out.println("<a class='btn btn-success' href='ProductoController?accion=listaDeProductos'>Lista de Productos</a>");
            out.println("</div>");

            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    private void almacenarProducto(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Guardar Producto</title>");

            out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css' rel='stylesheet'>");
            out.println("<script src='https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js'></script>");

            out.println("<script src='https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js'></script>");
            out.println("<script src='https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.min.js'></script>");

            out.println("</head>");
            out.println("<body>");

            out.println("<div class='container'>");

            out.println(
                    "<nav class='navbar navbar-expand-lg navbar-dark bg-dark'>"
                    + "<div class='container-fluid'>"
                    + "<a class='navbar-brand' href='#'>P1 - 3CM13</a>"
                    + "<button class='navbar-toggler' type='button' data-bs-toggle='collapse' data-bs-target='#navbarNav' aria-controls='navbarNav' aria-expanded='false' aria-label='Toggle navigation'>"
                    + "<span class='navbar-toggler-icon'></span>"
                    + "</button>"
                    + "<div class='collapse navbar-collapse' id='navbarNav'>"
                    + "<ul class='navbar-nav'>"
                    + "<li class='nav-item'>"
                    + "<a class='nav-link' aria-current='page' href='index.html'>Home</a>"
                    + "</li>"
                    + "<li class='nav-item'>"
                    + "<a class='nav-link' href='TablasDeMultiplicar'>Tablas de multiplicar</a>"
                    + "</li>"
                    + "<li class='nav-item'>"
                    + "<a class='nav-link' href='MostrarDatosCategoria'>Mostrar datos Categoria</a>"
                    + "</li>"
                    + "<li class='nav-item'>"
                    + "<a class='nav-link' href='categoriaForm.html'>Agragar Categor??a</a>"
                    + "</li>"
                    + "<li class='nav-item'>"
                    + "<a class='nav-link' href='ProductoController?accion=listaDeProductos'>Mostrar datos Productos</a>"
                    + "</li>"
                    + "<li class='nav-item'>"
                    + "<a class='nav-link active' aria-current='page' href='ProductoController?accion=nuevo'>Crear Producto</a>"
                    + "</li>"
                    + "</ul>"
                    + "</div>"
                    + "</div>"
                    + "</nav>"
            );
            String msg = "Error en la accion.";
            ProductoDAO dao = new ProductoDAO();
            ProductoDTO dto = new ProductoDTO();

            dto.getEntidad().setNombreProducto(request.getParameter("txtNombreProducto"));
            dto.getEntidad().setDescripcionProducto(request.getParameter("txtDescripcion"));
            dto.getEntidad().setExistencia(Integer.parseInt(request.getParameter("txtExistencia")));
            dto.getEntidad().setPrecio(Float.parseFloat(request.getParameter("txtPrecio")));
            dto.getEntidad().setStockMinimo(Integer.parseInt(request.getParameter("txtStockMinimo")));
            dto.getEntidad().setClaveCategoria(Integer.parseInt(request.getParameter("txtClaveCategoria")));

            try {
                dao.create(dto);
//                Logger.getLogger(ProductoController.class.getName()).log(Level.INFO, null, "Producto almacenado");

                msg = "Producto almacenado";
            } catch (SQLException ex) {
                Logger.getLogger(ProductoController.class.getName()).log(Level.SEVERE, null, ex);
            }           
            
            out.println("<div class='card text-white  mb-3' style='width: 25rem; margin: auto; margin-top: 20px;'>");
                out.println("<div class='card-header ' style=' background-color: black; text-align: center;'>");
                    out.println("<h1>" + msg + "</h1>");
                out.println("</div>");

                out.println("<div class='card-body' style='background-color: #352b2b; text-align: center;'>");
                    
                    out.println("<a href='ProductoController?accion=listaDeProductos' class='btn btn-primary'> Lista de Productos </a>");
                out.println("</div>");

            out.println("</div>");

            out.println("</div>");
            out.println("</center>");

            out.println("</body>");
            out.println("</html>");
        }

    }

    private void eliminarProducto(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ProductoDAO dao = new ProductoDAO();
        ProductoDTO dto = new ProductoDTO();
        response.setContentType("text/html;charset=UTF-8");

        dto.getEntidad().setIdProducto(Integer.parseInt(request.getParameter("id")));

        String msg = "";

        try {
            dao.delete(dto);
            msg = "Registro eliminado.";

        } catch (SQLException ex) {
            Logger.getLogger(ProductoController.class.getName()).log(Level.SEVERE, null, ex);
        }

        try ( PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Resultado de Operacion</title>");

            out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css' rel='stylesheet'>");
            out.println("<script src='https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js'></script>");

            out.println("<script src='https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js'></script>");
            out.println("<script src='https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.min.js'></script>");

            out.println("</head>");
            out.println("<body>");

            out.println("<center>");
            out.println("<div class='container'>");
            out.println(
                    "<nav class='navbar navbar-expand-lg navbar-dark bg-dark'>"
                    + "<div class='container-fluid'>"
                    + "<a class='navbar-brand' href='#'>P1 - 3CM13</a>"
                    + "<button class='navbar-toggler' type='button' data-bs-toggle='collapse' data-bs-target='#navbarNav' aria-controls='navbarNav' aria-expanded='false' aria-label='Toggle navigation'>"
                    + "<span class='navbar-toggler-icon'></span>"
                    + "</button>"
                    + "<div class='collapse navbar-collapse' id='navbarNav'>"
                    + "<ul class='navbar-nav'>"
                    + "<li class='nav-item'>"
                    + "<a class='nav-link' aria-current='page' href='index.html'>Home</a>"
                    + "</li>"
                    + "<li class='nav-item'>"
                    + "<a class='nav-link' href='TablasDeMultiplicar'>Tablas de multiplicar</a>"
                    + "</li>"
                    + "<li class='nav-item'>"
                    + "<a class='nav-link' href='MostrarDatosCategoria'>Mostrar datos Categoria</a>"
                    + "</li>"
                    + "<li class='nav-item'>"
                    + "<a class='nav-link' href='categoriaForm.html'>Agragar Categor??a</a>"
                    + "</li>"
                    + "<li class='nav-item'>"
                    + "<a class='nav-link active' href='ProductoController?accion=listaDeProductos'>Mostrar datos Productos</a>"
                    + "</li>"
                    + "<li class='nav-item'>"
                    + "<a class='nav-link' aria-current='page' href='ProductoController?accion=nuevo'>Crear Producto</a>"
                    + "</li>"
                    + "</ul>"
                    + "</div>"
                    + "</div>"
                    + "</nav>"
            );

            out.println("<div class='card text-white  mb-3' style='width: 25rem; margin: auto; margin-top: 20px;'>");
                out.println("<div class='card-header ' style=' background-color: black; text-align: center;'>");
                    out.println("<h1>" + "Registro eliminado Satisfactoriamente" + "</h1>");
                out.println("</div>");

                out.println("<div class='card-body' style='background-color: #352b2b; text-align: center;'>");
                    
                    out.println("<a href='ProductoController?accion=listaDeProductos' class='btn btn-primary'> Lista de Productos </a>");
                out.println("</div>");

            out.println("</div>");

            out.println("</div>");

            out.println("</body>");
            out.println("</html>");

        }
    }

}
