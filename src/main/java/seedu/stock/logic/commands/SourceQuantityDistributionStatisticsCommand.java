package seedu.stock.logic.commands;

import static seedu.stock.commons.core.Messages.MESSAGE_SOURCE_COMPANY_NOT_FOUND;
import static seedu.stock.logic.commands.statisticsutil.GenerateStatisticsData
        .generateSourceQuantityDistributionStatisticsData;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.stock.commons.core.LogsCenter;
import seedu.stock.logic.commands.exceptions.SourceCompanyNotFoundException;
import seedu.stock.model.Model;


public class SourceQuantityDistributionStatisticsCommand extends StatisticsCommand {

    public static final String STATISTICS_TYPE = "source-qd-";
    public static final String MESSAGE_SUCCESS = "Opened statistics for source-qd window\n"
            + "WARNING: Diagram shown may not be optimal for certain quantities";

    private static final Logger logger = LogsCenter.getLogger(SourceQuantityDistributionStatisticsCommand.class);

    private final String targetSource;

    /**
     * Constructor for new SourceQuantityDistributionStatisticsCommand object.
     *
     * @param targetSource The target source company to show statistics for.
     */
    public SourceQuantityDistributionStatisticsCommand(String targetSource) {
        assert(targetSource.length() > 0);
        this.targetSource = targetSource;
    }

    @Override
    public CommandResult execute(Model model) throws SourceCompanyNotFoundException {
        logger.log(Level.INFO, "Starting to generate statistics");
        Map<String, Integer> nameQuantityTable = generateSourceQuantityDistributionStatisticsData(model, targetSource);

        if (nameQuantityTable.size() == 0) {
            logger.log(Level.WARNING, "Valid input but source company not found.");
            throw new SourceCompanyNotFoundException(MESSAGE_SOURCE_COMPANY_NOT_FOUND);
        }

        //array of size 2, index 0 is statistics type, index 1 is target source company
        String[] otherStatisticsDetails = {"source-qd-", targetSource};
        logger.log(Level.INFO, "Valid input with found source company.");
        return new CommandResult(MESSAGE_SUCCESS, nameQuantityTable, false,
                false, null, true, otherStatisticsDetails, false, false);
    }

    public String getTargetSource() {
        return targetSource;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SourceQuantityDistributionStatisticsCommand // instanceof handles nulls
                && targetSource.equals(((
                        SourceQuantityDistributionStatisticsCommand) other).targetSource)); // state check
    }
}
