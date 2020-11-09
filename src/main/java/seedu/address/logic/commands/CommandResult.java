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

    /** The application should reflect the sorted tag list. */
    private final boolean sortTag;

    /** The application should reflect the sorted person list. */
    private final boolean sortPerson;

    /** The application should reflect the tag list. */
    private final boolean tagList;

    /** The application should reflect the person list. */
    private final boolean personList;

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */

    public CommandResult(String feedbackToUser, boolean showHelp, boolean exit, boolean sortTag,
                         boolean sortPerson, boolean tagList, boolean personlist) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = showHelp;
        this.exit = exit;
        this.sortTag = sortTag;
        this.sortPerson = sortPerson;
        this.tagList = tagList;
        this.personList = personlist;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, false, false, false, false, false, false);
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

    public boolean isSortTag() {
        return sortTag;
    }

    public boolean isSortPerson() {
        return sortPerson;
    }

    public boolean isTagList() {
        return tagList;
    }

    public boolean isPersonList() {
        return personList;
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
                && sortTag == otherCommandResult.sortTag
                && sortPerson == otherCommandResult.sortPerson
                && tagList == otherCommandResult.tagList
                && personList == otherCommandResult.personList;
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, showHelp, exit, sortTag, sortPerson, tagList, personList);
    }

}
