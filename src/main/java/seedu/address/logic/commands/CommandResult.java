package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

import javafx.collections.ObservableList;
import seedu.address.model.patient.Patient;
import seedu.address.model.visit.Visit;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    private final String feedbackToUser;

    /** Help information should be shown to the user. */
    private final boolean showHelp;

    /** The application should exit. */
    private final boolean exit;

    private int index;
    private int reportIdx;
    private String date;
    private ObservableList<Visit> reports;
    private Visit oldReport;
    private Patient profilePatient;

    /** The application should AddVisit. */
    private final boolean isAddVisit;

    /** The application should ShowVisitList. */
    private final boolean isShowVisitList;

    /** The application should EditVisit. */
    private final boolean isEditVisit;

    /** The application should show the Profile */
    private final boolean isProfile;

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean isAddVisit, boolean isShowVisitList,
                         boolean isEditVisit, boolean isProfile, boolean exit) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = showHelp;
        this.exit = exit;
        this.isAddVisit = isAddVisit;
        this.isShowVisitList = isShowVisitList;
        this.isEditVisit = isEditVisit;
        this.isProfile = isProfile;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, false, false, false, false, false, false);
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser, int idx, String date) {
        this(feedbackToUser, false, true, false, false, false, false);
        this.index = idx;
        this.date = date;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser, ObservableList<Visit> lst) {
        this(feedbackToUser, false, false, true, false,
            false, false);
        this.reports = lst;
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
