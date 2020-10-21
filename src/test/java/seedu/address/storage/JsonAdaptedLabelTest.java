package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.label.Label;


public class JsonAdaptedLabelTest {
    private static final String VALID_LABEL = "project";
    private static final String INVALID_LABEL = "#project";

    @Test
    public void toModelType_invalidLabel_throwsIllegalValueException() {
        JsonAdaptedLabel label = new JsonAdaptedLabel(INVALID_LABEL);
        String expectedMessage = Label.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, label::toModelType);
    }

    @Test
    public void toModelType_validLabel_returnsLabel() throws Exception {
        Label myFileLabel = new Label(VALID_LABEL);
        JsonAdaptedLabel label = new JsonAdaptedLabel(myFileLabel);
        assertEquals(myFileLabel, label.toModelType());
    }

}
