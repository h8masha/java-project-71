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
    public void whenResIsStylishAndJsonDataIsEqual_shouldReturnWithoutSigns() throws Exception {
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
    public void whenResIsStylishAndJsonSecondHasDifferentValueInSameKeys_shouldReturnPlusMinus()
            throws Exception {
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
    public void whenResIsStylishAndJsonSecondHasNoSuchKey_shouldReturnMinus() throws Exception {
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
    public void whenResIsStylishAndJsonSecondHasAdditionKey_shouldReturnPlus() throws Exception {
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
    public void whenResIsStylishAndYAMLDataIsEqual_shouldReturnWithoutSigns() throws Exception {
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
    public void whenResIsStylishAndYAMLSecondHasDifferentValueInSameKeys_shouldReturnPlusMinus()
            throws Exception {
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
    public void whenResIsStylishAndYAMLSecondHasNoSuchKey_shouldReturnMinus() throws Exception {
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
    public void whenResIsStylishAndYAMLSecondHasAdditionKey_shouldReturnPlus() throws Exception {
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

    @Test
    public void whenResIsPlainAndJsonDataIsEqual_shouldReturnEmpty() throws Exception {
        String expected = "";
        assertEquals(expected, Differ.generate(file1, file2, "plain"));
    }

    @Test
    public void whenResIsPlainAndJsonSecondHasDifferentValueInSameKey_shouldReturnOneUpdated()
            throws Exception {
        String expected = "Property 'aboba' was updated. From null to 1";
        assertEquals(expected, Differ.generate(file1, file3, "plain"));
    }

    @Test
    public void whenResIsPlainAndJsonSecondHasNoSuchKey_shouldReturnOnlyOneRemoved()
            throws Exception {
        String expected = "Property 'aboba' was removed";
        assertEquals(expected, Differ.generate(file1, file4, "plain"));
    }

    @Test
    public void whenResIsPlainAndJsonSecondHasAdditionKey_shouldReturnOneAdded() throws Exception {
        String expected = "Property 'third' was added with value: '10 seconds'";
        assertEquals(expected, Differ.generate(file1, file5, "plain"));
    }

    @Test
    public void whenResIsPlainAndYAMLDataIsEqual_shouldReturnEmpty() throws Exception {
        String expected = "";
        assertEquals(expected, Differ.generate(file1, file2, "plain"));
    }

    @Test
    public void whenResIsPlainAndYAMLSecondHasDifferentValueInSameKeys_shouldReturnOneUpdated()
            throws Exception {
        String expected = "Property 'aboba' was updated. From null to 1";
        assertEquals(expected, Differ.generate(file1, file3, "plain"));
    }

    @Test
    public void whenResIsPlainAndYAMLSecondHasNoSuchKey_shouldReturnOneRemoved() throws Exception {
        String expected = "Property 'aboba' was removed";
        assertEquals(expected, Differ.generate(file1, file4, "plain"));
    }

    @Test
    public void whenResIsPlainAndYAMLSecondHasAdditionKey_shouldReturnOneAdded() throws Exception {
        String expected = "";
        assertEquals(expected, Differ.generate(file1, file2, "plain"));
    }

    @Test
    public void whenResIsJsonAndJsonFieldsAreEqual_ShouldReturnUnchangedJson() throws Exception {
        String expected =
                """
                {"aboba":null,"first":"done","second":"lol"}""";
        assertEquals(expected, Differ.generate(file1, file2, "json"));
    }

    @Test
    public void whenResIsJsonAndJsonSecondRemove_shouldReturnOnlyOneEmbeddedRemovingBlock()
            throws Exception {
        String expected =
                """
                {"aboba":{"status":"removed","oldValue":null},"first":"done","second":"lol"}""";
        assertEquals(expected, Differ.generate(file1, file4, "json"));
    }

    @Test
    public void whenResIsJsonAndJsonSecondChanged_shouldReturnOnlyOneEmbeddedEditedBlock()
            throws Exception {
        String expected =
                """
                {"aboba":{"status":"updated","oldValue":null,"newValue":1},"first":"done","second":"lol"}""";

        assertEquals(expected, Differ.generate(file1, file3, "json"));
    }

    @Test
    public void whenResIsJsonAndJsonSecondHadAdditionBlock_shouldReturnOnlyOneEmbeddedAddedBlock()
            throws Exception {
        String expected =
                """
                {"aboba":null,"first":"done","second":"lol","third":{"status":"added","newValue":"10 seconds"}}""";
        assertEquals(expected, Differ.generate(file1, file5, "json"));
    }

    @Test
    public void whenResIsJsonAndYamlFieldsAreEqual_ShouldReturnUnchangedJson() throws Exception {
        String expected =
                """
                {"aboba":null,"first":"done","second":"lol"}""";
        assertEquals(expected, Differ.generate(file1y, file2y, "json"));
    }

    @Test
    public void whenResIsJsonAndYamlSecondRemove_shouldReturnOnlyOneEmbeddedRemovingBlock()
            throws Exception {
        String expected =
                """
                {"aboba":{"status":"removed","oldValue":null},"first":"done","second":"lol"}""";
        assertEquals(expected, Differ.generate(file1y, file4y, "json"));
    }

    @Test
    public void whenResIsJsonAndYamlSecondChanged_shouldReturnOnlyOneEmbeddedEditedBlock()
            throws Exception {
        String expected =
                """
                {"aboba":{"status":"updated","oldValue":null,"newValue":1},"first":"done","second":"lol"}""";
        assertEquals(expected, Differ.generate(file1y, file3y, "json"));
    }

    @Test
    public void whenResIsJsonAndYamlSecondHadAdditionBlock_shouldReturnOnlyOneEmbeddedAddedBlock()
            throws Exception {
        String expected =
                """
                {"aboba":null,"first":"done","second":"lol","third":{"status":"added","newValue":"10 seconds"}}""";
        assertEquals(expected, Differ.generate(file1y, file5y, "json"));
    }
}
