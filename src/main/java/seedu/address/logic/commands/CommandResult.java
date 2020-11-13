package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    private final String feedbackToUser;

    /** Help information should be shown to the user. */
    private final boolean showHelp;

    /** The application should exit. */
    private final boolean exit;

    /** Statistics should be shown to the user */
    private final boolean showAllStats;
    private final boolean showCalorieStats;
    private final boolean showWeightStats;
    private final boolean showCommanderStats;

    /** All lists should be cleared */
    private final boolean clear;

    /** If the index matches the current day's calories being shown, remove them */
    private final boolean delete;
    private final int indexDelete;

    private final boolean view;
    private final int indexView;

    private final boolean profileHasChanged;

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean exit, boolean showAllStats,
                         boolean showCalorieStats, boolean showWeightStats, boolean clear, boolean delete,
                         int indexDelete, boolean profileHasChanged, boolean view, int indexView,
                         boolean showCommanderStats) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = showHelp;
        this.exit = exit;
        this.showAllStats = showAllStats;
        this.showCalorieStats = showCalorieStats;
        this.showWeightStats = showWeightStats;
        this.clear = clear;
        this.delete = delete;
        this.indexDelete = indexDelete;
        this.profileHasChanged = profileHasChanged;
        this.view = view;
        this.indexView = indexView;
        this.showCommanderStats = showCommanderStats;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, false, false, false, false, false, false, false, 0, false, false, 0, false);
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser, showAllStats, showCalorieStats,
     * showWeightStats},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser, boolean showAllStats, boolean showCalorieStats,
                         boolean showWeightStats, boolean showCommanderStats) {
        this(feedbackToUser, false, false, showAllStats, showCalorieStats, showWeightStats,
                false, false, 0, false, false, 0,
                showCommanderStats);
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser, showHelp, exit},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean exit) {
        this(feedbackToUser, showHelp, exit, false, false, false, false, false, 0, false, false, 0, false);
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser, clear},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser, boolean clear) {
        this(feedbackToUser, false, false, false, false, false, clear, false, 0, false, false, 0, false);
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser, delete, index},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser, boolean delete, int indexDelete) {
        this(feedbackToUser, false, false, false, false, false, false, delete, indexDelete, false, false, 0, false);
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code profileHasChanged, feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(boolean profileHasChanged, String feedbackToUser) {
        this(feedbackToUser, false, false, false, false, false, false, false, 0, profileHasChanged, false, 0, false);
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser, indexView, view},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser, int indexView, boolean view) {
        this(feedbackToUser, false, false, false, false, false, false, false, 0, false, view, indexView, false);
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public boolean isShowHelp() {
        return showHelp;
    }

    public boolean isExit() {
        return exit;
    }

    public boolean isShowAllStats() {
        return showAllStats;
    }

    public boolean isShowCalorieStats() {
        return showCalorieStats;
    }

    public boolean isShowWeightStats() {
        return showWeightStats;
    }

    public boolean isClear() {
        return clear;
    }

    public boolean isDelete() {
        return delete;
    }

    public int getIndexDelete() {
        return indexDelete;
    }

    public boolean isProfileChanged() {
        return profileHasChanged;
    }

    public boolean isView() {
        return view;
    }

    public int getIndexView() {
        return indexView;
    }

    public boolean isShowCommanderStats() {
        return showCommanderStats;
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
                && showAllStats == otherCommandResult.showAllStats
                && showCalorieStats == otherCommandResult.showCalorieStats
                && showWeightStats == otherCommandResult.showWeightStats
                && clear == otherCommandResult.clear
                && delete == otherCommandResult.delete
                && indexDelete == otherCommandResult.indexDelete
                && profileHasChanged == otherCommandResult.profileHasChanged
                && view == otherCommandResult.view
                && indexView == otherCommandResult.indexView
                && showCommanderStats == otherCommandResult.showCommanderStats;
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, showHelp, exit, showAllStats, showCalorieStats,
                showWeightStats, clear, delete, indexDelete, profileHasChanged, view, indexView, showCommanderStats);
    }

}
