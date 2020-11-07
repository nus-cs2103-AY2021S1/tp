package seedu.stock.logic.commands;

import static seedu.stock.logic.commands.statisticsutil.GenerateStatisticsData.generateSourceStatisticsData;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.stock.commons.core.LogsCenter;
import seedu.stock.model.Model;

public class SourceStatisticsCommand extends StatisticsCommand {

    public static final String STATISTICS_TYPE = "source";
    public static final String MESSAGE_SUCCESS = "Opened statistics for source window\n"
                    + "WARNING: Diagram shown may not be optimal for certain quantities";

    private static final Logger logger = LogsCenter.getLogger(SourceStatisticsCommand.class);

    @Override
    public CommandResult execute(Model model) {
        logger.log(Level.INFO, "Starting to generate statistics");
        Map<String, Integer> sourceData = generateSourceStatisticsData(model);

        //array of size 1, index 0 is statistics type
        String[] otherStatisticsDetails = {"source"};
        logger.log(Level.INFO, "Valid input.");
        return new CommandResult(MESSAGE_SUCCESS, sourceData, false, false,
                null, true, otherStatisticsDetails, false, false);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SourceStatisticsCommand);
    }
}
