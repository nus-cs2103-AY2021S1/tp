package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.CountCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;

/**
 * Controller for a help page
 */
public class HelpWindow extends UiPart<Stage> {

    public static final String USERGUIDE_URL = "https://ay2021s1-cs2103t-w15-3.github.io/tp/UserGuide.html";
    public static final String MORE_INFO = "For more information, please refer to the user guide: "
            + USERGUIDE_URL;

    private static final Logger logger = LogsCenter.getLogger(HelpWindow.class);
    private static final String FXML = "HelpWindow.fxml";
    private final CommandDescription addCommandDes =
            new CommandDescription(AddCommand.COMMAND_WORD, AddCommand.MESSAGE_USAGE);
    private final CommandDescription clearCommandDes =
            new CommandDescription(ClearCommand.COMMAND_WORD, ClearCommand.COMMAND_WORD);
    private final CommandDescription deleteCommandDes =
            new CommandDescription(DeleteCommand.COMMAND_WORD, DeleteCommand.MESSAGE_USAGE);
    private final CommandDescription editCommandDes =
            new CommandDescription(EditCommand.COMMAND_WORD, EditCommand.MESSAGE_USAGE);
    private final CommandDescription findCommandDes =
            new CommandDescription(FindCommand.COMMAND_WORD, FindCommand.MESSAGE_USAGE);
    private final CommandDescription listCommandDes =
            new CommandDescription(ListCommand.COMMAND_WORD, ListCommand.COMMAND_WORD);
    private final CommandDescription helpCommandDes =
            new CommandDescription(HelpCommand.COMMAND_WORD, HelpCommand.MESSAGE_USAGE);
    private final CommandDescription countCommandDes =
            new CommandDescription(CountCommand.COMMAND_WORD, CountCommand.MESSAGE_UASGE);

    public final ObservableList<CommandDescription> commandDescriptions = FXCollections
            .observableArrayList(addCommandDes, clearCommandDes, deleteCommandDes, editCommandDes,
                    findCommandDes, listCommandDes, helpCommandDes, countCommandDes);

    @FXML
    private Button copyButton;

    @FXML
    private TableView<CommandDescription> helpTable;

    @FXML
    private Label moreInfo;

    @FXML
    private Label copyButtonNotification;

    /**
     * Creates a new HelpWindow.
     *
     * @param root Stage to use as the root of the HelpWindow.
     */
    public HelpWindow(Stage root) {
        super(FXML, root);
        moreInfo.setText(MORE_INFO);
        helpTable.setItems(commandDescriptions);
    }

    /**
     * Creates a new HelpWindow.
     */
    public HelpWindow() {
        this(new Stage());
    }

    /**
     * Shows the help window.
     *
     * @throws IllegalStateException <ul>
     *                                   <li>
     *                                       if this method is called on a thread other
     *                                       than the JavaFX Application Thread.
     *                                   </li>
     *                                   <li>
     *                                       if this method is called during animation or layout processing.
     *                                   </li>
     *                                   <li>
     *                                       if this method is called on the primary stage.
     *                                   </li>
     *                                   <li>
     *                                       if {@code dialogStage} is already showing.
     *                                   </li>
     *                               </ul>
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
        copyButtonNotification.setText("Link Copied!");
        url.putString(USERGUIDE_URL);
        clipboard.setContent(url);
    }
}
