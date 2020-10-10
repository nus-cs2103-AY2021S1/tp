package tp.cap5buddy.logic.parser;

import tp.cap5buddy.logic.commands.AddTaskCommand;
import tp.cap5buddy.logic.commands.Command;
import tp.cap5buddy.logic.parser.exception.ParseException;
import tp.cap5buddy.todolist.Date;
import tp.cap5buddy.todolist.Description;
import tp.cap5buddy.todolist.ParserUtilTodoList;
import tp.cap5buddy.todolist.Priority;
import tp.cap5buddy.todolist.Type;

public class AddTaskParser extends Parser {
    @Override
    public Command parse(String userInput) throws ParseException {
        Tokenizer token = new Tokenizer(userInput, PrefixList.MODULE_NAME_PREFIX, PrefixList.TYPE_PREFIX,
                PrefixList.MODULE_DELETE_PREFIX, PrefixList.PRIORITY_PREFIX);
        String[] parsedArguments = token.tokenize();

        Description description = parseDescription(parsedArguments[0]);
        Type type = ParserUtilTodoList.parseType(parsedArguments[1]);
        Date date = ParserUtilTodoList.parseDate(parsedArguments[2]);
        Priority priority = ParserUtilTodoList.parsePriority(parsedArguments[3]);

        return new AddTaskCommand(type, description, date, priority);
    }

    /**
     * Parses description into a Description object.
     *
     * @param input user input.
     * @return a Description object.
     * @throws ParseException if description is invalid.
     */
    public Description parseDescription(String input) throws ParseException {
        if (!Description.isValidDescription(input)) {
            throw new ParseException("Task description is invalid");
        }
        return new Description(input);
    }
}
