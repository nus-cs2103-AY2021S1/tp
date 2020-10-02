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
        Tokenizer tokenizer = new Tokenizer();
        String[] parsedArguments = tokenizer.tokenize(userInput,
                PrefixList.MODULE_NAME_PREFIX, PrefixList.MODULE_LINK_PREFIX, PrefixList.MODULE_INDEX_PREFIX,
                PrefixList.MODULE_NEWNAME_PREFIX, PrefixList.MODULE_VIEW_PREFIX, PrefixList.MODULE_DELETE_PREFIX);
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
