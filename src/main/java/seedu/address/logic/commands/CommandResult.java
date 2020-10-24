package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

import seedu.address.model.recipe.Recipe;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    private final String feedbackToUser;

    /** Help information should be shown to the user. */
    private boolean showHelp = false;

    /** The application should exit. */
    private boolean exit = false;

    /** Should show recipe list. */
    private boolean isShowRecipe = false;

    /** Should show ingredient list. */
    private boolean isShowIngredient = false;

    /** Should show consumption list. */
    private boolean isShowConsumption = false;

    /** Should show edit ingredient command. */
    private boolean isEditIngredient = false;

    /** Should show edit recipe command. */
    private boolean isEditRecipe = false;

    /** Should close drawer. */
    private boolean isClose = false;

    private Recipe recipe;

    private String commandBoxText;


    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, String keyword) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        switch (keyword) {
        case HelpCommand.COMMAND_WORD:
            this.showHelp = true;
            break;
        case ExitCommand.COMMAND_WORD:
            this.exit = true;
            break;
        case GetEditIngredientCommand.COMMAND_WORD:
            this.isEditIngredient = true;
            break;
        case GetEditRecipeCommand.COMMAND_WORD:
            this.isEditRecipe = true;
            break;
        case ListIngredientsCommand.COMMAND_WORD:
            this.isShowIngredient = true;
            break;
        case ListConsumptionCommand.COMMAND_WORD:
            this.isShowConsumption = true;
            break;
        case ListRecipesCommand.COMMAND_WORD:
            this.isShowRecipe = true;
            break;
        case CloseCommand.COMMAND_WORD:
            this.isClose = true;
            break;
        default:
            break;
        }
    }

    /**
     * Constructor for selecting single recipe
     * @param feedbackToUser
     * @param recipe
     */
    public CommandResult(String feedbackToUser, Recipe recipe) {
        this(feedbackToUser, "showRecipe");
        this.recipe = recipe;
    }

    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, "general");
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public boolean isShowHelp() {
        return showHelp;
    }

    public Recipe getRecipe() {
        return recipe;
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

    public boolean isClose() {
        return isClose;
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
