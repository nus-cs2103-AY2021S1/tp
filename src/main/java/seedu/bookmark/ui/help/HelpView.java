package seedu.bookmark.ui.help;

public class HelpView implements HelpAction {

    private static final String VIEW_INTRO = "VIEW A CHOSEN BOOK!";
    private static final String VIEW_MESSAGE = "Want a closer look? Find the stored Notes and Goals of your chosen "
                                                + "book using the View Command! ";
    private static final String VIEW_COMMAND = "COMMAND: view INDEX";
    private static final String VIEW_EXAMPLE = "EXAMPLE: view 3";
    private static final String VIEW_RESPONSE = "RESPONSE: Viewing 3";
    private static final String LB = "\n";

    public HelpView() {}

    @Override
    public String helpIntro() {
        return VIEW_INTRO;
    }

    @Override
    public String helpMessage() {
        return VIEW_MESSAGE;
    }

    @Override
    public String helpExample() {
        return VIEW_COMMAND + LB + VIEW_EXAMPLE + LB + VIEW_RESPONSE;
    }
}
