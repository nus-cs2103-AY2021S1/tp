package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.logic.commands.SolveQuestionCommand.MESSAGE_BAD_QUESTION_INDEX;
import static seedu.address.logic.commands.SolveQuestionCommand.MESSAGE_SOLVED_QUESTION;
import static seedu.address.logic.commands.SolveQuestionCommand.MESSAGE_SUCCESS;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalStudents.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.student.Question;
import seedu.address.model.student.Student;
import seedu.address.testutil.StudentBuilder;

public class SolveQuestionCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    void constructor_null_throwsNullPointerException() {
        // both fields null
        assertThrows(NullPointerException.class, () -> new SolveQuestionCommand(null, null));

        // one null field
        assertThrows(NullPointerException.class, () -> new SolveQuestionCommand(null, INDEX_FIRST_PERSON));
        assertThrows(NullPointerException.class, () -> new SolveQuestionCommand(INDEX_FIRST_PERSON, null));
    }

    @Test
    void execute_validIndexUnfilteredList_success() {
        String testQuestion = "What is 1 + 1?";
        Student asker = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Student clone = new StudentBuilder(asker).withQuestions(testQuestion).build();
        model.setPerson(asker, clone);

        Student expectedStudent = new StudentBuilder(asker).withSolved(testQuestion).build();
        Question solved = expectedStudent.getQuestions().get(0);
        String expectedMessage = String.format(MESSAGE_SUCCESS, solved);

        SolveQuestionCommand solveCommand = new SolveQuestionCommand(INDEX_FIRST_PERSON, INDEX_FIRST_PERSON);

        ModelManager expectedModel = new ModelManager(model.getReeve(), new UserPrefs());
        expectedModel.setPerson(clone, expectedStudent);

        assertCommandSuccess(solveCommand, model, expectedMessage, expectedModel);
    }

    @Test
    void execute_invalidStudentIndexUnfilteredList_throwsCommandException() {
        Index outOfBounds = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        SolveQuestionCommand solveCommand = new SolveQuestionCommand(outOfBounds, INDEX_FIRST_PERSON);
        assertCommandFailure(solveCommand, model, MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showPersonAtIndex(model, INDEX_SECOND_PERSON);

        String testQuestion = "What is 1 + 1?";
        Student asker = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Student clone = new StudentBuilder(asker).withQuestions(testQuestion).build();
        model.setPerson(asker, clone);

        Student expectedStudent = new StudentBuilder(asker).withSolved(testQuestion).build();
        Question solved = expectedStudent.getQuestions().get(0);
        String expectedMessage = String.format(SolveQuestionCommand.MESSAGE_SUCCESS, solved);

        SolveQuestionCommand solveCommand = new SolveQuestionCommand(Index.fromZeroBased(0), Index.fromZeroBased(0));

        ModelManager expectedModel = new ModelManager(model.getReeve(), new UserPrefs());
        expectedModel.setPerson(clone, expectedStudent);

        assertCommandSuccess(solveCommand, model, expectedMessage, expectedModel);

    }

    @Test
    public void execute_invalidStudentIndexFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_SECOND_PERSON);

        Index outOfBounds = INDEX_SECOND_PERSON;
        SolveQuestionCommand solveCommand = new SolveQuestionCommand(outOfBounds, INDEX_FIRST_PERSON);
        assertCommandFailure(solveCommand, model, MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidQuestionIndex_throwsCommandException() {
        String testQuestion = "What is 1 + 1?";

        Student asker = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Student clone = new StudentBuilder(asker).withQuestions(testQuestion).build();
        model.setPerson(asker, clone);

        SolveQuestionCommand solveCommand = new SolveQuestionCommand(INDEX_FIRST_PERSON, INDEX_SECOND_PERSON);
        assertCommandFailure(solveCommand, model, MESSAGE_BAD_QUESTION_INDEX);
    }

    @Test
    public void execute_alreadySolvedQuestion_throwsCommandException() {
        String testQuestion = "What is 1 + 1?";

        Student asker = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Student clone = new StudentBuilder(asker).withSolved(testQuestion).build();
        model.setPerson(asker, clone);

        SolveQuestionCommand solveCommand = new SolveQuestionCommand(INDEX_FIRST_PERSON, INDEX_FIRST_PERSON);
        assertCommandFailure(solveCommand, model, MESSAGE_SOLVED_QUESTION);
    }

    @Test
    public void equals() {
        SolveQuestionCommand command = new SolveQuestionCommand(INDEX_FIRST_PERSON, INDEX_FIRST_PERSON);

        // same object -> true
        assertTrue(command.equals(command));

        // same fields -> true
        assertTrue(command.equals(new SolveQuestionCommand(INDEX_FIRST_PERSON, INDEX_FIRST_PERSON)));

        // different questionIndex -> false
        assertFalse(command.equals(new SolveQuestionCommand(INDEX_FIRST_PERSON, INDEX_SECOND_PERSON)));

        // different studentIndex -> false
        assertFalse(command.equals(new SolveQuestionCommand(INDEX_SECOND_PERSON, INDEX_FIRST_PERSON)));
    }
}
