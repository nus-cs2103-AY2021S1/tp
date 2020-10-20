package seedu.address.logic;

import seedu.address.logic.parser.FeatureParser;
import seedu.address.logic.parser.ModuleListParser;
import seedu.address.logic.parser.TodoListParser;
import seedu.address.logic.parser.contactlistparsers.ContactListParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ContactList;
import seedu.address.model.contact.Contact;

public class ParserManager {
    private final ModuleListParser moduleListParser;
    private final ContactListParser contactListParser;
    private final TodoListParser todoListParser;

    public ParserManager(ModuleListParser moduleListParser,
                         TodoListParser todoListParser,
                         ContactListParser contactListParser) {
        this.contactListParser = contactListParser;
        this.todoListParser = todoListParser;
        this.moduleListParser = moduleListParser;
    }

    public FeatureParser select(String commandWord) throws ParseException {
        if (commandWord.contains("module")) {
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
