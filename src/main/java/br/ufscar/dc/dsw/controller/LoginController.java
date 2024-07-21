package br.ufscar.dc.dsw.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufscar.dc.dsw.dao.UsuarioDAO;
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.utils.Erro;

import java.io.IOException;

@WebServlet("/login")
public class LoginController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        Erro errosEmail = new Erro();
        Erro errosSenha = new Erro();

        String email = request.getParameter("email");
        String senha = request.getParameter("senha");

        if (email == null || email.isEmpty()) {
            errosEmail.add("Email não informado!");
        }
        if (senha == null || senha.isEmpty()) {
            errosSenha.add("Senha não informada!");
        }
        
        if(!errosEmail.isExisteErros() && !errosSenha.isExisteErros())
        {
            UsuarioDAO userDAO = new UsuarioDAO();
            Usuario user = userDAO.get(email);


            if(user != null)
            {
                if (user.getSenha().equals(senha))
                {
                    request.getSession().setAttribute("usuarioLogado", user);
                    if(user.getTipoUsuario().equals("admin"))
                    {
                        response.sendRedirect("/ClinicaMedica/admin");
                    } else if (user.getTipoUsuario().equals("paciente"))
                    {
                        response.sendRedirect("/ClinicaMedica/paciente");
                    }
                    else{
                        response.sendRedirect("/ClinicaMedica/medico");
                    } 

                    return;
                } else {
                    errosSenha.add("Senha incorreta!");
                }
            } else
            {
                errosEmail.add("Nenhum usuário cadastrado com este email!");
            }
        }    
    
        
        request.getSession().invalidate();
        request.setAttribute("mensagensEmail", errosEmail);
        request.setAttribute("mensagensSenha", errosSenha);

        String URL = "/login/";
        RequestDispatcher rd = request.getRequestDispatcher(URL);
		rd.forward(request, response);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogado");
    	
    	if (usuario != null) {
    		if (usuario.getTipoUsuario().equals("admin"))
                response.sendRedirect(request.getContextPath() + "/admin");
            else if (usuario.getTipoUsuario().equals("paciente"))
                response.sendRedirect(request.getContextPath() + "/paciente");
            else if (usuario.getTipoUsuario().equals("medico"))
                response.sendRedirect(request.getContextPath() + "/medico");
        } 
        else
        {
            request.getRequestDispatcher("/login/index.jsp").forward(request, response);
        }
            

    }
}
