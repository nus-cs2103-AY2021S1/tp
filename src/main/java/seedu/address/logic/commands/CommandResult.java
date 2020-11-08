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

    /** Commands for Ui navigation purposes **/
    private boolean isCalendarNavigation;

    /** The tab should automatically change. */
    private EntityType entityType;


    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean exit) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = showHelp;
        this.exit = exit;
    }

    /**
     * Constructs a {@code CommandResult} with the specified fields including EntityType.
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean exit, EntityType entityType) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = showHelp;
        this.exit = exit;
        this.entityType = entityType;
    }

    /**
     * Constructs a {@code CommandResult} with the specified fields including isUiNavigation.
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean exit, boolean isCalendarNavigation) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = showHelp;
        this.exit = exit;
        this.isCalendarNavigation = isCalendarNavigation;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, false, false);
    }

    /**
     * Sets the entity type for which the tab bar has to navigate to.
     * @param entityType type of the entity in the command.
     */
    public CommandResult setEntity(EntityType entityType) {
        return new CommandResult(feedbackToUser, showHelp, exit, entityType);
    }

    public CommandResult setNavigationTrue() {
        return new CommandResult(feedbackToUser, showHelp, exit, true);
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public EntityType getEntityType() {
        return entityType;
    }

    public boolean isShowHelp() {
        return showHelp;
    }

    public boolean isExit() {
        return exit;
    }

    public boolean isCalendarNavigation() {
        return this.isCalendarNavigation;
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
                && exit == otherCommandResult.exit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, showHelp, exit);
    }

}
