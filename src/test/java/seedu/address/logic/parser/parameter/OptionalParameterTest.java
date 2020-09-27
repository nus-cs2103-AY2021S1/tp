package seedu.address.logic.parser.parameter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import java.util.Optional;
import org.junit.jupiter.api.Test;

public class OptionalParameterTest {
    @Test
    void parameter_storesCorrectValue() {
        OptionalParameter<Integer> testParameter = new OptionalParameter<>(
            "intparam",
            "i",
            "test",
            "test",
            String::length);
        testParameter.setRawValue("abcdef");
        assertEquals(testParameter.consumeIfPresent(i -> i + 1), Optional.of(7));
    }
}
