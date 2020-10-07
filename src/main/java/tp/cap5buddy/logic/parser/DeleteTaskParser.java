package tp.cap5buddy.logic.parser;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import tp.cap5buddy.logic.commands.Command;
import tp.cap5buddy.logic.commands.DeleteTaskCommand;
import tp.cap5buddy.logic.parser.exception.ParseException;
import tp.cap5buddy.todolist.ParserUtilTodoList;

public class DeleteTaskParser extends Parser {
    @Override
    public Command parse(String userInput) throws ParseException {
        Tokenizer token = new Tokenizer(userInput, PrefixList.MODULE_INDEX_PREFIX);
        String[] parsedArguments = token.tokenize();
        int indexToRemove = ParserUtilTodoList.parseIndex(parsedArguments[0]);

        return new DeleteTaskCommand(indexToRemove);
    }
}
