package org.example.demo.pages;

import org.example.demo.database.Day;
import org.example.demo.repo.Repository;

import java.util.Map;

public class FoodPreviewPage {

    private Repository repository;

    public FoodPreviewPage(Repository repository) {
        this.repository = repository;
    }

    public String foodPreviewHtml(String password) {
        StringBuilder html = new StringBuilder();

        html.append("<!DOCTYPE html>\n");
        html.append("<html lang=\"sr\">\n");
        html.append("<head>\n");
        html.append("    <meta charset=\"UTF-8\">\n");
        html.append("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n");
        html.append("    <title>Odabrana jela</title>\n");
        html.append("    <style>\n");
        html.append("        * {\n");
        html.append("            box-sizing: border-box;\n");
        html.append("            margin: 0;\n");
        html.append("            padding: 0;\n");
        html.append("        }\n");
        html.append("\n");
        html.append("        body {\n");
        html.append("            font-family: Arial, sans-serif;\n");
        html.append("            background: #f3f3f3;\n");
        html.append("            color: #222;\n");
        html.append("        }\n");
        html.append("\n");
        html.append("        .container {\n");
        html.append("            max-width: 760px;\n");
        html.append("            margin: 0 auto;\n");
        html.append("            padding: 20px 28px 40px 28px;\n");
        html.append("        }\n");
        html.append("\n");
        html.append("        .top-bar {\n");
        html.append("            position: relative;\n");
        html.append("            margin-bottom: 30px;\n");
        html.append("        }\n");
        html.append("\n");
        html.append("        h1 {\n");
        html.append("            text-align: center;\n");
        html.append("            font-size: 28px;\n");
        html.append("            font-weight: 700;\n");
        html.append("            margin-bottom: 10px;\n");
        html.append("        }\n");
        html.append("\n");
        html.append("        .clear-btn {\n");
        html.append("            position: absolute;\n");
        html.append("            right: 0;\n");
        html.append("            top: 0;\n");
        html.append("            background: #e53935;\n");
        html.append("            color: white;\n");
        html.append("            border: none;\n");
        html.append("            padding: 8px 14px;\n");
        html.append("            border-radius: 4px;\n");
        html.append("            font-size: 14px;\n");
        html.append("            cursor: pointer;\n");
        html.append("        }\n");
        html.append("\n");
        html.append("        .clear-btn:hover {\n");
        html.append("            background: #d32f2f;\n");
        html.append("        }\n");
        html.append("\n");
        html.append("        .day-section {\n");
        html.append("            margin-bottom: 28px;\n");
        html.append("        }\n");
        html.append("\n");
        html.append("        .day-title {\n");
        html.append("            font-size: 20px;\n");
        html.append("            font-weight: 700;\n");
        html.append("            margin-bottom: 10px;\n");
        html.append("            padding-bottom: 6px;\n");
        html.append("            border-bottom: 1px solid #d8d8d8;\n");
        html.append("        }\n");
        html.append("\n");
        html.append("        table {\n");
        html.append("            width: 100%;\n");
        html.append("            border-collapse: collapse;\n");
        html.append("            margin-top: 10px;\n");
        html.append("        }\n");
        html.append("\n");
        html.append("        thead th {\n");
        html.append("            text-align: left;\n");
        html.append("            font-size: 15px;\n");
        html.append("            font-weight: 700;\n");
        html.append("            padding: 10px 10px;\n");
        html.append("        }\n");
        html.append("\n");
        html.append("        tbody td {\n");
        html.append("            padding: 16px 10px;\n");
        html.append("            font-size: 15px;\n");
        html.append("            background: #e9e9e9;\n");
        html.append("        }\n");
        html.append("\n");
        html.append("        tbody tr {\n");
        html.append("            border-bottom: 10px solid #f3f3f3;\n");
        html.append("        }\n");
        html.append("\n");
        html.append("        .col-index {\n");
        html.append("            width: 35px;\n");
        html.append("            font-weight: 700;\n");
        html.append("        }\n");
        html.append("\n");
        html.append("        .col-amount {\n");
        html.append("            width: 100px;\n");
        html.append("            text-align: right;\n");
        html.append("        }\n");
        html.append("\n");
        html.append("        .empty-row td {\n");
        html.append("            text-align: center;\n");
        html.append("            font-style: italic;\n");
        html.append("            color: #666;\n");
        html.append("        }\n");
        html.append("    </style>\n");
        html.append("</head>\n");
        html.append("<body>\n");
        html.append("    <div class=\"container\">\n");
        html.append("        <div class=\"top-bar\">\n");
        html.append("            <h1>Odabrana jela</h1>\n");
        html.append("            <form method=\"post\" action=\"admin\">\n");
        html.append("                <input type=\"hidden\" name=\"password\" value=\"" + password + "\">\n");
        html.append("                <button type=\"submit\" class=\"clear-btn\">Očisti</button>\n");
        html.append("            </form>\n");
        html.append("        </div>\n");

        appendDaySection(html, "Ponedeljak", repository.getMealForDay(Day.Ponedeljak));
        appendDaySection(html, "Utorak", repository.getMealForDay(Day.Utorak));
        appendDaySection(html, "Sreda", repository.getMealForDay(Day.Sreda));
        appendDaySection(html, "Četvrtak", repository.getMealForDay(Day.Cetvrtak));
        appendDaySection(html, "Petak", repository.getMealForDay(Day.Petak));

        html.append("    </div>\n");
        html.append("</body>\n");
        html.append("</html>\n");

        return html.toString();
    }

    private void appendDaySection(StringBuilder html, String dayName, Map<String, Integer> meals) {
        html.append("        <div class=\"day-section\">\n");
        html.append("            <div class=\"day-title\">").append(dayName).append("</div>\n");
        html.append("            <table>\n");
        html.append("                <thead>\n");
        html.append("                    <tr>\n");
        html.append("                        <th class=\"col-index\">#</th>\n");
        html.append("                        <th>Jelo</th>\n");
        html.append("                        <th class=\"col-amount\">Količina</th>\n");
        html.append("                    </tr>\n");
        html.append("                </thead>\n");
        html.append("                <tbody>\n");

        if (meals == null || meals.isEmpty()) {
            html.append("                    <tr class=\"empty-row\">\n");
            html.append("                        <td colspan=\"3\">Nema podataka</td>\n");
            html.append("                    </tr>\n");
        } else {
            int index = 1;
            for (Map.Entry<String, Integer> entry : meals.entrySet()) {
                html.append("                    <tr>\n");
                html.append("                        <td class=\"col-index\">").append(index++).append("</td>\n");
                html.append("                        <td>").append(escapeHtml(entry.getKey())).append("</td>\n");
                html.append("                        <td class=\"col-amount\">").append(entry.getValue()).append("</td>\n");
                html.append("                    </tr>\n");
            }
        }

        html.append("                </tbody>\n");
        html.append("            </table>\n");
        html.append("        </div>\n");
    }

    private String escapeHtml(String text) {
        if (text == null) {
            return "";
        }

        return text.replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&#39;");
    }
}