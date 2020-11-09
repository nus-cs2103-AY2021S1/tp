package tp.acecs2103.logic.parser;

import tp.acecs2103.logic.commands.HelpCommand;
import tp.acecs2103.logic.parser.exceptions.ParseException;

public class HelpCommandParser implements Parser<HelpCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the HelpCommand
     * and returns an HelpCommand object for execution.
     * @throws ParseException if the user input is not in correct format
     */
    @Override
    public HelpCommand parse(String userInput) throws ParseException {
        return new HelpCommand();
    }
}
