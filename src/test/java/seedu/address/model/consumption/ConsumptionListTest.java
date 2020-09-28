package seedu.address.model.consumption;

import static seedu.address.testutil.Assert.assertThrows;

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
}
