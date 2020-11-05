package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.AddTutorialGroupCommand.MESSAGE_DUPLICATE_TUTORIAL;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyTrackr;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.Trackr;
import seedu.address.model.module.Module;
import seedu.address.model.student.Student;
import seedu.address.model.tutorialgroup.TutorialGroup;
import seedu.address.testutil.TutorialGroupBuilder;

public class AddTutorialGroupCommandTest {

    @Test
    public void constructor_nullTutorialGroup_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddTutorialGroupCommand(null));
    }

    @Test
    public void execute_tutorialGroupAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingTutorialGroupAdded modelStub = new ModelStubAcceptingTutorialGroupAdded();
        TutorialGroup validTutorialGroup = new TutorialGroupBuilder().build();

        CommandResult commandResult = new AddTutorialGroupCommand(validTutorialGroup).execute(modelStub);

        assertEquals(String.format(AddTutorialGroupCommand.MESSAGE_SUCCESS, validTutorialGroup),
            commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validTutorialGroup), modelStub.tutorialGroupsAdded);
    }

    @Test
    public void execute_duplicateTutorialGroup_throwsCommandException() {
        TutorialGroup validTutorialGroup = new TutorialGroupBuilder().build();
        AddTutorialGroupCommand addTutorialGroupCommand = new AddTutorialGroupCommand(validTutorialGroup);
        ModelStub modelStub = new ModelStubWithTutorialGroup(validTutorialGroup);

        assertThrows(CommandException.class, MESSAGE_DUPLICATE_TUTORIAL, ()
            -> addTutorialGroupCommand.execute(modelStub)
        );
    }

    @Test
    public void equals() {
        TutorialGroup b014 = new TutorialGroupBuilder().withTutorialGroupId("B014").build();
        TutorialGroup t003 = new TutorialGroupBuilder().withTutorialGroupId("T003").build();
        AddTutorialGroupCommand addb014Command = new AddTutorialGroupCommand(b014);
        AddTutorialGroupCommand addt003Command = new AddTutorialGroupCommand(t003);

        // same object -> returns true
        assertTrue(addb014Command.equals(addb014Command));

        // same values -> returns true
        AddTutorialGroupCommand addb014CommandCopy = new AddTutorialGroupCommand(b014);
        assertTrue(addb014Command.equals(addb014CommandCopy));

        // different types -> returns false
        assertFalse(addb014Command.equals(1));

        // null -> returns false
        assertFalse(addb014Command.equals(null));

        // different person -> returns false
        assertFalse(addb014Command.equals(addt003Command));
    }

    public static class ModelStub implements Model {

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
            return false;
        }

        @Override
        public Module getCurrentModuleInView() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setViewToTutorialGroup(Module target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setCurrentViewToTutorialGroup() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addTutorialGroup(TutorialGroup tutorialGroup) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteTutorialGroup(TutorialGroup tutorialGroup) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasTutorialGroup(TutorialGroup tutorialGroup) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setTutorialGroup(TutorialGroup target, TutorialGroup edited) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean isInTutorialGroupView() {
            return true;
        }

        @Override
        public void setViewToStudent(TutorialGroup target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setCurrentViewToStudent() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public TutorialGroup getCurrentTgInView() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasStudent(Student student) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteStudent(Student target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addStudent(Student student) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setStudent(Student target, Student editedStudent) {
            throw new AssertionError("This method should not be called.");
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
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setCurrentViewToModule() {
            throw new AssertionError("This method should not be called.");
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
        public void setModule(Module target, Module editedModule) {
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
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredTutorialGroupList(Predicate<TutorialGroup> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Student> getFilteredStudentList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredStudentList(Predicate<Student> predicate) {
            throw new AssertionError("This method should not be called.");
        }
    }

    private class ModelStubWithTutorialGroup extends ModelStub {
        private ObservableList<TutorialGroup> tutorialGroupsList = FXCollections.observableArrayList();
        private final TutorialGroup tutorialGroup;

        ModelStubWithTutorialGroup(TutorialGroup tutorialGroup) {
            requireNonNull(tutorialGroup);
            this.tutorialGroup = tutorialGroup;
            tutorialGroupsList.add(tutorialGroup);
            tutorialGroupsList = new FilteredList<>(tutorialGroupsList);
        }

        @Override
        public boolean hasTutorialGroup(TutorialGroup tutorialGroup) {
            requireNonNull(tutorialGroup);
            return this.tutorialGroup.isSame(tutorialGroup);
        }



        @Override
        public ObservableList<TutorialGroup> getFilteredTutorialGroupList() {
            return tutorialGroupsList;
        }
    }

    /*
     * A Model stub that always accept the Module being added.
     */
    private class ModelStubAcceptingTutorialGroupAdded extends ModelStub {
        final ArrayList<Module> modulesAdded = new ArrayList<>();
        final ArrayList<TutorialGroup> tutorialGroupsAdded = new ArrayList<>();
        final ObservableList<TutorialGroup> tutorialGroupsList =
            new FilteredList<TutorialGroup>(FXCollections.observableArrayList());

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
        public ObservableList<TutorialGroup> getFilteredTutorialGroupList() {
            return tutorialGroupsList;
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
