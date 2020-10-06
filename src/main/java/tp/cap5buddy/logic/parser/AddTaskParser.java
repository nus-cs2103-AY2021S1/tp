package tp.cap5buddy.logic.parser;

import tp.cap5buddy.logic.commands.AddTaskCommand;
import tp.cap5buddy.logic.commands.Command;
import tp.cap5buddy.logic.parser.exception.ParseException;
import tp.cap5buddy.todolist.Date;
import tp.cap5buddy.todolist.Description;
import tp.cap5buddy.todolist.Priority;
import tp.cap5buddy.todolist.Type;

public class AddTaskParser extends Parser {
    @Override
    public Command parse(String userInput) throws ParseException {
        Tokenizer token = new Tokenizer(userInput, PrefixList.MODULE_NAME_PREFIX, PrefixList.TYPE_PREFIX,
                PrefixList.MODULE_DELETE_PREFIX, PrefixList.PRIORITY_PREFIX);
        String[] parsedArguments = token.tokenize();

        Description description = parseDescription(parsedArguments[0]);
        Type type = parseType(parsedArguments[1]);
        Date date = parseDate(parsedArguments[2]);
        Priority priority = parsePriority(parsedArguments[3]);

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

    /**
     * Parses type.
     *
     * @param input user input.
     * @return a Type object.
     * @throws ParseException if the type is invalid.
     */
    public Type parseType(String input) throws ParseException {
        String inputAllUpperCase = input.toUpperCase();
        switch(inputAllUpperCase) {
        case("ASSIGNMENT"):
            return Type.ASSIGNMENT;
        case("LAB"):
            return Type.LAB;
        case("TUTORIAL"):
            return Type.TUTORIAL;
        case("PROJECT"):
            return Type.PROJECT;
        case("STUDY"):
            return Type.STUDY;
        case("DAILY"):
            return Type.DAILY;
        default:
            throw new ParseException("Task type is invalid");
        }
    }

    /**
     * Parses date into a Date object.
     *
     * @param input user input.
     * @return a Date object.
     * @throws ParseException if the date is invalid.
     */
    public Date parseDate(String input) throws ParseException {
        if (!Date.isValidDate(input)) {
            throw new ParseException("Task date is invalid");
        }
        return new Date(input);
    }

    /**
     * Parses priority.
     *
     * @param input user input.
     * @return a Priority object.
     * @throws ParseException if the priority is invalid.
     */
    public Priority parsePriority(String input) throws ParseException {
        String inputAllUpperCase = input.toUpperCase();
        switch(inputAllUpperCase) {
        case("HIGHEST"):
            return Priority.HIGHEST;
        case("HIGH"):
            return Priority.HIGH;
        case("NORMAL"):
            return Priority.NORMAL;
        case("LOW"):
            return Priority.LOW;
        default:
            throw new ParseException("Task priority is invalid");
        }
    }
}
