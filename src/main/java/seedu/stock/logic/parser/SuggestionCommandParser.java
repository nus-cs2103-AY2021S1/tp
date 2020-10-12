package seedu.stock.logic.parser;

import seedu.stock.logic.commands.SuggestionCommand;
import seedu.stock.logic.parser.exceptions.ParseException;

public class SuggestionCommandParser implements Parser<SuggestionCommand> {

    @Override
    public SuggestionCommand parse(String userInput) throws ParseException {
        return new SuggestionCommand();
    }
}
