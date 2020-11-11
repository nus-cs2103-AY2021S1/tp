package seedu.bookmark.ui.help;

public class HelpRedo implements HelpAction {

    private static final String REDO_INTRO = "REDO YOUR COMMANDS!";
    private static final String REDO_MESSAGE = "Decided to stick with it? Redo past commands using our Redo Command!";
    private static final String REDO_COMMAND = "COMMAND: redo";
    private static final String REDO_EXAMPLE = "EXAMPLE: redo";
    private static final String REDO_RESPONSE = "RESPONSE: Previous undone command successfully redone";
    private static final String LB = " \n";

    public HelpRedo() {}

    @Override
    public String helpIntro() {
        return REDO_INTRO;
    }

    @Override
    public String helpMessage() {
        return REDO_MESSAGE;
    }

    @Override
    public String helpExample() {
        return REDO_COMMAND + LB + REDO_EXAMPLE + LB + REDO_RESPONSE;
    }
}
