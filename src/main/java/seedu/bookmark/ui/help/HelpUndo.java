package seedu.bookmark.ui.help;

public class HelpUndo implements HelpAction {

    private static final String UNDO_INTRO = "UNDO YOUR COMMANDS!";
    private static final String UNDO_MESSAGE = "Made a mistake? No worries! Undo your "
                                                + "commands by using our Undo Command!";
    private static final String UNDO_COMMAND = "COMMAND: undo";
    private static final String UNDO_EXAMPLE = "EXAMPLE: undo";
    private static final String UNDO_RESPONSE = "RESPONSE: Previous command successfully undone";
    private static final String LB = " \n";

    public HelpUndo() {}


    @Override
    public String helpIntro() {
        return UNDO_INTRO;
    }

    @Override
    public String helpMessage() {
        return UNDO_MESSAGE;
    }

    @Override
    public String helpExample() {
        return UNDO_COMMAND + LB + UNDO_EXAMPLE + LB + UNDO_RESPONSE;
    }
}
