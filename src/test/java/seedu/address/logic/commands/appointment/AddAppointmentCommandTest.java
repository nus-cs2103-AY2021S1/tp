package seedu.address.logic.commands.appointment;

import static seedu.address.testutil.TypicalAppointmentDates.FUTURE_APPOINTMENT_DATE;
import static seedu.address.testutil.TypicalAppointmentDates.FUTURE_APPOINTMENT_DATE_2;
import static seedu.address.testutil.TypicalAppointmentDates.PAST_APPOINTMENT_DATE;
import static seedu.address.testutil.TypicalAppointmentTimes.VALID_APPOINTMENT_TIME;
import static seedu.address.testutil.TypicalClients.ALICE;
import static seedu.address.testutil.TypicalHairdressers.BENEDICT;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ModelStub;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.AppointmentStatus;
import seedu.address.model.person.client.Client;
import seedu.address.model.person.client.ClientId;
import seedu.address.model.person.hairdresser.Hairdresser;
import seedu.address.model.person.hairdresser.HairdresserId;

class AddAppointmentCommandTest {

    @Test
    void execute_nullModel() {
        Assertions.assertThrows(NullPointerException.class, () ->
            new AddAppointmentCommand(ALICE.getId(), BENEDICT.getId(),
                FUTURE_APPOINTMENT_DATE,
                VALID_APPOINTMENT_TIME)
            .execute(null)
        );
    }

    @Test
    void execute_success() {
        class ModelStubSuccess extends ModelStub {
            ModelStubSuccess(){
            }

            @Override
            public Client getClientById(ClientId clientId) {
                return ALICE;
            }

            @Override
            public Hairdresser getHairdresserById(HairdresserId hairdresserId) {
                return BENEDICT;
            }

            @Override
            public boolean hasAppointment(Appointment appointment) {
                return false;
            }

            @Override
            public List<Appointment> getAppointmentList() {
                return new ArrayList<>();
            }

            @Override
            public void addAppointment(Appointment appointment) {
            }
        }

        ModelStubSuccess model = new ModelStubSuccess();

        Assertions.assertDoesNotThrow(() ->
            new AddAppointmentCommand(ALICE.getId(), BENEDICT.getId(),
                FUTURE_APPOINTMENT_DATE,
                VALID_APPOINTMENT_TIME)
                .execute(model)
        );
    }

    @Test
    void execute_pastAppointmentDate() {
        class ModelStubSuccess extends ModelStub {
            ModelStubSuccess(){
            }

            @Override
            public Client getClientById(ClientId clientId) {
                return ALICE;
            }

            @Override
            public Hairdresser getHairdresserById(HairdresserId hairdresserId) {
                return BENEDICT;
            }

            @Override
            public boolean hasAppointment(Appointment appointment) {
                return false;
            }

            @Override
            public List<Appointment> getAppointmentList() {
                return new ArrayList<>();
            }

            @Override
            public void addAppointment(Appointment appointment) {
            }
        }

        ModelStubSuccess model = new ModelStubSuccess();

        Assertions.assertThrows(CommandException.class, () ->
            new AddAppointmentCommand(ALICE.getId(), BENEDICT.getId(),
                PAST_APPOINTMENT_DATE,
                VALID_APPOINTMENT_TIME)
                .execute(model)
        );
    }

    @Test
    void execute_noClient() {
        class ModelStubNoClient extends ModelStub {
            ModelStubNoClient(){
            }

            @Override
            public Client getClientById(ClientId clientId) {
                return null;
            }

            @Override
            public Hairdresser getHairdresserById(HairdresserId hairdresserId) {
                return BENEDICT;
            }

            @Override
            public boolean hasAppointment(Appointment appointment) {
                return false;
            }

            @Override
            public List<Appointment> getAppointmentList() {
                return new ArrayList<>();
            }

            @Override
            public void addAppointment(Appointment appointment) {
            }
        }
        ModelStubNoClient model = new ModelStubNoClient();

        Assertions.assertThrows(CommandException.class, () ->
            new AddAppointmentCommand(ALICE.getId(), BENEDICT.getId(),
                FUTURE_APPOINTMENT_DATE,
                VALID_APPOINTMENT_TIME)
                .execute(model)
        );
    }

    @Test
    void execute_noHairdresser() {
        class ModelStubNoHairdresser extends ModelStub {
            ModelStubNoHairdresser(){
            }

            @Override
            public Client getClientById(ClientId clientId) {
                return ALICE;
            }

            @Override
            public Hairdresser getHairdresserById(HairdresserId hairdresserId) {
                return null;
            }

            @Override
            public boolean hasAppointment(Appointment appointment) {
                return false;
            }

            @Override
            public List<Appointment> getAppointmentList() {
                return new ArrayList<>();
            }

            @Override
            public void addAppointment(Appointment appointment) {
            }
        }
        ModelStubNoHairdresser model = new ModelStubNoHairdresser();

        Assertions.assertThrows(CommandException.class, () ->
            new AddAppointmentCommand(ALICE.getId(), BENEDICT.getId(),
                FUTURE_APPOINTMENT_DATE,
                VALID_APPOINTMENT_TIME)
                .execute(model)
        );
    }

    @Test
    void execute_appointmentExists() {
        class ModelStubNoHairdresser extends ModelStub {
            ModelStubNoHairdresser(){
            }

            @Override
            public Client getClientById(ClientId clientId) {
                return ALICE;
            }

            @Override
            public Hairdresser getHairdresserById(HairdresserId hairdresserId) {
                return BENEDICT;
            }

            @Override
            public boolean hasAppointment(Appointment appointment) {
                return true;
            }

            @Override
            public List<Appointment> getAppointmentList() {
                return new ArrayList<>();
            }

            @Override
            public void addAppointment(Appointment appointment) {
            }
        }
        ModelStubNoHairdresser model = new ModelStubNoHairdresser();

        Assertions.assertThrows(CommandException.class, () ->
            new AddAppointmentCommand(ALICE.getId(), BENEDICT.getId(),
                FUTURE_APPOINTMENT_DATE,
                VALID_APPOINTMENT_TIME)
                .execute(model)
        );
    }

    @Test
    void execute_appointmentClash() {
        class ModelStubHasAppt extends ModelStub {
            ModelStubHasAppt(){
            }

            @Override
            public Client getClientById(ClientId clientId) {
                return ALICE;
            }

            @Override
            public Hairdresser getHairdresserById(HairdresserId hairdresserId) {
                return BENEDICT;
            }

            @Override
            public boolean hasAppointment(Appointment appointment) {
                return false;
            }

            @Override
            public List<Appointment> getAppointmentList() {
                List<Appointment> list = new ArrayList<>();
                list.add(
                    new Appointment(
                        ALICE,
                        BENEDICT,
                        FUTURE_APPOINTMENT_DATE,
                        VALID_APPOINTMENT_TIME,
                        AppointmentStatus.ACTIVE
                    )
                );
                return list;
            }

            @Override
            public void addAppointment(Appointment appointment) {
            }
        }
        ModelStubHasAppt model = new ModelStubHasAppt();

        Assertions.assertThrows(CommandException.class, () ->
            new AddAppointmentCommand(ALICE.getId(), BENEDICT.getId(),
                FUTURE_APPOINTMENT_DATE,
                VALID_APPOINTMENT_TIME)
                .execute(model)
        );
    }

    @Test
    void execute_appointmentNoClash() {
        class ModelStubHasNoClashAppt extends ModelStub {
            ModelStubHasNoClashAppt(){
            }

            @Override
            public Client getClientById(ClientId clientId) {
                return ALICE;
            }

            @Override
            public Hairdresser getHairdresserById(HairdresserId hairdresserId) {
                return BENEDICT;
            }

            @Override
            public boolean hasAppointment(Appointment appointment) {
                return false;
            }

            @Override
            public List<Appointment> getAppointmentList() {
                List<Appointment> list = new ArrayList<>();
                list.add(
                    new Appointment(
                        ALICE,
                        BENEDICT,
                        FUTURE_APPOINTMENT_DATE_2,
                        VALID_APPOINTMENT_TIME,
                        AppointmentStatus.ACTIVE
                    )
                );
                return list;
            }

            @Override
            public void addAppointment(Appointment appointment) {
            }
        }
        ModelStubHasNoClashAppt model = new ModelStubHasNoClashAppt();

        Assertions.assertDoesNotThrow(() ->
            new AddAppointmentCommand(ALICE.getId(), BENEDICT.getId(),
                FUTURE_APPOINTMENT_DATE,
                VALID_APPOINTMENT_TIME)
                .execute(model)
        );
    }
}
