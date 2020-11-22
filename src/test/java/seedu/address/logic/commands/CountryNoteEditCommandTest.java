package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TestUtil.basicEqualsTests;
import static seedu.address.testutil.TypicalClients.getTypicalTbmManager;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.country.Country;
import seedu.address.model.note.CountryNote;
import seedu.address.model.tag.Tag;

public class CountryNoteEditCommandTest {

    private final Index index = Index.fromOneBased(1);
    private final CountryNote countryNote = new CountryNote("abc", new Country("US"));
    private final CountryNote countryNoteWithDifferentCountry = new CountryNote("abc", new Country("SG"));
    private final CountryNote countryNoteWithDifferentContent = new CountryNote("abcde", new Country("US"));
    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalTbmManager(), new UserPrefs());
        model.setCountryNotesListPanelIsVisible(true);
    }

    @Test
    public void equals_basicTests() {
        CountryNoteEditCommand countryNoteEditCommand =
                new CountryNoteEditCommand(index, countryNoteWithDifferentCountry);
        // basic equals tests
        basicEqualsTests(countryNoteEditCommand);
    }

    @Test
    public void equals_allEqual_equal() {
        CountryNoteEditCommand expected = new CountryNoteEditCommand(index, countryNote);
        CountryNoteEditCommand actual = new CountryNoteEditCommand(index, countryNote);
        assertEquals(expected, actual);
    }

    @Test
    public void equals_diffIndex_notEqual() {
        CountryNoteEditCommand expected = new CountryNoteEditCommand(index, countryNote);
        CountryNoteEditCommand actual = new CountryNoteEditCommand(Index.fromOneBased(2), countryNote);
        assertNotEquals(expected, actual);
    }

    @Test
    public void equals_diffCountryNote_notEqual() {
        CountryNoteEditCommand expected = new CountryNoteEditCommand(index, countryNote);
        CountryNoteEditCommand actual = new CountryNoteEditCommand(index, countryNoteWithDifferentCountry);
        CountryNoteEditCommand actual2 = new CountryNoteEditCommand(index, countryNoteWithDifferentContent);
        assertNotEquals(expected, actual);
        assertNotEquals(expected, actual2);
        assertNotEquals(actual, actual2);
    }

    @Test
    public void equals_sameTags_equal() {
        CountryNoteEditCommand expected = new CountryNoteEditCommand(index, new HashSet<>());
        CountryNoteEditCommand actual = new CountryNoteEditCommand(index, new HashSet<>());
        assertEquals(expected, actual);
    }

    @Test
    public void equals_diffTags_equal() {
        Set<Tag> tags = new HashSet<>();
        tags.add(new Tag("a"));
        CountryNoteEditCommand expected = new CountryNoteEditCommand(index, tags);
        CountryNoteEditCommand actual = new CountryNoteEditCommand(index, new HashSet<>());
        assertNotEquals(expected, actual);
    }

    @Test
    public void execute_invalidCountryNoteIndex_throwsCommandException() {
        Index invalidCountryNoteIndex = Index.fromOneBased(model.getSortedFilteredCountryNoteList().size() + 1);
        CountryNoteEditCommand countryNoteEditCommand =
                new CountryNoteEditCommand(invalidCountryNoteIndex, countryNote);
        assertCommandFailure(countryNoteEditCommand, model, Messages.MESSAGE_INVALID_COUNTRY_NOTE_DISPLAYED_INDEX);
    }

    @Test
    public void execute_nullModel_throwsNullPointerException() {
        CountryNoteEditCommand countryNoteEditCommand = new CountryNoteEditCommand(index, countryNote);
        assertThrows(NullPointerException.class, () -> countryNoteEditCommand.execute(null));
    }

    @Test
    public void execute_validCountryNoteIndex_successfullyDeletesNote() {
        model.addCountryNote(countryNote);
        CountryNoteEditCommand countryNoteEditCommand =
                new CountryNoteEditCommand(index, countryNoteWithDifferentContent);

        Model expectedModel = new ModelManager(getTypicalTbmManager(), new UserPrefs());
        expectedModel.addCountryNote(countryNoteWithDifferentContent);
        String expectedMessage = String.format(CountryNoteEditCommand.MESSAGE_SUCCESS, index.getOneBased(),
                countryNoteWithDifferentContent);

        assertCommandSuccess(countryNoteEditCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_countryNotesPanelNotVisible_throwsCommandException() {
        model.setCountryNotesListPanelIsVisible(false);
        model.addCountryNote(countryNote);
        CountryNoteEditCommand countryNoteEditCommand =
                new CountryNoteEditCommand(index, countryNoteWithDifferentContent);

        assertCommandFailure(countryNoteEditCommand, model,
                CountryNoteEditCommand.MESSAGE_COUNTRY_NOTES_NOT_VISIBLE);
    }

}
