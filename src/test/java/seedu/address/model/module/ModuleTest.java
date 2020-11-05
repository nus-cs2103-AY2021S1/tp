package seedu.address.model.module;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.ModuleBuilder;

public class ModuleTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Module module = new ModuleBuilder().build();
        // assertThrows(UnsupportedOperationException.class, () -> module.get().remove(0));
    }

}
