package tp.cap5buddy.logic.parser;

import tp.cap5buddy.logic.commands.DeleteContactCommand;
import tp.cap5buddy.logic.parser.exception.ParseException;

public class DeleteContactParser extends Parser {

    /**
     * Parses the user input to retrieve the necessary command arguments for the execution of a DeleteContactCommand.
     *
     * @param userInput User input.
     * @return DeleteContactCommand to be executed.
     * @throws ParseException If the user input could not be successfully parsed.
     */
    public DeleteContactCommand parse(String userInput) throws ParseException {
        Tokenizer tokenizer = new Tokenizer(userInput,
                PrefixList.CONTACT_INDEX_PREFIX);
        String[] parsedArguments = tokenizer.tokenize();
        try {
            int contactID = Integer.parseInt(parsedArguments[0]);
            return new DeleteContactCommand(contactID);
        } catch (NumberFormatException ex) {
            String error = "Invalid contact id";
            throw new ParseException(error);
        } catch (ArrayIndexOutOfBoundsException ex) {
            String error = "Missing arguments";
            throw new ParseException(error);
        }

    }
}
