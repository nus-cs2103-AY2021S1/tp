package seedu.homerce.storage.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.homerce.storage.service.JsonAdaptedService.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.homerce.testutil.Assert.assertThrows;
import static seedu.homerce.testutil.service.TypicalServices.LASH_LIFT;

import org.junit.jupiter.api.Test;

import seedu.homerce.commons.exceptions.IllegalValueException;
import seedu.homerce.model.service.Duration;
import seedu.homerce.model.service.ServiceCode;
import seedu.homerce.model.util.attributes.Amount;
import seedu.homerce.model.util.attributes.Title;

public class JsonAdaptedServiceTest {
    private static final String INVALID_TITLE = "@pple Massage";
    private static final Double INVALID_DURATION = 2.2;
    private static final Double INVALID_AMOUNT = -5.5;
    private static final String INVALID_SERVICE_CODE = "SC1234";

    private static final String VALID_TITLE = LASH_LIFT.getTitle().toString();
    private static final Double VALID_DURATION = LASH_LIFT.getDuration().value;
    private static final Double VALID_AMOUNT = Double.valueOf(LASH_LIFT.getAmount().toString());
    private static final String VALID_SERVICE_CODE = LASH_LIFT.getServiceCode().toString();

    @Test
    public void toModelType_validServiceDetails_returnsService() throws Exception {
        JsonAdaptedService service = new JsonAdaptedService(LASH_LIFT);
        assertEquals(LASH_LIFT, service.toModelType());
    }

    @Test
    public void toModelType_invalidTitle_throwsIllegalValueException() {
        JsonAdaptedService service =
            new JsonAdaptedService(INVALID_TITLE, VALID_AMOUNT, VALID_DURATION, VALID_SERVICE_CODE);
        String expectedMessage = Title.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, service::toModelType);
    }

    @Test
    public void toModelType_nullTitle_throwsIllegalValueException() {
        JsonAdaptedService service = new JsonAdaptedService(null, VALID_AMOUNT, VALID_DURATION, VALID_SERVICE_CODE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Title.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, service::toModelType);
    }

    @Test
    public void toModelType_invalidDuration_throwsIllegalValueException() {
        JsonAdaptedService service =
            new JsonAdaptedService(VALID_TITLE, VALID_AMOUNT, INVALID_DURATION, VALID_SERVICE_CODE);
        String expectedMessage = Duration.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, service::toModelType);
    }

    @Test
    public void toModelType_nullDuration_throwsIllegalValueException() {
        JsonAdaptedService service = new JsonAdaptedService(VALID_TITLE, VALID_AMOUNT, null, VALID_SERVICE_CODE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Duration.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, service::toModelType);
    }

    @Test
    public void toModelType_invalidAmount_throwsIllegalValueException() {
        JsonAdaptedService service =
            new JsonAdaptedService(VALID_TITLE, INVALID_AMOUNT, VALID_DURATION, VALID_SERVICE_CODE);
        String expectedMessage = Amount.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, service::toModelType);
    }

    @Test
    public void toModelType_nullAmount_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new JsonAdaptedService(VALID_TITLE, null, VALID_DURATION,
            VALID_SERVICE_CODE));
    }

    @Test
    public void toModelType_invalidServiceCode_throwsIllegalValueException() {
        JsonAdaptedService service = new JsonAdaptedService(VALID_TITLE, VALID_AMOUNT, VALID_DURATION,
            INVALID_SERVICE_CODE);
        String expectedMessage = String.format(ServiceCode.MESSAGE_CONSTRAINTS, Amount.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, service::toModelType);
    }

    @Test
    public void toModelType_nullServiceCode_throwsIllegalValueException() {
        JsonAdaptedService service = new JsonAdaptedService(VALID_TITLE, VALID_AMOUNT, VALID_DURATION, null);
        String expectedMessage = String.format(JsonAdaptedService.MISSING_FIELD_MESSAGE_FORMAT,
            ServiceCode.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, service::toModelType);
    }
}
