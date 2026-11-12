package hexlet.code;

import java.util.*;

public class Differ {
    public static String generate(String filePath1, String filePath2) throws Exception {
        Map<String, Object> file1 = Parser.parse(filePath1);
        Map<String, Object> file2 = Parser.parse(filePath2);

        StringBuilder result = new StringBuilder("{");

        Set<String> uniqueKeysSet = new TreeSet<>(file1.keySet());
        uniqueKeysSet.addAll(file2.keySet());
        List<String> uniqueKeys = new ArrayList<>(uniqueKeysSet);

        uniqueKeys.sort(Comparator.naturalOrder());

        for (String key : uniqueKeys) {
            result.append("\n  ");
            if (file1.containsKey(key)) {
                if (file2.containsKey(key)) {
                    if (Objects.equals(file1.get(key), file2.get(key))) {
                        result.append("  ")
                                .append(key)
                                .append(": ")
                                .append(file1.get(key)); // есть и там и там в одном виде
                    } else {
                        // есть и там и там, но изменили во втором
                        result.append("- ")
                                .append(key)
                                .append(": ")
                                .append(file1.get(key))
                                .append("\n  ");
                        result.append("+ ").append(key).append(": ").append(file2.get(key));
                    }
                } else {
                    result.append("- ")
                            .append(key)
                            .append(": ")
                            .append(file1.get(key)); // есть только в первом, во втором удалили
                }
            } else {
                result.append("+ ")
                        .append(key)
                        .append(": ")
                        .append(file2.get(key)); // есть только во втором, добавили
            }
        }

        result.append("\n}");

        return result.toString();
    }
}
