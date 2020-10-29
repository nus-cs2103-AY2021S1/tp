package seedu.address.logic.commands.project;

import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;


class TaskFilterCommandTest {

    @Test
    void execute_nullPredicate_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TaskFilterCommand(null));
    }
}
