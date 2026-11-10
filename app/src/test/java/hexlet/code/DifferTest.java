package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DifferTest {

    private static String file1;
    private static String file2;
    private static String file3;
    private static String file4;
    private static String file5;

    @BeforeAll
    public static void beforeAll() {
        file1 = "fixtures/file1.json";
        file2 = "fixtures/file2.json";
        file3 = "fixtures/file3.json";
        file4 = "fixtures/file4.json";
        file5 = "fixtures/file5.json";
    }

    @Test
    public void whenDataIsEqual_shouldReturnWithoutSigns() throws Exception {
        String expected = """
                {
                    aboba: null
                    first: done
                    second: lol
                }""";
        assertEquals(expected, Differ.generate(file1, file2));
    }

    @Test
    public void whenSecondHasDifferentValueInSameKeys_shouldReturnPlusMinus() throws Exception{
        String expected = """
                {
                  - aboba: null
                  + aboba: 1
                    first: done
                    second: lol
                }""";
        assertEquals(expected, Differ.generate(file1, file3));
    }

    @Test
    public void whenSecondHasNoSuchKey_shouldReturnMinus() throws Exception{
        String expected = """
                {
                  - aboba: null
                    first: done
                    second: lol
                }""";
        assertEquals(expected, Differ.generate(file1, file4));
    }

    @Test
    public void whenSecondHasAdditionKey_shouldReturnPlus() throws Exception{
        String expected = """
                {
                    aboba: null
                    first: done
                    second: lol
                  + third: 10 seconds
                }""";
        assertEquals(expected, Differ.generate(file1, file5));
    }

}
