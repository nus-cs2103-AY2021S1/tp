package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;
import org.junit.jupiter.api.Test;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ReadOnlyTrackr;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.Trackr;
import seedu.address.model.UserPrefs;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleId;
import seedu.address.model.student.Student;
import seedu.address.model.tutorialgroup.TutorialGroup;
import seedu.address.testutil.TypicalModules;
import seedu.address.testutil.TypicalModulesPopulatedWithTutorialGroups;
import seedu.address.testutil.TypicalTutorialGroups;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeleteTutorialGroupCommandTest {

    private Model model = new ModelManager(TypicalModulesPopulatedWithTutorialGroups.getTypicalModuleList(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() throws CommandException {
        Module moduleToDeleteFrom = model.getFilteredModuleList().get(INDEX_FIRST_PERSON.getZeroBased());
        model.setViewToTutorialGroup(moduleToDeleteFrom);
        TutorialGroup tutorialGroupToDelete = moduleToDeleteFrom.getTutorialGroups().get(INDEX_FIRST_PERSON.getZeroBased());
        DeleteTutorialGroupCommand deleteTutorialGroupCommand = new DeleteTutorialGroupCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(DeleteTutorialGroupCommand.MESSAGE_DELETE_TUTORIAL_SUCCESS, tutorialGroupToDelete);

        ModelManager expectedModel = new ModelManager(model.getModuleList(), new UserPrefs());
        expectedModel.setViewToTutorialGroup(moduleToDeleteFrom);

        CommandResult deleteTutorialGroupCommandResult = deleteTutorialGroupCommand.execute(model);

        assertEquals(expectedMessage, deleteTutorialGroupCommandResult.getFeedbackToUser());
        assertEquals(expectedModel, model);
    }


    private void showNoTutorialGroup(Model model) {
        model.updateFilteredTutorialGroupList(p -> false);

        assertTrue(model.getFilteredTutorialGroupList().isEmpty());
    }

    public static class ModelStub implements Model {
        private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

        private final Trackr moduleList;

        private FilteredList<Module> filteredModules;
        private FilteredList<TutorialGroup> filteredTutorialGroup;
        private FilteredList<Student> filteredStudents;

        private final UserPrefs userPrefs;
        private Module currentModuleInView;
        private TutorialGroup currentTgInView;

        private boolean isInModuleView;
        private boolean isInTutorialGroupView;
        private boolean isInStudentView;

        public ModelStub(Trackr moduleList, UserPrefs userPrefs) {
            super();
            requireAllNonNull(moduleList, userPrefs);
            logger.fine("Initializing with module data: " + moduleList + " and user prefs: " + userPrefs);

            this.moduleList = new Trackr(moduleList);

            this.filteredModules = new FilteredList<>(this.moduleList.getList());
            this.filteredTutorialGroup = new FilteredList<>(FXCollections.observableArrayList());
            this.filteredStudents = new FilteredList<>(FXCollections.observableArrayList());

            this.userPrefs = new UserPrefs(userPrefs);

            this.isInModuleView = true;
            this.isInTutorialGroupView = false;
            this.isInStudentView = false;
        }

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
        public boolean isInTutorialGroupView() { return true; }

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
        public void setModule(Module target, ModuleId newModuleId) {
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
        private final TutorialGroup tutorialGroup;
        ObservableList<TutorialGroup> tutorialGroupsList = FXCollections.observableArrayList();

        ModelStubWithTutorialGroup(TutorialGroup tutorialGroup) {
            super(TypicalModules.getTypicalModuleList(), new UserPrefs());
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

}
