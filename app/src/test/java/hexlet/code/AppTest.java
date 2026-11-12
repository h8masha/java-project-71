package hexlet.code;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import picocli.CommandLine;

public class AppTest {

    private static String file1;

    @BeforeAll
    public static void beforeAll() {
        file1 = "fixtures/nullsOnly.json";
    }

    @Test
    public void whenFilesConsistOfNullElems_shouldReturnZeroCode() {
        String[] args = {file1, file1};
        int exitCode = new CommandLine(new App()).execute(args);
        assertEquals(0, exitCode);
    }
}
