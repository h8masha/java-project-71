package hexlet.code;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class DifferTest {

    private static String file1;
    private static String file2;
    private static String file3;
    private static String file4;
    private static String file5;
    private static String file1y;
    private static String file2y;
    private static String file3y;
    private static String file4y;
    private static String file5y;

    @BeforeAll
    public static void beforeAll() {
        file1 = "fixtures/file1.json";
        file2 = "fixtures/file2.json";
        file3 = "fixtures/file3.json";
        file4 = "fixtures/file4.json";
        file5 = "fixtures/file5.json";
        file1y = "fixtures/file1y.yml";
        file2y = "fixtures/file2y.yml";
        file3y = "fixtures/file3y.yml";
        file4y = "fixtures/file4y.yml";
        file5y = "fixtures/file5y.yml";
    }

    @Test
    public void whenJsonDataIsEqual_shouldReturnWithoutSigns() throws Exception {
        String expected =
                """
                {
                    aboba: null
                    first: done
                    second: lol
                }""";
        assertEquals(expected, Differ.generate(file1, file2));
    }

    @Test
    public void whenJsonSecondHasDifferentValueInSameKeys_shouldReturnPlusMinus() throws Exception {
        String expected =
                """
                {
                  - aboba: null
                  + aboba: 1
                    first: done
                    second: lol
                }""";
        assertEquals(expected, Differ.generate(file1, file3));
    }

    @Test
    public void whenJsonSecondHasNoSuchKey_shouldReturnMinus() throws Exception {
        String expected =
                """
                {
                  - aboba: null
                    first: done
                    second: lol
                }""";
        assertEquals(expected, Differ.generate(file1, file4));
    }

    @Test
    public void whenJsonSecondHasAdditionKey_shouldReturnPlus() throws Exception {
        String expected =
                """
                {
                    aboba: null
                    first: done
                    second: lol
                  + third: 10 seconds
                }""";
        assertEquals(expected, Differ.generate(file1, file5));
    }

    @Test
    public void whenYAMLDataIsEqual_shouldReturnWithoutSigns() throws Exception {
        String expected =
                """
                {
                    aboba: null
                    first: done
                    second: lol
                }""";
        assertEquals(expected, Differ.generate(file1y, file2y));
    }

    @Test
    public void whenYAMLSecondHasDifferentValueInSameKeys_shouldReturnPlusMinus() throws Exception {
        String expected =
                """
                {
                  - aboba: null
                  + aboba: 1
                    first: done
                    second: lol
                }""";
        assertEquals(expected, Differ.generate(file1y, file3y));
    }

    @Test
    public void whenYAMLSecondHasNoSuchKey_shouldReturnMinus() throws Exception {
        String expected =
                """
                {
                  - aboba: null
                    first: done
                    second: lol
                }""";
        assertEquals(expected, Differ.generate(file1y, file4y));
    }

    @Test
    public void whenYAMLSecondHasAdditionKey_shouldReturnPlus() throws Exception {
        String expected =
                """
                        {
                            aboba: null
                            first: done
                            second: lol
                          + third: 10 seconds
                        }""";
        assertEquals(expected, Differ.generate(file1y, file5y));
    }
}
