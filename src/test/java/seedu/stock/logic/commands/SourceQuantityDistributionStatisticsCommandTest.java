package seedu.stock.logic.commands;

import static seedu.stock.commons.core.Messages.MESSAGE_SOURCE_COMPANY_NOT_FOUND;
import static seedu.stock.logic.commands.CommandTestUtil.assertCommandFailureForStatistics;
import static seedu.stock.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.stock.testutil.TypicalSerialNumberSets.getTypicalSerialNumberSetsBook;
import static seedu.stock.testutil.TypicalStocks.RESERVED_NON_EXISTENCE_SOURCE;
import static seedu.stock.testutil.TypicalStocks.getTypicalStockBook;

import org.junit.jupiter.api.Test;

import seedu.stock.model.Model;
import seedu.stock.model.ModelManager;
import seedu.stock.model.UserPrefs;

public class SourceQuantityDistributionStatisticsCommandTest {

    private Model model = new ModelManager(getTypicalStockBook(), new UserPrefs(),
            getTypicalSerialNumberSetsBook());

    @Test
    public void executeValidSourceQuantityDistributionStatisticsCommand_success() {
        String existentSource = model.getStockBook().getStockList().get(0).getSource().value;

        SourceQuantityDistributionStatisticsCommand sourceQuantityDistributionStatisticsCommand =
                new SourceQuantityDistributionStatisticsCommand(existentSource);

        String expectedMessage = SourceQuantityDistributionStatisticsCommand.MESSAGE_SUCCESS;

        Model expectedModel = model;

        assertCommandSuccess(sourceQuantityDistributionStatisticsCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void executeSourceCompanyNotFound_throwsSourceCompanyNotFoundException() {
        boolean isReservedNonExistentSourcePresent = model.getStockBook().getStockList().stream().anyMatch(stock ->
                stock.getSource().value.equals(RESERVED_NON_EXISTENCE_SOURCE));

        //additional layer to ensure that the reserved non-existent source is non-existent in the stock book
        String nonExistentSource = isReservedNonExistentSourcePresent ? "" : RESERVED_NON_EXISTENCE_SOURCE;

        SourceQuantityDistributionStatisticsCommand sourceQuantityDistributionStatisticsCommand =
                new SourceQuantityDistributionStatisticsCommand(nonExistentSource);

        String expectedMessage = MESSAGE_SOURCE_COMPANY_NOT_FOUND;

        assertCommandFailureForStatistics(sourceQuantityDistributionStatisticsCommand, model, expectedMessage);
    }


}
