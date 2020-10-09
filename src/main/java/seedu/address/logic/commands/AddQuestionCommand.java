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
import seedu.address.model.student.Question;
import seedu.address.model.student.Student;

/**
 * Adds an unresolved question to a student at the displayed index in Reeve.
 */
public class AddQuestionCommand extends QuestionCommand {

    public static final String MESSAGE_SUCCESS = "New question added to student: %1$s";
    public static final String MESSAGE_DUPLICATE_QUESTION = "This student has already asked this question";

    private final Index index;
    private final Question questionToAdd;

    /**
     * Creates an AddQuestionCommand to add the specified {@code Question} to the student
     * at the specified {@code Index}.
     */
    public AddQuestionCommand(Index index, Question questionToAdd) {
        requireAllNonNull(index, questionToAdd);

        this.index = index;
        this.questionToAdd = questionToAdd;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Student> lastShownList = model.getFilteredPersonList();
        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Student asker = lastShownList.get(index.getZeroBased());
        if (asker.containsQuestion(questionToAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_QUESTION);
        }

        List<Question> questions = new ArrayList<>(asker.getQuestions());
        questions.add(questionToAdd);
        Student replacement = new Student(asker.getName(), asker.getPhone(),
                asker.getSchool(), asker.getYear(), asker.getAdmin(), questions);
        model.setPerson(asker, replacement);

        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_SUCCESS, questionToAdd));
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof AddQuestionCommand)) {
            return false;
        }

        AddQuestionCommand other = (AddQuestionCommand) obj;
        return index.equals(other.index) && questionToAdd.equals(other.questionToAdd);
    }

}
