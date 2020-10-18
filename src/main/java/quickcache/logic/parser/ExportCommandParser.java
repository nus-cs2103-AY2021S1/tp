package quickcache.logic.parser;

import static java.util.Objects.requireNonNull;

import quickcache.commons.core.Messages;
import quickcache.logic.commands.ExportCommand;
import quickcache.logic.parser.exceptions.ParseException;

public class ExportCommandParser implements Parser<ExportCommand> {

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
        return new ExportCommand(fileName);
    }
}
