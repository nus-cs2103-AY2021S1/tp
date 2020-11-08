package seedu.address.logic.parser;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.UntagCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class UntagCommandParser implements Parser<UntagCommand> {

    @Override
    public UntagCommand parse(String args) throws ParseException {
        String trimArgs = args.trim();
        String[] argsArr = trimArgs.split(" +");
        ParserUtil.checkArgsLength(argsArr, UntagCommand.COMMAND_WORD, UntagCommand.MESSAGE_USAGE, 1);

        Index index = ParserUtil.parseIndex(argsArr[0], "Order Item Index");

        return new UntagCommand(index);
    }
}
