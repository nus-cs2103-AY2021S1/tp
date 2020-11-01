package seedu.stock.logic.commands;

import static seedu.stock.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.stock.testutil.TypicalSerialNumberSets.getTypicalSerialNumberSetsBook;
import static seedu.stock.testutil.TypicalStocks.getTypicalStockBook;

import org.junit.jupiter.api.Test;

import seedu.stock.model.Model;
import seedu.stock.model.ModelManager;
import seedu.stock.model.SerialNumberSetsBook;
import seedu.stock.model.UserPrefs;

public class SuggestionCommandTest {

    private SerialNumberSetsBook serialNumbers = getTypicalSerialNumberSetsBook();

    @Test
    public void execute() {
        SuggestionCommand suggestionCommand = new SuggestionCommand("suggestion message");
        String expectedMessage = "suggestion message";
        Model expectedModel = new ModelManager(getTypicalStockBook(), new UserPrefs(), serialNumbers);

        assertCommandFailure(suggestionCommand, expectedModel, expectedMessage);
    }
}
