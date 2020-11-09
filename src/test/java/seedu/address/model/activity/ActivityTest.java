package seedu.address.model.activity;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.typicals.TypicalActivities.THEMEPARK;
import static seedu.address.testutil.typicals.TypicalActivities.ZOO;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.builders.ActivityBuilder;

public class ActivityTest {

    @Test
    public void isSameActivity() {
        // same object -> returns true
        assertTrue(ZOO.isSameActivity(ZOO));

        // null -> returns false
        assertFalse(ZOO.isSameActivity(null));

        // different Cost -> returns true
        Activity editedZoo = new ActivityBuilder(ZOO).withCost("10").build();
        assertTrue(ZOO.isSameActivity(editedZoo));

        // different name -> returns false
        editedZoo = new ActivityBuilder(ZOO).withName("Singapore Mandai Bird Park").build();
        assertFalse(ZOO.isSameActivity(editedZoo));

    }

    @Test
    public void equals() {
        // same values -> returns true
        Activity zooCopy = new ActivityBuilder(ZOO).build();
        assertTrue(ZOO.equals(zooCopy));

        // same object -> returns true
        assertTrue(ZOO.equals(ZOO));

        // null -> returns false
        assertFalse(ZOO.equals(null));

        // different type -> returns false
        assertFalse(ZOO.equals(5));

        // different Activity -> returns false
        assertFalse(ZOO.equals(THEMEPARK));

        // different name -> returns false
        Activity editedZoo = new ActivityBuilder(ZOO).withName("Park").build();
        assertFalse(ZOO.equals(editedZoo));

        // different Cost -> returns false
        editedZoo = new ActivityBuilder(ZOO).withCost("10").build();
        assertFalse(ZOO.equals(editedZoo));

        // different Location -> returns false
        editedZoo = new ActivityBuilder(ZOO).withLocation("123 Pasir Ris Park").build();
        assertFalse(ZOO.equals(editedZoo));

        // different level of importance -> returns false
        editedZoo = new ActivityBuilder(ZOO).withLevelOfImportance("1").build();
        assertFalse(ZOO.equals(editedZoo));

        // different date and time -> returns false
        editedZoo = new ActivityBuilder(ZOO).withDateTime("2020-12-31 12:00").build();
        assertFalse(ZOO.equals(editedZoo));
    }
}
