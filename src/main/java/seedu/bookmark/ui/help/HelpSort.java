package seedu.bookmark.ui.help;

public class HelpSort implements HelpAction {

    private static final String SORT_INTRO = "SORT YOUR BOOKS AS YOU LIKE!";
    private static final String SORT_MESSAGE = "Too messy? Sort your books by Name, Genre or Reading Progress using "
                                                + "our Sort Command!";
    private static final String SORT_COMMAND = "COMMAND: sort n/";
    private static final String SORT_EXAMPLE = "EXAMPLE: sort n/";
    private static final String SORT_RESPONSE = "RESPONSE: Sorted by Name";
    private static final String SORT_NOTES = "NOTE: You can also sort on g/ (genre), b/ (bookmark) or "
                                                + "rp/ (reading progress)";
    private static final String LB = "\n";

    public HelpSort() {}

    @Override
    public String helpIntro() {
        return SORT_INTRO;
    }

    @Override
    public String helpMessage() {
        return SORT_MESSAGE;
    }

    @Override
    public String helpExample() {
        return SORT_COMMAND + LB + SORT_EXAMPLE + LB + SORT_RESPONSE + LB + SORT_NOTES;
    }
}
