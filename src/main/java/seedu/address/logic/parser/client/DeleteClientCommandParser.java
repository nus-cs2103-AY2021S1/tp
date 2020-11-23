package seedu.address.logic.parser.client;

import seedu.address.logic.commands.client.DeleteClientCommand;
import seedu.address.logic.parser.DeleteCommandParser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.client.ClientId;

/**
 * Parses input arguments and creates a new DeleteClientCommand object
 */
public class DeleteClientCommandParser extends DeleteCommandParser<DeleteClientCommand> {

    @Override
    public DeleteClientCommand getCommand(String args) throws ParseException {
        ClientId id = ParserUtil.parseClientId(args);
        return new DeleteClientCommand(id);
    }

    @Override
    public String getMessageUsage() {
        return DeleteClientCommand.MESSAGE_USAGE;
    }


}
