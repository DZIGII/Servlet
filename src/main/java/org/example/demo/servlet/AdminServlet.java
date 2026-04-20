package org.example.demo.servlet;

import org.example.demo.pages.FoodPreviewPage;
import org.example.demo.repo.Repository;
import org.example.demo.repo.RepositoryProvider;
import org.example.demo.util.FileReader;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "adminServlet", value = "/admin")
public class AdminServlet extends HttpServlet {

    private FoodPreviewPage foodPreviewPage;
    private Repository repository;

    @Override
    public void init() {
        repository = RepositoryProvider.getRepository();
        foodPreviewPage = new FoodPreviewPage(repository);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");

        String password = request.getParameter("password");
        String password2;

        try {
            password2 = FileReader.readLines("password.txt").get(0);

            if (password == null || !password.equals(password2)) {
                response.getWriter().write("<h1>Neispravna lozinka</h1>");
                return;
            }

            response.getWriter().write(foodPreviewPage.foodPreviewHtml(password));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");

        String password = request.getParameter("password");
        String password2;

        try {
            password2 = FileReader.readLines("password.txt").get(0);

            if (password == null || !password.equals(password2)) {
                response.getWriter().write("<h1>Neispravna lozinka</h1>");
                return;
            }

            repository.clearAllMeals();
            AppState.incrementResetVersion();

            response.sendRedirect(request.getContextPath() + "/admin?password=" + password);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}