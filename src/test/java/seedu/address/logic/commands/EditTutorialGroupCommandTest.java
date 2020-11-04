package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.nio.file.Path;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.EditTutorialGroupCommand.EditTutorialGroupDescriptor;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ReadOnlyTrackr;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.module.Module;
import seedu.address.model.student.Student;
import seedu.address.model.tutorialgroup.TutorialGroup;
import seedu.address.testutil.ModuleBuilder;
import seedu.address.testutil.TutorialGroupBuilder;

public class EditTutorialGroupCommandTest {

    private Model model = new ModelManager();

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() throws CommandException {
        ModelStubWithOneTutorialGroup modelStub = new ModelStubWithOneTutorialGroup();
        TutorialGroup editedTutorialGroup = new TutorialGroupBuilder().withTutorialGroupId("B014").build();
        EditTutorialGroupDescriptor editTutorialGroupDescriptor =
            new EditTutorialGroupDescriptor(editedTutorialGroup);

        CommandResult commandResult = new EditTutorialGroupCommand(INDEX_FIRST_PERSON, editTutorialGroupDescriptor)
            .execute(modelStub);

        assertEquals(String.format(EditTutorialGroupCommand.MESSAGE_EDIT_TUTORIAL_SUCCESS, editedTutorialGroup.getId()),
            commandResult.getFeedbackToUser());
        assertEquals(editedTutorialGroup, modelStub.tutorialGroup);
    }

    @Test
    public void execute_duplicateTutorialUnfilteredList_failure() throws CommandException {
        ModelStubWithTwoTutorialGroup modelStub = new ModelStubWithTwoTutorialGroup();
        TutorialGroup editedTutorialGroup = new TutorialGroupBuilder().withTutorialGroupId("T003").build();
        EditTutorialGroupDescriptor editTutorialGroupDescriptor = new EditTutorialGroupDescriptor(editedTutorialGroup);
        EditTutorialGroupCommand editCommand = new EditTutorialGroupCommand(INDEX_FIRST_PERSON,
            editTutorialGroupDescriptor);

        assertThrows(CommandException.class, EditTutorialGroupCommand.MESSAGE_DUPLICATE_TUTORIAL, ()
            -> editCommand.execute(modelStub));

    }



    private class ModelStubWithOneTutorialGroup extends ModelStub {
        private ObservableList<Module> filteredModuleList = FXCollections.observableArrayList();
        private ObservableList<TutorialGroup> filteredTutorialGroupList = FXCollections.observableArrayList();
        private TutorialGroup defaultTutorialGroup = new TutorialGroupBuilder().build();
        private TutorialGroup tutorialGroup = defaultTutorialGroup;

        public ModelStubWithOneTutorialGroup() {
            Module module = new ModuleBuilder().build();
            module.addTutorialGroup(defaultTutorialGroup);
            filteredModuleList.add(module);
            filteredModuleList = new FilteredList<>(filteredModuleList);
            filteredTutorialGroupList.add(defaultTutorialGroup);
            filteredTutorialGroupList = new FilteredList<>(filteredTutorialGroupList);
        }

        @Override
        public void setTutorialGroup(TutorialGroup target, TutorialGroup edited) {
            this.tutorialGroup = edited;
        }

        @Override
        public ObservableList<Module> getFilteredModuleList() {
            return filteredModuleList;
        }

        @Override
        public ObservableList<TutorialGroup> getFilteredTutorialGroupList() {
            return filteredTutorialGroupList;
        }

    }

    private class ModelStubWithTwoTutorialGroup extends ModelStub {
        private ObservableList<Module> filteredModuleList = FXCollections.observableArrayList();
        private ObservableList<TutorialGroup> filteredTutorialGroupList = FXCollections.observableArrayList();
        private TutorialGroup defaultTutorialGroup1 = new TutorialGroupBuilder().build();
        private TutorialGroup defaultTutorialGroup2 = new TutorialGroupBuilder().withTutorialGroupId("T003").build();

        public ModelStubWithTwoTutorialGroup() {
            Module module = new ModuleBuilder().build();
            module.addTutorialGroup(defaultTutorialGroup1);
            module.addTutorialGroup(defaultTutorialGroup2);
            filteredModuleList.add(module);
            filteredModuleList = new FilteredList<>(filteredModuleList);
            filteredTutorialGroupList.add(defaultTutorialGroup1);
            filteredTutorialGroupList.add(defaultTutorialGroup2);
            filteredTutorialGroupList = new FilteredList<>(filteredTutorialGroupList);
        }

        @Override
        public void setTutorialGroup(TutorialGroup target, TutorialGroup edited) {
            filteredTutorialGroupList.remove(target);
            filteredTutorialGroupList.add(edited);
        }

        @Override
        public ObservableList<Module> getFilteredModuleList() {
            return filteredModuleList;
        }

        @Override
        public ObservableList<TutorialGroup> getFilteredTutorialGroupList() {
            return filteredTutorialGroupList;
        }

        @Override
        public boolean hasTutorialGroup(TutorialGroup tutorialGroup) {
            return filteredTutorialGroupList.contains(tutorialGroup);
        }
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
        public void updateFilteredModuleList(Predicate<Module> predicate) { }

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
}
