package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PATH;

import java.io.File;
import java.nio.file.Path;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

public class ArchiveCommand extends Command {

    public static final String COMMAND_WORD = "archive";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a person to the address book. "
            + "Parameters: "
            + PREFIX_PATH + "File Location"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_PATH + "data\\archive";

    public static final String MESSAGE_SUCCESS = "File has been archived to the specified location";

    private final Path specifiedLocation;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public ArchiveCommand(Path fileLocation) {
        requireNonNull(fileLocation);
        this.specifiedLocation = fileLocation;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        File specifiedFile = specifiedLocation.toFile();

        if (specifiedFile.exists()) {
            throw new CommandException("A File with same name exists at the specified location. Please specify another"
                + "location or delete the file with the same name ");
        }
        if (! specifiedFile.canWrite()) {
            throw new CommandException("Right to write that specified location is not granted."
            + "Please specify another location");
        }

        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ArchiveCommand // instanceof handles nulls
                && specifiedLocation.equals(((ArchiveCommand) other).specifiedLocation));
    }
}
