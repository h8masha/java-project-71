package hexlet.formatters;

import hexlet.code.Formatter;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Plain implements Formatter {

    private String prepare(Object value) {
        if (value instanceof Map<?, ?> || value instanceof List<?>) {
            return "[complex value]";
        } else if (value == null) {
            return "null";
        } else if (value instanceof String) {
            return "'" + value + "'";
        }
        return value.toString();
    }

    @Override
    public String process(List<String> keys, Map<String, Object> file1, Map<String, Object> file2) {

        StringBuilder result = new StringBuilder();
        for (String key : keys) {
            Object value1 = file1.getOrDefault(key, null);
            Object value2 = file2.getOrDefault(key, null);
            if (file1.containsKey(key)) {
                if (file2.containsKey(key)) {
                    if (Objects.equals(file1.get(key), file2.get(key))) {
                        continue; // есть и там и там в одном виде, следовательно не пишем ничего в
                        // результат
                    } else {
                        // есть и там и там, но изменили во втором
                        result.append("Property '")
                                .append(key)
                                .append("' was updated. From ")
                                .append(prepare(value1))
                                .append(" to ")
                                .append(prepare(value2))
                                .append("\n");
                    }
                } else {
                    // есть только в первом, удалили
                    result.append("Property '").append(key).append("' was removed\n");
                }
            } else {
                result.append("Property '")
                        .append(key)
                        .append("' was added with value: ")
                        .append(prepare(value2))
                        .append("\n");
            }
        }

        return result.toString().trim();
    }
}
