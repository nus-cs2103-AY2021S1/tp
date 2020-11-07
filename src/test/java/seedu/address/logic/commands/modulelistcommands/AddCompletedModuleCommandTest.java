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
import seedu.address.testutil.TypicalModules;

public class AddCompletedModuleCommandTest {
    @Test
    public void constructor_nullModule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCompletedModuleCommand(null));
    }

    @Test
    public void execute_moduleAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingModuleAdded modelStub =
                new ModelStubAcceptingModuleAdded();
        Module validCompletedModule = TypicalModules.CS2030;
        CommandResult commandResult = new AddCompletedModuleCommand(validCompletedModule).execute(modelStub);
        assertEquals(String.format(AddCompletedModuleCommand.MESSAGE_SUCCESS, validCompletedModule),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validCompletedModule), modelStub.modulesAdded);
    }

    @Test
    public void execute_duplicateModule_throwsCommandException() {
        Module validCompletedModule = TypicalModules.CS2030;
        AddCompletedModuleCommand addCompletedModuleCommand =
                new AddCompletedModuleCommand(validCompletedModule);
        ModelStub modelStub = new ModelStubWithModule(validCompletedModule);

        assertThrows(CommandException.class,
            AddCompletedModuleCommand.MESSAGE_DUPLICATE_MODULE, () ->
                        addCompletedModuleCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Module firstModule = TypicalModules.CS2030;
        Module secondModule = new ModuleBuilder().withName("CS2040").withGradePoint(4.0).withTag("completed").build();
        AddCompletedModuleCommand addCompletedCS2030Command = new AddCompletedModuleCommand(firstModule);
        AddCompletedModuleCommand addCompletedCS2040Command = new AddCompletedModuleCommand(secondModule);

        // same object -> returns true
        assertTrue(addCompletedCS2030Command.equals(addCompletedCS2030Command));

        // same values -> returns true
        AddCompletedModuleCommand addCompletedCS2030CommandCopy = new AddCompletedModuleCommand(firstModule);
        assertTrue(addCompletedCS2030Command.equals(addCompletedCS2030CommandCopy));

        // different types -> returns false
        assertFalse(addCompletedCS2030Command.equals(1));

        // null -> returns false
        assertFalse(addCompletedCS2030Command.equals(null));

        // different module -> returns false
        assertFalse(addCompletedCS2030Command.equals(addCompletedCS2040Command));
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
        public void commitModuleList() {
        }
        @Override
        public boolean hasModule(Module module) {
            requireNonNull(module);
            return modulesAdded.stream().anyMatch(module::isSameModule);
        }

        @Override
        public boolean hasArchivedModule(Module module) {
            requireNonNull(module);
            return modulesAdded.stream().anyMatch(module::isSameModule);
        }

        @Override
        public void addModule(Module module) {
            requireNonNull(module);
            modulesAdded.add(module);
        }

        @Override
        public ReadOnlyModuleList getModuleList() {
            return new ModuleList();
        }
    }
}
