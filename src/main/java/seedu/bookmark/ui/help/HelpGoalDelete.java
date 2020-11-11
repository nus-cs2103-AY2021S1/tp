package seedu.bookmark.ui.help;

public class HelpGoalDelete implements HelpAction {

    private static final String GOAL_DELETE_INTRO = "DELETE A GOAL FROM YOUR BOOK!";
    private static final String GOAL_DELETE_MESSAGE = "Feeling overwhelmed? It's okay, delete your goal by "
                                                + "using our Goal Command!";
    private static final String GOAL_DELETE_COMMAND = "COMMAND: goaldel INDEX";
    private static final String GOAL_DELETE_EXAMPLE = "EXAMPLE: goaldel 1";
    private static final String GOAL_DELETE_RESPONSE = "RESPONSE: Goal successfully removed for Harry Potter";
    private static final String LB = " \n";

    public HelpGoalDelete() {}

    @Override
    public String helpIntro() {
        return GOAL_DELETE_INTRO;
    }

    @Override
    public String helpMessage() {
        return GOAL_DELETE_MESSAGE;
    }

    @Override
    public String helpExample() {
        return GOAL_DELETE_COMMAND + LB + GOAL_DELETE_EXAMPLE + LB + GOAL_DELETE_RESPONSE;
    }
}
