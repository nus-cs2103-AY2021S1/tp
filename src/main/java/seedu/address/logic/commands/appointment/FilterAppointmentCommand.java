package seedu.address.logic.commands.appointment;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PLACEHOLDER_APPT_STATUS;
import static seedu.address.logic.parser.CliSyntax.PLACEHOLDER_CLIENT_INDEX;
import static seedu.address.logic.parser.CliSyntax.PLACEHOLDER_DATE_OF_APPT;
import static seedu.address.logic.parser.CliSyntax.PLACEHOLDER_HAIRDRESSER_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPT_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLIENT_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE_OF_APPT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HAIRDRESSER_ID;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_APPOINTMENTS;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.AppointmentDate;
import seedu.address.model.appointment.AppointmentStatus;
import seedu.address.model.person.client.ClientId;
import seedu.address.model.person.hairdresser.HairdresserId;


/**
 * Filters all appointments in HairStyleX to the user.
 */
public class FilterAppointmentCommand extends Command {

    public static final String COMMAND_WORD = "filter_appt";
    public static final String COMMAND_EXAMPLE = "Example: " + COMMAND_WORD + " "
        + PREFIX_HAIRDRESSER_ID + "1 "
        + PREFIX_CLIENT_ID + "1 "
        + PREFIX_DATE_OF_APPT + "2019-06-01 "
        + PREFIX_APPT_STATUS + "active ";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " "
        + "[" + PREFIX_CLIENT_ID + PLACEHOLDER_CLIENT_INDEX + "] "
        + "[" + PREFIX_HAIRDRESSER_ID + PLACEHOLDER_HAIRDRESSER_INDEX + "] "
        + "[" + PREFIX_DATE_OF_APPT + PLACEHOLDER_DATE_OF_APPT + "] "
        + "[" + PREFIX_APPT_STATUS + PLACEHOLDER_APPT_STATUS + "] "
        + COMMAND_EXAMPLE;

    public static final String MESSAGE_SUCCESS = "Listed all appointments";
    public static final String MESSAGE_SUCCESS_FILTERED_CLIENT = ", filtered by client ID";
    public static final String MESSAGE_SUCCESS_FILTERED_HAIRDRESSER = ", filtered by hairdresser ID";
    public static final String MESSAGE_SUCCESS_FILTERED_DATE = ", filtered by date";
    public static final String MESSAGE_SUCCESS_FILTERED_STATUS = ", filtered by status";
    public static final String MESSAGE_NOT_STATED = "At least one field must be provided.";

    private final FilterAppointmentDescriptor filterAppointmentDescriptor;

    public FilterAppointmentCommand(FilterAppointmentDescriptor filterAppointmentDescriptor) {
        this.filterAppointmentDescriptor = new FilterAppointmentDescriptor(filterAppointmentDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Predicate<Appointment>> predicates = new ArrayList<>();
        StringBuilder result = new StringBuilder(MESSAGE_SUCCESS);

        if (filterAppointmentDescriptor.getClientId().isPresent()) {
            ClientId id = filterAppointmentDescriptor.getClientId().get();
            if (model.getClientById(id) == null) {
                throw new CommandException(AddAppointmentCommand.MESSAGE_CLIENT_NOT_FOUND);
            }
            assert id != null : "client id cannot be null";
            predicates.add(appointment -> {
                return appointment.getClientId().equals(id);
            });
            result.append(MESSAGE_SUCCESS_FILTERED_CLIENT + ": ");
            result.append(id);
        }

        if (filterAppointmentDescriptor.getHairdresserId().isPresent()) {
            HairdresserId id = filterAppointmentDescriptor.getHairdresserId().get();
            if (model.getHairdresserById(id) == null) {
                throw new CommandException(AddAppointmentCommand.MESSAGE_HAIRDRESSER_NOT_NOT_FOUND);
            }
            assert id != null : "hairdresser id cannot be null";
            predicates.add(appointment -> {
                return appointment.getHairdresserId().equals(id);
            });
            result.append(MESSAGE_SUCCESS_FILTERED_HAIRDRESSER + ": ");
            result.append(id);
        }

        filterAppointmentDescriptor.getDate().ifPresent(date -> {
            predicates.add(appointment -> {
                return appointment.getDate().equals(date);
            });
            result.append(MESSAGE_SUCCESS_FILTERED_DATE + ": ");
            result.append(date);
        });

        filterAppointmentDescriptor.getStatus().ifPresent(status -> {
            predicates.add(appointment -> {
                return appointment.getAppointmentStatus().equals(status);
            });
            result.append(MESSAGE_SUCCESS_FILTERED_STATUS + ": ");
            result.append(status);
        });

        // reduce predicate list to a single predicate
        Predicate<Appointment> combinedPredicate = predicates.stream().reduce(x -> true, Predicate::and);
        model.updateFilteredAppointmentList((PREDICATE_SHOW_ALL_APPOINTMENTS.and(combinedPredicate)));

        if (model.getFilteredAppointmentList().size() == 1) {
            return new CommandResult(Messages.MESSAGE_APPOINTMENT_LISTED_OVERVIEW_SINGULAR);
        } else {
            return new CommandResult(
                String.format(Messages.MESSAGE_APPOINTMENT_LISTED_OVERVIEW,
                    model.getFilteredAppointmentList().size()));
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FilterAppointmentCommand that = (FilterAppointmentCommand) o;
        return Objects.equals(filterAppointmentDescriptor, that.filterAppointmentDescriptor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(filterAppointmentDescriptor);
    }

    /**
     * Stores the fields to filter appointments by.
     */
    public static class FilterAppointmentDescriptor {
        private Optional<ClientId> clientId = Optional.empty();
        private Optional<HairdresserId> hairdresserId = Optional.empty();
        private Optional<AppointmentDate> date = Optional.empty();
        private Optional<AppointmentStatus> status = Optional.empty();

        public FilterAppointmentDescriptor() {
        }

        /**
         * Copy constructor. For defensive purposes, ensures only a copy is used.
         */
        public FilterAppointmentDescriptor(FilterAppointmentDescriptor toCopy) {
            setClientId(toCopy.clientId);
            setHairdresserId(toCopy.hairdresserId);
            setDate(toCopy.date);
            setStatus(toCopy.status);
        }

        public Optional<ClientId> getClientId() {
            return clientId;
        }

        public void setClientId(Optional<ClientId> clientId) {
            this.clientId = clientId;
        }

        public Optional<HairdresserId> getHairdresserId() {
            return hairdresserId;
        }

        public void setHairdresserId(Optional<HairdresserId> hairdresserId) {
            this.hairdresserId = hairdresserId;
        }

        public Optional<AppointmentDate> getDate() {
            return date;
        }

        public void setDate(Optional<AppointmentDate> date) {
            this.date = date;
        }

        public Optional<AppointmentStatus> getStatus() {
            return status;
        }

        public void setStatus(Optional<AppointmentStatus> status) {
            this.status = status;
        }

        /**
         * Returns true if no field is stated to filter the list.
         */
        public boolean noFieldStated() {
            return clientId.isEmpty() && hairdresserId.isEmpty()
                && date.isEmpty() && status.isEmpty();
        }


        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            FilterAppointmentDescriptor that = (FilterAppointmentDescriptor) o;
            return Objects.equals(clientId, that.clientId)
                && Objects.equals(hairdresserId, that.hairdresserId)
                && Objects.equals(date, that.date)
                && Objects.equals(status, that.status);
        }

        @Override
        public int hashCode() {
            return Objects.hash(clientId, hairdresserId, date, status);
        }
    }
}
