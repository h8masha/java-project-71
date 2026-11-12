package hexlet.code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.json.JsonMapper;
import tools.jackson.dataformat.yaml.YAMLMapper;

public class Parser {
    protected static Map<String, Object> parse(String filePath) throws IOException {
        Path path = Path.of(filePath);
        var extension = filePath.split("\\.")[filePath.split("\\.").length - 1];

        ObjectMapper mapper =
                extension.equals("json")
                        ? JsonMapper.builder().build()
                        : YAMLMapper.builder().build();

        String read_value = Files.readString(path);
        @SuppressWarnings("unchecked")
        Map<String, Object> processedFile = mapper.readValue(read_value, Map.class);

        return processedFile;
    }
}
