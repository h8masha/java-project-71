package hexlet.code;

import java.util.List;
import java.util.Map;

@FunctionalInterface
public interface Formatter {
    String process(List<String> keys, Map<String, Object> file1, Map<String, Object> file2);
}
