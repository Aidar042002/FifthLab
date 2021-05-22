package fifthLab;

import java.util.ArrayList;

/**
 * Утилита для парсинга и сборки CSV.
 */

public class CSVBuilder {
    public String line = "";

    public void append(String field) {
        if (!line.equals(""))
            line += ",";
        line += "\"" + field.replace("\"", "\"\"") + "\"";
    }

    public static ArrayList<String> parse(String line) {
        boolean parsingField = false;
        boolean quoteDuringParsing = false;
        ArrayList<String> fields = new ArrayList<>();
        StringBuilder field = new StringBuilder();
        for (char c : line.toCharArray()) {
            if (c == '"' && !parsingField) {
                parsingField = true;
                continue;
            }
            if (c == '"' && parsingField && !quoteDuringParsing) {
                quoteDuringParsing = true;
                continue;
            }
            if (c == '"' && parsingField && quoteDuringParsing) {
                field.append('"');
                quoteDuringParsing = false;
                continue;
            }
            if (c == ',' && parsingField && quoteDuringParsing) {
                parsingField = false;
                fields.add(field.toString());
                field.setLength(0);
                quoteDuringParsing = false;
                continue;
            }
            if (parsingField) {
                field.append(c);
                quoteDuringParsing = false;
            }
        }
        fields.add(field.toString());
        return fields;
    }
}