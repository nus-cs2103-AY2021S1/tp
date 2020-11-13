package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.expenditure.Expenditure;
import seedu.address.testutil.TypicalExpenditure;

class JsonAdaptedExpenditureTest {
    public static final String INVALID_NAME_BLANK = "";
    public static final String INVALID_NAME_NON_ALPHANUMERIC = "@pple Pie";
    public static final String INVALID_PRICE_BLANK = "";
    public static final String INVALID_PRICE_MORE_THAN_TWO_DECIMAL_PLACES = "2.000";
    public static final String INVALID_PRICE_NON_NUMERIC = "abc";
    public static final String INVALID_DATE_BLANK = "";
    public static final String INVALID_DATE_WRONG_FORMAT = "08-10-2020";
    public static final String INVALID_TAG = "#Snacks";

    public static final Expenditure MC_MUFFIN = TypicalExpenditure.getMcMuffinExpenditure();
    public static final String VALID_NAME = MC_MUFFIN.getName().value;
    public static final String VALID_PRICE = MC_MUFFIN.getPrice().value;
    public static final String VALID_DATE = MC_MUFFIN.getCreatedOn().value;
    public static final List<JsonAdaptedTag> VALID_TAGS = MC_MUFFIN.getTags().stream().map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    @Test
    void toModelType_validExpenditureDetails_returnsExpenditure() throws IllegalValueException {
        JsonAdaptedExpenditure expenditure = new JsonAdaptedExpenditure(VALID_NAME, VALID_PRICE, VALID_DATE, null);
        assertEquals(TypicalExpenditure.MC_MUFFIN, expenditure.toModelType());
    }

    @Test
    void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedExpenditure expenditure = new JsonAdaptedExpenditure(null,
                VALID_PRICE, VALID_DATE, VALID_TAGS);
        assertThrows(IllegalValueException.class, expenditure::toModelType);
    }

    @Test
    void toModelType_invalidNameBlank_throwsIllegalValueException() {
        JsonAdaptedExpenditure expenditure = new JsonAdaptedExpenditure(INVALID_NAME_BLANK,
                VALID_PRICE, VALID_DATE, VALID_TAGS);
        assertThrows(IllegalValueException.class, expenditure::toModelType);
    }

    @Test
    void toModelType_invalidNameNonNumeric_throwsIllegalValueException() {
        JsonAdaptedExpenditure expenditure = new JsonAdaptedExpenditure(INVALID_NAME_NON_ALPHANUMERIC,
                VALID_PRICE, VALID_DATE, VALID_TAGS);
        assertThrows(IllegalValueException.class, expenditure::toModelType);
    }

    @Test
    void toModelType_nullPrice_throwsIllegalValueException() {
        JsonAdaptedExpenditure expenditure = new JsonAdaptedExpenditure(VALID_NAME,
                null, VALID_DATE, VALID_TAGS);
        assertThrows(IllegalValueException.class, expenditure::toModelType);
    }

    @Test
    void toModelType_invalidPriceBlank_throwsIllegalValueException() {
        JsonAdaptedExpenditure expenditure = new JsonAdaptedExpenditure(VALID_NAME,
                INVALID_PRICE_BLANK, VALID_DATE, VALID_TAGS);
        assertThrows(IllegalValueException.class, expenditure::toModelType);
    }

    @Test
    void toModelType_invalidPriceMoreThanTwoDecimalPlaces_throwsIllegalValueException() {
        JsonAdaptedExpenditure expenditure = new JsonAdaptedExpenditure(VALID_NAME,
                INVALID_PRICE_MORE_THAN_TWO_DECIMAL_PLACES, VALID_DATE, VALID_TAGS);
        assertThrows(IllegalValueException.class, expenditure::toModelType);
    }

    @Test
    void toModelType_invalidPriceNonNumeric_throwsIllegalValueException() {
        JsonAdaptedExpenditure expenditure = new JsonAdaptedExpenditure(VALID_NAME,
                INVALID_PRICE_NON_NUMERIC, VALID_DATE, VALID_TAGS);
        assertThrows(IllegalValueException.class, expenditure::toModelType);
    }

    @Test
    void toModelType_nullDate_throwsIllegalValueException() {
        JsonAdaptedExpenditure expenditure = new JsonAdaptedExpenditure(VALID_NAME,
                VALID_PRICE, null, VALID_TAGS);
        assertThrows(IllegalValueException.class, expenditure::toModelType);
    }

    @Test
    void toModelType_invalidDateBlank_throwsIllegalValueException() {
        JsonAdaptedExpenditure expenditure = new JsonAdaptedExpenditure(VALID_NAME,
                VALID_PRICE, INVALID_DATE_BLANK, VALID_TAGS);
        assertThrows(IllegalValueException.class, expenditure::toModelType);
    }

    @Test
    void toModelType_invalidDateWrongFormat_throwsIllegalValueException() {
        JsonAdaptedExpenditure expenditure = new JsonAdaptedExpenditure(VALID_NAME,
                VALID_PRICE, INVALID_DATE_WRONG_FORMAT, VALID_TAGS);
        assertThrows(IllegalValueException.class, expenditure::toModelType);
    }

    @Test
    void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedExpenditure expenditure = new JsonAdaptedExpenditure(VALID_NAME,
                VALID_PRICE, VALID_DATE, invalidTags);
        assertThrows(IllegalValueException.class, expenditure::toModelType);
    }
}
