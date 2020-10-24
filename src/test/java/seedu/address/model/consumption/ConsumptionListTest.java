package seedu.address.model.consumption;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalConsumption.EAT_MARGARITAS;
import static seedu.address.testutil.TypicalConsumption.EAT_SANDWICH;

import org.junit.jupiter.api.Test;

public class ConsumptionListTest {
    private final ConsumptionList consumptionList = new ConsumptionList();

    @Test
    public void eat_nullRecipe_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> consumptionList.eat(null));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> consumptionList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void clear_allRecipe_clearRecipe() {
        consumptionList.eat(EAT_SANDWICH);
        consumptionList.eat(EAT_MARGARITAS);
        consumptionList.clear();
        ConsumptionList expectedConsumptionList = new ConsumptionList();
        assertEquals(expectedConsumptionList, consumptionList);
    }
}
