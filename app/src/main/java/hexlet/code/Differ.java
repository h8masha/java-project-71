package hexlet.code;

import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.json.JsonMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Differ {
    public static String generate(String filePath1, String filePath2) throws Exception {
        Map<String, Object> json1 = parse(filePath1);
        Map<String, Object> json2 = parse(filePath2);

        StringBuilder result = new StringBuilder("{");

        Set<String> uniqueKeysSet = new TreeSet<>(json1.keySet());
        uniqueKeysSet.addAll(json2.keySet());
        List<String> uniqueKeys = new ArrayList<>(uniqueKeysSet);

        uniqueKeys.sort(Comparator.naturalOrder());

        for (String key : uniqueKeys) {
            result.append("\n  ");
            if (json1.containsKey(key)) {
                if (json2.containsKey(key)) {
                    if (Objects.equals(json1.get(key), json2.get(key))) {
                        result.append("  ").append(key).append(": ").append(json1.get(key)); //есть и там и там в одном виде
                    } else {
                        // есть и там и там, но изменили во втором
                        result.append("- ").append(key).append(": ").append(json1.get(key)).append("\n  ");
                        result.append("+ ").append(key).append(": ").append(json2.get(key));
                    }
                } else {
                    result.append("- ").append(key).append(": ").append(json1.get(key)); // есть только в первом, во втором удалили
                }
            } else {
                result.append("+ ").append(key).append(": ").append(json2.get(key)); // есть только во втором, добавили
            }
        }

        result.append("\n}");

        return result.toString();
    }

    private static  Map<String, Object> parse(String filePath) throws IOException {
        Path path = Path.of(filePath);
        ObjectMapper mapper = JsonMapper.builder().build();

        String jsonString = Files.readString(path);
        @SuppressWarnings("unchecked")
        Map<String, Object> processedJson = mapper.readValue(jsonString, Map.class);

        return processedJson;
    }
}