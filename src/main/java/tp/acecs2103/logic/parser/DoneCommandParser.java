package tp.acecs2103.logic.parser;

import tp.acecs2103.commons.core.Messages;
import tp.acecs2103.logic.commands.DoneCommand;
import tp.acecs2103.logic.parser.exceptions.ParseException;
import tp.acecs2103.model.task.Index;

public class DoneCommandParser implements Parser<DoneCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DoneCommand
     * and returns an DoneCommand object for execution.
     * @throws ParseException if the user input is not in correct format
     */
    public DoneCommand parse(String args) throws ParseException {
        try {
            String indexParsed = ParserUtil.parseIndex(args);
            Index index = new Index(indexParsed);
            return new DoneCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, DoneCommand.MESSAGE_USAGE), pe);
        }
    }
}
