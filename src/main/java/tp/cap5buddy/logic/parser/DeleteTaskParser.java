package tp.cap5buddy.logic.parser;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import tp.cap5buddy.logic.commands.Command;
import tp.cap5buddy.logic.commands.DeleteTaskCommand;
import tp.cap5buddy.logic.parser.exception.ParseException;

public class DeleteTaskParser extends Parser {
    @Override
    public Command parse(String userInput) throws ParseException {
        Tokenizer token = new Tokenizer(userInput, PrefixList.MODULE_INDEX_PREFIX);
        String[] parsedArguments = token.tokenize();
        Index indexToRemove = parseIndex(parsedArguments[0]);

        return new DeleteTaskCommand(indexToRemove);
    }

    /**
     * Parses description into a Description object.
     *
     * @param input user input.
     * @return a Description object.
     * @throws ParseException if description is invalid.
     */
    public Index parseIndex(String input) throws ParseException {
        if (!StringUtil.isNonZeroUnsignedInteger(input)) {
            throw new ParseException("Index is invalid");
        }
        int index = Integer.parseInt(input);
        return Index.fromOneBased(index);
    }

}
