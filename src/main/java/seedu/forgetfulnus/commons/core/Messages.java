package seedu.forgetfulnus.commons.core;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_MULTIPLE_PREFIX =
        "More than one prefix for a field has been detected!"
                + "\nPlease check your command again!";
    public static final String MESSAGE_INVALID_FLASHCARD_DISPLAYED_INDEX =
            "The flashcard index provided is invalid!";
    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";
    public static final String MESSAGE_INVALID_PREFIX_PARAM = " %s is not a valid prefix parameter.";
    public static final String MESSAGE_INVALID_SORT_PARAM = " %s is not a valid sort parameter.";
    public static final String MESSAGE_PHRASES_LISTED_OVERVIEW = "%1$d flashcard(s) listed!\n"
            + "Type 'list' to show entire glossary.";
    public static final String MESSAGE_QUIZ_ALREADY_STARTED =
            "Quiz has already started! Enter 'end' to end quizzing.";
    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command!";
    public static final String MESSAGE_ZERO_INDEX = "Index is 0.";
}
