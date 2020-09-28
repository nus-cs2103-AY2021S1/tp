package tp.cap5buddy.parser;

import tp.cap5buddy.commands.Command;

/**
 * Represents the super class of all Parser commands.
 */
public abstract class Parser {

    public abstract Command parse(String userInput);

    // public abstract ResultCommand execute();
}

