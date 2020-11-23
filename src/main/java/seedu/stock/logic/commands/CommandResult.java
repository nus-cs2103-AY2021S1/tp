package seedu.stock.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

import seedu.stock.model.stock.Stock;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    private final String feedbackToUser;

    /** Help information should be shown to the user. */
    private final boolean showHelp;

    /** Notes of the stock should be shown to user. */
    private final boolean showStockView;

    /** Stock to show notes. */
    private final Stock stockToView;

    /** Statistics information should be shown to the user. */
    private final boolean showStatistics;

    /** Statistics data to be shown to the user, if any. */
    private final Map<String, Integer> statisticsData;

    /** Other statistics data details to be shown to the user, if any. */
    private final String[] otherStatisticsDetails;

    /** Tab is to be switched is true. */
    private final boolean isSwitchTab;

    /** The application should exit. */
    private final boolean exit;


    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, Map<String, Integer> statisticsData,
                         boolean showHelp, boolean showStockView, Stock stockToView,
                         boolean showStatistics, String[] otherStatisticsDetails,
                         boolean isSwitchTab, boolean exit) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = showHelp;
        this.showStockView = showStockView;
        this.stockToView = stockToView;
        this.showStatistics = showStatistics;
        this.statisticsData = statisticsData;
        this.otherStatisticsDetails = otherStatisticsDetails;
        this.isSwitchTab = isSwitchTab;
        this.exit = exit;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, null, false, false, null, false, null, false, false);
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public boolean isShowHelp() {
        return showHelp;
    }

    public boolean isShowStockView() {
        return showStockView;
    }

    public Stock getStockToView() {
        return this.stockToView;
    }

    public boolean isShowStatistics() {
        return showStatistics;
    }

    public Map<String, Integer> getStatisticsData() {
        return this.statisticsData;
    }

    public String[] getOtherStatisticsDetails() {
        return this.otherStatisticsDetails;
    }

    public boolean isSwitchTab() {
        return isSwitchTab;
    }

    public boolean isExit() {
        return exit;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CommandResult)) {
            return false;
        }

        CommandResult otherCommandResult = (CommandResult) other;
        return feedbackToUser.equals(otherCommandResult.feedbackToUser)
                && showHelp == otherCommandResult.showHelp
                && exit == otherCommandResult.exit
                && showStockView == otherCommandResult.showStockView
                && checkEqualStock(stockToView, otherCommandResult.stockToView)
                && showStatistics == otherCommandResult.showStatistics
                && checkEqualStatisticsData(statisticsData, otherCommandResult.statisticsData)
                && checkEqualOtherStatisticsData(otherStatisticsDetails, otherCommandResult.otherStatisticsDetails)
                && isSwitchTab == otherCommandResult.isSwitchTab;
    }

    /**
     * Additional layer of equals method for stock as it might be null.
     * @param thisStock The current stock object.
     * @param otherStock The other stock object to be compared with.
     * @return True if both are null, false if only one is null,
     * else the resulting boolean value from stock comparator.
     */
    public boolean checkEqualStock(Stock thisStock, Stock otherStock) {
        if (thisStock == null && otherStock == null) {
            return true;
        }
        if (thisStock == null || otherStock == null) {
            return false;
        }
        return thisStock.equals(otherStock);
    }

    /**
     * Additional layer of equals method for data as it might be null.
     * @param thisData The current data object.
     * @param otherData The other data object to be compared with.
     * @return True if both are null, false if only one is null,
     * else the resulting boolean value from map comparator.
     */
    public boolean checkEqualStatisticsData(Map<String, Integer> thisData, Map<String, Integer> otherData) {
        if (thisData == null && otherData == null) {
            return true;
        }
        if (thisData == null || otherData == null) {
            return false;
        }
        return thisData.equals(otherData);
    }

    /**
     * Additional layer of equals method for data as it might be null.
     * @param thisData The current data object.
     * @param otherData The other data object to be compared with.
     * @return True if both are null, false if only one is null or the array lengths are different,
     * else compare each element in the array.
     */
    public boolean checkEqualOtherStatisticsData(String[] thisData, String[] otherData) {
        if (thisData == null && otherData == null) {
            return true;
        }
        if (thisData == null || otherData == null) {
            return false;
        }
        if (thisData.length != otherData.length) {
            return false;
        }
        for (int i = 0; i < thisData.length; i++) {
            if (!thisData[i].equals(otherData[i])) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, statisticsData, showHelp, showStockView, stockToView,
                                   showStatistics, otherStatisticsDetails, isSwitchTab, exit);
    }

    @Override
    public String toString() {
        return "feedbackToUser:" + feedbackToUser + "\n"
                + "showHelp: " + showHelp + "\n"
                + "exit: " + exit + "\n"
                + "showStockView: " + showStockView + "\n"
                + "stockToView: " + stockToView + "\n"
                + "showStatistics: " + showStatistics + "\n"
                + "statisticsData: " + statisticsData + "\n"
                + "otherStatisticsDetails: " + Arrays.toString(otherStatisticsDetails) + "\n"
                + "isSwitchTab: " + isSwitchTab;
    }

}
