package seedu.address.logic;

import seedu.address.logic.parser.FeatureParser;
import seedu.address.logic.parser.ModuleListParser;
import seedu.address.logic.parser.TodoListParser;
import seedu.address.logic.parser.contactlistparsers.ContactListParser;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Represents the manager in charge of overseeing all the medium feature parsers.
 */
public class ParserManager {
    private final ModuleListParser moduleListParser;
    private final ContactListParser contactListParser;
    private final TodoListParser todoListParser;

    /**
     * Creates a Container the holds all the medium parsers.
     * @param moduleListParser parses module related commands.
     * @param todoListParser parses task related commands.
     * @param contactListParser parses contact related commands.
     */
    public ParserManager(ModuleListParser moduleListParser,
                         TodoListParser todoListParser,
                         ContactListParser contactListParser) {
        this.contactListParser = contactListParser;
        this.todoListParser = todoListParser;
        this.moduleListParser = moduleListParser;
    }

    /**
     * Selects which medium parser to be used based on the command word.
     * @param commandWord the command word of the user input.
     * @return FeatureParser a medium parser.
     * @throws ParseException when the command word is invalid.
     */
    public FeatureParser select(String commandWord) throws ParseException {
        if (commandWord.contains("module") || commandWord.contains("undo") || commandWord.contains("redo")
                || commandWord.contains("cap")) {
            return this.moduleListParser;
        } else if (commandWord.contains("task")) {
            return this.todoListParser;
        } else if (commandWord.contains("contact")) {
            return this.contactListParser;
        } else {
            throw new ParseException("Does not recognise command type!");
        }
    }
}
