package seedu.bookmark.ui.help;

public class HelpAdd implements HelpAction {

    private static final String ADD_INTRO = "ADD A BOOK!";
    private static final String ADD_MESSAGE = "Add books to the storage to keep track of your "
                                                + "reading progress using our Add Command!";
    private static final String ADD_COMMAND = "COMMAND: add n/BOOK_TITLE g/GENRE [t/TAG]... tp/TOTAL_PAGES "
                                                + "[b/PAGE_NUMBER]";
    private static final String ADD_EXAMPLE = "EXAMPLE: add n/Harry Potter g/Fiction t/Magic"
                                                + " t/Wizard tp/550 b/20";
    private static final String ADD_RESPONSE = "RESPONSE: New book added: Harry Potter "
                                                + "Genre: Fiction Total Pages: 550 "
                                                + "Bookmarked at: 20 Tags: [Magic, Wizard]";
    private static final String ADD_NOTES = "NOTE: adding Tags and Bookmarked Page are optional and you can add \n"
                                                + "multiple tags";
    private static final String LB = " \n";

    public HelpAdd() {}

    @Override
    public String helpIntro() {
        return ADD_INTRO;
    }

    @Override
    public String helpMessage() {
        return ADD_MESSAGE;
    }

    @Override
    public String helpExample() {
        return ADD_COMMAND + LB + ADD_EXAMPLE + LB + ADD_RESPONSE + LB + ADD_NOTES;
    }
}
