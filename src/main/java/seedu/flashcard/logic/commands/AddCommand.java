package seedu.flashcard.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.flashcard.logic.parser.CliSyntax.PREFIX_ANSWER;
import static seedu.flashcard.logic.parser.CliSyntax.PREFIX_CATEGORY;
import static seedu.flashcard.logic.parser.CliSyntax.PREFIX_DIAGRAM;
import static seedu.flashcard.logic.parser.CliSyntax.PREFIX_NOTE;
import static seedu.flashcard.logic.parser.CliSyntax.PREFIX_QUESTION;
import static seedu.flashcard.logic.parser.CliSyntax.PREFIX_RATING;
import static seedu.flashcard.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.flashcard.logic.commands.exceptions.CommandException;
import seedu.flashcard.model.Model;
import seedu.flashcard.model.flashcard.Flashcard;

/**
 * Adds a flashcard.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a flashcard to the list of flashcards.\n"
            + "Parameters: "
            + PREFIX_QUESTION + "QUESTION "
            + PREFIX_ANSWER + "ANSWER "
            + "[" + PREFIX_CATEGORY + "CATEGORY] "
            + "[" + PREFIX_NOTE + "NOTE] "
            + "[" + PREFIX_RATING + "RATING] "
            + "[" + PREFIX_TAG + "TAG]... "
            + "[" + PREFIX_DIAGRAM + "DIAGRAM]\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_QUESTION + "What does OOP stand for? "
            + PREFIX_ANSWER + "Object-Oriented Programming "
            + PREFIX_CATEGORY + "Acronyms "
            + PREFIX_NOTE + "Important question to take note of! "
            + PREFIX_RATING + "2 "
            + PREFIX_TAG + "exam "
            + PREFIX_DIAGRAM + "images/diagram_1.png";

    public static final String MESSAGE_SUCCESS = "New flashcard added: %1$s";
    public static final String MESSAGE_DUPLICATE_FLASHCARD = "This flashcard already exists";

    private final Flashcard toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Flashcard}
     */
    public AddCommand(Flashcard flashcard) {
        requireNonNull(flashcard);
        toAdd = flashcard;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasFlashcard(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_FLASHCARD);
        }

        model.addFlashcard(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && toAdd.equals(((AddCommand) other).toAdd));
    }
}
