package seedu.address.logic.parser.parameter;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.Test;

public class OptionalParameterTest {
    @Test
    void parameter_storesCorrectValue() throws Exception {
        OptionalParameter<Integer> testParameter = new OptionalParameter<>(
            "intparam",
            "i",
            "test",
            "test",
            String::length);
        testParameter.setValue("abcdef");
        assertEquals(testParameter.getValue().map(i -> i + 1), Optional.of(7));
    }
}
