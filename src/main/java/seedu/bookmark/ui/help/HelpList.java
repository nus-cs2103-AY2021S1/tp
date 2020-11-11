package seedu.bookmark.ui.help;

public class HelpList implements HelpAction {

    private static final String LIST_INTRO = "LIST ALL BOOKS";
    private static final String LIST_MESSAGE = "Want to take a peek of your bookshelf? See all of your "
                                                + "stored books using our List Command!";
    private static final String LIST_COMMAND = "COMMAND: list";
    private static final String LIST_EXAMPLE = "EXAMPLE: list";
    private static final String LIST_RESPONSE = "RESPONSE: Listing all books";
    private static final String LB = "\n";

    public HelpList() {}

    @Override
    public String helpIntro() {
        return LIST_INTRO;
    }

    @Override
    public String helpMessage() {
        return LIST_MESSAGE;
    }

    @Override
    public String helpExample() {
        return LIST_COMMAND + LB + LIST_EXAMPLE + LB + LIST_RESPONSE;
    }
}
