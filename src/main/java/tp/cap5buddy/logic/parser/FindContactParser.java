package tp.cap5buddy.logic.parser;

import tp.cap5buddy.logic.commands.Command;
import tp.cap5buddy.logic.commands.FindContactCommand;
import tp.cap5buddy.logic.parser.exception.ParseException;

/**
 * Parsers user input relating to a command to find a contact.
 */
public class FindContactParser extends Parser {

    /**
     * Parses the given {@code String} of arguments in the context of the FindContactCommand
     * and returns a FindContactCommand object for execution.
     *
     * @param userInput User input.
     * @throws ParseException If the user input does not conform the expected format.
     */
    @Override
    public Command parse(String userInput) throws ParseException {
        // String trimmedInput = userInput.trim();
        Tokenizer tokenizer = new Tokenizer(userInput, PrefixList.SEARCH_KEYWORD_PREFIX);
        // current implementation only allows one keyword,
        // this will be expanded to allow multiple keywords
        String[] parsedArguments = tokenizer.tokenize();
        String keyword = parsedArguments[0];
        if (keyword.isBlank() || keyword == null) {
            String error = "Search keyword not found";
            throw new ParseException(error);
        }
        return new FindContactCommand(keyword);
    }
}
