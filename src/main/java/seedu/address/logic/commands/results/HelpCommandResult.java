package seedu.address.logic.commands.results;

import static java.util.Objects.requireNonNull;

/**
 * Represents the result of the Help command execution.
 */
public class HelpCommandResult extends CommandResult {

    private String popUpContent = "";
    private String dataToUser;

    /**
     * Constructs a {@code HelpCommandResult} with the specified {@code dataToUser}, {@code feedbackToUser}, and
     * {@code displayType}.
     * @param dataToUser data to show user.
     * @param feedbackToUser feedback to user.
     * @param displayType the alias of the item type.
     */
    public HelpCommandResult(String feedbackToUser, boolean showHelp, boolean exit, String dataToUser, String popUpContent) {
        super(feedbackToUser, showHelp, exit);
        this.dataToUser = requireNonNull(dataToUser);
        this.popUpContent = requireNonNull(popUpContent);
    }

    public String getPopUpContent() {
        return popUpContent;
    }
}
