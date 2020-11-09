package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalMeetings.getTypicalMeetingBook;
import static seedu.address.testutil.TypicalModules.CS2100;
import static seedu.address.testutil.TypicalModules.CS2105;
import static seedu.address.testutil.TypicalModules.getTypicalModuleBook;
import static seedu.address.testutil.TypicalPersons.HOON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleName;
import seedu.address.model.person.Person;
import seedu.address.testutil.ModuleBuilder;
import seedu.address.testutil.PersonBuilder;


public class AddModuleCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), getTypicalMeetingBook(), getTypicalModuleBook(),
            new UserPrefs());

    @Test
    public void constructor_nullParams_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new AddModuleCommand(null, null));
    }

    @Test
    public void execute_moduleAcceptedByModel_addSuccessful() throws Exception {
        Module validModule = new ModuleBuilder(CS2105).build();

        CommandResult commandResult = new AddModuleCommand(validModule).execute(model);

        assertEquals(String.format(AddModuleCommand.MESSAGE_SUCCESS, validModule), commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_duplicateModule_addFailure() throws Exception {
        Module duplicateModule = new ModuleBuilder(CS2100).build();

        AddModuleCommand command = new AddModuleCommand(duplicateModule);

        assertThrows(CommandException.class,
            String.format(AddModuleCommand.MESSAGE_DUPLICATE_MODULE, duplicateModule), () -> command.execute(model));
    }

    @Test
    public void execute_personNotInAddressbook_addFailure() throws Exception {
        Set<Person> classmates = new HashSet<>();
        classmates.add(HOON);
        Module invalidModule = new Module(new ModuleName("CS2105"), classmates);
        AddModuleCommand command = new AddModuleCommand(invalidModule);

        assertThrows(CommandException.class,
            String.format(AddModuleCommand.MESSAGE_NONEXISTENT_PERSON, HOON.getName()), () -> command.execute(model));
    }

    @Test
    public void equals() {
        Set<Person> dummyMembers = new HashSet<>();
        Person alice = new PersonBuilder().withName("Alice").build();
        Person bob = new PersonBuilder().withName("Bob").build();
        dummyMembers.add(alice);
        dummyMembers.add(bob);
        Module cs2103 = new ModuleBuilder().withName("CS2103").withMembers(dummyMembers).build();
        Module cs3234 = new ModuleBuilder().withName("CS3234").withMembers(dummyMembers).build();
        AddModuleCommand moduleCommand1 = new AddModuleCommand(cs2103);
        AddModuleCommand moduleCommand2 = new AddModuleCommand(cs3234);

        // same object -> returns true
        assertTrue(moduleCommand1.equals(moduleCommand1));

        // same values -> returns true
        AddModuleCommand moduleCommand1Copy = new AddModuleCommand(cs2103);
        assertTrue(moduleCommand1.equals(moduleCommand1Copy));

        // different types -> returns false
        assertFalse(moduleCommand1.equals(1));

        // null -> returns false
        assertFalse(moduleCommand1.equals(null));

        // different meeting -> returns false
        assertFalse(moduleCommand1.equals(moduleCommand2));
    }

}
