package tp.cap5buddy.logic;

import tp.cap5buddy.logic.commands.ResultCommand;
import tp.cap5buddy.logic.parser.exception.ParseException;

public interface Logic {

    ResultCommand execute(String userInput) throws ParseException;
}
