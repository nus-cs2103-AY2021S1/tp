package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PATH;

import java.nio.file.Path;
import java.nio.file.Paths;

import seedu.address.logic.commands.ArchiveCommand;
import seedu.address.logic.parser.exceptions.ParseException;
/**
 * Parses input arguments and creates a new EditCommand object
 */
public class ArchiveCommandParser implements Parser<ArchiveCommand> {


    /**
     * Parses the given {@code String} of arguments in the context of the ArchiveCommand
     * and returns an ArchiveCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ArchiveCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_PATH);
        String trimArgs = args.trim();
        if (!trimArgs.endsWith(".json")) {
            throw new ParseException("Specified Location should ends with '.json'."
                    + "Example: parentDirectory/filename.json");
        }
        Path specifiedLocation = Paths.get(trimArgs);


        return new ArchiveCommand(specifiedLocation);
    }

}
