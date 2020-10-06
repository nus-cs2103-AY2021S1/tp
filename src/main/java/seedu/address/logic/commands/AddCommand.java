package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SPECIES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.animal.Animal;

/**
 * Adds a animal to the address book.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an animal to the address book. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_ID + "ID "
            + PREFIX_SPECIES + "SPECIES "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "Thomas "
            + PREFIX_ID + "0000237 "
            + PREFIX_SPECIES + "Hummingbird "
            + PREFIX_TAG + "Healthy "
            + PREFIX_TAG + "Tame";

    public static final String MESSAGE_SUCCESS = "New animal added: %1$s";
    public static final String MESSAGE_DUPLICATE_ANIMAL = "This animal already exists in the address book";

    private final Animal toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Animal}
     */
    public AddCommand(Animal animal) {
        requireNonNull(animal);
        toAdd = animal;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasAnimal(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_ANIMAL);
        }

        model.addAnimal(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && toAdd.equals(((AddCommand) other).toAdd));
    }
}
