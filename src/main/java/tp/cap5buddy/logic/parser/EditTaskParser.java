package tp.cap5buddy.logic.parser;

import tp.cap5buddy.logic.commands.Command;
import tp.cap5buddy.logic.commands.EditTaskCommand;
import tp.cap5buddy.logic.commands.EditTaskCommand.EditTaskDescriptor;
import tp.cap5buddy.logic.parser.exception.ParseException;
import tp.cap5buddy.todolist.Date;
import tp.cap5buddy.todolist.Description;
import tp.cap5buddy.todolist.ParserUtilTodoList;
import tp.cap5buddy.todolist.Priority;
import tp.cap5buddy.todolist.Type;

public class EditTaskParser extends Parser {
    @Override
    public Command parse(String userInput) throws ParseException {
        Tokenizer token = new Tokenizer(userInput, PrefixList.MODULE_INDEX_PREFIX, PrefixList.TYPE_PREFIX,
            PrefixList.MODULE_NAME_PREFIX, PrefixList.MODULE_DELETE_PREFIX, PrefixList.PRIORITY_PREFIX);
        String[] parsedArguments = token.tokenize();

        int index = ParserUtilTodoList.parseIndex(parsedArguments[0]);

        EditTaskDescriptor editTaskDescriptor = new EditTaskDescriptor();

        // Arguments from 0 to (n-1) must be ordered, i.e cannot have empty field in the middle.
        if (parsedArguments[1] != null) {
            Type editedType = ParserUtilTodoList.parseType(parsedArguments[1]);
            editTaskDescriptor.setType(editedType);
        }
        if (parsedArguments[2] != null) {
            Description editedDescription = ParserUtilTodoList.parseDescription(parsedArguments[2]);
            editTaskDescriptor.setDescription(editedDescription);
        }
        if (parsedArguments[3] != null) {
            Date editedDate = ParserUtilTodoList.parseDate(parsedArguments[3]);
            editTaskDescriptor.setDate(editedDate);
        }
        if (parsedArguments[4] != null) {
            Priority editedPriority = ParserUtilTodoList.parsePriority(parsedArguments[4]);
            editTaskDescriptor.setPriority(editedPriority);
        }
        return new EditTaskCommand(index, editTaskDescriptor);
    }
}
