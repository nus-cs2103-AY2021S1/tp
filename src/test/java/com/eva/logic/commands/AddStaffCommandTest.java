package com.eva.logic.commands;

import static com.eva.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class AddStaffCommandTest {
    @Test
    public void constructor_nullStaff_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }
}
