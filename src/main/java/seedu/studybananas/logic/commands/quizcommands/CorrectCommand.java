package seedu.studybananas.logic.commands.quizcommands;

import static java.util.Objects.requireNonNull;
import static seedu.studybananas.logic.commands.commandresults.QuizCommandResultType.CORRECT;

import seedu.studybananas.logic.commands.Command;
import seedu.studybananas.logic.commands.commandresults.CommandResult;
import seedu.studybananas.logic.commands.commandresults.QuizCommandResult;
import seedu.studybananas.logic.commands.exceptions.CommandException;
import seedu.studybananas.model.QuizModel;
import seedu.studybananas.ui.quizui.QuizCard;

public class CorrectCommand extends Command<QuizModel> {

    public static final String COMMAND_WORD = "c";
    public static final Status STATUS = Status.ON_ANSWER;

    @Override
    public CommandResult execute(QuizModel model) throws CommandException {
        requireNonNull(model);

        if (!model.hasStarted()) {
            throw new CommandException(QuizCommandUtil.MESSAGE_QUIZ_NEVER_STARTED);
        }

        if (!QuizCommandUtil.getStatus().equals(STATUS)) {
            throw new CommandException(QuizCommandUtil.MESSAGE_UNAVAIL_ON_QUESTION);
        }

        try {
            model.tallyScore(true);

            QuizCommandUtil.setStatus(Status.ON_QUESTION);
            QuizCard.setQuestion(model.getQuestion());
            String questionStringToShow = QuizCommandUtil.MESSAGE_AVAIL_ON_QUESTION;
            QuizCommandUtil.updateCommandResult(questionStringToShow);

            return new QuizCommandResult(questionStringToShow, model.getQuiz(), CORRECT);

        } catch (NullPointerException | IndexOutOfBoundsException e) {
            QuizCommandUtil.updateCommandResult(null);
            model.setQuizRecordsToView(model.getQuiz().getFlsetName());
            return new QuizCommandResult(model.stopQuiz(), CORRECT);
        }
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof CorrectCommand;
    }
}
