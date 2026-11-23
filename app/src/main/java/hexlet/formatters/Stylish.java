package hexlet.formatters;

import hexlet.code.Formatter;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Stylish implements Formatter {

    public String process(List<String> keys, Map<String, Object> file1, Map<String, Object> file2) {

        StringBuilder result = new StringBuilder("{");
        for (String key : keys) {
            result.append("\n  ");
            if (file1.containsKey(key)) {
                if (file2.containsKey(key)) {
                    if (Objects.equals(file1.get(key), file2.get(key))) {
                        result.append("  ")
                                .append(key)
                                .append(": ")
                                .append(
                                        file1.getOrDefault(
                                                key, null)); // есть и там и там в одном виде
                    } else {
                        // есть и там и там, но изменили во втором
                        result.append("- ")
                                .append(key)
                                .append(": ")
                                .append(file1.get(key))
                                .append("\n  ");
                        result.append("+ ")
                                .append(key)
                                .append(": ")
                                .append(file2.getOrDefault(key, null));
                    }
                } else {
                    result.append("- ")
                            .append(key)
                            .append(": ")
                            .append(
                                    file1.getOrDefault(
                                            key, null)); // есть только в первом, во втором удалили
                }
            } else {
                result.append("+ ")
                        .append(key)
                        .append(": ")
                        .append(file2.getOrDefault(key, null)); // есть только во втором, добавили
            }
        }

        result.append("\n}");

        return result.toString();
    }
}
