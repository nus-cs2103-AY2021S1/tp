package seedu.studybananas.logic.parser.quizparsers;

import seedu.studybananas.logic.commands.Command;
import seedu.studybananas.logic.commands.quizcommands.AnswerCommand;
import seedu.studybananas.logic.commands.quizcommands.CancelCommand;
import seedu.studybananas.logic.commands.quizcommands.CorrectCommand;
import seedu.studybananas.logic.commands.quizcommands.FlipCommand;
import seedu.studybananas.logic.commands.quizcommands.RefreshCommand;
import seedu.studybananas.logic.commands.quizcommands.StartCommand;
import seedu.studybananas.logic.commands.quizcommands.ViewScoreCommand;
import seedu.studybananas.logic.commands.quizcommands.WrongCommand;
import seedu.studybananas.logic.parser.Parser;
import seedu.studybananas.logic.parser.exceptions.ParseException;
import seedu.studybananas.model.Model;
import seedu.studybananas.model.flashcard.FlashcardSetName;

public class QuizParser implements Parser<Command> {

    public static final String EMPTY_SPACE = "";
    public static final String MESSAGE_PARSING_ERROR =
            "The command for quiz is invalid. Please check the command format and try again.";

    @Override
    public Command<? super Model> parse(String userInput) throws ParseException {
        String lowerCaseUserInput = userInput.toLowerCase();
        lowerCaseUserInput = lowerCaseUserInput.trim();
        if (lowerCaseUserInput.startsWith(StartCommand.COMMAND_WORD)) {
            lowerCaseUserInput = lowerCaseUserInput.replace(StartCommand.COMMAND_WORD, EMPTY_SPACE);
            return parseStartCommand(lowerCaseUserInput);
        } else if (lowerCaseUserInput.startsWith(ViewScoreCommand.COMMAND_WORD)) {
            lowerCaseUserInput = lowerCaseUserInput.replace(ViewScoreCommand.COMMAND_WORD, EMPTY_SPACE);
            return parseViewScoreCommand(lowerCaseUserInput);
        } else if (lowerCaseUserInput.startsWith(AnswerCommand.COMMAND_WORD)) {
            userInput = userInput.substring(AnswerCommand.STARTING_INDEX_OF_ANSWER);
            return new AnswerCommand(userInput);
        }

        switch (lowerCaseUserInput) {
        case CancelCommand.COMMAND_WORD:
            return new CancelCommand();
        case FlipCommand.COMMAND_WORD:
            return new FlipCommand();
        case CorrectCommand.COMMAND_WORD:
            return new CorrectCommand();
        case WrongCommand.COMMAND_WORD:
            return new WrongCommand();
        case RefreshCommand.COMMAND_WORD:
            return new RefreshCommand();
        default:
            throw new ParseException(MESSAGE_PARSING_ERROR);
        }
    }

    /**
     * Parses a int number from an input string
     * @param input string provided
     * @return an int number
     * @throws ParseException if the format of string is invalid
     */
    public int parseNumber(String input) throws ParseException {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new ParseException("Invalid characters provided for flashcard set number");
        }
    }

    private StartCommand parseStartCommand(String input) throws ParseException {
        input = input.trim();
        if (input.equals(EMPTY_SPACE)) {
            throw new ParseException("Please enter a valid flashcard set or index to get quiz score.");
        }
        try {
            int index = parseNumber(input);
            return new StartCommand(index);
        } catch (ParseException e) {
            FlashcardSetName name = new FlashcardSetName(input);
            return new StartCommand(name);
        }
    }

    private ViewScoreCommand parseViewScoreCommand(String input) throws ParseException {
        input = input.trim();
        if (input.equals(EMPTY_SPACE)) {
            throw new ParseException("Please enter a valid flashcard set or index to get quiz score.");
        }
        try {
            int index = parseNumber(input);
            return new ViewScoreCommand(index);
        } catch (ParseException e) {
            FlashcardSetName name = new FlashcardSetName(input);
            return new ViewScoreCommand(name);
        }
    }
}
