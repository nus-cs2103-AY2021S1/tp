package seedu.cc.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.cc.storage.JsonAdaptedRevenue.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.cc.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.cc.commons.exceptions.IllegalValueException;
import seedu.cc.model.account.entry.Amount;
import seedu.cc.model.account.entry.Description;
import seedu.cc.model.account.entry.Revenue;
import seedu.cc.testutil.RevenueBuilder;

public class JsonAdaptedRevenueTest {
    private static final String INVALID_DESCRIPTION = " ";
    private static final String INVALID_AMOUNT = "a1";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_DESCRIPTION = "bought cooking utensils";
    private static final String VALID_AMOUNT = "1000.39";
    private static final List<JsonAdaptedTag> VALID_TAGS = new RevenueBuilder().build().getTags().stream()
        .map(JsonAdaptedTag::new)
        .collect(Collectors.toList());


    @Test
    public void toModelType_validRevenueDetails_returnsRevenue() throws Exception {
        Revenue modelRevenue = new RevenueBuilder().build();
        JsonAdaptedRevenue adaptedRevenue = new JsonAdaptedRevenue(modelRevenue);
        assertEquals(modelRevenue, adaptedRevenue.toModelType());
    }

    @Test
    public void toModelType_invalidDescription_throwsIllegalValueException() {
        JsonAdaptedRevenue adaptedRevenue = new JsonAdaptedRevenue(INVALID_DESCRIPTION, VALID_AMOUNT, VALID_TAGS);
        String expectedMessage = Description.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, adaptedRevenue::toModelType);
    }

    @Test
    public void toModelType_nullDescription_throwsIllegalValueException() {
        JsonAdaptedRevenue adaptedRevenue = new JsonAdaptedRevenue(null, VALID_AMOUNT, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Description.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, adaptedRevenue::toModelType);
    }

    @Test
    public void toModelType_invalidAmount_throwsIllegalValueException() {
        JsonAdaptedRevenue adaptedRevenue = new JsonAdaptedRevenue(VALID_DESCRIPTION, INVALID_AMOUNT, VALID_TAGS);
        String expectedMessage = Amount.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, adaptedRevenue::toModelType);
    }

    @Test
    public void toModelType_nullAmount_throwsIllegalValueException() {
        JsonAdaptedRevenue adaptedRevenue = new JsonAdaptedRevenue(VALID_DESCRIPTION, null, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Amount.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, adaptedRevenue::toModelType);
    }

    @Test
    public void toModelType_invalidTag_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedRevenue adaptedRevenue = new JsonAdaptedRevenue(VALID_DESCRIPTION, VALID_AMOUNT, invalidTags);
        assertThrows(IllegalValueException.class, adaptedRevenue::toModelType);
    }

}
