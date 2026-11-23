package hexlet.formatters;

import hexlet.code.Formatter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import tools.jackson.databind.ObjectMapper;

public class Json implements Formatter {
    @Override
    public String process(List<String> keys, Map<String, Object> file1, Map<String, Object> file2) {

        ObjectMapper objectMapper = new ObjectMapper();

        var supportMap = new LinkedHashMap<String, Object>();

        LinkedHashMap<String, Object> res = new LinkedHashMap<>();
        for (String key : keys) {
            supportMap = new LinkedHashMap<>();
            var value1 = file1.getOrDefault(key, null);
            var value2 = file2.getOrDefault(key, null);
            if (file1.containsKey(key)) {
                if (file2.containsKey(key)) {
                    if (Objects.equals(file1.get(key), file2.get(key))) {
                        res.put(key, value1); // есть и там и там в одном виде
                        continue;
                    } else {
                        supportMap.put(
                                "status", "updated"); // есть и там и там, но изменили во втором
                        supportMap.put("oldValue", value1);
                        supportMap.put("newValue", value2);
                    }
                } else {
                    supportMap.put("status", "removed"); // есть только в первом, во втором удалили
                    supportMap.put("oldValue", value1);
                }
            } else {
                supportMap.put("status", "added"); // есть только во втором, добавили
                supportMap.put("newValue", value2);
            }
            res.put(key, supportMap);
        }
        return objectMapper.writeValueAsString(res);
    }
}
