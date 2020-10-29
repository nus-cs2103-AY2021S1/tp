package seedu.address.logic.commands;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyTrackr;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.Trackr;
import seedu.address.model.module.Module;
import seedu.address.model.person.Student;
import seedu.address.model.tutorialgroup.TutorialGroup;
import seedu.address.testutil.ModuleBuilder;

import java.nio.file.Path;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static seedu.address.testutil.Assert.assertThrows;

import static java.util.Objects.requireNonNull;

public class AddModuleCommandTest {
    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
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

        assertThrows(CommandException.class, AddModuleCommand.MESSAGE_DUPLICATE_MODULE,
            () -> addModuleCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Module cs2103t = new ModuleBuilder().withModuleId("CS2103T").build();
        Module cs3243 = new ModuleBuilder().withModuleId("CS3243").build();
        AddModuleCommand addCs2103tCommand = new AddModuleCommand(cs2103t);
        AddModuleCommand addCs3243Command = new AddModuleCommand(cs3243);

        // same object -> returns true
        assertTrue(addCs2103tCommand.equals(addCs2103tCommand));

        // same values -> returns true
        AddModuleCommand addCs2103tCommandCopy = new AddModuleCommand(cs2103t);
        assertTrue(addCs2103tCommand.equals(addCs2103tCommandCopy));

        // different types -> returns false
        assertFalse(addCs2103tCommand.equals(1));

        // null -> returns false
        assertFalse(addCs2103tCommand.equals(null));

        // different person -> returns false
        assertFalse(addCs2103tCommand.equals(addCs3243Command));
    }

    /*
     * A default model stub that have all of the methods failing.
     */

    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getTrackrFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setTrackrFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addModule(Module module) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean isInModuleView() {
            return true;
        }

        @Override
        public Module getCurrentModuleInView() {
            return null;
        }

        @Override
        public void setViewToTutorialGroup(Module target) {

        }

        @Override
        public void addTutorialGroup(TutorialGroup tutorialGroup) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteTutorialGroup(TutorialGroup tutorialGroup) {

        }

        @Override
        public boolean hasTutorialGroup(TutorialGroup tutorialGroup) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setTutorialGroup(TutorialGroup target, TutorialGroup edited) {

        }

        @Override
        public boolean isInTutorialGroupView() {
            return false;
        }

        @Override
        public void setViewToStudent(TutorialGroup target) {

        }

        @Override
        public TutorialGroup getCurrentTgInView() {
            return null;
        }

        @Override
        public boolean hasStudent(Student student) {
            return false;
        }

        @Override
        public void deleteStudent(Student target) {

        }

        @Override
        public void addStudent(Student student) {

        }

        @Override
        public void setStudent(Student target, Student editedStudent) {

        }

        @Override
        public boolean isInStudentView() {
            return false;
        }


        @Override
        public void setModuleList(ReadOnlyTrackr<Module> newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyTrackr<Module> getModuleList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setViewToModule() {

        }

        @Override
        public boolean hasModule(Module module) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteModule(Module module) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setModule(Module target, String newModuleId) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Module> getFilteredModuleList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredModuleList(Predicate<Module> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<TutorialGroup> getFilteredTutorialGroupList() {
            return null;
        }

        @Override
        public void updateFilteredTutorialGroupList(Predicate<TutorialGroup> predicate) {

        }

        @Override
        public ObservableList<Student> getFilteredStudentList() {
            return null;
        }

        @Override
        public void updateFilteredStudentList(Predicate<Student> predicate) {

        }
    }

    /*
     * A Model stub that contains a single person.
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
            return this.module.isSame(module);
        }
    }

    /*
     * A Model stub that always accept the person being added.
     */

    private class ModelStubAcceptingModuleAdded extends ModelStub {
        final ArrayList<Module> modulesAdded = new ArrayList<>();
        final ArrayList<TutorialGroup> tutorialGroupsAdded = new ArrayList<>();

        public boolean hasTutorialGroup(TutorialGroup tutorialGroup) {
            requireNonNull(tutorialGroup);
            return tutorialGroupsAdded.stream().anyMatch(tutorialGroup::isSame);
        }

        @Override
        public void addTutorialGroup(TutorialGroup tutorialGroup) {
            requireNonNull(tutorialGroup);
            tutorialGroupsAdded.add(tutorialGroup);
        }

        @Override
        public boolean hasModule(Module module) {
            requireNonNull(module);
            return modulesAdded.stream().anyMatch(module::isSame);
        }

        @Override
        public void addModule(Module module) {
            requireNonNull(module);
            modulesAdded.add(module);
        }

        @Override
        public ReadOnlyTrackr<Module> getModuleList() {
            return new Trackr();
        }
    }

}
