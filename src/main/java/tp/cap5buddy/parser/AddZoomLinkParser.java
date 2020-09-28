package tp.cap5buddy.parser;

import tp.cap5buddy.commands.AddZoomLinkCommand;

public class AddZoomLinkParser extends Parser {

    public AddZoomLinkCommand parse(String userInput) {
        String[] parsedArguments = {"module name, zoom link"};
        return new AddZoomLinkCommand(parsedArguments);
    }
}
