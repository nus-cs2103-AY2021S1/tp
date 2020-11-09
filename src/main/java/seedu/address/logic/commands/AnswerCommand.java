package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ANSWER;

import javafx.collections.ObservableList;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.quiz.Question;
import seedu.address.model.quiz.Response;
import seedu.address.model.quiz.exceptions.InvalidQuestionAnswerException;


/**
 * Answers a question identified using it's displayed index from the quiz book.
 */
public class AnswerCommand extends Command {
    public static final String COMMAND_WORD = "answer";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Answers a question identified by the index number used in the displayed question list. \n"
            + "Parameters: "
            + "INDEX (must be a positive integer) "
            + PREFIX_ANSWER + "ANSWER\n"
            + "True/False Example: " + COMMAND_WORD + " 1 "
            + PREFIX_ANSWER + "true\n"
            + "MCQ Example: " + COMMAND_WORD + " 6 "
            + PREFIX_ANSWER + "1\n"
            + "Answers should not be left blank!";

    public static final String MESSAGE_ANSWER_SUCCESS = "Your answer has successfully been recorded.";

    public static final String MESSAGE_CURRENTLY_NOT_ATTEMPTING = "Please start an attempt before answering a "
            + "question!";

    private final Index index;
    private final String answer;

    /**
     * Creates an AnswerCommand to record the specified {@code Response}
     */
    public AnswerCommand(Index index, String answer) {
        requireAllNonNull(index, answer);
        this.index = index;
        this.answer = answer;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (!model.hasCurrentAttempt()) {
            return new CommandResult(MESSAGE_CURRENTLY_NOT_ATTEMPTING);
        }

        ObservableList<Question> lastShownQuestionList = model.getQuizList();

        if (index.getZeroBased() >= lastShownQuestionList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_QUESTION_DISPLAYED_INDEX);
        }

        Question qn = lastShownQuestionList.get(index.getZeroBased());
        Response resp;

        try {
            resp = new Response(this.answer, qn);
            resp.markResponse();
            model.setSelectedIndex(qn, answer);
        } catch (InvalidQuestionAnswerException e) {
            throw new CommandException(e.getMessage());
        }

        model.recordResponse(resp);
        return new CommandResult(String.format(MESSAGE_ANSWER_SUCCESS, this.index.getZeroBased(), this.answer));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AnswerCommand // instanceof handles nulls
                && index.equals(((AnswerCommand) other).index)
                && answer.equals(((AnswerCommand) other).answer));
    }
}
