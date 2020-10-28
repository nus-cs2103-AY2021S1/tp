package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteTutorialGroupCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class DeleteTutorialGroupCommandParser implements Parser<DeleteTutorialGroupCommand> {
    @Override
    public DeleteTutorialGroupCommand parse(String userInput) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(userInput);
            return new DeleteTutorialGroupCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteTutorialGroupCommand.MESSAGE_USAGE), pe);
        }
    }
}
