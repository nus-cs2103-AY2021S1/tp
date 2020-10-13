package tp.cap5buddy.logic.parser;

import tp.cap5buddy.logic.commands.AddContactCommand;
import tp.cap5buddy.logic.parser.exception.ParseException;

public class AddContactParser extends Parser {

    /**
     * Parses a user input for an AddContactCommand.
     *
     * @param userInput User input.
     * @return AddContactCommand.
     * @throws ParseException If the user input could not be parsed.
     */
    public AddContactCommand parse(String userInput) throws ParseException {
        Tokenizer tokenizer = new Tokenizer(userInput, PrefixList.CONTACT_NAME_PREFIX, PrefixList.CONTACT_EMAIL_PREFIX);
        ArgumentMap argumentMap = tokenizer.tokenize();
        if (!argumentMap.arePrefixesPresent(PrefixList.CONTACT_NAME_PREFIX)) {
            String error = "Missing prefix arguments";
            throw new ParseException(error);
        }
        String contactName = argumentMap.getValue(PrefixList.CONTACT_NAME_PREFIX).get();
        String contactEmail = argumentMap.getValue(PrefixList.CONTACT_EMAIL_PREFIX).orElse("");
        return new AddContactCommand(contactName, contactEmail);
    }
}
