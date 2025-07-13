package com.mapa.controller;

import com.mapa.exception.LivroException;
import com.mapa.extra.GerarHtmlLivro;
import com.mapa.model.Livro;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "LivroServLet",value = "/Livros")

public class LivroServLet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private List<Livro> Livros;

    public LivroServLet() {
        super();
    }
    @Override
    public void init() {
        Livros = new ArrayList<Livro>();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String htmlLivros = GerarHtmlLivro.gerar(request,Livros);
        request.setAttribute("htmlLivros",htmlLivros);
        request.getRequestDispatcher("/view/index.jsp").forward(request, response);
        }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if("delete".equals(action)){
            String id = request.getParameter("id");
            if (id != null && !id.isEmpty()) {
                int idParse = Integer.parseInt(id);
                Livros.removeIf(livro -> livro.getId() == idParse);
            }
            response.sendRedirect(request.getContextPath() + "/livros");
            return;
        }

        try {
            Livro livro = new Livro();
            livro.setTitulo(request.getParameter("titulo").toUpperCase());
            livro.setAutor(request.getParameter("autor").toUpperCase());
            livro.setAno(Integer.parseInt(request.getParameter("ano")));
            livro.validar();
            Livros.add(livro);
            response.sendRedirect(request.getContextPath() + "/livros");
        } catch (NumberFormatException e) {
            request.setAttribute("mensagemErro", "Ano deve ser um número.");
            request.setAttribute("htmlLivros", GerarHtmlLivro.gerar(request, Livros));
            request.getRequestDispatcher("/view/index.jsp").forward(request, response);
        } catch (LivroException e) {
            request.setAttribute("mensagemErro", e.getMessage());
            request.setAttribute("htmlLivros",  GerarHtmlLivro.gerar(request, Livros));
            request.getRequestDispatcher("/view/index.jsp").forward(request, response);
        }

    }
}

