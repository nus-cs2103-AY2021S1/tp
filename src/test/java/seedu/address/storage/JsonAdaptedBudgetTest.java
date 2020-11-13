package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.budget.Budget;
import seedu.address.testutil.TypicalBudget;

class JsonAdaptedBudgetTest {
    public static final String INVALID_NAME_BLANK = "";
    public static final String INVALID_NAME_NON_ALPHANUMERIC = "@Household Expenses";
    public static final String INVALID_DATE_FORMAT = "10-10-2010";
    public static final String INVALID_THRESHOLD_MORE_THAN_TWO_DECIMAL_PLACES = "2.500";
    public static final String INVALID_THRESHOLD_NON_NUMERIC = "abc";

    public static final Budget MC_DONALDS = TypicalBudget.getMcDonaldsBudget();
    public static final String VALID_NAME = MC_DONALDS.getName().toString();
    public static final String VALID_DATE = MC_DONALDS.getCreatedOn().toString();
    public static final String VALID_THRESHOLD = MC_DONALDS.getThreshold().get().toString();
    public static final List<JsonAdaptedExpenditure> VALID_EXPENDITURES = MC_DONALDS.getExpendituresList().stream()
            .map(JsonAdaptedExpenditure::new).collect(Collectors.toList());

    @Test
    public void toModelType_validBudgetDetails_returnsBudget() throws IllegalValueException {
        Budget budget = TypicalBudget.getMcDonaldsBudget();
        JsonAdaptedBudget jsonAdaptedBudget = new JsonAdaptedBudget(budget);
        assertEquals(TypicalBudget.getMcDonaldsBudget(), jsonAdaptedBudget.toModelType());
    }

    @Test
    void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedBudget budget = new JsonAdaptedBudget(null, VALID_DATE, VALID_THRESHOLD, VALID_EXPENDITURES);
        assertThrows(IllegalValueException.class, budget::toModelType);
    }

    @Test
    void toModelType_invalidNameBlank_throwsIllegalValueException() {
        JsonAdaptedBudget budget = new JsonAdaptedBudget(INVALID_NAME_BLANK, VALID_DATE,
                VALID_THRESHOLD, VALID_EXPENDITURES);
        assertThrows(IllegalValueException.class, budget::toModelType);
    }

    @Test
    void toModelType_invalidNameNonAlphaNumeric_throwsIllegalValueException() {
        JsonAdaptedBudget budget = new JsonAdaptedBudget(INVALID_NAME_NON_ALPHANUMERIC, VALID_DATE, VALID_THRESHOLD,
                VALID_EXPENDITURES);
        assertThrows(IllegalValueException.class, budget::toModelType);
    }

    @Test
    void toModelType_nullDate_throwsIllegalValueException() {
        JsonAdaptedBudget budget = new JsonAdaptedBudget(VALID_NAME, null, VALID_THRESHOLD, VALID_EXPENDITURES);
        assertThrows(IllegalValueException.class, budget::toModelType);
    }

    @Test
    void toModelType_invalidDate_throwsIllegalValueException() {
        JsonAdaptedBudget budget = new JsonAdaptedBudget(VALID_NAME, INVALID_DATE_FORMAT,
                VALID_THRESHOLD, VALID_EXPENDITURES);
        assertThrows(IllegalValueException.class, budget::toModelType);
    }

    @Test
    void toModelType_invalidThresholdMoreThanTwoDecimalPlaces_throwsIllegalValueException() {
        JsonAdaptedBudget budget = new JsonAdaptedBudget(VALID_NAME, VALID_DATE,
            INVALID_THRESHOLD_MORE_THAN_TWO_DECIMAL_PLACES, VALID_EXPENDITURES);
        assertThrows(IllegalValueException.class, budget::toModelType);
    }

    @Test
    void toModelType_invalidThresholdNonNumeric_throwsIllegalValueException() {
        JsonAdaptedBudget budget = new JsonAdaptedBudget(VALID_NAME, VALID_DATE,
            INVALID_THRESHOLD_NON_NUMERIC, VALID_EXPENDITURES);
        assertThrows(IllegalValueException.class, budget::toModelType);
    }
}
