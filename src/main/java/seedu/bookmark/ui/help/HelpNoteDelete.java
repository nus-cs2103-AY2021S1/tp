package seedu.bookmark.ui.help;

public class HelpNoteDelete implements HelpAction {

    private static final String NOTE_DELETE_INTRO = "DELETE A NOTE FROM YOUR BOOK!";
    private static final String NOTE_DELETE_MESSAGE = "Having second thoughts? Delete past Notes "
                                                    + "by using our Note Command!";
    private static final String NOTE_DELETE_COMMAND = "COMMAND: note INDEX NOTE_INDEX";
    private static final String NOTE_DELETE_EXAMPLE = "EXAMPLE: notedel 1 3";
    private static final String NOTE_DELETE_RESPONSE = "RESPONSE: Deleted Note: Thoughts, from Book: 1984";
    private static final String LB = " \n";

    public HelpNoteDelete() {}

    @Override
    public String helpIntro() {
        return NOTE_DELETE_INTRO;
    }

    @Override
    public String helpMessage() {
        return NOTE_DELETE_MESSAGE;
    }

    @Override
    public String helpExample() {
        return NOTE_DELETE_COMMAND + LB + NOTE_DELETE_EXAMPLE + LB + NOTE_DELETE_RESPONSE;
    }
}
