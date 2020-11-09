package seedu.address.logic.commands;

public class PerformanceCommandResult extends CommandResult {
    /** Application should be switched to show attempt list. */
    private final boolean switchToAttempts;

    /** Application should be switched to response list */
    private final boolean switchToResponses;

    /**
     * Constructs a {@code PerformanceCommandResult} with the specified fields (3),
     * and other fields set to their default value.
     */
    public PerformanceCommandResult(String feedbackToUser, boolean switchToAttempts, boolean switchToResponses) {
        super(feedbackToUser, false, false, false, false, false);
        this.switchToAttempts = switchToAttempts;
        this.switchToResponses = switchToResponses;
    }

    /**
     * Constructs a {@code PerformanceCommandResult} with the specified fields (3),
     * and other fields set to their default value.
     */
    public PerformanceCommandResult(String feedbackToUser, boolean showHelp) {
        super(feedbackToUser, false, false, true, false, false);
        this.switchToAttempts = false;
        this.switchToResponses = false;
    }

    public boolean isSwitchToAttempts() {
        return switchToAttempts;
    }

    public boolean isSwitchToResponses() {
        return switchToResponses;
    }

}
