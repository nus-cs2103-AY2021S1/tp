package tp.cap5buddy.parser;

import tp.cap5buddy.commands.AddZoomLinkCommand;

public class AddZoomLinkParser extends Parser {

    /**
     * Parses a user input for a command to add a zoom link.
     *
     * @param userInput
     * @return
     */
    public AddZoomLinkCommand parse(String userInput) {
        String[] parsedArguments = {"module name, zoom link"};
        return new AddZoomLinkCommand(parsedArguments);
    }
}
