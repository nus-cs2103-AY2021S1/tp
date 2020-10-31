package seedu.tasklist.logic.commands;

import static seedu.tasklist.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ImportCommandTest {
    @Test
    public void constructor_nullAssignment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ImportCommand(null));
    }
}
