package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.time.DayOfWeek;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.model.IngredientBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.SalesBook;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) for {@code FindByTagCommand}.
 */
public class FindByTagTodayCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new SalesBook(),
            new IngredientBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new SalesBook(),
            new IngredientBook(), new UserPrefs());

    @Test
    public void equals() {

        LocalDate todayDate = LocalDate.now();
        DayOfWeek dayOfWeek = todayDate.getDayOfWeek();
        ObservableList ob;
        switch (dayOfWeek) {
        case SUNDAY:
            model.updateFilteredPersonList(Model.PREDICATE_SHOW_ALL_ACTIVE_SUNDAY_PERSONS);
            ob = model.getFilteredPersonList();
            break;
        case MONDAY:
            model.updateFilteredPersonList(Model.PREDICATE_SHOW_ALL_ACTIVE_MONDAY_PERSONS);
            ob = model.getFilteredPersonList();
            break;
        case TUESDAY:
            model.updateFilteredPersonList(Model.PREDICATE_SHOW_ALL_ACTIVE_TUESDAY_PERSONS);
            ob = model.getFilteredPersonList();
            break;
        case WEDNESDAY:
            model.updateFilteredPersonList(Model.PREDICATE_SHOW_ALL_ACTIVE_WEDNESDAY_PERSONS);
            ob = model.getFilteredPersonList();
            break;
        case THURSDAY:
            model.updateFilteredPersonList(Model.PREDICATE_SHOW_ALL_ACTIVE_THURSDAY_PERSONS);
            ob = model.getFilteredPersonList();
            break;
        case FRIDAY:
            model.updateFilteredPersonList(Model.PREDICATE_SHOW_ALL_ACTIVE_FRIDAY_PERSONS);
            ob = model.getFilteredPersonList();
            break;
        case SATURDAY:
            model.updateFilteredPersonList(Model.PREDICATE_SHOW_ALL_ACTIVE_SATURDAY_PERSONS);
            ob = model.getFilteredPersonList();
            break;
        default:
            model.updateFilteredPersonList(person -> false);
            ob = model.getFilteredPersonList();
        }

        // same object -> returns true
        assertTrue(ob.equals(ob));

        // different types -> returns false
        assertFalse(ob.equals(1));

        // null -> returns false
        assertFalse(ob.equals(null));
    }
}
