package hexlet.code;

import hexlet.formatters.Json;
import hexlet.formatters.Plain;
import hexlet.formatters.Stylish;
import java.util.*;

public class Differ {
    public static String generate(String filePath1, String filePath2, String format)
            throws Exception {
        Map<String, Object> file1 = Parser.parse(filePath1);
        Map<String, Object> file2 = Parser.parse(filePath2);

        Set<String> uniqueKeysSet = new TreeSet<>(file1.keySet());
        uniqueKeysSet.addAll(file2.keySet());
        List<String> uniqueKeys = new ArrayList<>(uniqueKeysSet);

        uniqueKeys.sort(Comparator.naturalOrder());

        Formatter formatter =
                format.equals("plain")
                        ? new Plain()
                        : (format.equals("json") ? new Json() : new Stylish());

        return formatter.process(uniqueKeys, file1, file2);
    }

    public static String generate(String filePath1, String filePath2) throws Exception {
        return generate(filePath1, filePath2, "stylish");
    }
}
