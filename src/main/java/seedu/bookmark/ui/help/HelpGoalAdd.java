package seedu.bookmark.ui.help;

public class HelpGoalAdd implements HelpAction {

    private static final String GOAL_ADD_INTRO = "ADD A READING GOAL TO YOUR BOOK!";
    private static final String GOAL_ADD_MESSAGE = "Up for a challenge? Add a reading goal to your book using our "
                                                    + "Goal Command!";
    private static final String GOAL_ADD_COMMAND = "COMMAND: goal INDEX p/PAGE d/DD-MM-YYYY";
    private static final String GOAL_ADD_EXAMPLE = "EXAMPLE: goal 1 p/69 d/22-05-2020";
    private static final String GOAL_ADD_RESPONSE = "RESPONSE: New goal for Harry Potter: "
                                                    + "Finish page 13 before 20-11-2024";
    private static final String LB = " \n";

    public HelpGoalAdd() {}

    @Override
    public String helpIntro() {
        return GOAL_ADD_INTRO;
    }

    @Override
    public String helpMessage() {
        return GOAL_ADD_MESSAGE;
    }

    @Override
    public String helpExample() {
        return GOAL_ADD_COMMAND + LB + GOAL_ADD_EXAMPLE + LB + GOAL_ADD_RESPONSE;
    }
}
