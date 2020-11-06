package jimmy.mcgymmy.model.macro;

import static jimmy.mcgymmy.testutil.TypicalMacros.TEST_MACRO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.junit.jupiter.api.Test;

import jimmy.mcgymmy.commons.exceptions.IllegalValueException;


public class MacroTest {

    private static final String[] COMMAND = {"Command"};
    private static final String[] ARGUMENTS = {"Hello", "World"};
    private static Macro MACRO_TEST;

    static {
        try {
            initialiseMacros();
        } catch (IllegalValueException e) {
            assert false : "Error in food name";
        }
    }

    private static void initialiseMacros() throws IllegalValueException{
        MACRO_TEST = new Macro("name", ARGUMENTS, COMMAND);
    }

    @Test
    void testMacro_createdWithCorrectOptions() {

        //Check if options are correct
        assertTrue(TEST_MACRO.getOptions().hasOption("c"));
        assertTrue(TEST_MACRO.getOptions().hasOption("p"));

        //Check if other options are not present
        assertFalse(TEST_MACRO.getOptions().hasOption("d"));
        assertFalse(TEST_MACRO.getOptions().hasOption("q"));
    }

    @Test
    void testMacro_noExtraOptions() {
        assertEquals(TEST_MACRO.getOptions().getOptions().size(), 2);
    }

    @Test
    void testParseOptions() {
        Options options = new Options();
        Option option1 = new Option(ARGUMENTS[0], true, String.format("macro argument %s", ARGUMENTS[0]));
        option1.setRequired(true);
        Option option2 = new Option(ARGUMENTS[1], true, String.format("macro argument %s", ARGUMENTS[1]));
        option2.setRequired(true);
        options.addOption(option1);
        options.addOption(option2);

        //Check if the option is correct
        assertEquals(MACRO_TEST.getOptions().toString(), options.toString());
    }
}
