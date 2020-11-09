package seedu.address.logic.commands.modulelistcommands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.ModelStub;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.ModuleList;
import seedu.address.model.ReadOnlyModuleList;
import seedu.address.model.module.Module;
import seedu.address.testutil.ModuleBuilder;

public class AddModuleCommandTest {

    @Test
    public void constructor_nullModule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddModuleCommand(null));
    }

    @Test
    public void execute_moduleAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingModuleAdded modelStub = new ModelStubAcceptingModuleAdded();
        Module validModule = new ModuleBuilder().build();

        CommandResult commandResult = new AddModuleCommand(validModule).execute(modelStub);

        assertEquals(String.format(AddModuleCommand.MESSAGE_SUCCESS, validModule), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validModule), modelStub.modulesAdded);
    }

    @Test
    public void execute_duplicateModule_throwsCommandException() {
        Module validModule = new ModuleBuilder().build();
        AddModuleCommand addModuleCommand = new AddModuleCommand(validModule);
        ModelStub modelStub = new ModelStubWithModule(validModule);

        assertThrows(CommandException.class,
                AddModuleCommand.MESSAGE_DUPLICATE_MODULE, () -> addModuleCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Module cs2103t = new ModuleBuilder().withName("CS2103T").build();
        Module cs2101 = new ModuleBuilder().withName("CS2101").build();
        AddModuleCommand addCs2103tCommand = new AddModuleCommand(cs2103t);
        AddModuleCommand addCs2101Command = new AddModuleCommand(cs2101);

        // same object -> returns true
        assertTrue(addCs2103tCommand.equals(addCs2103tCommand));

        // same values -> returns true
        AddModuleCommand addCs2103tCommandCopy = new AddModuleCommand(cs2103t);
        assertTrue(addCs2103tCommand.equals(addCs2103tCommandCopy));

        // different types -> returns false
        assertFalse(addCs2103tCommand.equals(1));

        // null -> returns false
        assertFalse(addCs2103tCommand.equals(null));

        // different module -> returns false
        assertFalse(addCs2103tCommand.equals(addCs2101Command));
    }

    @Test
    public void test_toString() {
        Module validModule = new ModuleBuilder().build();
        AddModuleCommand addModuleCommand = new AddModuleCommand(validModule);
        String expectedString = "addmodule" + " " + validModule.toString();

        assertEquals(expectedString, addModuleCommand.toString());
    }

    /**
     * A Model stub that contains a single module.
     */
    public class ModelStubWithModule extends ModelStub {
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
     * A Model stub that always accept the module being added.
     */
    public class ModelStubAcceptingModuleAdded extends ModelStub {
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
        public boolean hasArchivedModule(Module module) {
            requireNonNull(module);
            return modulesAdded.stream().anyMatch(module::isSameModule);
        }

        @Override
        public ReadOnlyModuleList getModuleList() {
            return new ModuleList();
        }

        @Override
        public void commitModuleList() {
        }
    }
}
