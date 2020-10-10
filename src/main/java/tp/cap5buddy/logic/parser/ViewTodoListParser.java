package tp.cap5buddy.logic.parser;

import tp.cap5buddy.logic.commands.Command;
import tp.cap5buddy.logic.commands.ViewTodoListCommand;

public class ViewTodoListParser extends Parser {
    @Override
    public Command parse(String input) {
        return new ViewTodoListCommand();
    }
}
