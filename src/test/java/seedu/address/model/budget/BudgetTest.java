package seedu.address.model.budget;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.expenditure.ExpenditureList;
import seedu.address.testutil.BudgetBuilder;
import seedu.address.testutil.TypicalBudget;
import seedu.address.testutil.TypicalExpenditure;
import seedu.address.testutil.TypicalExpenditures;

class BudgetTest {

    private Budget mcDonalds;

    @BeforeEach
    public void setUp() {
        mcDonalds = TypicalBudget.getMcDonaldsBudget();
    }
    @Test
    public void equalsTest() {
        // same object -> returns true
        assertTrue(mcDonalds.equals(mcDonalds));

        // different type -> returns false
        assertFalse(mcDonalds.equals(5));

        // null -> returns false
        assertFalse(mcDonalds.equals(null));

        // different budget -> returns false
        assertFalse(mcDonalds.equals(new BudgetBuilder(TypicalBudget.getKfcBudget()).build()));

        // different createdOn -> returns false
        Budget editedMcdonalds = new BudgetBuilder(mcDonalds)
                .withCreatedOn("2020-08-08T00:00:00.000000")
                .build();
        assertFalse(mcDonalds.equals(editedMcdonalds));

        // different name -> returns false
        editedMcdonalds = new BudgetBuilder(mcDonalds).withName("Pizza Hut").build();
        assertFalse(mcDonalds.equals(editedMcdonalds));

        // different threshold -> returns false
        editedMcdonalds = new BudgetBuilder(mcDonalds).withThreshold("10").build();
        assertFalse(mcDonalds.equals(editedMcdonalds));

        // different expenditures -> returns false
        editedMcdonalds = new BudgetBuilder(mcDonalds).withExpenditures(TypicalExpenditures.KFC_EXPENDITURES).build();
        assertFalse(mcDonalds.equals(editedMcdonalds));

        // same values -> returns true
        editedMcdonalds = new BudgetBuilder(mcDonalds).build();
        assertTrue(editedMcdonalds.equals(editedMcdonalds));
    }

    @Test
    void constructorTest() {
        Budget mcDonalds = new Budget(new Name("McDonalds"), new Date("2020-10-10T00:00:00.000000"),
                TypicalExpenditures.getMcDonaldsExpenditures());

        Budget expectedBudget = new BudgetBuilder(mcDonalds).build();

        assertEquals(mcDonalds, expectedBudget);
    }

    @Test
    void getName() {
        assertEquals(new Name("McDonalds"), mcDonalds.getName());
    }

    @Test
    void getCreatedOn() {
        assertEquals(new Date("2020-10-10T00:00:00.000000"), mcDonalds.getCreatedOn());
    }

    @Test
    void getThreshold() {
        assertEquals(new Threshold("100"), mcDonalds.getThreshold().get());
    }

    @Test
    void getExpenditures() {
        ExpenditureList expectedExpenditureList = TypicalExpenditures.getMcDonaldsExpenditures();
        assertEquals(expectedExpenditureList, mcDonalds.getExpenditures());
    }

    @Test
    void getExpenditureSize() {
        assertEquals(3, mcDonalds.getExpenditureSize());
    }

    @Test
    void getTotalExpenditure() {
        assertEquals(TypicalExpenditures.getMcDonaldsExpenditures().getTotalExpenditure(),
                mcDonalds.getTotalExpenditure());
    }

    @Test
    void addExpenditure() {
        mcDonalds.addExpenditure(TypicalExpenditure.getMcMuffinExpenditure());
        assertEquals(4, mcDonalds.getExpenditureSize());
    }

    @Test
    void testToString() {
        assertEquals(TypicalBudget.getMcDonaldsBudget().toString(), mcDonalds.toString());
    }

    @Test
    void contains() {
        assertTrue(mcDonalds.contains("MC"));
    }
}
