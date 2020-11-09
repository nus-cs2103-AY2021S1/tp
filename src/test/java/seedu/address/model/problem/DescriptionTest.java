package seedu.address.model.problem;

import org.junit.jupiter.api.Test;

class DescriptionTest {

    @Test
    void isValidDescription() {
        // test all alphabet valid input
        boolean isValid1 = Description.isValidDescription("alphabetical");
        assert isValid1 : "all alphabet valid input failed";
        // test all number valid input
        boolean isValid2 = Description.isValidDescription("123456");
        assert isValid2 : "all alphabet valid input failed";
        // test valid input with space
        boolean isValid3 = Description.isValidDescription("with space");
        assert isValid3 : "valid input with space failed";
        // test invalid input with special characters
        boolean isValid4 = Description.isValidDescription("*");
        assert !isValid4 : "invalid input with special characters passed";
        // test invalid empty input
        boolean isValid5 = Description.isValidDescription("");
        assert !isValid5 : "invalid empty input passed";
    }

    @Test
    void testToString() {
        // test if the method returns the same string as the input description
        String input = "description with space and 1 number and UPPER CASES";
        Description description = new Description(input);
        String result = description.toString();
        assert input.equals(result) : "does not return the same string as the input";
    }
}
