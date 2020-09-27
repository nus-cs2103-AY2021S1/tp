package seedu.address.logic.parser.parameter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;

public class ParameterTest { // TODO: options test, here + optional param
    @Test
    void parameter_storesCorrectValue() {
        Parameter<Integer> testParameter = new Parameter<>(
            "intparam",
            "i",
            "test",
            "test",
            String::length);
        testParameter.setRawValue("abcdef");
        assertEquals(testParameter.consume(), 6);
    }
}
