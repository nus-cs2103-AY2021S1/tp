package seedu.bookmark.ui.help;

public class HelpFind implements HelpAction {

    private static final String FIND_INTRO = "FIND YOUR BOOKS!";
    private static final String FIND_MESSAGE = "Looking for something? Find specific books using our Find Command!";
    private static final String FIND_COMMAND = "COMMAND: find n/BOOK_TITLE";
    private static final String FIND_EXAMPLE = "EXAMPLE: find n/Harry Potter";
    private static final String FIND_RESPONSE = "RESPONSE: 1 books listed!";
    private static final String FIND_NOTES = "NOTE: You can also find book on g/GENRE, t/TAG, c/ (completed), "
                                                + "nc/ (not completed)";
    private static final String LB = " \n";

    public HelpFind() {}

    @Override
    public String helpIntro() {
        return FIND_INTRO;
    }

    @Override
    public String helpMessage() {
        return FIND_MESSAGE;
    }

    @Override
    public String helpExample() {
        return FIND_COMMAND + LB + FIND_EXAMPLE + LB + FIND_RESPONSE + LB + FIND_NOTES;
    }
}
