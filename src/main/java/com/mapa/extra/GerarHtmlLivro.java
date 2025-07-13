package com.mapa.extra;

import com.mapa.model.Livro;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public class GerarHtmlLivro {
    public static String gerar(HttpServletRequest request, List<Livro> Livros) {
        StringBuilder htmlLivros = new StringBuilder();
        htmlLivros.append("<h2>Livros cadastrados:</h2>");
        for (Livro livro : Livros) {
            htmlLivros
                    .append("<div class=\"lista\">")
                    //Lista dos Livros
                    .append("<p><strong> Titulo: </strong>" + livro.getTitulo() + "</p>")
                    .append("<p><strong> Autor: </strong>" + livro.getAutor() + "</p>")
                    .append("<p><strong> Ano: </strong>" + livro.getAno() + "</p>")
                    .append("<p><strong> ISBN: </strong>" + livro.getIsbn() + "</p>")
                    .append("<p><strong> id: </strong>" + livro.getId() + "</p>")

                    //Botao para excluir
                    .append("<form class=\"delete-form\" method='post' action='" + request.getContextPath() + "/Livros' style='display:inline;'> ")
                    .append("<input type='hidden' name='action' value='delete' />")
                    .append("<input type='hidden' name='id' value='" + livro.getId() + "' />")
                    .append("<button type='submit'>EXCLUIR</button>")
                    .append("</form>")
                    .append("</div>");
        }
        return htmlLivros.toString();
    }
}