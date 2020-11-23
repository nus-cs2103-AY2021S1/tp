package seedu.address.model.appointment;

import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAppointmentDates.FUTURE_APPOINTMENT_DATE;
import static seedu.address.testutil.TypicalAppointmentDates.FUTURE_APPOINTMENT_DATE_2;
import static seedu.address.testutil.TypicalAppointmentDates.PAST_APPOINTMENT_DATE;
import static seedu.address.testutil.TypicalAppointmentTimes.VALID_APPOINTMENT_TIME;
import static seedu.address.testutil.TypicalAppointmentTimes.VALID_APPOINTMENT_TIME_2;
import static seedu.address.testutil.TypicalClients.ALICE;
import static seedu.address.testutil.TypicalClients.CARL;
import static seedu.address.testutil.TypicalHairdressers.BENEDICT;
import static seedu.address.testutil.TypicalHairdressers.DAVID;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import seedu.address.model.person.client.Client;
import seedu.address.model.person.hairdresser.Hairdresser;

class AppointmentTest {
    @Test
    public void testValidConstructor() {
        Assertions.assertDoesNotThrow(() -> new Appointment(
            ALICE,
            BENEDICT,
            FUTURE_APPOINTMENT_DATE,
            VALID_APPOINTMENT_TIME,
            AppointmentStatus.ACTIVE
        ));
    }

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Appointment(
            null,
            BENEDICT,
            FUTURE_APPOINTMENT_DATE,
            VALID_APPOINTMENT_TIME,
            AppointmentStatus.ACTIVE
        ));
        assertThrows(NullPointerException.class, () -> new Appointment(
            ALICE,
            null,
            FUTURE_APPOINTMENT_DATE,
            VALID_APPOINTMENT_TIME,
            AppointmentStatus.ACTIVE
        ));
        assertThrows(NullPointerException.class, () -> new Appointment(
            ALICE,
            BENEDICT,
            null,
            VALID_APPOINTMENT_TIME,
            AppointmentStatus.ACTIVE
        ));
        assertThrows(NullPointerException.class, () -> new Appointment(
            ALICE,
            BENEDICT,
            FUTURE_APPOINTMENT_DATE,
            null,
            AppointmentStatus.ACTIVE
        ));
        assertThrows(NullPointerException.class, () -> new Appointment(
            ALICE,
            BENEDICT,
            FUTURE_APPOINTMENT_DATE,
            VALID_APPOINTMENT_TIME,
            null
        ));
    }

    @Test
    void getId() {
        Assertions.assertDoesNotThrow(() -> {
            new Appointment(
                ALICE,
                BENEDICT,
                FUTURE_APPOINTMENT_DATE,
                VALID_APPOINTMENT_TIME,
                AppointmentStatus.ACTIVE
            ).getId();
        });
    }

    @Test
    void getClientId() {
        Appointment appt = new Appointment(
            ALICE,
            BENEDICT,
            FUTURE_APPOINTMENT_DATE,
            VALID_APPOINTMENT_TIME,
            AppointmentStatus.ACTIVE
        );
        Assertions.assertEquals(appt.getClientId(), ALICE.getId());
    }

    @Test
    void getHairdresserId() {
        Appointment appt = new Appointment(
            ALICE,
            BENEDICT,
            FUTURE_APPOINTMENT_DATE,
            VALID_APPOINTMENT_TIME,
            AppointmentStatus.ACTIVE
        );
        Assertions.assertEquals(appt.getHairdresserId(), BENEDICT.getId());
    }

    @Test
    void getClient() {
        Appointment appt = new Appointment(
            ALICE,
            BENEDICT,
            FUTURE_APPOINTMENT_DATE,
            VALID_APPOINTMENT_TIME,
            AppointmentStatus.ACTIVE
        );
        Assertions.assertEquals(appt.getClient(), ALICE);
    }

    @Test
    void replaceClient() {
        Appointment appt = new Appointment(
            ALICE,
            BENEDICT,
            FUTURE_APPOINTMENT_DATE,
            VALID_APPOINTMENT_TIME,
            AppointmentStatus.ACTIVE
        );
        appt = appt.replaceClient(CARL);
        Assertions.assertEquals(appt.getClient(), CARL);
    }

    @Test
    void deleteClient() {
        Appointment appt = new Appointment(
            ALICE,
            BENEDICT,
            FUTURE_APPOINTMENT_DATE,
            VALID_APPOINTMENT_TIME,
            AppointmentStatus.ACTIVE
        );
        appt = appt.deleteClient();
        Client deletedClient = appt.getClient();
        Assertions.assertEquals(deletedClient.getName().toString(), "DELETED NAME FIELD");
        Assertions.assertEquals(deletedClient.getPhone().toString(), "DELETED PHONE FIELD");
        Assertions.assertEquals(deletedClient.getEmail().toString(), "DELETED EMAIL FIELD");
    }

    @Test
    void getHairdresser() {
        Appointment appt = new Appointment(
            ALICE,
            BENEDICT,
            FUTURE_APPOINTMENT_DATE,
            VALID_APPOINTMENT_TIME,
            AppointmentStatus.ACTIVE
        );
        Assertions.assertEquals(appt.getHairdresser(), BENEDICT);
    }

    @Test
    void replaceHairdresser() {
        Appointment appt = new Appointment(
            ALICE,
            BENEDICT,
            FUTURE_APPOINTMENT_DATE,
            VALID_APPOINTMENT_TIME,
            AppointmentStatus.ACTIVE
        );
        appt = appt.replaceHairdresser(DAVID);
        Assertions.assertEquals(appt.getHairdresser(), DAVID);
    }

    @Test
    void deleteHairdresser() {
        Appointment appt = new Appointment(
            ALICE,
            BENEDICT,
            FUTURE_APPOINTMENT_DATE,
            VALID_APPOINTMENT_TIME,
            AppointmentStatus.ACTIVE
        );
        appt = appt.deleteHairdresser();
        Hairdresser deletedHairdresser = appt.getHairdresser();
        Assertions.assertEquals(deletedHairdresser.getName().toString(), "DELETED NAME FIELD");
        Assertions.assertEquals(deletedHairdresser.getPhone().toString(), "DELETED PHONE FIELD");
        Assertions.assertEquals(deletedHairdresser.getEmail().toString(), "DELETED EMAIL FIELD");
    }

    @Test
    void getDate() {
        Appointment appt = new Appointment(
            ALICE,
            BENEDICT,
            FUTURE_APPOINTMENT_DATE,
            VALID_APPOINTMENT_TIME,
            AppointmentStatus.ACTIVE
        );
        Assertions.assertEquals(appt.getDate(), FUTURE_APPOINTMENT_DATE);
    }

    @Test
    void getTime() {
        Appointment appt = new Appointment(
            ALICE,
            BENEDICT,
            FUTURE_APPOINTMENT_DATE,
            VALID_APPOINTMENT_TIME,
            AppointmentStatus.ACTIVE
        );
        Assertions.assertEquals(appt.getTime(), VALID_APPOINTMENT_TIME);
    }

    @Test
    void getAppointmentStatus() {
        Appointment appt = new Appointment(
            ALICE,
            BENEDICT,
            FUTURE_APPOINTMENT_DATE,
            VALID_APPOINTMENT_TIME,
            AppointmentStatus.ACTIVE
        );
        Assertions.assertEquals(appt.getAppointmentStatus(), AppointmentStatus.ACTIVE);
        appt = new Appointment(
            ALICE,
            BENEDICT,
            FUTURE_APPOINTMENT_DATE,
            VALID_APPOINTMENT_TIME,
            AppointmentStatus.COMPLETED
        );
        Assertions.assertEquals(appt.getAppointmentStatus(), AppointmentStatus.COMPLETED);
    }

    @Test
    void startDateTime() {
        Appointment appt = new Appointment(
            ALICE,
            BENEDICT,
            FUTURE_APPOINTMENT_DATE,
            VALID_APPOINTMENT_TIME,
            AppointmentStatus.ACTIVE
        );
        Assertions.assertEquals(appt.startDateTime(), LocalDateTime
            .of(FUTURE_APPOINTMENT_DATE.date, VALID_APPOINTMENT_TIME.time));
    }

    @Test
    void endDateTime() {
        Appointment appt = new Appointment(
            ALICE,
            BENEDICT,
            FUTURE_APPOINTMENT_DATE,
            VALID_APPOINTMENT_TIME,
            AppointmentStatus.ACTIVE
        );
        LocalDateTime supposedEndTime = LocalDateTime.of(FUTURE_APPOINTMENT_DATE.date, VALID_APPOINTMENT_TIME.time)
            .plusMinutes(120);
        Assertions.assertEquals(appt.endDateTime(), supposedEndTime);
    }

    @Test
    void isSameAppointment() {
        final Appointment appt = new Appointment(
            ALICE,
            BENEDICT,
            FUTURE_APPOINTMENT_DATE,
            VALID_APPOINTMENT_TIME,
            AppointmentStatus.ACTIVE
        );

        //all parameters same, returns true
        Assertions.assertEquals(true, appt.isSameAppointment(
            new Appointment(
                ALICE,
                BENEDICT,
                FUTURE_APPOINTMENT_DATE,
                VALID_APPOINTMENT_TIME,
                AppointmentStatus.ACTIVE
            )));

        //multiple parameters different, returns false
        Assertions.assertEquals(false, appt.isSameAppointment(
            new Appointment(
                CARL,
                DAVID,
                FUTURE_APPOINTMENT_DATE_2,
                VALID_APPOINTMENT_TIME_2,
                AppointmentStatus.ACTIVE
            )));
    }

    @Test
    void isSame() {
        final Appointment appt = new Appointment(
            ALICE,
            BENEDICT,
            FUTURE_APPOINTMENT_DATE,
            VALID_APPOINTMENT_TIME,
            AppointmentStatus.ACTIVE
        );

        //non-appointment Entity, returns false
        Assertions.assertEquals(false, appt.isSame(ALICE));

        //all parameters same, returns true
        Assertions.assertEquals(true, appt.isSame(
            new Appointment(
                ALICE,
                BENEDICT,
                FUTURE_APPOINTMENT_DATE,
                VALID_APPOINTMENT_TIME,
                AppointmentStatus.ACTIVE
            )));
    }

    @Test
    void isPast() {
        Appointment appt = new Appointment(
            ALICE,
            BENEDICT,
            FUTURE_APPOINTMENT_DATE,
            VALID_APPOINTMENT_TIME,
            AppointmentStatus.ACTIVE
        );
        Assertions.assertEquals(false, appt.isPast());

        appt = new Appointment(
            ALICE,
            BENEDICT,
            PAST_APPOINTMENT_DATE,
            VALID_APPOINTMENT_TIME,
            AppointmentStatus.ACTIVE
        );
        Assertions.assertEquals(true, appt.isPast());
    }

    @Test
    void isClashDateTimeTest() {
        // this test is designed on the assumption that appointments are 120 minutes

        final Appointment appt = new Appointment(
            ALICE,
            BENEDICT,
            new AppointmentDate("2020-01-01"),
            new AppointmentTime("23:00"),
            AppointmentStatus.ACTIVE
        );

        //appointments clash with themselves
        Assertions.assertEquals(true,
            appt.isClash(appt));

        //identical appointment, should clash
        Assertions.assertEquals(true,
            appt.isClash(
                new Appointment(
                    ALICE,
                    BENEDICT,
                    new AppointmentDate("2020-01-01"),
                    new AppointmentTime("23:00"),
                    AppointmentStatus.ACTIVE
                )
            ));

        //time is 1 minute later, should clash
        Assertions.assertEquals(true,
            appt.isClash(
                new Appointment(
                    ALICE,
                    BENEDICT,
                    new AppointmentDate("2020-01-01"),
                    new AppointmentTime("23:01"),
                    AppointmentStatus.ACTIVE
                )
            ));

        //time is 1 minute earlier, should clash
        Assertions.assertEquals(true,
            appt.isClash(
                new Appointment(
                    ALICE,
                    BENEDICT,
                    new AppointmentDate("2020-01-01"),
                    new AppointmentTime("22:59"),
                    AppointmentStatus.ACTIVE
                )
            ));

        //time is 60 minutes later (different day), should clash
        Assertions.assertEquals(true,
            appt.isClash(
                new Appointment(
                    ALICE,
                    BENEDICT,
                    new AppointmentDate("2020-01-02"),
                    new AppointmentTime("00:00"),
                    AppointmentStatus.ACTIVE
                )
            ));

        //time is exactly 120 minutes earlier, should not clash
        Assertions.assertEquals(false,
            appt.isClash(
                new Appointment(
                    ALICE,
                    BENEDICT,
                    new AppointmentDate("2020-01-01"),
                    new AppointmentTime("21:00"),
                    AppointmentStatus.ACTIVE
                )
            ));

        //time is exactly 120 minutes later (next day), should not clash
        Assertions.assertEquals(false,
            appt.isClash(
                new Appointment(
                    ALICE,
                    BENEDICT,
                    new AppointmentDate("2020-01-02"),
                    new AppointmentTime("01:00"),
                    AppointmentStatus.ACTIVE
                )
            ));

        //same time, different day (more than 120 mins apart), should not clash
        Assertions.assertEquals(false,
            appt.isClash(
                new Appointment(
                    ALICE,
                    BENEDICT,
                    new AppointmentDate("2020-01-02"),
                    new AppointmentTime("23:00"),
                    AppointmentStatus.ACTIVE
                )
            ));

        //large date and time difference, should not clash
        Assertions.assertEquals(false,
            appt.isClash(
                new Appointment(
                    ALICE,
                    BENEDICT,
                    new AppointmentDate("2020-02-02"),
                    new AppointmentTime("01:30"),
                    AppointmentStatus.ACTIVE
                )
            ));
    }

    @Test
    void isClashPersonsTest() {
        // this test is designed on the assumption that appointments are 120 minutes

        final Appointment appt = new Appointment(
            ALICE,
            BENEDICT,
            new AppointmentDate("2020-01-01"),
            new AppointmentTime("23:00"),
            AppointmentStatus.ACTIVE
        );

        //appointments clash with themselves
        Assertions.assertEquals(true,
            appt.isClash(appt));

        //identical appointment, should clash
        Assertions.assertEquals(true,
            appt.isClash(
                new Appointment(
                    ALICE,
                    BENEDICT,
                    new AppointmentDate("2020-01-01"),
                    new AppointmentTime("23:00"),
                    AppointmentStatus.ACTIVE
                )
            ));

        //Same time
        //same time, same client, different hairdresser, should clash
        Assertions.assertEquals(true,
            appt.isClash(
                new Appointment(
                    ALICE,
                    DAVID,
                    new AppointmentDate("2020-01-01"),
                    new AppointmentTime("23:00"),
                    AppointmentStatus.ACTIVE
                )
            ));

        //same time, same hairdresser, different client, should clash
        Assertions.assertEquals(true,
            appt.isClash(
                new Appointment(
                    CARL,
                    BENEDICT,
                    new AppointmentDate("2020-01-01"),
                    new AppointmentTime("23:00"),
                    AppointmentStatus.ACTIVE
                )
            ));

        //same time, different hairdresser, different client, should not clash
        Assertions.assertEquals(false,
            appt.isClash(
                new Appointment(
                    CARL,
                    DAVID,
                    new AppointmentDate("2020-01-01"),
                    new AppointmentTime("23:00"),
                    AppointmentStatus.ACTIVE
                )
            ));

        //Different but overlapping time
        //same time, same client, different hairdresser, should clash
        Assertions.assertEquals(true,
            appt.isClash(
                new Appointment(
                    ALICE,
                    DAVID,
                    new AppointmentDate("2020-01-01"),
                    new AppointmentTime("23:01"),
                    AppointmentStatus.ACTIVE
                )
            ));

        //same time, same hairdresser, different client, should clash
        Assertions.assertEquals(true,
            appt.isClash(
                new Appointment(
                    CARL,
                    BENEDICT,
                    new AppointmentDate("2020-01-01"),
                    new AppointmentTime("23:01"),
                    AppointmentStatus.ACTIVE
                )
            ));

        //same time, different hairdresser, different client, should not clash
        Assertions.assertEquals(false,
            appt.isClash(
                new Appointment(
                    CARL,
                    DAVID,
                    new AppointmentDate("2020-01-01"),
                    new AppointmentTime("23:01"),
                    AppointmentStatus.ACTIVE
                )
            ));
    }

    @Test
    void isClashStatusTest() {
        // this test is designed on the assumption that appointments are 120 minutes
        // Only appointments that are active can clash
        final Appointment activeAppt = new Appointment(
            ALICE,
            BENEDICT,
            new AppointmentDate("2020-01-01"),
            new AppointmentTime("23:00"),
            AppointmentStatus.ACTIVE
        );

        final Appointment completedAppt = new Appointment(
            ALICE,
            BENEDICT,
            new AppointmentDate("2020-01-01"),
            new AppointmentTime("23:01"),
            AppointmentStatus.COMPLETED
        );

        final Appointment missedAppt = new Appointment(
            ALICE,
            BENEDICT,
            new AppointmentDate("2020-01-01"),
            new AppointmentTime("23:02"),
            AppointmentStatus.MISSED
        );

        Assertions.assertEquals(true,
            activeAppt.isClash(activeAppt));

        Assertions.assertEquals(false,
            completedAppt.isClash(completedAppt));

        Assertions.assertEquals(false,
            missedAppt.isClash(missedAppt));

        Assertions.assertEquals(false,
            completedAppt.isClash(activeAppt));

        Assertions.assertEquals(false,
            completedAppt.isClash(missedAppt));
    }
    @Test
    void testEquals() {
        final Appointment appt = new Appointment(
            ALICE,
            BENEDICT,
            FUTURE_APPOINTMENT_DATE,
            VALID_APPOINTMENT_TIME,
            AppointmentStatus.ACTIVE
        );

        //same object, returns true
        Assertions.assertEquals(true, appt.equals(appt));

        //non-appointment object, returns false
        Assertions.assertEquals(false, appt.equals(ALICE));

        //all parameters same, returns true
        Assertions.assertEquals(true, appt.equals(
            new Appointment(
                ALICE,
                BENEDICT,
                FUTURE_APPOINTMENT_DATE,
                VALID_APPOINTMENT_TIME,
                AppointmentStatus.ACTIVE
            )));
        //all parameters same except for status, returns true
        Assertions.assertEquals(true, appt.equals(
            new Appointment(
                ALICE,
                BENEDICT,
                FUTURE_APPOINTMENT_DATE,
                VALID_APPOINTMENT_TIME,
                AppointmentStatus.COMPLETED
            )));
        //all parameters same except for client, returns false
        Assertions.assertEquals(false, appt.equals(
            new Appointment(
                CARL,
                BENEDICT,
                FUTURE_APPOINTMENT_DATE,
                VALID_APPOINTMENT_TIME,
                AppointmentStatus.ACTIVE
            )));
        //all parameters same except for hairdresser, returns false
        Assertions.assertEquals(false, appt.equals(
            new Appointment(
                ALICE,
                DAVID,
                FUTURE_APPOINTMENT_DATE,
                VALID_APPOINTMENT_TIME,
                AppointmentStatus.ACTIVE
            )));
        //all parameters same except for date, returns false
        Assertions.assertEquals(false, appt.equals(
            new Appointment(
                ALICE,
                BENEDICT,
                FUTURE_APPOINTMENT_DATE_2,
                VALID_APPOINTMENT_TIME,
                AppointmentStatus.ACTIVE
            )));
        //all parameters same except for time, returns false
        Assertions.assertEquals(false, appt.equals(
            new Appointment(
                ALICE,
                BENEDICT,
                FUTURE_APPOINTMENT_DATE,
                VALID_APPOINTMENT_TIME_2,
                AppointmentStatus.ACTIVE
            )));
        //multiple parameters different, returns false
        Assertions.assertEquals(false, appt.equals(
            new Appointment(
                CARL,
                DAVID,
                FUTURE_APPOINTMENT_DATE_2,
                VALID_APPOINTMENT_TIME_2,
                AppointmentStatus.ACTIVE
            )));
    }

    @Test
    void testToString() {
        Appointment appt = new Appointment(
            ALICE,
            BENEDICT,
            FUTURE_APPOINTMENT_DATE,
            VALID_APPOINTMENT_TIME,
            AppointmentStatus.ACTIVE
        );
        Assertions.assertEquals(
            "Appointment - Client ID: "
                + ALICE.getId().toString() + " Hairdresser ID: "
                + BENEDICT.getId().toString() + " Date: 2022-01-01 Time: 09:00",
            appt.toString());
    }
}
