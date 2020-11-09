package seedu.medibook.storage;

import static seedu.medibook.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.medibook.commons.exceptions.IllegalValueException;

public class JsonAdaptedAccountTest {
    private static final String INVALID_DOCTORNAME = "R@chel";
    private static final String INVALID_DOCTORMCR = "M12DD2Q";

    private static final String VALID_USERNAME = "user";
    private static final String VALID_PASSWORD = "pass";
    private static final String VALID_DOCTORNAME = "Capital Tan";
    private static final String VALID_DOCTORMCR = "M99999x";

    @Test
    public void toModelType_nullDoctorName_throwsIllegalValueException() {
        JsonAdaptedAccount account = new JsonAdaptedAccount(VALID_USERNAME, VALID_PASSWORD,
                null, VALID_DOCTORMCR);
        String expectedMessage = JsonAdaptedAccount.MISSING_FIELD_MESSAGE_FORMAT;
        assertThrows(IllegalValueException.class, expectedMessage, account::toModelType);
    }

    @Test
    public void toModelType_invalidDoctorName_throwsIllegalValueException() {
        JsonAdaptedAccount account = new JsonAdaptedAccount(VALID_USERNAME, VALID_PASSWORD,
                INVALID_DOCTORNAME, VALID_DOCTORMCR);
        String expectedMessage = JsonAdaptedAccount.MISSING_FIELD_MESSAGE_FORMAT;
        assertThrows(IllegalValueException.class, expectedMessage, account::toModelType);
    }

    @Test
    public void toModelType_nullDoctorMcr_throwsIllegalValueException() {
        JsonAdaptedAccount account = new JsonAdaptedAccount(VALID_USERNAME, VALID_PASSWORD,
                VALID_DOCTORNAME, null);
        String expectedMessage = JsonAdaptedAccount.MISSING_FIELD_MESSAGE_FORMAT;
        assertThrows(IllegalValueException.class, expectedMessage, account::toModelType);
    }

    @Test
    public void toModelType_invalidDoctorMcr_throwsIllegalValueException() {
        JsonAdaptedAccount account = new JsonAdaptedAccount(VALID_USERNAME, VALID_PASSWORD,
                VALID_DOCTORNAME, INVALID_DOCTORMCR);
        String expectedMessage = JsonAdaptedAccount.MISSING_FIELD_MESSAGE_FORMAT;
        assertThrows(IllegalValueException.class, expectedMessage, account::toModelType);
    }

}
