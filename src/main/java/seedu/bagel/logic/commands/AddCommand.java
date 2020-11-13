package seedu.bagel.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.bagel.logic.parser.CliSyntax.PREFIX_DESC;
import static seedu.bagel.logic.parser.CliSyntax.PREFIX_LINK;
import static seedu.bagel.logic.parser.CliSyntax.PREFIX_SET;
import static seedu.bagel.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.bagel.logic.parser.CliSyntax.PREFIX_TITLE;

import seedu.bagel.logic.commands.exceptions.CommandException;
import seedu.bagel.model.Model;
import seedu.bagel.model.flashcard.Flashcard;

/**
 * Adds a flashcard to the bagel.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a flashcard to bagel.\n"
            + "Parameters: "
            + PREFIX_TITLE + "TITLE "
            + PREFIX_DESC + "DESCRIPTION "
            + PREFIX_SET + "SET "
            + "[" + PREFIX_LINK + "LINK] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_TITLE + "Definition of p-value "
            + PREFIX_DESC + "P-value is the probability of obtaining results at least as extreme as the observed "
            + "results of a statistical hypothesis test, assuming that the null hypothesis is correct "
            + PREFIX_SET + "1 "
            + PREFIX_TAG + "pvalue "
            + PREFIX_TAG + "definition";

    public static final String MESSAGE_SUCCESS = "New flashcard added: %1$s";
    public static final String MESSAGE_DUPLICATE_FLASHCARD = "This flashcard already exists in your library."
            + "\nConsider using the search/edit command instead.";

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
        if (!model.hasFlashcardSet(toAdd.getFlashcardSet())) {
            model.addFlashcard(toAdd);
            return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd), false, false, true);
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
