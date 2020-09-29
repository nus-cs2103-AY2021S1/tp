package seedu.address.logic.parser.parameter;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.CommandParserTestUtil;

public class ParameterTest { // TODO: options test, here + optional param
    @Test
    void parameter_storesCorrectValue() throws Exception {
        Parameter<Integer> testParameter = new Parameter<>(
            "intparam",
            "i",
            "test",
            "test",
            String::length);
        testParameter.setValue("abcdef");
        assertEquals(testParameter.consume(), 6);
    }

    @Test
    void parameter_keepsParentRawValue() throws Exception {
        Parameter<String> parameter = CommandParserTestUtil.makeDummyParameter("test", "t");
        parameter.setValue("poop");
        assertEquals(parameter.getRawValue(), Optional.of("poop"));
    }
}
