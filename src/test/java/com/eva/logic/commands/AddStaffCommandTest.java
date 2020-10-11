package com.eva.logic.commands;

import org.junit.jupiter.api.Test;

import static com.eva.testutil.Assert.assertThrows;

public class AddStaffCommandTest {
    @Test
    public void constructor_nullStaff_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

//    @Test
//    public void execute_personAcceptedByModel_addSuccessful() throws Exception {
//        AddCommandTest.ModelStubAcceptingPersonAdded modelStub = new AddCommandTest.ModelStubAcceptingPersonAdded();
//        Person validPerson = new PersonBuilder().build();
//
//        CommandResult commandResult = new AddCommand(validPerson).execute(modelStub);
//
//        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, validPerson), commandResult.getFeedbackToUser());
//        assertEquals(Arrays.asList(validPerson), modelStub.personsAdded);
//    }

}
