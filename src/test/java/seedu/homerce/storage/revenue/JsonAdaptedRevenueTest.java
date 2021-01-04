package seedu.homerce.storage.revenue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.homerce.model.util.attributes.Date.MESSAGE_CONSTRAINTS;
import static seedu.homerce.storage.revenue.JsonAdaptedRevenue.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.homerce.testutil.Assert.assertThrows;
import static seedu.homerce.testutil.revenue.TypicalRevenues.LASH_LIFT;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import seedu.homerce.commons.exceptions.IllegalValueException;
import seedu.homerce.model.service.Duration;
import seedu.homerce.model.service.Service;
import seedu.homerce.model.service.ServiceCode;
import seedu.homerce.model.util.attributes.Amount;
import seedu.homerce.model.util.attributes.Date;
import seedu.homerce.model.util.attributes.Title;

public class JsonAdaptedRevenueTest {

    private static final String INVALID_TITLE = "R@chel";
    private static final Double INVALID_PRICE = -0.9;
    private static final Double INVALID_DURATION = -0.9;
    private static final String INVALID_SERVICECODE = "R@chel";
    private static final String INVALID_DATE = "21-13-2020";


    private static final Service VALID_SERVICE = LASH_LIFT.getService();
    private static final String VALID_TITLE = LASH_LIFT.getService().getTitle().toString();
    private static final Double VALID_PRICE = LASH_LIFT.getService().getAmount().value.doubleValue();
    private static final Double VALID_DURATION = LASH_LIFT.getService().getDuration().value;
    private static final String VALID_SERVICECODE = LASH_LIFT.getService().getServiceCode().toString();
    private static final String VALID_DATE = LASH_LIFT.getDate().toString();

    @Test
    public void toModelType_validClientDetails_returnsClient() throws Exception {
        JsonAdaptedRevenue revenue = new JsonAdaptedRevenue(LASH_LIFT);
        assertEquals(LASH_LIFT, revenue.toModelType());
    }

    @Test
    public void toModelType_invalidTitle_throwsIllegalValueException() {
        JsonAdaptedRevenue revenue =
            new JsonAdaptedRevenue(INVALID_TITLE, VALID_PRICE, VALID_DURATION, VALID_SERVICECODE, VALID_DATE);
        String expectedMessage = Title.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, revenue::toModelType);
    }

    @Test
    public void toModelType_nullTitle_throwsIllegalValueException() {
        JsonAdaptedRevenue revenue = new JsonAdaptedRevenue(null, VALID_PRICE, VALID_DURATION, VALID_SERVICECODE,
            VALID_DATE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Title.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, revenue::toModelType);
    }

    @Test
    public void toModelType_invalidPrice_throwsIllegalValueException() {
        JsonAdaptedRevenue revenue =
            new JsonAdaptedRevenue(VALID_TITLE, INVALID_PRICE, VALID_DURATION, VALID_SERVICECODE, VALID_DATE);
        String expectedMessage = Amount.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, revenue::toModelType);
    }

    @Test
    public void toModelType_nullPrice_throwsIllegalValueException() {
        Exception exception = Assertions.assertThrows(NullPointerException.class, () -> {
            new JsonAdaptedRevenue(VALID_TITLE, null, VALID_DURATION, VALID_SERVICECODE,
                VALID_DATE);
        });
        assertEquals(new NullPointerException().toString(), exception.toString());
    }

    @Test
    public void toModelType_invalidDuration_throwsIllegalValueException() {
        JsonAdaptedRevenue revenue =
            new JsonAdaptedRevenue(VALID_TITLE, VALID_PRICE, INVALID_DURATION, VALID_SERVICECODE, VALID_DATE);
        String expectedMessage = Duration.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, revenue::toModelType);
    }

    @Test
    public void toModelType_nullDuration_throwsIllegalValueException() {
        JsonAdaptedRevenue revenue = new JsonAdaptedRevenue(VALID_TITLE, VALID_PRICE, null, VALID_SERVICECODE,
            VALID_DATE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Duration.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, revenue::toModelType);
    }

    @Test
    public void toModelType_invalidServiceCode_throwsIllegalValueException() {
        JsonAdaptedRevenue revenue =
            new JsonAdaptedRevenue(VALID_TITLE, VALID_PRICE, VALID_DURATION, INVALID_SERVICECODE, VALID_DATE);
        String expectedMessage = ServiceCode.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, revenue::toModelType);
    }

    @Test
    public void toModelType_nullServiceCode_throwsIllegalValueException() {
        JsonAdaptedRevenue revenue = new JsonAdaptedRevenue(VALID_TITLE, VALID_PRICE, VALID_DURATION, null,
            VALID_DATE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, ServiceCode.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, revenue::toModelType);
    }

    @Test
    public void toModelType_invalidDate_throwsIllegalValueException() {
        JsonAdaptedRevenue revenue =
            new JsonAdaptedRevenue(VALID_TITLE, VALID_PRICE, VALID_DURATION, VALID_SERVICECODE, INVALID_DATE);
        String expectedMessage = MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, revenue::toModelType);
    }

    @Test
    public void toModelType_nullDate_throwsIllegalValueException() {
        JsonAdaptedRevenue revenue = new JsonAdaptedRevenue(VALID_TITLE, VALID_PRICE, VALID_DURATION, VALID_SERVICECODE,
            INVALID_DATE);
        String expectedMessage = String.format(MESSAGE_CONSTRAINTS, Date.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, revenue::toModelType);
    }
}
