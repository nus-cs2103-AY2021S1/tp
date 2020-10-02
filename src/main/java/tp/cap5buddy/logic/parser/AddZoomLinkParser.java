package tp.cap5buddy.logic.parser;

import tp.cap5buddy.logic.commands.AddZoomLinkCommand;
import tp.cap5buddy.logic.parser.exception.ParseException;

public class AddZoomLinkParser extends Parser {

    /**
     * Parses a user input for a command to add a zoom link.
     *
     * @param userInput
     * @return
     */
    public AddZoomLinkCommand parse(String userInput) throws ParseException {
        Tokenizer tokenizer = new Tokenizer();
        String[] parsedArguments = tokenizer.tokenize(userInput,
                PrefixList.MODULE_NAME_PREFIX, PrefixList.MODULE_LINK_PREFIX, PrefixList.MO)
        return new AddZoomLinkCommand(parsedArguments);
    }
}
