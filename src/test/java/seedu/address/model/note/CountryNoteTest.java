package seedu.address.model.note;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TestUtil.basicEqualsTests;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.country.Country;
import seedu.address.model.tag.Tag;

public class CountryNoteTest {

    private static final String COUNTRY_NOTE_CONTENT = "country note";
    private static final Country COUNTRY = new Country("SG");
    private CountryNote countryNote;

    @BeforeEach
    public void setUp() {
        countryNote = new CountryNote(COUNTRY_NOTE_CONTENT, COUNTRY);
    }

    @Test
    public void constructor() {
        assertThrows(NullPointerException.class, () -> new CountryNote(null, COUNTRY));
        assertThrows(NullPointerException.class, () -> new CountryNote(COUNTRY_NOTE_CONTENT, null));
        assertEquals(COUNTRY, countryNote.getCountry());
        assertEquals(COUNTRY_NOTE_CONTENT, countryNote.getNoteContent());
    }

    @Test
    public void setTags() {
        Set<Tag> tags = Set.of(new Tag("tag1"));
        countryNote.setTags(tags);
        assertEquals(countryNote.getTags(), tags);
    }

    @Test
    public void isClientNote_returnFalse() {
        assertFalse(countryNote.isClientNote());
    }

    @Test
    public void equals() {
        // basic equals tests
        basicEqualsTests(countryNote);

        // same note content same country -> returns true
        assertTrue(countryNote.equals(new CountryNote(COUNTRY_NOTE_CONTENT, COUNTRY)));

        // different country -> returns false
        assertFalse(countryNote.equals(new CountryNote(COUNTRY_NOTE_CONTENT, new Country("AL"))));

        // different note content -> returns false
        assertFalse(countryNote.equals(new CountryNote("new country note", COUNTRY)));

        // different tags -> return false
        CountryNote countryNoteWithTags = new CountryNote(COUNTRY_NOTE_CONTENT, COUNTRY);
        countryNoteWithTags.setTags(Set.of(new Tag("tag1")));
        assertFalse(countryNote.equals(countryNoteWithTags));
    }

    @Test
    public void hashCode_test() {
        // same note content same country -> same hashcode
        assertEquals(countryNote.hashCode(), new CountryNote(COUNTRY_NOTE_CONTENT, COUNTRY).hashCode());

        // different country -> different hashcode
        assertNotEquals(countryNote.hashCode(),
                new CountryNote(COUNTRY_NOTE_CONTENT, new Country("AL")).hashCode());

        // different note content -> different hashcode
        assertNotEquals(countryNote.hashCode(), new CountryNote("new country note", COUNTRY).hashCode());

        // different tags -> different hashcode
        CountryNote countryNoteWithTags = new CountryNote(COUNTRY_NOTE_CONTENT, COUNTRY);
        countryNoteWithTags.setTags(Set.of(new Tag("tag1")));
        assertNotEquals(countryNote.hashCode(), countryNoteWithTags.hashCode());
    }

    @Test
    public void constructor_countryNote_ensureEqualsExpected() {
        String content = "content";
        Country country = new Country("SG");
        Set<Tag> tags = new HashSet<>();
        tags.add(new Tag("t"));
        CountryNote c = new CountryNote(content, country, tags);
        assertEquals(content, c.getNoteContent());
        assertEquals(country, c.getCountry());
        assertEquals(tags, c.getTags());
    }

}
