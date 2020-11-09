package seedu.address.model.problem;

import org.junit.jupiter.api.Test;

class SeverityTest {

    @Test
    void isValidSeverity() {
        // test all lower case valid input
        boolean isValid1 = Severity.isValidSeverity("high");
        assert isValid1 == true : "all lower case valid input failed";

        // test all lower case invalid input
        boolean isValid2 = Severity.isValidSeverity("invalid");
        assert isValid2 == false : "all lower case invalid input passed";

        // test with upper case valid input
        boolean isValid3 = Severity.isValidSeverity("hiGH");
        assert isValid3 == true : "with upper case valid input failed";

        // test with upper case invalid input
        boolean isValid4 = Severity.isValidSeverity("inVALID");
        assert isValid4 == false : "with upper case invalid input passed";

        // test empty input
        boolean isValid5 = Severity.isValidSeverity("");
        assert isValid5 == false : "empty input passed";
    }

    @Test
    void testToString() {
        // test if the method return an all-upper-case severity
        Severity severity = new Severity("high");
        String result = severity.toString();
        assert result.equals("HIGH") : "does not return an all-upper-case same-letter string";
    }
}
