package seedu.bookmark.commons.core;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_BOOK_DISPLAYED_INDEX = "The book index provided is invalid";
    public static final String MESSAGE_INVALID_NOTE_DISPLAYED_INDEX = "The note index provided is invalid";
    public static final String MESSAGE_BOOKS_LISTED_OVERVIEW = "%1$d books listed!";
    public static final String MESSAGE_WORD_SUGGESTION = "Could not find: %1$s \nDid you mean: %2$s?";
    public static final String MESSAGE_WORD_NOT_UNDERSTOOD = "Could not find: %1$s \nSorry! I don't"
                                                            + " have any suggestions!!";
    public static final String MESSAGE_TOO_MANY_TAGS = "The book has too many tags, every book can only have %d tags!";
    public static final String MESSAGE_TOO_MANY_NOTES = "The book has too many notes, "
            + "every book can only have %d notes";
    public static final String MESSAGE_TOO_MANY_BOOKS = "Sorry, bookmark can only store up to %d books!";
    public static final String MESSAGE_BOOKS_SORTED = "Sorted by ";

}
