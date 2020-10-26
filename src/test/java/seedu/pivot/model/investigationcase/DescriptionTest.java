package seedu.pivot.model.investigationcase;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class DescriptionTest {
    private static final String BLANK = " ";
    private static final String EMPTY = "";

    // Description is an Alphanumeric that can be blank.
    @Test
    public void isValidDescription_blank_true() {
        assertTrue(Description.isValidDescription(BLANK));
        assertTrue(Description.isValidDescription(EMPTY));
    }
}
