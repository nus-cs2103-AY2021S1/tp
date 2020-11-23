package seedu.address.logic.parser;

import static seedu.address.logic.commands.CommandTestUtil.DESC_INVALID_CLIENT_ID;
import static seedu.address.logic.commands.CommandTestUtil.DESC_INVALID_DATE_OF_APPT;
import static seedu.address.logic.commands.CommandTestUtil.DESC_INVALID_HAIRDRESSER_ID;
import static seedu.address.logic.commands.CommandTestUtil.DESC_INVALID_STATUS;
import static seedu.address.logic.commands.CommandTestUtil.DESC_VALID_CLIENT_ID;
import static seedu.address.logic.commands.CommandTestUtil.DESC_VALID_DATE_OF_APPT;
import static seedu.address.logic.commands.CommandTestUtil.DESC_VALID_HAIRDRESSER_ID;
import static seedu.address.logic.commands.CommandTestUtil.DESC_VALID_STATUS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CLIENT_ID;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_OF_APPT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_HAIRDRESSER_ID;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STATUS;
import static seedu.address.logic.commands.appointment.FilterAppointmentCommand.MESSAGE_NOT_STATED;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.appointment.FilterAppointmentCommand;
import seedu.address.logic.parser.appointment.FilterAppointmentCommandParser;
import seedu.address.model.appointment.AppointmentDate;
import seedu.address.model.appointment.AppointmentStatus;
import seedu.address.model.person.client.ClientId;
import seedu.address.model.person.hairdresser.HairdresserId;

public class FilterAppointmentCommandParserTest {
    private FilterAppointmentCommandParser parser = new FilterAppointmentCommandParser();

    @Test
    public void parse_baseCommand_failure() {
        String expectedMessage = String.format(MESSAGE_NOT_STATED);

        assertParseFailure(parser, "", expectedMessage);
    }

    @Test
    public void parse_clientIdFilter_success() {
        FilterAppointmentCommand.FilterAppointmentDescriptor descriptor =
                new FilterAppointmentCommand.FilterAppointmentDescriptor();
        descriptor.setClientId(Optional.of(new ClientId(VALID_CLIENT_ID)));
        FilterAppointmentCommand command = new FilterAppointmentCommand(descriptor);

        assertParseSuccess(parser, DESC_VALID_CLIENT_ID, command);
    }

    @Test
    public void parse_clientIdFilter_failure() {
        String expectedMessage = String.format(ClientId.MESSAGE_CONSTRAINTS);

        assertParseFailure(parser, DESC_INVALID_CLIENT_ID, expectedMessage);
    }

    @Test
    public void parse_hairdresserIdFilter_success() {
        FilterAppointmentCommand.FilterAppointmentDescriptor descriptor =
                new FilterAppointmentCommand.FilterAppointmentDescriptor();
        descriptor.setHairdresserId(Optional.of(new HairdresserId(VALID_HAIRDRESSER_ID)));
        FilterAppointmentCommand command = new FilterAppointmentCommand(descriptor);

        assertParseSuccess(parser, DESC_VALID_HAIRDRESSER_ID, command);
    }

    @Test
    public void parse_hairdresserIdFilter_failure() {
        String expectedMessage = String.format(HairdresserId.MESSAGE_CONSTRAINTS);

        assertParseFailure(parser, DESC_INVALID_HAIRDRESSER_ID, expectedMessage);
    }

    @Test
    public void parse_dateFilter_success() {
        FilterAppointmentCommand.FilterAppointmentDescriptor descriptor =
                new FilterAppointmentCommand.FilterAppointmentDescriptor();
        descriptor.setDate(Optional.of(new AppointmentDate(VALID_DATE_OF_APPT)));
        FilterAppointmentCommand command = new FilterAppointmentCommand(descriptor);

        assertParseSuccess(parser, DESC_VALID_DATE_OF_APPT, command);
    }

    @Test
    public void parse_dateFilter_failure() {
        String expectedMessage = String.format(AppointmentDate.MESSAGE_CONSTRAINTS);

        assertParseFailure(parser, DESC_INVALID_DATE_OF_APPT, expectedMessage);
    }

    @Test
    public void parse_statusFilter_success() {
        FilterAppointmentCommand.FilterAppointmentDescriptor descriptor =
                new FilterAppointmentCommand.FilterAppointmentDescriptor();
        descriptor.setStatus(Optional.of(AppointmentStatus.valueOf(VALID_STATUS)));
        FilterAppointmentCommand command = new FilterAppointmentCommand(descriptor);

        assertParseSuccess(parser, DESC_VALID_STATUS, command);
    }

    @Test
    public void parse_statusFilter_failure() {
        String expectedMessage = String.format(AppointmentStatus.MESSAGE_CONSTRAINTS);

        assertParseFailure(parser, DESC_INVALID_STATUS, expectedMessage);
    }


    @Test
    public void parse_allFilter_success() {
        FilterAppointmentCommand.FilterAppointmentDescriptor descriptor =
                new FilterAppointmentCommand.FilterAppointmentDescriptor();

        descriptor.setClientId(Optional.of(new ClientId(VALID_CLIENT_ID)));
        descriptor.setHairdresserId(Optional.of(new HairdresserId(VALID_HAIRDRESSER_ID)));
        descriptor.setDate(Optional.of(new AppointmentDate(VALID_DATE_OF_APPT)));
        descriptor.setStatus(Optional.of(AppointmentStatus.valueOf(VALID_STATUS)));

        FilterAppointmentCommand command = new FilterAppointmentCommand(descriptor);

        assertParseSuccess(parser, DESC_VALID_CLIENT_ID + DESC_VALID_HAIRDRESSER_ID
                + DESC_VALID_DATE_OF_APPT + DESC_VALID_STATUS, command);
    }
}
