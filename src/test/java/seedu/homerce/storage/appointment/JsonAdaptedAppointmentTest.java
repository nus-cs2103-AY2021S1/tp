package seedu.homerce.storage.appointment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.homerce.storage.appointment.JsonAdaptedAppointment.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.homerce.testutil.Assert.assertThrows;
import static seedu.homerce.testutil.appointment.TypicalAppointments.OCT_25_2020_2PM_HAIR_TREATMENT_ALICE;
import static seedu.homerce.testutil.client.TypicalClients.ALICE;
import static seedu.homerce.testutil.service.TypicalServices.HAIR_TREATMENT;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.homerce.commons.exceptions.IllegalValueException;
import seedu.homerce.model.appointment.Appointment;
import seedu.homerce.model.appointment.TimeOfDay;
import seedu.homerce.model.client.Client;
import seedu.homerce.model.service.Service;
import seedu.homerce.model.util.attributes.Date;
import seedu.homerce.model.util.attributes.Tag;
import seedu.homerce.model.util.attributes.Title;
import seedu.homerce.storage.JsonAdaptedTag;
import seedu.homerce.storage.client.JsonAdaptedClient;
import seedu.homerce.storage.service.JsonAdaptedService;
import seedu.homerce.testutil.appointment.AppointmentBuilder;

public class JsonAdaptedAppointmentTest {

    private static final String INVALID_DATE = "32-13-99999";
    private static final String INVALID_TIME = "2599";
    private static final JsonAdaptedClient INVALID_CLIENT = new JsonAdaptedClient(
        "R@chel", "+651234", "example.com",
        new ArrayList<>() {
            {
                add(new JsonAdaptedTag("#friend"));
            }
        }
    );
    private static final JsonAdaptedService INVALID_SERVICE = new JsonAdaptedService(
        "@pple Massage", 2.2, -5.5, "SC1234"
    );

    private static final String VALID_DATE = "25-10-2020";
    private static final String VALID_TIME = "1400";
    private static final boolean FALSE_STATUS = false;
    private static final JsonAdaptedClient VALID_CLIENT = new JsonAdaptedClient(ALICE);
    private static final JsonAdaptedService VALID_SERVICE = new JsonAdaptedService(HAIR_TREATMENT);

    @Test
    public void toModelType_validAppointmentDetails_returnsAppointment() throws Exception {
        JsonAdaptedAppointment appointment = new JsonAdaptedAppointment(OCT_25_2020_2PM_HAIR_TREATMENT_ALICE);
        assertEquals(OCT_25_2020_2PM_HAIR_TREATMENT_ALICE, appointment.toModelType());
    }

    @Test
    public void toModelType_validAppointmentDetailsMarkedDone_returnsAppointment() throws Exception {
        Appointment appointmentToJsonify = new AppointmentBuilder(OCT_25_2020_2PM_HAIR_TREATMENT_ALICE).build();
        appointmentToJsonify.markDone();
        JsonAdaptedAppointment appointment = new JsonAdaptedAppointment(appointmentToJsonify);
        assertEquals(appointmentToJsonify, appointment.toModelType());
    }

    @Test
    public void toModelType_invalidDate_throwsIllegalValueException() {
        JsonAdaptedAppointment appointment =
            new JsonAdaptedAppointment(INVALID_DATE, VALID_TIME, FALSE_STATUS, VALID_CLIENT, VALID_SERVICE);
        String expectedMessage = Date.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, appointment::toModelType);
    }

    @Test
    public void toModelType_nullDate_throwsIllegalValueException() {
        JsonAdaptedAppointment appointment =
            new JsonAdaptedAppointment(null, VALID_TIME, FALSE_STATUS, VALID_CLIENT, VALID_SERVICE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Date.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, appointment::toModelType);
    }

    @Test
    public void toModelType_invalidTime_throwsIllegalValueException() {
        JsonAdaptedAppointment appointment = new JsonAdaptedAppointment(
            VALID_DATE, INVALID_TIME, FALSE_STATUS, VALID_CLIENT, VALID_SERVICE
        );
        String expectedMessage = TimeOfDay.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, appointment::toModelType);
    }

    @Test
    public void toModelType_nullTime_throwsIllegalValueException() {
        JsonAdaptedAppointment appointment = new JsonAdaptedAppointment(
            VALID_DATE, null, FALSE_STATUS, VALID_CLIENT, VALID_SERVICE
        );
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, TimeOfDay.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, appointment::toModelType);
    }

    @Test
    public void toModelType_invalidClient_throwsIllegalValueException() {
        JsonAdaptedAppointment appointment =
            new JsonAdaptedAppointment(VALID_DATE, VALID_TIME, FALSE_STATUS, INVALID_CLIENT, VALID_SERVICE);
        String expectedMessage = Tag.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, appointment::toModelType);
    }

    @Test
    public void toModelType_nullClient_throwsIllegalValueException() {
        JsonAdaptedAppointment appointment = new JsonAdaptedAppointment(
            VALID_DATE, VALID_TIME, FALSE_STATUS, null, VALID_SERVICE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Client.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, appointment::toModelType);
    }

    @Test
    public void toModelType_invalidService_throwsIllegalValueException() {
        JsonAdaptedAppointment appointment =
            new JsonAdaptedAppointment(VALID_DATE, VALID_TIME, FALSE_STATUS, VALID_CLIENT, INVALID_SERVICE);
        String expectedMessage = Title.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, appointment::toModelType);
    }

    @Test
    public void toModelType_nullService_throwsIllegalValueException() {
        JsonAdaptedAppointment appointment = new JsonAdaptedAppointment(
            VALID_DATE, VALID_TIME, FALSE_STATUS, VALID_CLIENT, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Service.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, appointment::toModelType);
    }
}
