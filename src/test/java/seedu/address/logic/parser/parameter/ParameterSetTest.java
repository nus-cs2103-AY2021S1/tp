package seedu.address.logic.parser.parameter;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.apache.commons.cli.DefaultParser;
import org.junit.jupiter.api.Test;
import seedu.address.logic.parser.exceptions.ParameterConflictException;
import seedu.address.logic.parser.CommandParserTestUtil;


public class ParameterSetTest {
    private DefaultParser parser = new DefaultParser();

    private Parameter<String> dummyParameter1 = CommandParserTestUtil.makeDummyParameter("test", "t");

    private Parameter<String> dummyParameter2 = CommandParserTestUtil.makeDummyParameter("test2", "t2");

    @Test
    void add_newParameter_success() {
        ParameterSet parameterSet = new ParameterSet();
        assertDoesNotThrow(() -> parameterSet.addParameter(dummyParameter1));
    }

    @Test
    void add_multipleNewParameters_success() {
        ParameterSet parameterSet = new ParameterSet();
        assertDoesNotThrow(() -> parameterSet.addParameter(dummyParameter1));
        assertDoesNotThrow(() -> parameterSet.addParameter(dummyParameter2));
    }

    @Test
    void add_duplicateParameters_fail() {
        ParameterSet parameterSet = new ParameterSet();
        assertDoesNotThrow(() -> parameterSet.addParameter(dummyParameter1));
        assertThrows(ParameterConflictException.class, () -> parameterSet.addParameter(dummyParameter1));
    }

}
