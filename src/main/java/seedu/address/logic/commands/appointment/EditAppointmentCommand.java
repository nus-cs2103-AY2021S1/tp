package seedu.address.logic.commands.appointment;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APP_ENDTIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APP_PATIENTIC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APP_PATIENTNAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APP_STARTTIME;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_APPOINTMENTS;

import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.AppointmentDateTime;
import seedu.address.model.appointment.UniqueAppointmentList;
import seedu.address.model.patient.IcNumber;
import seedu.address.model.patient.Name;

/**
 * Edits the details of an existing appointment in the CliniCal application.
 */
public class EditAppointmentCommand extends Command {

    public static final String COMMAND_WORD = "editapp";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the appointment identified "
            + "by the index number used in the displayed appointment list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_APP_PATIENTNAME + "PATIENT_NAME] "
            + "[" + PREFIX_APP_PATIENTIC + "PATIENT_IC] "
            + "[" + PREFIX_APP_STARTTIME + "START_TIME] "
            + "[" + PREFIX_APP_ENDTIME + "END_TIME]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_APP_STARTTIME + "2020-10-23 11:00 "
            + PREFIX_APP_ENDTIME + "2020-10-23 11:40";

    public static final String MESSAGE_EDIT_APPOINTMENT_SUCCESS = "Edited Appointment: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_CONFLICTING_APPOINTMENT =
            "The new appointment time clashes with another appointment time.";

    private final Index index;
    private final EditAppointmentDescriptor editAppointmentDescriptor;

    /**
     * @param index of the Appointment in the filtered Appointment list to edit
     * @param editAppointmentDescriptor details to edit the Appointment with
     */
    public EditAppointmentCommand(Index index, EditAppointmentDescriptor editAppointmentDescriptor) {
        requireNonNull(index);
        requireNonNull(editAppointmentDescriptor);

        this.index = index;
        this.editAppointmentDescriptor = new EditAppointmentDescriptor(editAppointmentDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Appointment> lastShownList = model.getFilteredAppointmentList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX);
        }

        Appointment appointmentToEdit = lastShownList.get(index.getZeroBased());
        Appointment editedAppointment = createEditedAppointment(appointmentToEdit, editAppointmentDescriptor);

        // for editing appointment time
        if (!appointmentToEdit.isSameAppointmentTime(editedAppointment)) {
            UniqueAppointmentList temp = new UniqueAppointmentList();
            temp.setAppointments(model.getFilteredAppointmentList());
            temp.remove(appointmentToEdit);
            if (temp.clashes(editedAppointment)) {
                throw new CommandException(MESSAGE_CONFLICTING_APPOINTMENT);
            }
        }

        model.setAppointment(appointmentToEdit, editedAppointment);
        model.updateFilteredAppointmentList(PREDICATE_SHOW_ALL_APPOINTMENTS);
        model.commitCliniCal(String.format(Messages.MESSAGE_UNDONE_REDONE_INPUT, COMMAND_WORD, editedAppointment));
        return new CommandResult(String.format(MESSAGE_EDIT_APPOINTMENT_SUCCESS, editedAppointment));
    }

    /**
     * Creates and returns a {@code Appointment} with the details of {@code appointmentToEdit}
     * edited with {@code editAppointmentDescriptor}.
     */
    private static Appointment createEditedAppointment(Appointment appointmentToEdit, EditAppointmentDescriptor
            editAppointmentDescriptor) throws CommandException {
        assert appointmentToEdit != null;

        Name updatedPatientName = editAppointmentDescriptor.getPatientName().orElse(appointmentToEdit.getPatientName());
        IcNumber updatedPatientIc = editAppointmentDescriptor.getPatientIc().orElse(appointmentToEdit.getPatientIc());
        AppointmentDateTime updatedStartTime = editAppointmentDescriptor.getStartTime()
                .orElse(appointmentToEdit.getStartTime());
        AppointmentDateTime updatedEndTime = editAppointmentDescriptor.getEndTime()
                .orElse(appointmentToEdit.getEndTime());

        if (updatedStartTime.compareTo(updatedEndTime) >= 0) {
            throw new CommandException(Messages.MESSAGE_INVALID_APPOINTMENT_START_END);
        }

        return new Appointment(updatedPatientName, updatedPatientIc, updatedStartTime, updatedEndTime);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditAppointmentCommand)) {
            return false;
        }

        // state check
        EditAppointmentCommand e = (EditAppointmentCommand) other;
        return index.equals(e.index)
                && editAppointmentDescriptor.equals(e.editAppointmentDescriptor);
    }

    /**
     * Stores the details to edit the Appointment with. Each non-empty field value will replace the
     * corresponding field value of the Appointment.
     */
    public static class EditAppointmentDescriptor {
        private Name patientName;
        private IcNumber patientIc;
        private AppointmentDateTime startTime;
        private AppointmentDateTime endTime;

        public EditAppointmentDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code allergies} is used internally.
         */
        public EditAppointmentDescriptor(EditAppointmentDescriptor toCopy) {
            setPatientName(toCopy.patientName);
            setPatientIc(toCopy.patientIc);
            setStartTime(toCopy.startTime);
            setEndTime(toCopy.endTime);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(patientName, patientIc, startTime, endTime);
        }

        public void setPatientName(Name patientName) {
            this.patientName = patientName;
        }

        public Optional<Name> getPatientName() {
            return Optional.ofNullable(patientName);
        }

        public void setPatientIc(IcNumber patientIc) {
            this.patientIc = patientIc;
        }

        public Optional<IcNumber> getPatientIc() {
            return Optional.ofNullable(patientIc);
        }

        public void setStartTime(AppointmentDateTime startTime) {
            this.startTime = startTime;
        }

        public Optional<AppointmentDateTime> getStartTime() {
            return Optional.ofNullable(startTime);
        }

        public void setEndTime(AppointmentDateTime endTime) {
            this.endTime = endTime;
        }

        public Optional<AppointmentDateTime> getEndTime() {
            return Optional.ofNullable(endTime);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditAppointmentDescriptor)) {
                return false;
            }

            // state check
            EditAppointmentDescriptor e = (EditAppointmentDescriptor) other;

            return getPatientName().equals(e.getPatientName())
                    && getPatientIc().equals(e.getPatientIc())
                    && getStartTime().equals(e.getStartTime())
                    && getStartTime().equals(e.getEndTime());
        }
    }
}
