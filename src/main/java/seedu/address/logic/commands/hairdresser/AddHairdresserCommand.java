package seedu.address.logic.commands.hairdresser;

import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SPECIALISATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TITLE;

import seedu.address.logic.commands.AddPersonCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.hairdresser.Hairdresser;

/**
 * Adds a hairdresser to the address book.
 */
public class AddHairdresserCommand extends AddPersonCommand<Hairdresser> {

    public static final String COMMAND_WORD = "add_hairdresser";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a hairdresser to the address book. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_GENDER + "GENDER "
            + PREFIX_TITLE + "TITLE "
            + "[" + PREFIX_SPECIALISATION + "SPECIALISATION]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_GENDER + "M "
            + PREFIX_TITLE + "Senior Stylist "
            + PREFIX_SPECIALISATION + "Perm "
            + PREFIX_SPECIALISATION + "Color";

    public static final String MESSAGE_SUCCESS = "New hairdresser added: %1$s";
    public static final String MESSAGE_DUPLICATE_HAIRDRESSER = "This hairdresser already exists in HairStyleX";
    private static final String MESSAGE_DUPLICATE_CLIENT = "This person already exists in HairStyleX, "
            + "and is a Client";


    /**
     * Creates an AddCommand to add the specified {@code Hairdresser}
     */
    public AddHairdresserCommand(Hairdresser hairdresser) {
        super(hairdresser);
    }


    @Override
    public void addToModel(Model model) throws CommandException {
        if (model.hasHairdresser(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_HAIRDRESSER);
        }
        if (model.hasPerson(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_CLIENT);
        }

        model.addHairdresser(toAdd);
    }

    @Override
    public String getSuccessMessage() {
        return MESSAGE_SUCCESS;
    }
}
