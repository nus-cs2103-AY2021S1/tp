package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.module.Module;
import seedu.address.testutil.ModelStub;
import seedu.address.testutil.builders.ModuleBuilder;

public class AddModCommandTest {

    @Test
    public void constructor_nullModule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddModCommand(null));
    }

    @Test
    public void execute_moduleAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingModuleAdded modelStub = new ModelStubAcceptingModuleAdded();
        Module validModule = new ModuleBuilder().build();

        CommandResult commandResult = new AddModCommand(validModule).execute(modelStub);

        assertEquals(String.format(AddModCommand.MESSAGE_SUCCESS, validModule), commandResult.getFeedbackToUser());
        assertEquals(Collections.singletonList(validModule), modelStub.modulesAdded);
    }

    @Test
    public void execute_duplicateModule_throwsCommandException() {
        Module validModule = new ModuleBuilder().build();
        AddModCommand addModCommand = new AddModCommand(validModule);
        ModelStub modelStub = new ModelStubWithModule(validModule);

        assertThrows(CommandException.class,
                String.format(Messages.MESSAGE_DUPLICATE_MODULE, validModule.getModuleCode()), ()
                -> addModCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Module cs2102 = new ModuleBuilder().withCode("CS2102").build();
        Module cs2103 = new ModuleBuilder().withName("CS2103").build();
        AddModCommand addCs2102Command = new AddModCommand(cs2102);
        AddModCommand addCs2103Command = new AddModCommand(cs2103);

        // same object -> returns true
        assertEquals(addCs2103Command, addCs2103Command);

        // same values -> returns true
        AddModCommand addCs2103CommandCopy = new AddModCommand(cs2103);
        assertEquals(addCs2103CommandCopy, addCs2103Command);

        // different types -> returns false
        assertNotEquals(addCs2103Command, 1);

        // null -> returns false
        assertNotEquals(addCs2103Command, null);

        // different module -> returns false
        assertNotEquals(addCs2102Command, addCs2103Command);
    }

    /**
     * A Model stub that contains a single module.
     */
    private class ModelStubWithModule extends ModelStub {
        private final Module module;

        ModelStubWithModule(Module module) {
            requireNonNull(module);
            this.module = module;
        }

        @Override
        public boolean hasModule(Module module) {
            requireNonNull(module);
            return this.module.isSameModule(module);
        }
    }

    /**
     * A Model stub that always accepts the module being added.
     */
    private class ModelStubAcceptingModuleAdded extends ModelStub {
        final ArrayList<Module> modulesAdded = new ArrayList<>();

        @Override
        public boolean hasModule(Module module) {
            requireNonNull(module);
            return modulesAdded.stream().anyMatch(module::isSameModule);
        }

        @Override
        public void addModule(Module module) {
            requireNonNull(module);
            modulesAdded.add(module);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }

}
