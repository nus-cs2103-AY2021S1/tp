package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.GoalTarget;
import seedu.address.model.module.Module;
import seedu.address.model.util.ModuleInfoRetriever;

/**
 * Recommend modules to S/U based on user's goal.
 */
public class RecommendSuCommand extends Command {
    public static final String COMMAND_WORD = "recommendSU";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Recommends modules to S/U based on your goal and CAP.\n"
            + "Example: " + COMMAND_WORD;
    public static final String MESSAGE_SUCCESS = "Here's the list of module(s) that we recommend to S/U!\n"
            + "Use command 'list' to view all modules again.";
    public static final String MESSAGE_SUCCESS_NO_RECOMMENDATION = "Looks like there is no module that we "
            + "recommend to S/U based on your goal!";
    public static final String MESSAGE_FAILURE = "Please key in your goal using 'goal' command for "
            + "S/U recommendations!";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        GoalTarget userGoal = model.getGoalTarget();
        if (!GoalTarget.isValidGoal(userGoal.getGoalTarget())) {
            // user has yet to key in goal
            return new CommandResult(MESSAGE_FAILURE, false, false,
                    true, false, false);
        }

        filterModule(model, userGoal);
        return getCommandResult(model);
    }

    /**
     * Returns command result based on the filtered module list size.
     *
     * @param model Model.
     * @return CommandResult.
     */
    private CommandResult getCommandResult(Model model) {
        int modListSize = model.getFilteredModuleList().size();
        if (modListSize == 0) {
            return new CommandResult(MESSAGE_SUCCESS_NO_RECOMMENDATION, false,
                    false, true, false, false);
        }
        return new CommandResult(MESSAGE_SUCCESS, false,
                false, true, false, false);
    }

    /**
     * Filter the modules to only modules that MyMods recommends to user.
     *
     * @param model Current model.
     * @param goal  User's goal.
     */
    private void filterModule(Model model, GoalTarget goal) {
        model.updateFilteredModuleList(x -> isRecommendSu(goal, x));
    }

    /**
     * Recommend if user should S/U a module based on three criterion.
     *
     * @param goal User's goal.
     * @param x    Module to be checked.
     * @return boolean True if all three conditions are satisfied, else false.
     */
    private boolean isRecommendSu(GoalTarget goal, Module x) {
        return isGraded(x) && isModSuAble(x) && isGradeBelowGoal(x, goal);
    }

    /**
     * Compares module's grade with user's goal.
     *
     * @param x    Module to be compared.
     * @param goal Goal set by user.
     * @return boolean True if the module grade is under user's goal, else false.
     */
    private boolean isGradeBelowGoal(Module x, GoalTarget goal) {
        return x.getGrade().getGradePoint() < GoalTarget.getUserGoalGrade(goal);
    }

    /**
     * Checks if module's grade is valid.
     *
     * @param x    Module to be compared.
     * @return boolean True if the module grade is a valid grade, ie not NA or SUed.
     */
    private boolean isGraded(Module x) {
        return (x.getGrade().toString() != "NA" && x.getGrade().toString() != "SU");
    }
    /**
     * Returns true if module can be S/U from data.
     *
     * @param module Module to be checked.
     * @return True if module can be S/U, false otherwise.
     */
    private boolean isModSuAble(Module module) {
        String status = ModuleInfoRetriever.retrieve(module.getModuleName().fullModName).get("su");
        return status.contains("true");
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof RecommendSuCommand; // instanceof handles nulls
    }

}
