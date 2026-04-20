package org.example.demo.servlet;


import org.example.demo.database.Day;
import org.example.demo.pages.FoodChoicePage;
import org.example.demo.repo.Repository;
import org.example.demo.repo.RepositoryProvider;
import org.example.demo.repo.RepostioryImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "foodChoiceServlet", value = "/food-choice")
public class FoodChoiceServlet extends HttpServlet {


    private FoodChoicePage foodChoicePage;
    private Repository repository;

    public void init() {
        repository = RepositoryProvider.getRepository();

        foodChoicePage = new FoodChoicePage(repository);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        HttpSession session = request.getSession();

        Boolean ordered = (Boolean) session.getAttribute("ordered");
        Integer sessionVersion = (Integer) session.getAttribute("orderVersion");

        if (Boolean.TRUE.equals(ordered) && sessionVersion != null && sessionVersion == AppState.getResetVersion()) {
            response.getWriter().write(
                    "<!DOCTYPE html>" +
                            "<html><head><meta charset='UTF-8'></head><body>" +
                            "<h1>Vec ste napravili porudzbinu u ovoj sesiji.</h1>" +
                            "</body></html>"
            );
            return;
        }

        response.setContentType("text/html");
        try {

            response.getWriter().write(foodChoicePage.foodChoiceHtml());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {

            String ponedeljak = request.getParameter("ponedeljak");
            String utorak = request.getParameter("utorak");
            String sreda = request.getParameter("sreda");
            String cetvrtak = request.getParameter("cetvrtak");
            String petak = request.getParameter("petak");

            HttpSession session = request.getSession();

            if (ponedeljak == null || utorak == null || sreda == null || cetvrtak == null || petak == null) {
                response.getWriter().write(
                        "<!DOCTYPE html>" +
                                "<html><head><meta charset='UTF-8'></head><body>" +
                                "<h1>Sva polja su obavezna</h1>" +
                                "</body></html>"
                );
                return;
            }

            Boolean ordered = (Boolean) session.getAttribute("ordered");
            Integer sessionVersion = (Integer) session.getAttribute("orderVersion");

            if (Boolean.TRUE.equals(ordered) && sessionVersion != null && sessionVersion == AppState.getResetVersion()) {
                response.getWriter().write(
                        "<!DOCTYPE html>" +
                                "<html><head><meta charset='UTF-8'></head><body>" +
                                "<h1>Vec ste napravili porudzbinu u ovoj sesiji.</h1>" +
                                "</body></html>"
                );
                return;
            }

            session.setAttribute("ordered", true);
            session.setAttribute("orderVersion", AppState.getResetVersion());

            session.setAttribute("ponedeljak", ponedeljak);
            session.setAttribute("utorak", utorak);
            session.setAttribute("sreda", sreda);
            session.setAttribute("cetvrtak", cetvrtak);
            session.setAttribute("petak", petak);

            repository.addMeal(Day.Ponedeljak, ponedeljak);
            repository.addMeal(Day.Utorak, utorak);
            repository.addMeal(Day.Sreda, sreda);
            repository.addMeal(Day.Cetvrtak, cetvrtak);
            repository.addMeal(Day.Petak, petak);

            repository.printAll();

            response.sendRedirect("/sucsses-page");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void destroy() {

    }

}
