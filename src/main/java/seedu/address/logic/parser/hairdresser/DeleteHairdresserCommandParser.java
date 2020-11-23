package seedu.address.logic.parser.hairdresser;

import seedu.address.logic.commands.hairdresser.DeleteHairdresserCommand;
import seedu.address.logic.parser.DeleteCommandParser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.hairdresser.HairdresserId;

public class DeleteHairdresserCommandParser extends DeleteCommandParser<DeleteHairdresserCommand> {

    @Override
    public DeleteHairdresserCommand getCommand(String args) throws ParseException {
        HairdresserId id = ParserUtil.parseHairdresserId(args);
        return new DeleteHairdresserCommand(id);
    }

    @Override
    public String getMessageUsage() {
        return DeleteHairdresserCommand.MESSAGE_USAGE;
    }

}
