package quickcache.logic.parser;

import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.nio.file.Paths;

import quickcache.commons.core.Messages;
import quickcache.logic.commands.ExportCommand;
import quickcache.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ExportCommand object.
 */
public class ExportCommandParser implements Parser<ExportCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ExportCommand
     * and returns an ExportCommand object for execution.
     *
     * @throws ParseException If the given user input does not conform to the expected format.
     */
    @Override
    public ExportCommand parse(String args) throws ParseException {
        requireNonNull(args);
        String fileName;
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args);
        try {
            fileName = ParserUtil.parseFileName(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                ExportCommand.MESSAGE_USAGE), pe);
        }
        Path path = Paths.get("export", fileName);
        return new ExportCommand(path);
    }
}
