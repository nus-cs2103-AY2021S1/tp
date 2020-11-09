package tp.acecs2103.logic.parser;

import tp.acecs2103.commons.core.Messages;
import tp.acecs2103.logic.commands.UndoneCommand;
import tp.acecs2103.logic.parser.exceptions.ParseException;
import tp.acecs2103.model.task.Index;

public class UndoneCommandParser implements Parser<UndoneCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the UndoneCommand
     * and returns an UndoneCommand object for execution.
     * @throws ParseException if the user input is not in correct format
     */
    public UndoneCommand parse(String args) throws ParseException {
        try {
            String indexParsed = ParserUtil.parseIndex(args);
            Index index = new Index(indexParsed);
            return new UndoneCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, UndoneCommand.MESSAGE_USAGE), pe);
        }
    }
}
