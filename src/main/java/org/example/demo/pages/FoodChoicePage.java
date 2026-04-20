package org.example.demo.pages;

import org.example.demo.repo.Repository;

import java.util.List;

public class FoodChoicePage {

    private final Repository repository;

    public FoodChoicePage(Repository repository) {
        this.repository = repository;
    }

    public String foodChoiceHtml() {
        StringBuilder html = new StringBuilder();

        html.append("<!DOCTYPE html>\n");
        html.append("<html lang=\"sr\">\n");
        html.append("<head>\n");
        html.append("    <meta charset=\"UTF-8\">\n");
        html.append("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n");
        html.append("    <title>Odabir jela</title>\n");
        html.append("    <style>\n");
        html.append("        * {\n");
        html.append("            box-sizing: border-box;\n");
        html.append("            margin: 0;\n");
        html.append("            padding: 0;\n");
        html.append("        }\n");
        html.append("\n");
        html.append("        body {\n");
        html.append("            font-family: Arial, sans-serif;\n");
        html.append("            background-color: #f3f3f3;\n");
        html.append("            color: #333;\n");
        html.append("        }\n");
        html.append("\n");
        html.append("        .page-wrapper {\n");
        html.append("            width: 100%;\n");
        html.append("            min-height: 100vh;\n");
        html.append("            display: flex;\n");
        html.append("            justify-content: center;\n");
        html.append("            align-items: center;\n");
        html.append("            padding: 40px 20px;\n");
        html.append("        }\n");
        html.append("\n");
        html.append("        .container {\n");
        html.append("            width: 100%;\n");
        html.append("            max-width: 700px;\n");
        html.append("            text-align: center;\n");
        html.append("        }\n");
        html.append("\n");
        html.append("        h1 {\n");
        html.append("            font-size: 42px;\n");
        html.append("            margin-bottom: 20px;\n");
        html.append("            font-weight: 700;\n");
        html.append("        }\n");
        html.append("\n");
        html.append("        h2 {\n");
        html.append("            font-size: 22px;\n");
        html.append("            margin-bottom: 35px;\n");
        html.append("            font-weight: 600;\n");
        html.append("        }\n");
        html.append("\n");
        html.append("        form {\n");
        html.append("            text-align: left;\n");
        html.append("            width: 100%;\n");
        html.append("            max-width: 500px;\n");
        html.append("            margin: 0 auto;\n");
        html.append("        }\n");
        html.append("\n");
        html.append("        .form-group {\n");
        html.append("            margin-bottom: 18px;\n");
        html.append("        }\n");
        html.append("\n");
        html.append("        label {\n");
        html.append("            display: block;\n");
        html.append("            margin-bottom: 6px;\n");
        html.append("            font-size: 14px;\n");
        html.append("            font-weight: 500;\n");
        html.append("        }\n");
        html.append("\n");
        html.append("        select {\n");
        html.append("            width: 100%;\n");
        html.append("            padding: 10px 12px;\n");
        html.append("            border: 1px solid #cfcfcf;\n");
        html.append("            border-radius: 4px;\n");
        html.append("            background-color: white;\n");
        html.append("            font-size: 14px;\n");
        html.append("        }\n");
        html.append("\n");
        html.append("        .button-wrapper {\n");
        html.append("            display: flex;\n");
        html.append("            justify-content: flex-end;\n");
        html.append("            margin-top: 10px;\n");
        html.append("        }\n");
        html.append("\n");
        html.append("        button {\n");
        html.append("            background-color: #0d6efd;\n");
        html.append("            color: white;\n");
        html.append("            border: none;\n");
        html.append("            padding: 10px 18px;\n");
        html.append("            border-radius: 4px;\n");
        html.append("            font-size: 14px;\n");
        html.append("            cursor: pointer;\n");
        html.append("        }\n");
        html.append("\n");
        html.append("        button:hover {\n");
        html.append("            background-color: #0b5ed7;\n");
        html.append("        }\n");
        html.append("    </style>\n");
        html.append("</head>\n");
        html.append("<body>\n");
        html.append("    <div class=\"page-wrapper\">\n");
        html.append("        <div class=\"container\">\n");
        html.append("            <h1>Choose your food</h1>\n");
        html.append("            <h2>Odaberite vas rucak:</h2>\n");
        html.append("\n");
        html.append("            <form method=\"post\" action=\"food-choice\">\n");

        appendSelect(html, "Ponedeljak", "ponedeljak", repository.loadPonedaljak());
        appendSelect(html, "Utorak", "utorak", repository.loadUtorak());
        appendSelect(html, "Sreda", "sreda", repository.loadSreda());
        appendSelect(html, "Cetvrtak", "cetvrtak", repository.loadCetvrtak());
        appendSelect(html, "Petak", "petak", repository.loadPetak());

        html.append("                <div class=\"button-wrapper\">\n");
        html.append("                    <button type=\"submit\">Potvrdite unos</button>\n");
        html.append("                </div>\n");
        html.append("            </form>\n");
        html.append("        </div>\n");
        html.append("    </div>\n");
        html.append("</body>\n");
        html.append("</html>\n");

        return html.toString();
    }

    private void appendSelect(StringBuilder html, String label, String name, List<String> options) {
        html.append("                <div class=\"form-group\">\n");
        html.append("                    <label for=\"").append(name).append("\">").append(label).append("</label>\n");
        html.append("                    <select name=\"").append(name).append("\" id=\"").append(name).append("\">\n");

        if (options == null || options.isEmpty()) {
            html.append("                        <option value=\"\">Nema dostupnih jela</option>\n");
        } else {
            for (String option : options) {
                String escaped = escapeHtml(option);
                html.append("                        <option value=\"").append(escaped).append("\">")
                        .append(escaped)
                        .append("</option>\n");
            }
        }

        html.append("                    </select>\n");
        html.append("                </div>\n");
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