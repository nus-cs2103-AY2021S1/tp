package seedu.resireg.logic.commands;

import static seedu.resireg.logic.commands.CommandTestUtil.VALID_STUDENT_ID_AMY;
import static seedu.resireg.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.resireg.logic.commands.CommandTestUtil.assertToggleCommandSuccess;
import static seedu.resireg.logic.commands.CommandTestUtil.showStudentAtIndex;
import static seedu.resireg.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static seedu.resireg.testutil.TypicalStudents.ALICE;
import static seedu.resireg.testutil.TypicalStudents.AMY;
import static seedu.resireg.testutil.TypicalStudents.getTypicalResiReg;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.resireg.logic.CommandHistory;
import seedu.resireg.model.Model;
import seedu.resireg.model.ModelManager;
import seedu.resireg.model.UserPrefs;
import seedu.resireg.model.student.Email;
import seedu.resireg.model.student.Name;
import seedu.resireg.model.student.Phone;
import seedu.resireg.model.student.StudentId;
import seedu.resireg.model.student.faculty.Faculty;
import seedu.resireg.testutil.TypicalStudents;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListStudentsCommand.
 */
public class ListStudentsCommandTest {

    private CommandHistory history = new CommandHistory();
    private ListStudentsCommand.StudentFilter filter = new ListStudentsCommand.StudentFilter();
    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalResiReg(), new UserPrefs());
        expectedModel = new ModelManager(model.getResiReg(), new UserPrefs());
    }

    @Test
    public void execute_listStudentsIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListStudentsCommand(filter),
                model, history, ListStudentsCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listStudentsIsFiltered_showsEverything() {
        showStudentAtIndex(model, INDEX_FIRST_STUDENT);
        assertCommandSuccess(new ListStudentsCommand(filter),
                model, history, ListStudentsCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listStudentsFilteredName_showsStudentWithMatchingName() {
        // Case insensitive
        filter.addValidNames(List.of(new Name(TypicalStudents.KEYWORD_MATCHING_MEIER)));
        expectedModel.updateFilteredStudentList(filter.getStudentPredicate());
        assertToggleCommandSuccess(new ListStudentsCommand(filter), model,
                history, ListStudentsCommand.MESSAGE_FILTERED_SUCCESS, expectedModel, TabView.STUDENTS);

        // Partial matches
        filter.addValidNames(List.of(new Name("Benson"), new Name("Elle")));
        expectedModel.updateFilteredStudentList(filter.getStudentPredicate());
        assertToggleCommandSuccess(new ListStudentsCommand(filter), model,
                history, ListStudentsCommand.MESSAGE_FILTERED_SUCCESS, expectedModel, TabView.STUDENTS);

        // Non existent
        filter.addValidNames(List.of(new Name(AMY.getNameAsString()))); // should not be in Model yet
        expectedModel.updateFilteredStudentList(filter.getStudentPredicate());
        assertToggleCommandSuccess(new ListStudentsCommand(filter), model,
                history, ListStudentsCommand.MESSAGE_FILTERED_SUCCESS, expectedModel, TabView.STUDENTS);
    }

    @Test
    public void execute_listStudentsFilteredPhones_showsStudentWithMatchingPhones() {
        // Matching phone number exists
        filter.addValidPhones(List.of(new Phone(ALICE.getPhone().value)));
        expectedModel.updateFilteredStudentList(filter.getStudentPredicate());
        assertToggleCommandSuccess(new ListStudentsCommand(filter), model,
                history, ListStudentsCommand.MESSAGE_FILTERED_SUCCESS, expectedModel, TabView.STUDENTS);

        // Matching phone number does not exist
        String nonExistentNumber = "66642069";
        filter.addValidPhones(List.of(new Phone(nonExistentNumber)));
        expectedModel.updateFilteredStudentList(filter.getStudentPredicate());
        assertToggleCommandSuccess(new ListStudentsCommand(filter), model,
                history, ListStudentsCommand.MESSAGE_FILTERED_SUCCESS, expectedModel, TabView.STUDENTS);
    }

    @Test
    public void execute_listStudentsFilteredEmail_showsStudentWithMatchingEmail() {
        // Matching email exists
        filter.addValidEmails(List.of(new Email("benson@example.com")));
        expectedModel.updateFilteredStudentList(filter.getStudentPredicate());
        assertToggleCommandSuccess(new ListStudentsCommand(filter), model,
                history, ListStudentsCommand.MESSAGE_FILTERED_SUCCESS, expectedModel, TabView.STUDENTS);

        // Matching email does not exist
        String nonExistentEmail = "non@existent.email";
        filter.addValidEmails(List.of(new Email(nonExistentEmail)));
        expectedModel.updateFilteredStudentList(filter.getStudentPredicate());
        assertToggleCommandSuccess(new ListStudentsCommand(filter), model,
                history, ListStudentsCommand.MESSAGE_FILTERED_SUCCESS, expectedModel, TabView.STUDENTS);
    }

    @Test
    public void execute_listStudentsFilteredFaculty_showsStudentWithMatchingFaculty() {
        // Students in valid Faculty exist
        filter.addValidFaculties(List.of(new Faculty("FASS")));
        expectedModel.updateFilteredStudentList(filter.getStudentPredicate());
        assertToggleCommandSuccess(new ListStudentsCommand(filter), model,
                history, ListStudentsCommand.MESSAGE_FILTERED_SUCCESS, expectedModel, TabView.STUDENTS);

        // Students in valid Faculty do not exist
        String nonExistentFaculty = "ENG";
        filter.addValidFaculties(List.of(new Faculty(nonExistentFaculty)));
        expectedModel.updateFilteredStudentList(filter.getStudentPredicate());
        assertToggleCommandSuccess(new ListStudentsCommand(filter), model,
                history, ListStudentsCommand.MESSAGE_FILTERED_SUCCESS, expectedModel, TabView.STUDENTS);
    }

    @Test
    public void execute_listStudentsFilteredStudentId_showsStudentWithMatchingStudentId() {
        model.addStudent(AMY);
        expectedModel.addStudent(AMY);

        // Student with matching student ID exists
        filter.addValidStudentIds(List.of(new StudentId(VALID_STUDENT_ID_AMY)));
        expectedModel.updateFilteredStudentList(filter.getStudentPredicate());
        assertToggleCommandSuccess(new ListStudentsCommand(filter), model,
                history, ListStudentsCommand.MESSAGE_FILTERED_SUCCESS, expectedModel, TabView.STUDENTS);

        // no students with matching student ID
        String nonExistentStudentId = "E0696969";
        filter.addValidStudentIds(List.of(new StudentId(nonExistentStudentId)));
        expectedModel.updateFilteredStudentList(filter.getStudentPredicate());
        assertToggleCommandSuccess(new ListStudentsCommand(filter), model,
                history, ListStudentsCommand.MESSAGE_FILTERED_SUCCESS, expectedModel, TabView.STUDENTS);
    }
}
