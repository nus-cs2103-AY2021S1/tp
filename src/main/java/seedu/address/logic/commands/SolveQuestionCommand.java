package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.student.Student;
import seedu.address.model.student.question.Question;
import seedu.address.model.student.question.SolvedQuestion;

/**
 * Resolves a specified question of a student at the displayed index in Reeve.
 */
public class SolveQuestionCommand extends QuestionCommand {

    public static final String MESSAGE_SUCCESS = "%1$s's question resolved: %2$s";
    public static final String MESSAGE_SOLVED_QUESTION = "This question was already solved";
    public static final String MESSAGE_BAD_QUESTION_INDEX = "There is no question at this index";

    private final Index studentIndex;
    private final Index questionIndex;
    private final String solution;

    /**
     * Constructs a SolveQuestionCommand to mark a specified {@code Question} as solved.
     */
    public SolveQuestionCommand(Index studentIndex, Index questionIndex, String solution) {
        requireAllNonNull(studentIndex, questionIndex, solution);
        this.studentIndex = studentIndex;
        this.questionIndex = questionIndex;
        this.solution = solution;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Student> lastShownList = model.getFilteredPersonList();
        if (studentIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
        }

        Student asker = lastShownList.get(studentIndex.getZeroBased());
        checkQuestionIndex(asker);

        List<Question> questionList = solveQuestion(asker);
        Student replacement = new Student(asker.getName(), asker.getPhone(), asker.getSchool(),
                asker.getYear(), asker.getAdmin(), questionList);
        Question solvedQuestion = questionList.get(questionIndex.getZeroBased());

        model.setPerson(asker, replacement);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_SUCCESS, replacement.getName(), solvedQuestion));
    }

    /***
     * Checks if the question at the specified index exists or has already been solved.
     */
    private void checkQuestionIndex(Student asker) throws CommandException {
        if (questionIndex.getZeroBased() >= asker.getQuestions().size()) {
            throw new CommandException(MESSAGE_BAD_QUESTION_INDEX);
        }
        if (asker.getQuestions().get(questionIndex.getZeroBased()).isResolved()) {
            throw new CommandException(MESSAGE_SOLVED_QUESTION);
        }
    }

    /**
     * Marks a Student's question as done and returns the solved question.
     */
    private List<Question> solveQuestion(Student asker) {
        int position = questionIndex.getZeroBased();
        List<Question> questions = new ArrayList<>(asker.getQuestions());
        Question toSolve = questions.get(position);
        questions.set(position, new SolvedQuestion(toSolve.question, solution));

        return questions;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof SolveQuestionCommand)) {
            return false;
        }

        SolveQuestionCommand other = (SolveQuestionCommand) obj;
        return studentIndex.equals(other.studentIndex)
                && questionIndex.equals(other.questionIndex)
                && solution.equals(other.solution);
    }

}
