package seedu.bookmark.ui.help;

public class HelpNoteAdd implements HelpAction {

    private static final String NOTE_ADD_INTRO = "ADD A NOTE TO YOUR BOOK!";
    private static final String NOTE_ADD_EXPLANATION = "Have some post-reading reflections? Add them into your stored "
                                                        + "book by using our Note Command!";
    private static final String NOTE_ADD_COMMAND = "COMMAND: note 1 n/NOTE TITLE txt/TEXT";
    private static final String NOTE_ADD_EXAMPLE = "EXAMPLE: note 1 n/Chapter 1 txt/This is a good chapter";
    private static final String NOTE_ADD_RESPONSE = "RESPONSE: New Note added to Harry Potter";
    private static final String LB = " \n";

    public HelpNoteAdd() {}

    @Override
    public String helpIntro() {
        return NOTE_ADD_INTRO;
    }

    @Override
    public String helpMessage() {
        return NOTE_ADD_EXPLANATION;
    }

    @Override
    public String helpExample() {
        return NOTE_ADD_COMMAND + LB + NOTE_ADD_EXAMPLE + LB + NOTE_ADD_RESPONSE;
    }
}
