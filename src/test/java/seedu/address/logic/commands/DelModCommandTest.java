package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.UniqueModuleList;
import seedu.address.model.person.Person;
import seedu.address.testutil.ModelStub;
import seedu.address.testutil.builders.ModuleBuilder;
import seedu.address.testutil.builders.ModuleCodeBuilder;

class DelModCommandTest {

    private static final Module CS2103 = new ModuleBuilder().withCode("CS2103").build();
    private static final Module CS2102 = new ModuleBuilder().withCode("CS2102").build();
    private static final Module CS2101 = new ModuleBuilder().withCode("CS2101").build();

    private static final ModuleCode NON_EXISTENT_MODULE_CODE = new ModuleCodeBuilder().withCode("CS50").build();
    private static final ModuleCode MODULE_CODE_CS2103 = new ModuleCodeBuilder().withCode("CS2103").build();
    private static final ModuleCode MODULE_CODE_CS2102 = new ModuleCodeBuilder().withCode("CS2102").build();
    private static final ModuleCode MODULE_CODE_CS2101 = new ModuleCodeBuilder().withCode("CS2101").build();

    private ModelStubAcceptingModuleAdded modelStubWithNoModules = new ModelStubAcceptingModuleAdded();
    private ModelStubAcceptingModuleAdded modelStubWithModules = modelStubWithNoModules
            .withModules(CS2101, CS2102, CS2103);

    @Test
    public void constructor_nullModuleCode_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DelModCommand(null));
    }

    @Test
    void execute_deleteEmptyModules_deleteFail() {
        try {
            CommandResult commandResult = new DelModCommand(NON_EXISTENT_MODULE_CODE).execute(modelStubWithNoModules);
        } catch (CommandException e) {
            assertEquals(Messages.MESSAGE_MODULE_DOES_NOT_EXIST, e.getMessage());
        }
    }

    @Test
    void execute_deleteNonExistentModuleCode_deleteFail() {
        try {
            CommandResult commandResult = new DelModCommand(NON_EXISTENT_MODULE_CODE).execute(modelStubWithModules);
        } catch (CommandException e) {
            assertEquals(Messages.MESSAGE_MODULE_DOES_NOT_EXIST, e.getMessage());
        }
    }

    @Test
    void execute_deleteExistentModuleCode_deleteSuccess() throws CommandException {
        String expectedSuccessMessage1 = String.format(DelModCommand.MESSAGE_SUCCESS, MODULE_CODE_CS2101);
        String expectedSuccessMessage2 = String.format(DelModCommand.MESSAGE_SUCCESS, MODULE_CODE_CS2102);
        String expectedSuccessMessage3 = String.format(DelModCommand.MESSAGE_SUCCESS, MODULE_CODE_CS2103);

        CommandResult commandResult1 = new DelModCommand(MODULE_CODE_CS2101).execute(modelStubWithModules);
        assertEquals(expectedSuccessMessage1, commandResult1.getFeedbackToUser());
        CommandResult commandResult2 = new DelModCommand(MODULE_CODE_CS2102).execute(modelStubWithModules);
        assertEquals(expectedSuccessMessage2, commandResult2.getFeedbackToUser());
        CommandResult commandResult3 = new DelModCommand(MODULE_CODE_CS2103).execute(modelStubWithModules);
        assertEquals(expectedSuccessMessage3, commandResult3.getFeedbackToUser());
    }

    /**
     * A Model stub that has all modules
     */
    private class ModelStubAcceptingModuleAdded extends ModelStub {

        final ModuleListStub moduleList = new ModuleListStub();

        private void addModules(Module ...module) {
            requireNonNull(module);
            moduleList.add(module);
        }
        public ModelStubAcceptingModuleAdded withModules(Module ...modules) {
            ModelStubAcceptingModuleAdded modelStub = new ModelStubAcceptingModuleAdded();
            modelStub.addModules(modules);
            return modelStub;
        }

        @Override
        public void deleteModule(ModuleCode moduleCode) {
            moduleList.removeModuleWithCode(moduleCode);
        }

        @Override
        public ObservableList<Module> getFilteredModuleList() {
            return moduleList.asUnmodifiableObservableList();
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }

        @Override
        public boolean hasModuleCode(ModuleCode moduleCode) {
            return moduleList.containsModuleCode(moduleCode);
        }

        @Override
        public boolean moduleCodeHasInstructor(ModuleCode moduleCode, Person instructor) {
            throw new AssertionError("This method should not be called.");
        }
    }

    private class ModuleListStub extends UniqueModuleList {

        private ArrayList<Module> moduleList = new ArrayList<>();

        public void add(Module ... modules) {
            Collections.addAll(moduleList, modules);
        }

        @Override
        public void removeModuleWithCode(ModuleCode toRemove) {
            requireNonNull(toRemove);
            int indexOfModuleToBeRemoved = 0;
            while (!moduleList.get(indexOfModuleToBeRemoved).hasModuleCode(toRemove)
                    && indexOfModuleToBeRemoved < moduleList.size()) {
                indexOfModuleToBeRemoved++;
            }
            moduleList.remove(indexOfModuleToBeRemoved);
        }
        @Override
        public boolean containsModuleCode(ModuleCode moduleCodeToCheck) {
            requireNonNull(moduleCodeToCheck);
            return moduleList.stream().anyMatch(module -> module.hasModuleCode(moduleCodeToCheck));
        }
    }
}
