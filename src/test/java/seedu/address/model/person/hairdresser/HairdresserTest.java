package seedu.address.model.person.hairdresser;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BENJAMIN;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GENDER_BENJAMIN;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BENJAMIN;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BENJAMIN;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SPECIALISATION_PERM;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_BENJAMIN;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalHairdressers.AMELIA;
import static seedu.address.testutil.TypicalHairdressers.BENJAMIN;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.HairdresserBuilder;

class HairdresserTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Hairdresser hairdresser = new HairdresserBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> hairdresser.getSpecs()
            .remove(0));
    }

    @Test
    public void isSame() {
        // same object -> returns true
        assertTrue(AMELIA.isSame(AMELIA));

        // null -> returns false
        assertFalse(AMELIA.isSame(null));

        // different phone, email, gender and title -> returns false
        Hairdresser editedAmelia = new HairdresserBuilder(AMELIA).withPhone(VALID_PHONE_BENJAMIN)
            .withEmail(VALID_EMAIL_BENJAMIN)
            .withTitle(VALID_TITLE_BENJAMIN)
            .withGender(VALID_GENDER_BENJAMIN)
            .build();
        assertFalse(AMELIA.isSame(editedAmelia));

        // different name -> returns false
        editedAmelia = new HairdresserBuilder(AMELIA).withName(VALID_NAME_BENJAMIN)
            .build();
        assertFalse(AMELIA.isSame(editedAmelia));

        // same name, same phone, different attributes -> returns true
        editedAmelia = new HairdresserBuilder(AMELIA).withEmail(VALID_EMAIL_BENJAMIN)
            .withTitle(VALID_TITLE_BENJAMIN)
            .withSpecs(VALID_SPECIALISATION_PERM)
            .build();
        assertTrue(AMELIA.isSame(editedAmelia));

        // same name, same email, different attributes -> returns false
        editedAmelia = new HairdresserBuilder(AMELIA).withPhone(VALID_PHONE_BENJAMIN)
            .withTitle(VALID_TITLE_BENJAMIN)
            .withSpecs(VALID_SPECIALISATION_PERM)
            .build();
        assertFalse(AMELIA.isSame(editedAmelia));

        // same name, same phone, same email, different attributes -> returns true
        editedAmelia = new HairdresserBuilder(AMELIA).withTitle(VALID_TITLE_BENJAMIN)
            .withSpecs(VALID_SPECIALISATION_PERM)
            .build();
        assertTrue(AMELIA.isSame(editedAmelia));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Hairdresser aliceCopy = new HairdresserBuilder(AMELIA).build();
        assertTrue(AMELIA.equals(aliceCopy));

        // same object -> returns true
        assertTrue(AMELIA.equals(AMELIA));

        // null -> returns false
        assertFalse(AMELIA.equals(null));

        // different type -> returns false
        assertFalse(AMELIA.equals(5));

        // different hairdresser -> returns false
        assertFalse(AMELIA.equals(BENJAMIN));

        // different name -> returns false
        Hairdresser editedAmelia = new HairdresserBuilder(AMELIA).withName(VALID_NAME_BENJAMIN)
            .build();
        assertFalse(AMELIA.equals(editedAmelia));

        // different phone -> returns false
        editedAmelia = new HairdresserBuilder(AMELIA).withPhone(VALID_PHONE_BENJAMIN)
            .build();
        assertFalse(AMELIA.equals(editedAmelia));

        // different email -> returns false
        editedAmelia = new HairdresserBuilder(AMELIA).withEmail(VALID_EMAIL_BENJAMIN)
            .build();
        assertFalse(AMELIA.equals(editedAmelia));

        // different gender -> returns false
        editedAmelia = new HairdresserBuilder(AMELIA).withGender(VALID_GENDER_BENJAMIN)
            .build();
        assertFalse(AMELIA.equals(editedAmelia));

        // different title -> returns false
        editedAmelia = new HairdresserBuilder(AMELIA).withTitle(VALID_TITLE_BENJAMIN)
            .build();
        assertFalse(AMELIA.equals(editedAmelia));

        // different tags -> returns false
        editedAmelia = new HairdresserBuilder(AMELIA).withSpecs(VALID_SPECIALISATION_PERM)
            .build();
        assertFalse(AMELIA.equals(editedAmelia));
    }
}
