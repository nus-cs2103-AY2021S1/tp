package seedu.address.model.country;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TestUtil.basicEqualsTests;

import java.util.Locale;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.note.CountryNote;

public class CountryNotesManagerTest {

    //TODO: Add more tests if decide to include checking for 3-letter Country Code
    private static final String[] COUNTRY_CODES = Locale.getISOCountries();
    private CountryNotesManager countryNotesManager;

    @BeforeEach
    public void setUp() {
        countryNotesManager = new CountryNotesManager();
    }

    @Test
    public void hasCountryNote_duplicateNote_returnTrue() {
        for (String countryCode : COUNTRY_CODES) {
            Country country = new Country(countryCode);
            CountryNote genericNote = new CountryNote("generic note", country);
            countryNotesManager.addCountryNote(genericNote);
            assertTrue(countryNotesManager.hasCountryNote(genericNote));
        }
    }

    @Test
    public void hasCountryNote_notDuplicateNote_returnFalse() {
        for (String countryCode : COUNTRY_CODES) {
            CountryNote genericNote = new CountryNote("generic note", new Country(countryCode));
            assertFalse(countryNotesManager.hasCountryNote(genericNote));
        }
    }

    @Test
    public void addCountryNote_validNote_updatesCorrectly() {
        for (String countryCode : COUNTRY_CODES) {
            Country country = new Country(countryCode);
            CountryNote genericNote = new CountryNote("generic note", country);
            assertFalse(countryNotesManager.hasCountryNote(genericNote));
            countryNotesManager.addCountryNote(genericNote);
            assertTrue(countryNotesManager.hasCountryNote(genericNote));
        }
    }

    @Test
    public void addCountryNote_countryNoteWithNullCountry_throwsAssertionError() {
        CountryNote countryNote = new CountryNote("country note 1", Country.NULL_COUNTRY);
        assertThrows(AssertionError.class, () -> countryNotesManager.addCountryNote(countryNote));
    }

    @Test
    public void addCountryNote_duplicateNote_notAdded() {
        for (String countryCode : COUNTRY_CODES) {
            Country country = new Country(countryCode);
            CountryNote genericNote = new CountryNote("generic note", country);
            countryNotesManager.addCountryNote(genericNote);
            assertEquals(1, countryNotesManager.asUnmodifiableObservableList()
                    .stream()
                    .filter(x -> x.equals(genericNote))
                    .count());
            countryNotesManager.addCountryNote(genericNote);
            assertEquals(1, countryNotesManager.asUnmodifiableObservableList()
                    .stream()
                    .filter(x -> x.equals(genericNote))
                    .count());
        }
    }

    @Test
    public void deleteCountryNote_countryNoteWithNullCountry_throwsAssertionError() {
        CountryNote countryNote = new CountryNote("country note 1", Country.NULL_COUNTRY);
        assertThrows(AssertionError.class, () -> countryNotesManager.deleteCountryNote(countryNote));
    }

    @Test
    public void deleteCountryNote_nullCountryNote_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> countryNotesManager.deleteCountryNote(null));
    }

    @Test
    public void deleteCountryNote_countryNoteNotExist_assertionError() {
        CountryNote countryNote = new CountryNote("random", new Country("SG"));
        assertThrows(AssertionError.class, () -> countryNotesManager.deleteCountryNote(countryNote));
    }

    @Test
    public void deleteCountryNote_countryNoteExists_deletesCountryNote() {
        CountryNote countryNote = new CountryNote("random", new Country("SG"));
        countryNotesManager.addCountryNote(countryNote);
        assertTrue(countryNotesManager.hasCountryNote(countryNote));
        countryNotesManager.deleteCountryNote(countryNote);
        assertFalse(countryNotesManager.hasCountryNote(countryNote));
    }

    @Test
    public void asUnmodifiableObservableList_editList_throwsUnsupportedOperationException() {
        CountryNote countryNote = new CountryNote("random", new Country("SG"));
        assertThrows(UnsupportedOperationException.class, () ->
                countryNotesManager.asUnmodifiableObservableList().add(countryNote));
    }

    @Test
    public void equals() {
        // basic equals tests
        basicEqualsTests(countryNotesManager);

        // same clients and country notes -> returns true
        assertTrue(countryNotesManager.equals(new CountryNotesManager()));

        // different country notes -> returns false
        CountryNotesManager countryNotesManagerWithCountryNote = new CountryNotesManager();
        countryNotesManagerWithCountryNote.addCountryNote(new CountryNote("country note", new Country("AL")));
        assertFalse(countryNotesManager.equals(countryNotesManagerWithCountryNote));
    }

    @Test
    public void hashCode_test() {
        // same object -> same hashcode
        assertEquals(countryNotesManager.hashCode(), countryNotesManager.hashCode());

        // same clients and country notes -> same hashcode
        assertEquals(countryNotesManager.hashCode(), new CountryNotesManager().hashCode());

        // different country notes -> different hashcode
        CountryNotesManager countryNotesManagerWithCountryNote = new CountryNotesManager();
        countryNotesManagerWithCountryNote.addCountryNote(new CountryNote("country note", new Country("AL")));
        assertNotEquals(countryNotesManager.hashCode(), countryNotesManagerWithCountryNote.hashCode());
    }

    @Test
    public void setCountryNote_validOldAndNewCountryNote_replacesOldCountryNoteWithNewCountryNote() {
        CountryNote oldCountryNote = new CountryNote("random", new Country("SG"));
        CountryNote newCountryNote = new CountryNote("random2", new Country("MY"));

        countryNotesManager.addCountryNote(oldCountryNote);
        assertTrue(countryNotesManager.hasCountryNote(oldCountryNote));
        assertFalse(countryNotesManager.hasCountryNote(newCountryNote));

        countryNotesManager.setCountryNote(oldCountryNote, newCountryNote);
        assertFalse(countryNotesManager.hasCountryNote(oldCountryNote));
        assertTrue(countryNotesManager.hasCountryNote(newCountryNote));
    }

    @Test
    public void setCountryNote_notExistsOldCountryNote_throwsAssertError() {
        CountryNote oldCountryNote = new CountryNote("random", new Country("SG"));
        CountryNote newCountryNote = new CountryNote("random2", new Country("MY"));
        assertThrows(AssertionError.class, () ->
                countryNotesManager.setCountryNote(oldCountryNote, newCountryNote));
    }

}
