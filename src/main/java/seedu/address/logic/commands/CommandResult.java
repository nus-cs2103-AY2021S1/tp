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

    /** Should show recipe list. */
    private final boolean isShowRecipe;

    /** Should show ingredient list. */
    private final boolean isShowIngredient;

    /** Should show consumption list. */
    private final boolean isShowConsumption;

    /** Should show edit ingredient command. */
    private final boolean isEditIngredient;

    /** Should show edit recipe command. */
    private final boolean isEditRecipe;

    private String commandBoxText;


    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean exit,
                         boolean isShowRecipe, boolean isShowIngredient, boolean isShowConsumption,
                         boolean isEditRecipe, boolean isEditIngredient) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = showHelp;
        this.exit = exit;
        this.isShowRecipe = isShowRecipe;
        this.isShowIngredient = isShowIngredient;
        this.isShowConsumption = isShowConsumption;
        this.isEditRecipe = isEditRecipe;
        this.isEditIngredient = isEditIngredient;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, false, false, false, false,
                false, false, false);
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

    public boolean isShowRecipe() {
        return isShowRecipe;
    }

    public boolean isShowIngredient() {
        return isShowIngredient;
    }

    public boolean isShowConsumption() {
        return isShowConsumption;
    }

    public boolean isEditRecipe() {
        return isEditRecipe;
    }

    public boolean isEditIngredient() {
        return isEditIngredient;
    }

    public void setCommandBox(String str) {
        requireNonNull(str);
        commandBoxText = str;
    }

    public String getCommandBox() {
        return commandBoxText;
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
        return Objects.hash(feedbackToUser, showHelp, exit, isShowRecipe, isShowIngredient, isShowConsumption,
                isEditRecipe, isEditIngredient);
    }

}
