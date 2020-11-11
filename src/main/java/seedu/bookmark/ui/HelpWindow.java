package seedu.bookmark.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import seedu.bookmark.commons.core.LogsCenter;
import seedu.bookmark.ui.help.HelpAdd;
import seedu.bookmark.ui.help.HelpDelete;
import seedu.bookmark.ui.help.HelpEdit;
import seedu.bookmark.ui.help.HelpFind;
import seedu.bookmark.ui.help.HelpGoalAdd;
import seedu.bookmark.ui.help.HelpGoalDelete;
import seedu.bookmark.ui.help.HelpList;
import seedu.bookmark.ui.help.HelpNoteAdd;
import seedu.bookmark.ui.help.HelpNoteDelete;
import seedu.bookmark.ui.help.HelpRedo;
import seedu.bookmark.ui.help.HelpSort;
import seedu.bookmark.ui.help.HelpUndo;
import seedu.bookmark.ui.help.HelpView;

/**
 * Controller for a help page
 */
public class HelpWindow extends UiPart<Stage> {

    public static final String USERGUIDE_URL = "https://ay2021s1-cs2103t-f13-2.github.io/tp/";
    public static final String HEADER_MESSAGE = "Welcome to bookmark!\n"
                                                + "Click on the Copy URL button for the link to our Website!\n";
    private static final Logger logger = LogsCenter.getLogger(HelpWindow.class);
    private static final String FXML = "HelpWindow.fxml";
    private Image bookmarkIcon = new Image(this.getClass().getResourceAsStream("/images/bookmark_logo.png"));

    @FXML
    private Button copyButton;

    @FXML
    private ImageView icon;

    @FXML
    private Label welcomeHelp;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private VBox helpContainer;


    /**
     * Creates a new HelpWindow.
     *
     * @param root Stage to use as the root of the HelpWindow.
     */
    public HelpWindow(Stage root) {
        super(FXML, root);
        welcomeHelp.setText(HEADER_MESSAGE);
        icon.setImage(bookmarkIcon);
        scrollPane.setVvalue(0);

        HelpAdd helpAdd = new HelpAdd();
        HelpList helpList = new HelpList();
        HelpView helpView = new HelpView();
        HelpEdit helpEdit = new HelpEdit();
        HelpDelete helpDelete = new HelpDelete();
        HelpSort helpSort = new HelpSort();
        HelpFind helpFind = new HelpFind();
        HelpGoalAdd helpGoalAdd = new HelpGoalAdd();
        HelpGoalDelete helpGoalDelete = new HelpGoalDelete();
        HelpNoteAdd helpNoteAdd = new HelpNoteAdd();
        HelpNoteDelete helpNoteDelete = new HelpNoteDelete();
        HelpUndo helpUndo = new HelpUndo();
        HelpRedo helpRedo = new HelpRedo();
        helpContainer.getChildren().addAll(
                HelpBox.getHelpBox(helpAdd.helpIntro(), helpAdd.helpMessage(), helpAdd.helpExample()),
                HelpBox.getHelpBox(helpList.helpIntro(), helpList.helpMessage(), helpList.helpExample()),
                HelpBox.getHelpBox(helpView.helpIntro(), helpView.helpMessage(), helpView.helpExample()),
                HelpBox.getHelpBox(helpEdit.helpIntro(), helpEdit.helpMessage(), helpEdit.helpExample()),
                HelpBox.getHelpBox(helpDelete.helpIntro(), helpDelete.helpMessage(), helpDelete.helpExample()),
                HelpBox.getHelpBox(helpSort.helpIntro(), helpSort.helpMessage(), helpSort.helpExample()),
                HelpBox.getHelpBox(helpFind.helpIntro(), helpFind.helpMessage(), helpFind.helpExample()),
                HelpBox.getHelpBox(helpGoalAdd.helpIntro(), helpGoalAdd.helpMessage(), helpGoalAdd.helpExample()),
                HelpBox.getHelpBox(helpGoalDelete.helpIntro(),
                            helpGoalDelete.helpMessage(), helpGoalDelete.helpExample()),
                HelpBox.getHelpBox(helpNoteAdd.helpIntro(), helpNoteAdd.helpMessage(), helpNoteAdd.helpExample()),
                HelpBox.getHelpBox(helpNoteDelete.helpIntro(),
                            helpNoteDelete.helpMessage(), helpNoteDelete.helpExample()),
                HelpBox.getHelpBox(helpUndo.helpIntro(), helpUndo.helpMessage(), helpUndo.helpExample()),
                HelpBox.getHelpBox(helpRedo.helpIntro(), helpRedo.helpMessage(), helpRedo.helpExample())
        );
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
