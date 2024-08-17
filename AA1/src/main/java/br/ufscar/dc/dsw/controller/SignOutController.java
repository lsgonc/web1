package br.ufscar.dc.dsw.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.ufscar.dc.dsw.domain.Usuario;


import java.io.IOException;

@WebServlet("/signout")
public class SignOutController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogado");
        
        if(usuario == null)
        {
            response.sendRedirect("/ClinicaMedica/login");
        }
        else 
        {
            HttpSession ses = request.getSession();
            ses.invalidate();

            response.sendRedirect(request.getContextPath());
        }
    }
}
