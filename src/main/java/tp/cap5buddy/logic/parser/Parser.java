package tp.cap5buddy.logic.parser;

import tp.cap5buddy.logic.commands.Command;
import tp.cap5buddy.logic.parser.exception.ParseException;

/**
 * Represents the super class of all Parser commands.
 */
public abstract class Parser {

    public abstract Command parse(String userInput) throws ParseException;

}

