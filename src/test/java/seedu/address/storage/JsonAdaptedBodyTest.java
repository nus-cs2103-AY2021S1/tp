package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.body.Body;
import seedu.address.model.body.Height;
import seedu.address.model.body.Weight;



public class JsonAdaptedBodyTest {

    private static final Weight MODEL_WEIGHT = new Weight(70);
    private static final Height MODEL_HEIGHT = new Height(170);
    private static final JsonAdaptedBody VALID_BODY =
            new JsonAdaptedBody(170, 70);

    private static final Body MODEL_SOURCE = new Body();


    @BeforeAll
    public static void init() {
        MODEL_SOURCE.setWeight(MODEL_WEIGHT);
        MODEL_SOURCE.setHeight(MODEL_HEIGHT);
    }

    @Test
    public void toModelType_allValidInputs_success() {
        JsonAdaptedBody body = new JsonAdaptedBody(MODEL_SOURCE);
        JsonAdaptedBody body1 = new JsonAdaptedBody(170, 70);

        try {
            assertEquals(body.toModelType(), MODEL_SOURCE);
            assertEquals(body1.toModelType(), MODEL_SOURCE);
        } catch (IllegalValueException e) {
            fail();
        }
    }

    @Test
    public void toModelType_invalidInputs_failure() {
        Body poorSource = new Body();
        poorSource.setWeight(MODEL_WEIGHT);
        poorSource.setHeight(new Height(20));

        //Poor source
        JsonAdaptedBody invalidBody = new JsonAdaptedBody(poorSource);

        //Invalid Height
        JsonAdaptedBody invalidBody1 = new JsonAdaptedBody(70, 70);

        //Invalid Weight
        JsonAdaptedBody invalidBody2 = new JsonAdaptedBody(170, 470);

        assertThrows(IllegalValueException.class,
                Height.MESSAGE_CONSTRAINTS_LIMIT, invalidBody::toModelType);

        assertThrows(IllegalValueException.class,
                Height.MESSAGE_CONSTRAINTS_LIMIT, invalidBody1::toModelType);

        assertThrows(IllegalValueException.class,
                Weight.MESSAGE_CONSTRAINTS_LIMIT, invalidBody2::toModelType);

    }

}
