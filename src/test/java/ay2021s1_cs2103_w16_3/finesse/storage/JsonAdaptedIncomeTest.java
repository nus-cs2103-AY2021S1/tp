package ay2021s1_cs2103_w16_3.finesse.storage;

import static ay2021s1_cs2103_w16_3.finesse.storage.JsonAdaptedIncome.MISSING_FIELD_MESSAGE_FORMAT;
import static ay2021s1_cs2103_w16_3.finesse.testutil.Assert.assertThrows;
import static ay2021s1_cs2103_w16_3.finesse.testutil.TypicalTransactions.TUITION_FEES;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import ay2021s1_cs2103_w16_3.finesse.commons.exceptions.IllegalValueException;
import ay2021s1_cs2103_w16_3.finesse.model.transaction.Amount;
import ay2021s1_cs2103_w16_3.finesse.model.transaction.Date;
import ay2021s1_cs2103_w16_3.finesse.model.transaction.Income;
import ay2021s1_cs2103_w16_3.finesse.model.transaction.Title;
import ay2021s1_cs2103_w16_3.finesse.testutil.TransactionBuilder;

public class JsonAdaptedIncomeTest {
    private static final String INVALID_TITLE = "R\u2416chel";
    private static final String INVALID_AMOUNT = "+651234";
    private static final String INVALID_DATE = "example.com";
    private static final String INVALID_CATEGORY = "\u2416friend";

    private static final String VALID_TITLE = TUITION_FEES.getTitle().toString();
    private static final String VALID_AMOUNT = TUITION_FEES.getAmount().toString();
    private static final String VALID_DATE = TUITION_FEES.getDate().toString();
    private static final List<JsonAdaptedCategory> VALID_CATEGORIES = TUITION_FEES.getCategories().stream()
            .map(JsonAdaptedCategory::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validIncomeDetails_returnsIncome() throws Exception {
        Income bensonIncome = new TransactionBuilder(TUITION_FEES).buildIncome();
        JsonAdaptedIncome income = new JsonAdaptedIncome(bensonIncome);
        assertEquals(bensonIncome, income.toModelType());
    }

    @Test
    public void toModelType_invalidTitle_throwsIllegalValueException() {
        JsonAdaptedIncome income =
                new JsonAdaptedIncome(INVALID_TITLE, VALID_AMOUNT, VALID_DATE, VALID_CATEGORIES);
        String expectedMessage = Title.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, income::toModelType);
    }

    @Test
    public void toModelType_nullTitle_throwsIllegalValueException() {
        JsonAdaptedIncome income = new JsonAdaptedIncome(null, VALID_AMOUNT, VALID_DATE,
                VALID_CATEGORIES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Title.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, income::toModelType);
    }

    @Test
    public void toModelType_invalidAmount_throwsIllegalValueException() {
        JsonAdaptedIncome income =
                new JsonAdaptedIncome(VALID_TITLE, INVALID_AMOUNT, VALID_DATE, VALID_CATEGORIES);
        String expectedMessage = Amount.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, income::toModelType);
    }

    @Test
    public void toModelType_nullAmount_throwsIllegalValueException() {
        JsonAdaptedIncome income = new JsonAdaptedIncome(VALID_TITLE, null, VALID_DATE,
                VALID_CATEGORIES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Amount.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, income::toModelType);
    }

    @Test
    public void toModelType_invalidDate_throwsIllegalValueException() {
        JsonAdaptedIncome income =
                new JsonAdaptedIncome(VALID_TITLE, VALID_AMOUNT, INVALID_DATE, VALID_CATEGORIES);
        String expectedMessage = Date.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, income::toModelType);
    }

    @Test
    public void toModelType_nullDate_throwsIllegalValueException() {
        JsonAdaptedIncome income = new JsonAdaptedIncome(VALID_TITLE, VALID_AMOUNT, null,
                VALID_CATEGORIES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Date.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, income::toModelType);
    }

    @Test
    public void toModelType_invalidCategories_throwsIllegalValueException() {
        List<JsonAdaptedCategory> invalidCategories = new ArrayList<>(VALID_CATEGORIES);
        invalidCategories.add(new JsonAdaptedCategory(INVALID_CATEGORY));
        JsonAdaptedIncome income =
                new JsonAdaptedIncome(VALID_TITLE, VALID_AMOUNT, VALID_DATE, invalidCategories);
        assertThrows(IllegalValueException.class, income::toModelType);
    }

}
