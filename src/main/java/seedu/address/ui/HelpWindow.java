package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;

/**
 * Controller for a help page
 */
public class HelpWindow extends UiPart<Stage> {

    public static final String USERGUIDE_URL = "https://tinyurl.com/candidatesUG";
    public static final String HELP_MESSAGE = "Refer to the user guide for more information: " + USERGUIDE_URL;

    public static final String TABLE_HEADER = "Command Summary";
    public static final String TABLE_FIRST_COLUMN = "Action";
    public static final String TABLE_SECOND_COLUMN = "Candidate Format";
    public static final String TABLE_THIRD_COLUMN = "Job Format";

    public static final String ADD_ACTION = "  Add";
    public static final String LIST_ACTION = "  List";
    public static final String EDIT_ACTION = "  Edit";
    public static final String FIND_ACTION = "  Find";
    public static final String SORT_ACTION = "  Sort";
    public static final String DELETE_ACTION = "  Delete";
    public static final String CLEAR_ACTION = "  Clear";
    public static final String VIEW_ACTION = "  View";
    public static final String HELP_ACTION = "  Help";
    public static final String EXIT_ACTION = "  Exit";

    // Candidate Formats
    public static final String ADD_CANDIDATE_FORMAT = "   add can\n   n/NAME\n   p/PHONE_NUMBER\n   e/EMAIL\n"
            + "   exp/YEARS_OF_EXPERIENCE\n   doa/DATE_OF_APPLICATION\n   [a/ADDRESS]\n   [sal/EXPECTED_SALARY]\n   "
            + "[bl/IS_BLACKLISTED]\n   [link/PROFILE_LINK]\n   [t/JOB_TYPE]… ";
    public static final String LIST_CANDIDATE_FORMAT = "   list can";
    public static final String EDIT_CANDIDATE_FORMAT = "   edit can INDEX\n   "
            + "[n/NAME]\n   [p/PHONE_NUMBER]\n   [e/EMAIL]\n   [exp/YEARS_OF_EXPERIENCE]\n   "
            + "[doa/DATE_OF_APPLICATION]\n   [a/ADDRESS]\n   [sal/EXPECTED_SALARY]\n   "
            + "[bl/IS_BLACKLISTED]\n   [link/PROFILE_LINK]\n   [t/JOB_TYPE]…";
    public static final String FIND_CANDIDATE_FORMAT = "   find can\n   [n/NAME]\n   [p/PHONE_NUMBER]\n   "
            + "[e/EMAIL]\n   [a/ADDRESS]\n   [exp/YEARS_OF_EXPERIENCE]\n   "
            + "[doa/DATE_OF_APPLICATION]\n   [sal/EXPECTED_SALARY]\n   "
            + "[bl/IS_BLACKLISTED]\n   [link/PROFILE_LINK]\n   [t/JOB_TYPE]…";
    public static final String SORT_CANDIDATE_FORMAT = "   sort can\n   type/FIELD_TO_SORT\n   order/ORDER";
    public static final String DELETE_CANDIDATE_FORMAT = "   delete can INDEX";
    public static final String CLEAR_CANDIDATE_FORMAT = "   clear can";
    public static final String VIEW_CANDIDATE_FORMAT = "   view can INDEX";

    //Job Formats
    public static final String ADD_JOB_FORMAT = "   add job\n   n/JOB_TITLE\n   c/COMPANY_NAME\n   "
            + "p/PHONE_NUMBER\n   e/EMAIL\n   a/ADDRESS\n   v/VACANCY\n   [pr/PRIORITY]\n   [t/JOB_DESCRIPTION]…";
    public static final String LIST_JOB_FORMAT = "   list job";
    public static final String EDIT_JOB_FORMAT = "   edit job INDEX\n   [n/JOB_TITLE]\n   "
            + "[c/COMPANY_NAME]\n   [p/PHONE]\n   [e/EMAIL]\n   "
            + "[a/ADDRESS]\n   [v/VACANCY]\n   [pr/PRIORITY]\n   [t/JOB_DESCRIPTION]…";
    public static final String FIND_JOB_FORMAT = "   find job\n   [n/JOB_TITLE]\n   [c/COMPANY_NAME]\n   [p/PHONE]\n"
            + "   [e/EMAIL]\n   " + "[a/ADDRESS]\n   [v/VACANCY]\n   [pr/PRIORITY]\n   [t/JOB_DESCRIPTION]…";
    public static final String SORT_JOB_FORMAT = "   sort job\n   type/FIELD_TO_SORT\n   order/ORDER";
    public static final String DELETE_JOB_FORMAT = "   delete job INDEX";
    public static final String CLEAR_JOB_FORMAT = "   clear job";
    public static final String VIEW_JOB_FORMAT = "   view job INDEX";

    //General Formats
    public static final String HELP_FORMAT = "   help";
    public static final String EXIT_FORMAT = "   exit";

    //@@author KishenKumarrrrr-reused
    //Reused from https://tinyurl.com/y5ahoa7f with modifications
    private static final ObservableList<Row> tableRows =
            FXCollections.observableArrayList(
                    new Row(ADD_ACTION, ADD_CANDIDATE_FORMAT, ADD_JOB_FORMAT),
                    new Row(LIST_ACTION, LIST_CANDIDATE_FORMAT, LIST_JOB_FORMAT),
                    new Row(EDIT_ACTION, EDIT_CANDIDATE_FORMAT, EDIT_JOB_FORMAT),
                    new Row(FIND_ACTION, FIND_CANDIDATE_FORMAT, FIND_JOB_FORMAT),
                    new Row(SORT_ACTION, SORT_CANDIDATE_FORMAT, SORT_JOB_FORMAT),
                    new Row(DELETE_ACTION, DELETE_CANDIDATE_FORMAT, DELETE_JOB_FORMAT),
                    new Row(CLEAR_ACTION, CLEAR_CANDIDATE_FORMAT, CLEAR_JOB_FORMAT),
                    new Row(VIEW_ACTION, VIEW_CANDIDATE_FORMAT, VIEW_JOB_FORMAT),
                    new Row(HELP_ACTION, HELP_FORMAT, HELP_FORMAT),
                    new Row(EXIT_ACTION, EXIT_FORMAT, EXIT_FORMAT));

    //@@author
    private static final Logger logger = LogsCenter.getLogger(HelpWindow.class);
    private static final String FXML = "HelpWindow.fxml";

    @FXML
    private Button copyButton;

    @FXML
    private Label helpMessage;

    @FXML
    private Label tableHeader;

    @FXML
    private TableColumn<Row, String> action;

    @FXML
    private TableColumn<Row, String> candidateFormat;

    @FXML
    private TableColumn<Row, String> jobFormat;

    @FXML
    private TableView<Row> table;


    /**
     * Creates a new HelpWindow.
     *
     * @param root Stage to use as the root of the HelpWindow.
     */
    public HelpWindow(Stage root) {
        super(FXML, root);
        helpMessage.setText(HELP_MESSAGE);
        helpMessage.setFont(new Font("Arial", 15));
        tableHeader.setText(TABLE_HEADER);
        tableHeader.setFont(new Font("Arial", 20));
        action.setText(TABLE_FIRST_COLUMN);
        candidateFormat.setText(TABLE_SECOND_COLUMN);
        jobFormat.setText(TABLE_THIRD_COLUMN);

        //@@author KishenKumarrrrr-reused
        //Reused from https://tinyurl.com/y5ahoa7f with modifications
        action.setCellValueFactory(new PropertyValueFactory<Row, String>("action"));
        candidateFormat.setCellValueFactory(new PropertyValueFactory<Row, String>("candidateFormat"));
        jobFormat.setCellValueFactory(new PropertyValueFactory<Row, String>("jobFormat"));
        table.setItems(tableRows);
    }

    //@@author
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

    //@@author Jakob Jenkov-reused
    //Reused from http://tutorials.jenkov.com/javafx/tableview.html with modifications
    public static class Row {
        private String action;
        private String candidateFormat;
        private String jobFormat;

        private Row(String action, String candidateFormat, String jobFormat) {
            this.action = action;
            this.candidateFormat = candidateFormat;
            this.jobFormat = jobFormat;
        }

        public String getAction() {
            return action;
        }

        public String getCandidateFormat() {
            return candidateFormat;
        }

        public String getJobFormat() {
            return jobFormat;
        }
    }
}
