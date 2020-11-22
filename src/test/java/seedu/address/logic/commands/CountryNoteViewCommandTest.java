package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TestUtil.basicEqualsTests;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.country.Country;
import seedu.address.model.note.CountryNote;

public class CountryNoteViewCommandTest {

    private Model getDefaultModel() {
        CountryNote countryNote1 = new CountryNote("country note 1", new Country("SG"));
        CountryNote countryNote2 = new CountryNote("country note 2", new Country("MY"));
        CountryNote countryNote3 = new CountryNote("country note 3", new Country("US"));
        Model model = new ModelManager();
        model.addCountryNote(countryNote1);
        model.addCountryNote(countryNote2);
        model.addCountryNote(countryNote3);

        return model;
    }

    @Test
    public void equals_basicTests() {
        CountryNoteViewCommand countryNoteViewCommand = new CountryNoteViewCommand(new Country("SG"));
        // basic equals tests
        basicEqualsTests(countryNoteViewCommand);
    }

    @Test
    public void equals_sameCountry_returnsTrue() {
        Country country = new Country("SG");
        CountryNoteViewCommand first = new CountryNoteViewCommand(country);
        CountryNoteViewCommand second = new CountryNoteViewCommand(country);
        assertEquals(first, second);
    }

    @Test
    public void equals_diffCountry_returnsFalse() {
        Country country = new Country("SG");
        Country country2 = new Country("MY");
        CountryNoteViewCommand first = new CountryNoteViewCommand(country);
        CountryNoteViewCommand second = new CountryNoteViewCommand(country2);
        assertNotEquals(first, second);
    }

    @Test
    public void execute_nullModel_throwsNullPointerException() {
        CountryNoteViewCommand countryNoteViewCommand = new CountryNoteViewCommand(Country.NULL_COUNTRY);
        assertThrows(NullPointerException.class, () -> countryNoteViewCommand.execute(null));
    }

    @Test
    public void execute_nullCountry_returnsAllCountryNotes() {
        Model model = getDefaultModel();

        CountryNoteViewCommand countryNoteViewCommand = new CountryNoteViewCommand(Country.NULL_COUNTRY);

        Model expectedModel = getDefaultModel();
        expectedModel.updateFilteredCountryNoteList(countryNote -> true);
        String expectedMessage = String.format(CountryNoteViewCommand.MESSAGE_SUCCESS, "all countries");

        assertCommandSuccess(countryNoteViewCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validCountry_returnsFilteredCountryNotes() {
        Country validCountry = new Country("SG");
        Model model = getDefaultModel();

        CountryNoteViewCommand countryNoteViewCommand = new CountryNoteViewCommand(validCountry);

        Model expectedModel = getDefaultModel();
        expectedModel.updateFilteredCountryNoteList(countryNote -> countryNote.getCountry().equals(validCountry));
        String expectedMessage = String.format(CountryNoteViewCommand.MESSAGE_SUCCESS, validCountry);

        assertCommandSuccess(countryNoteViewCommand, model, expectedMessage, expectedModel);
    }

}
