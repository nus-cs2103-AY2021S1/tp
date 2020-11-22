package seedu.forgetfulnus.logic.commands;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.forgetfulnus.commons.core.Comparators;
import seedu.forgetfulnus.model.Glossary;
import seedu.forgetfulnus.model.Model;
import seedu.forgetfulnus.model.flashcard.FlashCard;

public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sorts the glossary according to the input parameter.\n"
            + "Possible parameters: german, english, reversegerman, reverseenglish,"
            + "easytohard, hardtoeasy, earliest, latest\n"
            + "Example: " + COMMAND_WORD + " german";

    public static final String MESSAGE_SORT_SUCCESS = "The glossary has been sorted!";

    public static final String QUIZ_MODE_REMINDER = "Flashcards cannot be sorted in quiz mode. "
            + "Enter 'end' to end quizzing.";

    public static final String MESSAGE_EMPTY_GLOSSARY = "The glossary is empty, there is nothing to sort!";

    private static final CommandType type = CommandType.NOT_QUIZ_MODE;

    private Comparator<FlashCard> comp;

    private Logger logger = Logger.getLogger("Sort Command Logger");

    /**
     * Creates a new SortCommand using the input parameter from the user.
     * @param parameter the parameter that the user wants to sort the glossary by.
     */
    public SortCommand(String parameter) {
        assert parameter != null : "Input cannot be null!";
        parameter = parameter.trim().toLowerCase();
        logger.log(Level.INFO, String.format("Input parameter: %s", parameter));
        switch(parameter) {
        case("german"):
            comp = Comparators.GERMAN_COMP;
            break;
        case("english"):
            comp = Comparators.ENGLISH_COMP;
            break;
        case("reversegerman"):
            comp = Comparators.REVERSE_GERMAN_COMP;
            break;
        case("reverseenglish"):
            comp = Comparators.REVERSE_ENGLISH_COMP;
            break;
        case("easytohard"):
            comp = Comparators.DIFFICULTY_EASY_COMP;
            break;
        case("hardtoeasy"):
            comp = Comparators.DIFFICULTY_HARD_COMP;
            break;
        case("earliest"):
            comp = Comparators.CHRONOLOGICAL_EARLIEST_COMP;
            break;
        case("latest"):
            comp = Comparators.CHRONOLOGICAL_LATEST_COMP;
            break;
        default:
            assert false : "Invalid parameter %s";
        }
    }
    @Override
    public CommandResult execute(Model model) {
        assert model != null : "Model cannot be null!";
        if (model.getGlossary().getFlashCardList().size() == 0) {
            logger.log(Level.INFO, "Glossary is empty!");
            return new CommandResult(MESSAGE_EMPTY_GLOSSARY);
        }
        model.setGlossary(getSortedGlossary(model));
        return new CommandResult(MESSAGE_SORT_SUCCESS);
    }
    public Glossary getSortedGlossary(Model model) {
        assert model != null : "Model cannot be null!";
        List<FlashCard> sortedList = new ArrayList<>(model.getGlossary().getFlashCardList());
        sortedList.sort(comp);
        logger.log(Level.INFO, "List successfully sorted.");
        Glossary glossary = new Glossary();
        glossary.setFlashCards(sortedList);
        return glossary;
    }

    @Override
    public CommandType isQuizModeCommand() {
        return type;
    }

    @Override
    public String getQuizModeReminder() {
        return QUIZ_MODE_REMINDER;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || other.getClass() != this.getClass()) {
            return false;
        }
        SortCommand otherCommand = (SortCommand) other;
        return this.comp.equals(otherCommand.comp);
    }
}
