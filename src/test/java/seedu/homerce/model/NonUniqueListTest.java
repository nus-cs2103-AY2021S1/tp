package seedu.homerce.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.homerce.testutil.Assert.assertThrows;
import static seedu.homerce.testutil.expense.TypicalExpenses.CHAIR;
import static seedu.homerce.testutil.expense.TypicalExpenses.CONDITIONER;

import org.junit.jupiter.api.Test;

import seedu.homerce.model.expense.Expense;
import seedu.homerce.model.util.nonuniquelist.NonUniqueList;
import seedu.homerce.model.util.nonuniquelist.exceptions.ItemNotFoundException;

public class NonUniqueListTest {

    private final NonUniqueList<Expense> nonUniqueList = new NonUniqueList<>();

    @Test
    public void setItem_invalidItem_throwsItemNotFoundException() {
        assertThrows(seedu.homerce.model.util.nonuniquelist.exceptions.ItemNotFoundException.class, () -> nonUniqueList
            .setItem(CHAIR, CONDITIONER));
    }

    @Test
    public void remove_invalidItemToRemove_throwsItemNotFoundException() {
        assertThrows(ItemNotFoundException.class, () -> nonUniqueList.remove(CHAIR));
    }

    @Test
    public void setItems_replaceWithNonUniqueList_success() {
        NonUniqueList<Expense> originalList = new NonUniqueList<>();
        NonUniqueList<Expense> replacementList = new NonUniqueList<>();
        replacementList.add(CONDITIONER);
        originalList.setItems(replacementList);
        assertEquals(1, originalList.size());
    }

    @Test
    public void equals() {
        NonUniqueList<Expense> firstList = new NonUniqueList<>();
        NonUniqueList<Expense> secondList = new NonUniqueList<>();
        NonUniqueList<Expense> thirdList = new NonUniqueList<>();
        firstList.add(CONDITIONER);
        secondList.add(CONDITIONER);
        thirdList.add(CHAIR);

        assertTrue(firstList.equals(secondList));

        assertTrue(firstList.equals(firstList));

        assertFalse(firstList.equals(thirdList));
    }
}

