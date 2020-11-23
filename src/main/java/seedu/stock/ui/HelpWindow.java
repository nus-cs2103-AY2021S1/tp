package seedu.stock.ui;

import java.util.logging.Logger;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import seedu.stock.commons.core.LogsCenter;

/**
 * Controller for a help page
 */
public class HelpWindow extends UiPart<Stage> {

    public static final String COMMAND = "COMMAND";
    public static final String HELP = "HELP";
    public static final String ADD = "ADD";
    public static final String LIST = "LIST";
    public static final String DELETE = "DELETE";
    public static final String FIND = "FIND\n";
    public static final String FINDEXACT = "FINDEXACT\n";
    public static final String UPDATE = "UPDATE\n";
    public static final String NOTE = "NOTE\n";
    public static final String NOTEDELETE = "NOTEDELETE\n";
    public static final String STOCKVIEW = "STOCKVIEW\n";
    public static final String STATISTICS = "STATISTICS\n";
    public static final String SORT = "SORT\n";
    public static final String BOOKMARK = "BOOKMARK\n";
    public static final String UNBOOKMARK = "UNBOOKMARK\n";
    public static final String PRINT = "PRINT\n";
    public static final String CLEAR = "CLEAR\n";
    public static final String EXIT = "EXIT\n";
    public static final String TAB = "TAB\n";

    public static final String COMMAND_DESCRIPTION = "COMMAND DESCRIPTION";
    public static final String HELP_DESCRIPTION =
            "help";

    public static final String ADD_DESCRIPTION = "add n/<name> s/<source> q/<quantity>"
            + " l/<location> [lq/<low quantity>]";

    public static final String LIST_DESCRIPTION = "list lt/<list type>";

    public static final String DELETE_DESCRIPTION = "delete sn/<serial number> [sn/<serial number>]...";

    public static final String FIND_DESCRIPTION = "find { [n/<name>] [sn/<serial number>] "
            + "[s/<source>] [l/<location>] }";

    public static final String FINDEXACT_DESCRIPTION = "findexact { [n/<name>] [sn/<serial number>]"
            + " [s/<source>] [l/<location>] }";

    public static final String UPDATE_DESCRIPTION = "update sn/<serial number> [sn/<serial number>]..."
            + " [iq/<increment value> | nq/<new quantity>] [n/<name>] [l/<location>] [lq/<low quantity>]";

    public static final String NOTE_DESCRIPTION = "note sn/<serial number> nt/<note>";

    public static final String NOTE_DELETE_DESCRIPTION = "notedelete sn/<serial number> ni/<note index>";

    public static final String STOCK_VIEW_DESCRIPTION = "stockview sn/<serial number>";

    public static final String STATISTICS_DESCRIPTION = "stats st/<statistics type>";

    public static final String SORT_DESCRIPTION = "sort o/<order> by/<field>";

    public static final String BOOKMARK_DESCRIPTION = "bookmark sn/<serial number> [sn/<serial number>]...";

    public static final String UNBOOKMARK_DESCRIPTION = "unbookmark sn/<serial number> [sn/<serial number>]...";

    public static final String PRINT_DESCRIPTION = "print fn/<file name>";

    public static final String CLEAR_DESCRIPTION = "clear";

    public static final String EXIT_DESCRIPTION = "exit";

    public static final String TAB_DESCRIPTION = "tab";

    public static final String DISCLAIMER = "For more information refer to the guide :";
    public static final String USERGUIDE_URL = "https://ay2021s1-cs2103t-t15-3.github.io/tp/UserGuide.html";
    public static final Hyperlink USERGUIDE_LINK = new Hyperlink(USERGUIDE_URL);

    private static final Logger logger = LogsCenter.getLogger(HelpWindow.class);
    private static final String FXML = "HelpWindow.fxml";

    @FXML
    private Label command;

    @FXML
    private Label commandDescription;

    @FXML
    private Label addMethod;

    @FXML
    private Label addMessage;

    @FXML
    private Label listMethod;

    @FXML
    private Label listMessage;

    @FXML
    private Label deleteMethod;

    @FXML
    private Label deleteMessage;

    @FXML
    private Label findMethod;

    @FXML
    private Label findMessage;

    @FXML
    private Label findExactMethod;

    @FXML
    private Label findExactMessage;

    @FXML
    private Label updateMethod;

    @FXML
    private Label updateMessage;

    @FXML
    private Label exitMethod;

    @FXML
    private Label exitMessage;

    @FXML
    private Label helpMethod;

    @FXML
    private Label helpMessage;

    @FXML
    private Label noteMethod;

    @FXML
    private Label noteMessage;

    @FXML
    private Label noteDeleteMethod;

    @FXML
    private Label noteDeleteMessage;

    @FXML
    private Label stockViewMethod;

    @FXML
    private Label stockViewMessage;

    @FXML
    private Label statsMethod;

    @FXML
    private Label statsMessage;

    @FXML
    private Label sortMethod;

    @FXML
    private Label sortMessage;

    @FXML
    private Label bookmarkMethod;

    @FXML
    private Label bookmarkMessage;

    @FXML
    private Label unbookmarkMethod;

    @FXML
    private Label unbookmarkMessage;

    @FXML
    private Label printMethod;

    @FXML
    private Label printMessage;

    @FXML
    private Label clearMethod;

    @FXML
    private Label clearMessage;

    @FXML
    private Label tabMethod;

    @FXML
    private Label tabMessage;

    @FXML
    private Label url;

    @FXML
    private Label disclaimer;

    /**
     * Creates a new HelpWindow.
     *
     * @param root Stage to use as the root of the HelpWindow.
     */
    public HelpWindow(Stage root) {
        super(FXML, root);

        //Comand header
        command.setText(COMMAND);
        commandDescription.setText(COMMAND_DESCRIPTION);

        //Help Method
        helpMethod.setText(HELP);
        helpMessage.setText(HELP_DESCRIPTION);

        //Add Method
        addMethod.setText(ADD);
        addMessage.setText(ADD_DESCRIPTION);

        //List Method
        listMethod.setText(LIST);
        listMessage.setText(LIST_DESCRIPTION);

        //Delete Method
        deleteMethod.setText(DELETE);
        deleteMessage.setText(DELETE_DESCRIPTION);

        //Update Method
        updateMethod.setText(UPDATE);
        updateMessage.setText(UPDATE_DESCRIPTION);

        //Note Method
        noteMethod.setText(NOTE);
        noteMessage.setText(NOTE_DESCRIPTION);

        //Note Delete Method
        noteDeleteMethod.setText(NOTEDELETE);
        noteDeleteMessage.setText(NOTE_DELETE_DESCRIPTION);

        //Stock View Method
        stockViewMethod.setText(STOCKVIEW);
        stockViewMessage.setText(STOCK_VIEW_DESCRIPTION);

        //Sort Method
        sortMethod.setText(SORT);
        sortMessage.setText(SORT_DESCRIPTION);

        //Find Method
        findMethod.setText(FIND);
        findMessage.setText(FIND_DESCRIPTION);

        //FindExact Method
        findExactMethod.setText(FINDEXACT);
        findExactMessage.setText(FINDEXACT_DESCRIPTION);

        //Exit Method
        exitMethod.setText(EXIT);
        exitMessage.setText(EXIT_DESCRIPTION);

        //Stats Method
        statsMethod.setText(STATISTICS);
        statsMessage.setText(STATISTICS_DESCRIPTION);

        //Bookmark Method
        bookmarkMethod.setText(BOOKMARK);
        bookmarkMessage.setText(BOOKMARK_DESCRIPTION);

        //Unbookmark Method
        unbookmarkMethod.setText(UNBOOKMARK);
        unbookmarkMessage.setText(UNBOOKMARK_DESCRIPTION);

        //Print Method
        printMethod.setText(PRINT);
        printMessage.setText(PRINT_DESCRIPTION);

        //Clear Method
        clearMethod.setText(CLEAR);
        clearMessage.setText(CLEAR_DESCRIPTION);

        //Tab method
        tabMethod.setText(TAB);
        tabMessage.setText(TAB_DESCRIPTION);

        //Disclaimer on where to click
        disclaimer.setText(DISCLAIMER);
        disclaimer.setStyle("-fx-font-size: 200%;");

        //setting the colours for the link
        url.setText(USERGUIDE_URL);
        url.setStyle("-fx-text-fill: #0b6ae0;" + "-fx-font-size: 200%;");
        url.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Application a = new Application() {

                    @Override
                    public void start(Stage stage) {
                    }
                };
                a.getHostServices().showDocument(USERGUIDE_LINK.getText());

            }
        });
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


}
