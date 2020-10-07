package tp.cap5buddy.logic;

import tp.cap5buddy.logic.commands.CommandResult;
import tp.cap5buddy.logic.commands.exception.CommandException;
import tp.cap5buddy.logic.parser.exception.ParseException;

public interface Logic {

    CommandResult execute(String userInput) throws ParseException, CommandException;
}
