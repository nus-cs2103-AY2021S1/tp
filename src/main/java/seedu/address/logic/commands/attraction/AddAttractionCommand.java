package seedu.address.logic.commands.attraction;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OPENING_HOURS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRICE_RANGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RATING;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_VISITED;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.attraction.Attraction;

/**
 * Adds an attraction to the attractions list in TrackPad.
 */
public class AddAttractionCommand extends Command {

    public static final String COMMAND_WORD = "add-attraction";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an attraction to TrackPad. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_LOCATION + "LOCATION "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_DESCRIPTION + "DESCRIPTION] "
            + "[" + PREFIX_OPENING_HOURS + "OPENING HOURS] "
            + "[" + PREFIX_PRICE_RANGE + "PRICE RANGE] "
            + "[" + PREFIX_RATING + "RATING] "
            + "[" + PREFIX_VISITED + "VISITED] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "Singapore Zoo "
            + PREFIX_LOCATION + "Singapore, Singapore "
            + PREFIX_PHONE + "62693411 "
            + PREFIX_EMAIL + "sgzoo@example.com "
            + PREFIX_ADDRESS + "80 Mandai Lake Rd, 729826 "
            + PREFIX_DESCRIPTION + "Home to over 2,800 animals. "
            + PREFIX_OPENING_HOURS + "0830-1800 "
            + PREFIX_PRICE_RANGE + "MEDIUM "
            + PREFIX_RATING + "4.8 "
            + PREFIX_VISITED + "TRUE "
            + PREFIX_TAG + "animal.";

    public static final String MESSAGE_SUCCESS = "New attraction added: %1$s.";
    public static final String MESSAGE_DUPLICATE_ATTRACTION = "This attraction already exists in TrackPad.";

    private final Attraction toAdd;

    /**
     * Creates an AddAttractionCommand to add the specified {@code Attraction}
     */
    public AddAttractionCommand(Attraction attraction) {
        requireNonNull(attraction);
        toAdd = attraction;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasAttraction(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_ATTRACTION);
        }

        model.addAttraction(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddAttractionCommand // instanceof handles nulls
                && toAdd.equals(((AddAttractionCommand) other).toAdd));
    }
}
