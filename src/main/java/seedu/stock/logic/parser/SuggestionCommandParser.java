package seedu.stock.logic.parser;

import static seedu.stock.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_INCREMENT_QUANTITY;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_NEW_QUANTITY;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_QUANTITY;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_SERIAL_NUMBER;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_SOURCE;

import java.util.List;

import seedu.stock.commons.util.SuggestionUtil;
import seedu.stock.logic.commands.CommandWords;
import seedu.stock.logic.commands.SuggestionCommand;
import seedu.stock.logic.parser.exceptions.ParseException;

public class SuggestionCommandParser implements Parser<SuggestionCommand> {

    private String faultyCommandWord;

    public SuggestionCommandParser(String faultyCommandWord) {
        this.faultyCommandWord = faultyCommandWord;
    }

    @Override
    public SuggestionCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(
                        args, PREFIX_SERIAL_NUMBER, PREFIX_INCREMENT_QUANTITY, PREFIX_NEW_QUANTITY,
                        PREFIX_NAME, PREFIX_SOURCE, PREFIX_LOCATION, PREFIX_QUANTITY
                );
        List<String> allCommandWords = CommandWords.getAllCommandWords();
        StringBuilder toBeDisplayed = new StringBuilder();

        String suggestedCommandWord = faultyCommandWord;
        int bestEditDistanceSoFar = Integer.MAX_VALUE;
        for (String commandWord: allCommandWords) {
            int currentEditDistance = SuggestionUtil.minimumEditDistance(faultyCommandWord, commandWord);
            if (currentEditDistance < bestEditDistanceSoFar) {
                bestEditDistanceSoFar = currentEditDistance;
                suggestedCommandWord = commandWord;
            }
        }

        if (suggestedCommandWord.equals(faultyCommandWord)) {
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
        toBeDisplayed.append(suggestedCommandWord);

        return new SuggestionCommand(toBeDisplayed.toString());
    }
}
