package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX;
import static seedu.address.logic.commands.CommandTestUtil.TEST_QUESTIONS;
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

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.event.Scheduler;
import seedu.address.model.student.Question;
import seedu.address.model.student.Student;
import seedu.address.testutil.StudentBuilder;

public class SolveQuestionCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), new Scheduler());

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
        Student asker = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Student clone = new StudentBuilder(asker).withQuestions(TEST_QUESTIONS).build();
        model.setPerson(asker, clone);

        Index question = Index.fromOneBased(1);
        Student expectedStudent = getAnsweredStudent(question, clone);
        Question solved = expectedStudent.getQuestions().get(question.getZeroBased());
        String expectedMessage = String.format(MESSAGE_SUCCESS, solved);

        SolveQuestionCommand solveCommand = new SolveQuestionCommand(INDEX_FIRST_PERSON, question);
        ModelManager expectedModel = new ModelManager(model.getReeve(), new UserPrefs(), new Scheduler());
        expectedModel.setPerson(clone, expectedStudent);

        assertCommandSuccess(solveCommand, model, expectedMessage, expectedModel);
    }

    @Test
    void execute_invalidStudentIndexUnfilteredList_throwsCommandException() {
        Index outOfBounds = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        Index question = Index.fromOneBased(1);
        SolveQuestionCommand solveCommand = new SolveQuestionCommand(outOfBounds, question);
        assertCommandFailure(solveCommand, model, MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showPersonAtIndex(model, INDEX_SECOND_PERSON);

        Student asker = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Student clone = new StudentBuilder(asker).withQuestions(TEST_QUESTIONS).build();
        model.setPerson(asker, clone);

        Index question = Index.fromOneBased(2);
        Student expectedStudent = getAnsweredStudent(question, clone);
        Question solved = expectedStudent.getQuestions().get(question.getZeroBased());
        String expectedMessage = String.format(MESSAGE_SUCCESS, solved);

        SolveQuestionCommand solveCommand = new SolveQuestionCommand(INDEX_FIRST_PERSON, question);
        ModelManager expectedModel = new ModelManager(model.getReeve(), new UserPrefs(), new Scheduler());
        expectedModel.setPerson(clone, expectedStudent);

        assertCommandSuccess(solveCommand, model, expectedMessage, expectedModel);

    }

    @Test
    public void execute_invalidStudentIndexFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_SECOND_PERSON);

        Index question = Index.fromOneBased(1);
        SolveQuestionCommand solveCommand = new SolveQuestionCommand(INDEX_SECOND_PERSON, question);
        assertCommandFailure(solveCommand, model, MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidQuestionIndex_throwsCommandException() {
        Student asker = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Student clone = new StudentBuilder(asker).withQuestions(TEST_QUESTIONS).build();
        model.setPerson(asker, clone);

        Index outOfBounds = Index.fromOneBased(TEST_QUESTIONS.length + 1);
        SolveQuestionCommand solveCommand = new SolveQuestionCommand(INDEX_FIRST_PERSON, outOfBounds);
        assertCommandFailure(solveCommand, model, MESSAGE_BAD_QUESTION_INDEX);
    }

    @Test
    public void execute_alreadySolvedQuestion_throwsCommandException() {
        Student asker = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Student clone = new StudentBuilder(asker).withSolved(TEST_QUESTIONS).build();
        model.setPerson(asker, clone);

        Index question = Index.fromOneBased(1);
        SolveQuestionCommand solveCommand = new SolveQuestionCommand(INDEX_FIRST_PERSON, question);
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

    private Student getAnsweredStudent(Index index, Student toCopy) {
        List<Question> questions = new ArrayList<>();
        for (int i = 0; i < toCopy.getQuestions().size(); i++) {
            Question toAdd;
            if (i == index.getZeroBased()) {
                toAdd = new Question(TEST_QUESTIONS[i], true);
            } else {
                toAdd = new Question(TEST_QUESTIONS[i]);
            }
            questions.add(toAdd);
        }
        return new Student(toCopy.getName(), toCopy.getPhone(), toCopy.getSchool(),
                toCopy.getYear(), toCopy.getAdmin(), questions);
    }
}
