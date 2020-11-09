package ay2021s1_cs2103_w16_3.finesse.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;
import java.util.Optional;

import ay2021s1_cs2103_w16_3.finesse.ui.UiState.Tab;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    private final String feedbackToUser;

    /** Budget information should be recalculated. */
    private final boolean calculateBudgetInfo;

    /** Help information should be shown to the user. */
    private final boolean showHelp;

    /** The application should exit. */
    private final boolean exit;

    /** An optional {@code Tab} to switch to */
    private final Optional<Tab> tabToSwitchTo;

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     *
     * @param feedbackToUser The feedback to be displayed to the user.
     * @param calculateBudgetInfo Whether the budget information should be recalculated.
     * @param showHelp Whether the help dialog should be shown to the user.
     * @param exit Whether the application should exit.
     * @param tabToSwitchTo The tab the UI should switch to.
     */
    public CommandResult(String feedbackToUser, boolean calculateBudgetInfo,
                         boolean showHelp, boolean exit, Tab tabToSwitchTo) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.calculateBudgetInfo = calculateBudgetInfo;
        this.showHelp = showHelp;
        this.exit = exit;
        this.tabToSwitchTo = Optional.ofNullable(tabToSwitchTo);
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser}, {@code calculateBudgetInfo},
     * {@code showHelp}, {@code exit}, and other fields set to their default value.
     *
     * @param feedbackToUser The feedback to be displayed to the user.
     * @param calculateBudgetInfo Whether the budget information should be recalculated.
     * @param showHelp Whether the help dialog should be shown to the user.
     * @param exit Whether the application should exit.
     */
    public CommandResult(String feedbackToUser, boolean calculateBudgetInfo, boolean showHelp, boolean exit) {
        this(feedbackToUser, calculateBudgetInfo, showHelp, exit, null);
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser}, {@code tabToSwitchTo},
     * and other fields set to their default value.
     *
     * @param feedbackToUser The feedback to be displayed to the user.
     * @param tabToSwitchTo The tab the UI should switch to.
     */
    public CommandResult(String feedbackToUser, Tab tabToSwitchTo) {
        this(feedbackToUser, false, false, false, tabToSwitchTo);
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     *
     * @param feedbackToUser The feedback to be displayed to the user.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, false, false, false, null);
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser}, {@code calculateBudgetInfo},
     * and other fields set to their default value.
     *
     * @param feedbackToUser The feedback to be displayed to the user.
     * @param calculateBudgetInfo Whether the budget information should be recalculated.
     */
    public CommandResult(String feedbackToUser, boolean calculateBudgetInfo) {
        this(feedbackToUser, calculateBudgetInfo, false, false, null);
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser}, {@code calculateBudgetInfo},
     * {@code tabToSwitchTo}, and other fields set to their default value.
     *
     * @param feedbackToUser The feedback to be displayed to the user.
     * @param calculateBudgetInfo Whether the budget information should be recalculated.
     * @param tabToSwitchTo The tab the UI should switch to.
     */
    public CommandResult(String feedbackToUser, boolean calculateBudgetInfo, Tab tabToSwitchTo) {
        this(feedbackToUser, calculateBudgetInfo, false, false, tabToSwitchTo);
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public boolean isCalculateBudgetInfo() {
        return calculateBudgetInfo;
    }

    public boolean isShowHelp() {
        return showHelp;
    }

    public boolean isExit() {
        return exit;
    }

    public Optional<Tab> getTabToSwitchTo() {
        return tabToSwitchTo;
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
                && calculateBudgetInfo == otherCommandResult.calculateBudgetInfo
                && showHelp == otherCommandResult.showHelp
                && exit == otherCommandResult.exit
                && tabToSwitchTo.equals(otherCommandResult.tabToSwitchTo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, calculateBudgetInfo, showHelp, exit, tabToSwitchTo);
    }

}
