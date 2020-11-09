package seedu.address.model.module;

import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents the user's goal following the Honours Classification in NUS.
 */
public class GoalTarget {

    public static final String MESSAGE_CONSTRAINTS =
            "Goal should be an integer from 1 to 6, and it should not be blank."
                    + "\nUse command 'goal list' to see all goals!";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public static final String HIGHEST_DISTINCTION = "Highest Distinction (CAP 4.50 ~ 5.00)";
    public static final String DISTINCTION = "Distinction (CAP 4.00 ~ 4.49)";
    public static final String MERIT = "Merit (CAP 3.50 ~ 3.99)";
    public static final String HONOURS = "Honours (CAP 3.00 ~ 3.49)";
    public static final String PASS = "Pass (CAP 2.00 ~ 2.99)";
    public static final String FAIL = "Fail (CAP < 2.00)";
    public static final String GOAL_LIST = "'goal set 1': " + HIGHEST_DISTINCTION
            + "\n'goal set 2': " + DISTINCTION
            + "\n'goal set 3': " + MERIT
            + "\n'goal set 4': " + HONOURS
            + "\n'goal set 5': " + PASS
            + "\n'goal set 6': " + FAIL;
    public static final int DEFAULT_GOAL = 0;
    public static final double HIGHEST_DISTINCTION_CAP = 4.5;
    public static final double DISTINCTION_CAP = 4.0;
    public static final double MERIT_CAP = 3.5;
    public static final double HONOURS_CAP = 3.0;
    public static final double PASS_CAP = 2.0;
    public static final double FAIL_CAP = 0.0;

    public final int goalTarget;

    /**
     * Constructor for GoalTarget class.
     * @param goalTarget Sets the level of the goal based on Honour's Grading System.
     */
    public GoalTarget(int goalTarget) {
        checkArgument(isValidGoal(goalTarget), MESSAGE_CONSTRAINTS);
        this.goalTarget = goalTarget;
    }

    public GoalTarget() {
        this.goalTarget = DEFAULT_GOAL;
    }

    public int getGoalTarget() {
        return goalTarget;
    }

    /**
     * Returns true if a given int is a valid goal target.
     */
    public static boolean isValidGoal(int goalTarget) {
        return (goalTarget > 0 && goalTarget < 7) && Integer.toString(goalTarget).matches(VALIDATION_REGEX);
    }

    /**
     * Returns the minimum CAP for each honour's grading depending on user's goal.
     * @param userGoal Goal set by the user.
     * @return double of the minimum CAP of each honour's grading.
     */
    public static double getUserGoalGrade(GoalTarget userGoal) {
        int goalLevel = userGoal.getGoalTarget();
        assert (goalLevel >= 1 && goalLevel <= 6) : "Invalid Goal Target";
        switch (goalLevel) {
        case 1:
            return GoalTarget.HIGHEST_DISTINCTION_CAP;
        case 2:
            return GoalTarget.DISTINCTION_CAP;
        case 3:
            return GoalTarget.MERIT_CAP;
        case 4:
            return GoalTarget.HONOURS_CAP;
        case 5:
            return GoalTarget.PASS_CAP;
        case 6:
            return GoalTarget.FAIL_CAP;
        default:
            return GoalTarget.DEFAULT_GOAL;
        }
    }

    /**
     * Returns true if both GoalTarget have the same identity and data fields.
     * This defines a stronger notion of equality between two GoalTarget.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof GoalTarget)) {
            return false;
        }

        GoalTarget otherGoal = (GoalTarget) other;
        return otherGoal.getGoalTarget() == getGoalTarget();
    }

    @Override
    public String toString() {
        switch (goalTarget) {
        case 1:
            return HIGHEST_DISTINCTION;
        case 2:
            return DISTINCTION;
        case 3:
            return MERIT;
        case 4:
            return HONOURS;
        case 5:
            return PASS;
        case 6:
            return FAIL;
        default:
            return MESSAGE_CONSTRAINTS;
        }
    }
}
