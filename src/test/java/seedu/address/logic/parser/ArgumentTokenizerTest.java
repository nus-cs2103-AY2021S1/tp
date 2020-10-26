package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.*;

import org.junit.jupiter.api.Test;
import seedu.address.logic.parser.exceptions.ParseException;

public class ArgumentTokenizerTest {

    private final Prefix unknownPrefix = new Prefix("--u");
    private final Prefix pSlash = new Prefix("p/");
    private final Prefix dashT = new Prefix("-t");
    private final Prefix hatQ = new Prefix("^Q");

    @Test
    public void tokenize_emptyArgsString_noValues() throws ParseException {
        String argsString = "  ";
        ArgumentTokenizer tokenizer = new ArgumentTokenizer(argsString, PREFIX_NAME);
        ArgumentMultimap argMultimap = tokenizer.tokenize();
        assertPreambleEmpty(argMultimap);
    }

    @Test
    public void tokenize_missingArgument() throws ParseException {
        String argsString = "add n/";
        ArgumentTokenizer tokenizer = new ArgumentTokenizer(argsString, PREFIX_NAME);
        ArgumentMultimap argMultimap = tokenizer.tokenize();
        assertArgumentAbsent(argMultimap, PREFIX_NAME);
    }

    @Test
    public void tokenize_validArgsString_preamblePresent() throws ParseException {
        String argsString = "hello n/";
        ArgumentTokenizer tokenizer = new ArgumentTokenizer(argsString, PREFIX_NAME);
        ArgumentMultimap argMultimap = tokenizer.tokenize();
        assertPreamblePresent(argMultimap, "hello");
    }

    @Test
    public void tokenize_validArgument() throws ParseException {
        String argsString = "add n/matthias";
        ArgumentTokenizer tokenizer = new ArgumentTokenizer(argsString, PREFIX_NAME);
        ArgumentMultimap argMultimap = tokenizer.tokenize();
        assertArgumentPresent(argMultimap, PREFIX_NAME, "matthias");
    }

    @Test
    public void tokenize_validArgument_multiple() throws ParseException {
        String argsString = "add n/matthias testing";
        ArgumentTokenizer tokenizer = new ArgumentTokenizer(argsString, PREFIX_NAME);
        ArgumentMultimap argMultimap = tokenizer.tokenize();
        assertArgumentPresent(argMultimap, PREFIX_NAME, "matthias testing");
    }

    private void assertPreamblePresent(ArgumentMultimap argMultimap, String expectedPreamble) {
        assertEquals(expectedPreamble, argMultimap.getPreamble());
    }

    private void assertPreambleEmpty(ArgumentMultimap argMultimap) {
        assertTrue(argMultimap.getPreamble().isEmpty());
    }

    /**
     * Asserts all the arguments in {@code argMultimap} with {@code prefix} match the {@code expectedValues}
     * and only the last value is returned upon calling {@code ArgumentMultimap#getValue(Prefix)}.
     */
    private void assertArgumentPresent(ArgumentMultimap argMultimap, Prefix prefix, String... expectedValues) {

        // Verify the last value is returned
        assertEquals(expectedValues[expectedValues.length - 1], argMultimap.getValue(prefix).get());

        // Verify the number of values returned is as expected
        assertEquals(expectedValues.length, argMultimap.getAllValues(prefix).size());

        // Verify all values returned are as expected and in order
        for (int i = 0; i < expectedValues.length; i++) {
            assertEquals(expectedValues[i], argMultimap.getAllValues(prefix).get(i));
        }
    }

    private void assertArgumentAbsent(ArgumentMultimap argMultimap, Prefix prefix) {
        if (argMultimap.getValue(prefix).isPresent()) {
            assertTrue(argMultimap.getValue(prefix).get().equals(""));
        } else {
            assertFalse(argMultimap.getValue(prefix).isPresent());
        }
    }

    @Test
    public void tokenize_noPrefixes_allTakenAsPreamble() throws ParseException {
        String argsString = "  some random stringn/ tag with leading and trailing spaces ";
        ArgumentTokenizer tokenizer = new ArgumentTokenizer(argsString, PREFIX_NAME);
        ArgumentMultimap argMultimap = tokenizer.tokenize();

        // Same string expected as preamble, but leading/trailing spaces should be trimmed
        assertPreamblePresent(argMultimap, argsString.trim());
    }

    @Test
    public void tokenize_oneArgument() throws ParseException {
        // Preamble present
        String argString = "addmodule n/hello";
        ArgumentTokenizer tokenizer1 = new ArgumentTokenizer(argString, PREFIX_NAME);
        ArgumentMultimap map1 = tokenizer1.tokenize();
        assertPreamblePresent(map1, "addmodule");
        assertArgumentPresent(map1, PREFIX_NAME, "hello");
        // ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(argsString, pSlash);
        // assertPreamblePresent(argMultimap, "Some preamble string");
        // assertArgumentPresent(argMultimap, pSlash, "Argument value");

        // No preamble
        argString = " n/bye";
        ArgumentTokenizer tokenizer2 = new ArgumentTokenizer(argString, PREFIX_NAME);
        ArgumentMultimap map2 = tokenizer2.tokenize();
        assertPreambleEmpty(map2);
        assertArgumentPresent(map2, PREFIX_NAME, "bye");
    }

    @Test
    public void tokenize_multipleArguments() throws ParseException {
        // Only two arguments are present
        String argsString = "SomePreambleString n/CS2103T l/www.example.com";
        ArgumentTokenizer tokenizer = new ArgumentTokenizer(argsString, PREFIX_NAME, PREFIX_ZOOM_LINK);
        ArgumentMultimap argMultimap = tokenizer.tokenize();
        assertArgumentPresent(argMultimap, PREFIX_NAME, "CS2103T");
        assertArgumentPresent(argMultimap, PREFIX_ZOOM_LINK, "www.example.com");
        assertArgumentAbsent(argMultimap, PREFIX_ADD_NEW_ASSIGNMENT);

        // All three arguments are present
        argsString = "Different Preamble String ^Q111 -t dashT-Value p/pSlash value";
        // argMultimap = ArgumentTokenizer.tokenize(argsString, pSlash, dashT, hatQ);
        // assertPreamblePresent(argMultimap, "Different Preamble String");
        // assertArgumentPresent(argMultimap, pSlash, "pSlash value");
        // assertArgumentPresent(argMultimap, dashT, "dashT-Value");
        // assertArgumentPresent(argMultimap, hatQ, "111");

        /* Also covers: Reusing of the tokenizer multiple times */

        // Reuse tokenizer on an empty string to ensure ArgumentMultimap is correctly reset
        // (i.e. no stale values from the previous tokenizing remain)
        argsString = "";
        // argMultimap = ArgumentTokenizer.tokenize(argsString, pSlash, dashT, hatQ);
        // assertPreambleEmpty(argMultimap);
        // assertArgumentAbsent(argMultimap, pSlash);

        /* Also covers: testing for prefixes not specified as a prefix */

        // Prefixes not previously given to the tokenizer should not return any values
        argsString = unknownPrefix + "some value";
        // argMultimap = ArgumentTokenizer.tokenize(argsString, pSlash, dashT, hatQ);
        // assertArgumentAbsent(argMultimap, unknownPrefix);
        // assertPreamblePresent(argMultimap, argsString); // Unknown prefix is taken as part of preamble
    }

    @Test
    public void tokenize_multipleArgumentsWithRepeats() {
        // Two arguments repeated, some have empty values
        String argsString = "SomePreambleString -t dashT-Value ^Q ^Q -t another dashT value p/ pSlash value -t";
        // ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(argsString, pSlash, dashT, hatQ);
        // assertPreamblePresent(argMultimap, "SomePreambleString");
        // assertArgumentPresent(argMultimap, pSlash, "pSlash value");
        // assertArgumentPresent(argMultimap, dashT, "dashT-Value", "another dashT value", "");
        // assertArgumentPresent(argMultimap, hatQ, "", "");
    }

    @Test
    public void tokenize_multipleArgumentsJoined() {
        String argsString = "SomePreambleStringp/ pSlash joined-tjoined -t not joined^Qjoined";
        // ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(argsString, pSlash, dashT, hatQ);
        // assertPreamblePresent(argMultimap, "SomePreambleStringp/ pSlash joined-tjoined");
        // assertArgumentAbsent(argMultimap, pSlash);
        // assertArgumentPresent(argMultimap, dashT, "not joined^Qjoined");
        // assertArgumentAbsent(argMultimap, hatQ);
    }

    @Test
    public void equalsMethod() {
        Prefix aaa = new Prefix("aaa");

        assertEquals(aaa, aaa);
        assertEquals(aaa, new Prefix("aaa"));

        assertNotEquals(aaa, "aaa");
        assertNotEquals(aaa, new Prefix("aab"));
    }

}
