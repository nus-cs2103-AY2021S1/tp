
package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Adds a person to the address book.
 */
public abstract class AddPersonCommand<T extends Person> extends AddCommand {

    protected final T toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public AddPersonCommand(T person) {
        requireNonNull(person);
        toAdd = person;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        addToModel(model);

        return new CommandResult(String.format(getSuccessMessage(), toAdd));
    }



    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddPersonCommand // instanceof handles nulls
                && toAdd.equals(((AddPersonCommand) other).toAdd));
    }

    public abstract void addToModel(Model model) throws CommandException;

    public abstract String getSuccessMessage();

}



