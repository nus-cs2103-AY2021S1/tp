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
        Tokenizer tokenizer = new Tokenizer(userInput,
                PrefixList.MODULE_INDEX_PREFIX, PrefixList.MODULE_LINK_PREFIX);
        ArgumentMap argumentMap = tokenizer.tokenize();

        if (!argumentMap.arePrefixesPresent(PrefixList.MODULE_INDEX_PREFIX,
                PrefixList.MODULE_LINK_PREFIX)) {
            String error = "Missing prefix arguments";
            throw new ParseException(error);
        }
        try {
            int moduleID = Integer.parseInt(argumentMap.getValue(PrefixList.MODULE_INDEX_PREFIX).get());
            String zoomLink = argumentMap.getValue(PrefixList.MODULE_LINK_PREFIX).get();
            return new AddZoomLinkCommand(moduleID, zoomLink);
        } catch (NumberFormatException ex) {
            String error = "No module ID was provided";
            throw new ParseException(error);
        }
    }
}
