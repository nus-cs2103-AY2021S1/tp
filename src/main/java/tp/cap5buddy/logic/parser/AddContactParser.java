package tp.cap5buddy.logic.parser;

import tp.cap5buddy.logic.commands.AddContactCommand;
import tp.cap5buddy.logic.parser.exception.ParseException;

public class AddContactParser extends Parser {

    public AddContactCommand parse(String userInput) throws ParseException {
        Tokenizer tokenizer = new Tokenizer();
        String[] parsedArguments = tokenizer.tokenize(userInput,
                PrefixList.CONTACT_NAME_PREFIX, PrefixList.CONTACT_EMAIL_PREFIX);
        try {
            String contactName = parsedArguments[0];
            String contactEmail = parsedArguments[1];
            return new AddContactCommand(contactName, contactEmail);
        } catch (ArrayIndexOutOfBoundsException ex) {
            String error = "Missing arguments";
            throw new ParseException(error);
        }
    }
}
