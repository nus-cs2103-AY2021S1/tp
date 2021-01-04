package seedu.homerce.logic.commands.appointment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.homerce.commons.core.Messages.MESSAGE_APPOINTMENTS_LISTED_OVERVIEW;
import static seedu.homerce.logic.commands.expense.ExpenseCommandTestUtil.assertCommandSuccessTab;
import static seedu.homerce.testutil.appointment.TypicalAppointments.OCT_25_2020_2PM_HAIR_TREATMENT_ALICE;
import static seedu.homerce.testutil.appointment.TypicalAppointments.getTypicalAppointmentManager;
import static seedu.homerce.testutil.client.TypicalClients.getTypicalClientManager;
import static seedu.homerce.testutil.service.TypicalServices.getTypicalServiceManager;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.homerce.model.Model;
import seedu.homerce.model.ModelManager;
import seedu.homerce.model.UserPrefs;
import seedu.homerce.model.appointment.predicate.AppointmentDatePredicate;
import seedu.homerce.model.appointment.predicate.AppointmentPhonePredicate;
import seedu.homerce.model.client.Phone;
import seedu.homerce.model.manager.ExpenseTracker;
import seedu.homerce.model.manager.RevenueTracker;
import seedu.homerce.model.util.attributes.Date;
import seedu.homerce.testutil.appointment.AppointmentBuilder;

public class FindAppointmentCommandTest {
    private Model model = new ModelManager(new UserPrefs(), getTypicalClientManager(), getTypicalServiceManager(),
        new RevenueTracker(), new ExpenseTracker(), getTypicalAppointmentManager());
    private Model expectedModel = new ModelManager(new UserPrefs(), getTypicalClientManager(),
        getTypicalServiceManager(), new RevenueTracker(), new ExpenseTracker(), getTypicalAppointmentManager());

    @Test
    public void equals() {
        Phone phoneOne = new Phone("999");
        Phone phoneTwo = new Phone("91234567");
        AppointmentPhonePredicate testPredicateOne = new AppointmentPhonePredicate(phoneOne);
        AppointmentPhonePredicate testPredicateTwo = new AppointmentPhonePredicate(phoneTwo);
        FindAppointmentCommand testFindCommandOne = new FindAppointmentCommand(testPredicateOne);
        FindAppointmentCommand testFindCommandTwo = new FindAppointmentCommand(testPredicateTwo);

        // same object -> return true
        assertTrue(testFindCommandOne.equals(testFindCommandOne));

        // same values -> return true
        FindAppointmentCommand testFindCommandOneCopy = new FindAppointmentCommand(testPredicateOne);
        assertTrue(testFindCommandOne.equals(testFindCommandOneCopy));

        // different types -> return false
        assertFalse(testFindCommandOne.equals(1));

        // different values -> return false
        assertFalse(testFindCommandOne.equals(testFindCommandTwo));

        // null -> return false
        assertFalse(testFindCommandOne.equals(null));
    }

    @Test
    public void execute_parameterDoesNotExist_success() {
        String expectedMessage = String.format(MESSAGE_APPOINTMENTS_LISTED_OVERVIEW, 0);
        AppointmentPhonePredicate predicate = new AppointmentPhonePredicate(new Phone("999"));
        FindAppointmentCommand command = new FindAppointmentCommand(predicate);
        expectedModel.updateFilteredAppointmentList(predicate);
        assertCommandSuccessTab(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(), model.getFilteredAppointmentList());
    }

    @Test
    public void execute_parameterExists_success() {
        String expectedMessage = String.format(MESSAGE_APPOINTMENTS_LISTED_OVERVIEW, 1);
        AppointmentDatePredicate predicate = new AppointmentDatePredicate(new Date("25-10-2020"));
        FindAppointmentCommand command = new FindAppointmentCommand(predicate);
        expectedModel.updateFilteredAppointmentList(predicate);
        assertCommandSuccessTab(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(
            new AppointmentBuilder(OCT_25_2020_2PM_HAIR_TREATMENT_ALICE).build()),
            model.getFilteredAppointmentList()
        );
    }
}
