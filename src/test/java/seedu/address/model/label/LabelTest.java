package seedu.address.model.label;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class LabelTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Label(null));
    }

    @Test
    public void constructor_invalidLabel_throwsIllegalArgumentException() {
        String invalidDescription = "";
        assertThrows(IllegalArgumentException.class, () -> new Label(invalidDescription));
    }

    @Test
    public void isValidLabel() {
        // null description
        assertThrows(NullPointerException.class, () -> Label.isValidLabel(null));

        // invalid description
        assertFalse(Label.isValidLabel("")); // empty string
        assertFalse(Label.isValidLabel(" ")); // spaces only
        assertFalse(Label.isValidLabel("^")); // only non-alphanumeric characters
        assertFalse(Label.isValidLabel("CS2103T*")); // contains non-alphanumeric characters
        assertFalse(Label.isValidLabel("CS2103T Software Engineering")); // long labels

        // valid description
        assertTrue(Label.isValidLabel("cs")); // alphabets only
        assertTrue(Label.isValidLabel("12345")); // numbers only
        assertTrue(Label.isValidLabel("cs2103T")); // alphanumeric characters
        assertTrue(Label.isValidLabel("CS")); // with capital letters

    }

    @Test
    public void equals() {
        String labelName = "CS2013T";
        Label firstLabel = new Label(labelName);
        Label secondLabel = new Label("CS2101");

        //same objects -> returns True
        assertTrue(firstLabel.equals(firstLabel));

        //same values -> return true
        Label newLabelcopy = new Label(labelName);
        assertTrue(firstLabel.equals(newLabelcopy));

        //different types -> return false
        assertFalse(firstLabel.equals(1));

        //different object -> return false
        Label newLabel = new Label("CS2103T");
        assertFalse(firstLabel.equals(newLabel));

        //null -> return false
        assertFalse(firstLabel.equals(null));

        //different values -> return false
        assertFalse(firstLabel.equals(secondLabel));
    }

    @Test
    public void setLabel_labelInTag_returnsTrue() {
        String validLabel = "CS2103T";
        String anotherValidLabel = "CS2101";
        Label label = new Label(validLabel);
        label.setLabel(anotherValidLabel);
        Label expectedLabel = new Label(anotherValidLabel);
        assertTrue(expectedLabel.equals(label));
    }
}
