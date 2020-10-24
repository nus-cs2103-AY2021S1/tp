package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;

/**
 * Controller for a help page
 */
public class HelpWindow extends UiPart<Stage> {

    public static final String USERGUIDE_URL = "https://ay2021s1-cs2103t-f11-3.github.io/tp/UserGuide.html";
    public static final String HELP_MESSAGE = "Welcome to ProductiveNUS!\n\nHere are some of the commands that you can"
            + " try:\n\n"
            + "-----------------------------------------Basic Features---------------------------------------------\n"
            + "- To add an assignment: add n/Lab report 3 d/23-04-2020 1230 mod/CS2100\n"
            + "- To delete an assignment shown in the list: delete 3\n"
            + "- To list all assignments: list\n"
            + " *Tip: Use `list 2` to list assignments with deadlines 2 days from current date*\n\n"
            + "----------------------------------------Advance Features-------------------------------------------\n"
            + "- To import your timetable: import url/YOUR_NUSMODS_URL\n"
            + "- To find assignments by name: find n/Lab\n"
            + " *Tip: You can also find assignments by module code, deadline or priority using\n"
            + " `mod/`, `d/` and `priority/` prefixes respectively.*\n"
            + "- To set reminders for an assignment shown in the list: remind 3\n"
            + " *Tip: Assignments with reminders set will be displayed in `Your Reminders` section permanently*\n"
            + "- To remove reminders for an assignment: unremind 3\n"
            + "- To set a priority tag for an assignment: prioritize 3 priority/HIGH\n"
            + "- To remove the priority tag for an assignment: unprioritize 3\n"
            + "- To mark an assignment shown in the list as done: done 3\n"
            + "- To mark an assignment shown in the list as not done: undone 3\n"
            + "\n For more information,\n"
            + " kindly refer to our user guide: " + USERGUIDE_URL;

    private static final Logger logger = LogsCenter.getLogger(HelpWindow.class);
    private static final String FXML = "HelpWindow.fxml";

    @FXML
    private Button copyButton;

    @FXML
    private Label helpMessage;

    /**
     * Creates a new HelpWindow.
     *
     * @param root Stage to use as the root of the HelpWindow.
     */
    public HelpWindow(Stage root) {
        super(FXML, root);
        helpMessage.setText(HELP_MESSAGE);
    }

    /**
     * Creates a new HelpWindow.
     */
    public HelpWindow() {
        this(new Stage());
    }

    /**
     * Shows the help window.
     * @throws IllegalStateException
     * <ul>
     *     <li>
     *         if this method is called on a thread other than the JavaFX Application Thread.
     *     </li>
     *     <li>
     *         if this method is called during animation or layout processing.
     *     </li>
     *     <li>
     *         if this method is called on the primary stage.
     *     </li>
     *     <li>
     *         if {@code dialogStage} is already showing.
     *     </li>
     * </ul>
     */
    public void show() {
        logger.fine("Showing help page about the application.");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the help window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the help window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the help window.
     */
    public void focus() {
        getRoot().requestFocus();
    }

    /**
     * Copies the URL to the user guide to the clipboard.
     */
    @FXML
    private void copyUrl() {
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent url = new ClipboardContent();
        url.putString(USERGUIDE_URL);
        clipboard.setContent(url);
    }
}
