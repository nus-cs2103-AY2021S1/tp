package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.ANOTHER_DATE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DURATION;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DURATION_LONG;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TIME;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAppointments.ALICE_APPOINTMENT;
import static seedu.address.testutil.TypicalAppointments.getTypicalAppointmentBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_APPOINTMENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_APPOINTMENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_APPOINTMENT;
import static seedu.address.testutil.TypicalPatients.getTypicalPatientBook;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ChangeCommand.EditAppointmentDescriptor;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.AppointmentBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.PatientBook;
import seedu.address.model.UserPrefs;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.Date;
import seedu.address.model.appointment.Time;
import seedu.address.model.patient.Patient;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for
 * ChangeCommand.
 */
public class ChangeCommandTest {
    private Model model = new ModelManager(getTypicalPatientBook(), getTypicalAppointmentBook(), new UserPrefs());
    private Time time = ParserUtil.parseTime(VALID_TIME);
    private Time endTime = new Time(time.getTime().plusMinutes(VALID_DURATION_LONG));
    private Duration duration = ParserUtil.parseDuration(VALID_DURATION);
    private Date date = ParserUtil.parseDate(VALID_DATE);

    public ChangeCommandTest() throws ParseException {
    }

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        EditAppointmentDescriptor descriptor = new EditAppointmentDescriptor(date, time, duration);

        ChangeCommand changeCommand = new ChangeCommand(INDEX_FIRST_APPOINTMENT, descriptor);

        Patient firstAppointmentPatient = model.getFilteredAppointmentList()
            .get(INDEX_FIRST_APPOINTMENT.getZeroBased()).getPatient();

        Appointment firstEditedAppointment = new Appointment(date, time, endTime, firstAppointmentPatient, false);

        String expectedMessage = String.format(ChangeCommand.MESSAGE_EDIT_APPOINTMENT_SUCCESS,
                firstEditedAppointment);

        Model expectedModel = new ModelManager(new PatientBook(model.getPatientBook()),
                new AppointmentBook(model.getAppointmentBook()), new UserPrefs());

        expectedModel.setAppointment(model.getFilteredAppointmentList()
            .get(INDEX_FIRST_APPOINTMENT.getZeroBased()), firstEditedAppointment);
        expectedModel.commitAppointmentBook();
        expectedModel.commitPatientBook();
        assertCommandSuccess(changeCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        EditAppointmentDescriptor descriptor = new EditAppointmentDescriptor();
        descriptor.setDate(date);
        descriptor.setDuration(duration);

        ChangeCommand changeCommand = new ChangeCommand(INDEX_FIRST_APPOINTMENT, descriptor);

        Appointment firstAppointmentToEdit = model.getFilteredAppointmentList()
                .get(INDEX_FIRST_APPOINTMENT.getZeroBased());
        Patient firstAppointmentPatient = model.getFilteredAppointmentList()
                .get(INDEX_FIRST_APPOINTMENT.getZeroBased()).getPatient();

        Time endTime = new Time(firstAppointmentToEdit.getStartTime().getTime().plusMinutes(VALID_DURATION_LONG));

        Appointment firstEditedAppointment = new Appointment(date, firstAppointmentToEdit.getStartTime(), endTime,
                firstAppointmentPatient, false);

        String expectedMessage = String.format(ChangeCommand.MESSAGE_EDIT_APPOINTMENT_SUCCESS,
                firstEditedAppointment);

        Model expectedModel = new ModelManager(new PatientBook(model.getPatientBook()),
                new AppointmentBook(model.getAppointmentBook()), new UserPrefs());

        expectedModel.setAppointment(model.getFilteredAppointmentList()
                .get(INDEX_FIRST_APPOINTMENT.getZeroBased()), firstEditedAppointment);
        expectedModel.commitAppointmentBook();
        expectedModel.commitPatientBook();
        assertCommandSuccess(changeCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_fail() {
        ChangeCommand changeCommand = new ChangeCommand(INDEX_FIRST_APPOINTMENT, new EditAppointmentDescriptor());

        String expectedMessage = String.format(ChangeCommand.MESSAGE_SAME_APPOINTMENT);

        Model expectedModel = new ModelManager(new PatientBook(model.getPatientBook()),
                new AppointmentBook(model.getAppointmentBook()), new UserPrefs());
        expectedModel.commitPatientBook();
        expectedModel.commitAppointmentBook();
        assertCommandFailure(changeCommand, model, expectedMessage);
    }

    @Test
    public void execute_duplicateAppointmentUnfilteredList_failure() throws ParseException {
        EditAppointmentDescriptor descriptor = new EditAppointmentDescriptor();
        descriptor.setDate(ParserUtil.parseDate(ANOTHER_DATE));
        ChangeCommand changeCommand = new ChangeCommand(INDEX_THIRD_APPOINTMENT, descriptor);

        assertCommandFailure(changeCommand, model, ChangeCommand.MESSAGE_DUPLICATE_APPOINTMENT);
    }

    @Test
    public void execute_invalidAppointmentIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredAppointmentList().size() + 1);
        EditAppointmentDescriptor descriptor = new EditAppointmentDescriptor(date, time, duration);
        ChangeCommand changeCommand = new ChangeCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(changeCommand, model, Messages.MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_overlapAppointmentUnfilteredList_failure() {
        EditAppointmentDescriptor descriptor = new EditAppointmentDescriptor();
        Time overlappingTime = new Time(ALICE_APPOINTMENT.getStartTime().getTime().plusMinutes(30));
        descriptor.setStartTime(overlappingTime);
        descriptor.setDate(ALICE_APPOINTMENT.getDate());
        descriptor.setDuration(ALICE_APPOINTMENT.getDuration());

        ChangeCommand changeCommand = new ChangeCommand(INDEX_THIRD_APPOINTMENT, descriptor);

        assertCommandFailure(changeCommand, model, ChangeCommand.APPOINTMENT_OVERLAP);
    }

    @Test
    public void execute_doneAppointmentFilteredList_failure() {
        Appointment doneAppointment = ALICE_APPOINTMENT.markAsDone();
        model.setAppointment(ALICE_APPOINTMENT, doneAppointment);
        EditAppointmentDescriptor descriptor = new EditAppointmentDescriptor(date, time, duration);

        ChangeCommand changeCommand = new ChangeCommand(INDEX_FIRST_APPOINTMENT, descriptor);
        model.updateFilteredAppointmentList(appointment -> appointment.equals(doneAppointment));

        assertCommandFailure(changeCommand, model, ChangeCommand.APPOINTMENT_DONE);
    }

    @Test
    public void execute_invalidDurationEndTimeOutOfRange_failure() {
        Duration duration = Duration.between(time.getTime(), LocalTime.MIDNIGHT);;
        EditAppointmentDescriptor descriptor = new EditAppointmentDescriptor(date, time, duration);

        ChangeCommand changeCommand = new ChangeCommand(INDEX_FIRST_APPOINTMENT, descriptor);

        assertCommandFailure(changeCommand, model, ParserUtil.MESSAGE_DURATION_EXCEEDED);
    }

    @Test
    public void execute_expiredDateTime_failure() throws ParseException {
        Date expiredDate = new Date(LocalDate.of(2010, 2, 2));
        EditAppointmentDescriptor descriptor = new EditAppointmentDescriptor(expiredDate, time, duration);

        ChangeCommand changeCommand = new ChangeCommand(INDEX_FIRST_APPOINTMENT, descriptor);

        assertCommandFailure(changeCommand, model, Messages.MESSAGE_EXPIRED_DATE_TIME);
    }

    @Test
    public void equals() {
        EditAppointmentDescriptor descriptor = new EditAppointmentDescriptor(date, time, duration);
        EditAppointmentDescriptor descriptorTwo = new EditAppointmentDescriptor(date, endTime, duration);

        final ChangeCommand standardCommand = new ChangeCommand(INDEX_FIRST_APPOINTMENT, descriptor);

        // same values -> returns true
        EditAppointmentDescriptor copyDescriptor = new EditAppointmentDescriptor(descriptor);
        ChangeCommand commandWithSameValues = new ChangeCommand(INDEX_FIRST_APPOINTMENT, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new ChangeCommand(INDEX_SECOND_APPOINTMENT, descriptor)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new ChangeCommand(INDEX_FIRST_APPOINTMENT, descriptorTwo)));
    }
}
