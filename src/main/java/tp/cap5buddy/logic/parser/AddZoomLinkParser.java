package tp.cap5buddy.logic.parser;

import tp.cap5buddy.logic.commands.AddZoomLinkCommand;
import tp.cap5buddy.logic.parser.exception.ParseException;

public class AddZoomLinkParser extends Parser {

    /**
     * Parses a user input for a command to add a zoom link.
     *
     * @param userInput
     *
     * @return
     */
    public AddZoomLinkCommand parse(String userInput) throws ParseException {
        Tokenizer token = new Tokenizer(userInput);
        String[] parsedArguments = token.getWords();
        try {
            int moduleID = Integer.parseInt(parsedArguments[2]);
            String zoomLink = parsedArguments[1];
            return new AddZoomLinkCommand(moduleID, zoomLink);
        } catch (NumberFormatException ex) {
            String error = "No module ID was provided";
            throw new ParseException(error);
        }
    }
}
