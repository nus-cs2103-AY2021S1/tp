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
        Type editedType = ParserUtilTodoList.parseType(parsedArguments[1]);
        Description editedDescription = ParserUtilTodoList.parseDescription(parsedArguments[2]);
        Date editedDate = ParserUtilTodoList.parseDate(parsedArguments[3]);
        Priority editedPriority = ParserUtilTodoList.parsePriority(parsedArguments[4]);

        EditTaskDescriptor editTaskDescriptor = new EditTaskDescriptor();
        editTaskDescriptor.setType(editedType);
        editTaskDescriptor.setDescription(editedDescription);
        editTaskDescriptor.setDate(editedDate);
        editTaskDescriptor.setPriority(editedPriority);

        return new EditTaskCommand(index, editTaskDescriptor);
    }
}
