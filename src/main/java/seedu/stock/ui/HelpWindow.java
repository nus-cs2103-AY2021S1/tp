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

    public static final String ADD = "ADD \n---------------------------\n";
    public static final String DELETE = "---------------------------\nDELETE\n---------------------------\n";
    public static final String FIND = "---------------------------\nFIND\n---------------------------\n";
    public static final String UPDATE = "---------------------------\nUPDATE\n---------------------------\n";
    public static final String EXIT = "---------------------------\nEXIT\n---------------------------\n";

    public static final String ADD_DESCRIPTION =
            "format: \nadd n/<name> s/<source of stock> q/<quantity> l/<location in warehouse>\n\n" ;

    public static final String DELETE_DESCRIPTION =
            "format: \ndelete sn/<serial number>\n\n" ;

    public static final String FIND_DESCRIPTION =
            "format(any): \nfind n/<name> find sn/<serial number> find l/<location> find s/<source of the stock>\n\n" ;

    public static final String UPDATE_DESCRIPTION =
            "format(multiple): \nupdate sn/<Serial Number of product> (followed by one of):\n" +
            "q/<+/-><quantity to increment/decrement> nq/<new quantity> n/<new name> " +
            "l/<new location in warehouse> s/<new source of stock>\n\n";

    public static final String EXIT_DESCRIPTION =
            "format: \nexit\n\n" ;

    public static final String DISCLAIMER = "---------------------------\nFor more information refer to the guide :";
    public static final String USERGUIDE_URL = "https://github.com/AY2021S1-CS2103T-T15-3/tp/blob/master/docs/UserGuide.md";
    public static final Hyperlink USERGUIDE_LINK = new Hyperlink(USERGUIDE_URL);


    private static final Logger logger = LogsCenter.getLogger(HelpWindow.class);
    private static final String FXML = "HelpWindow.fxml";

    @FXML
    private Label addMethod;

    @FXML
    private Label addMessage;

    @FXML
    private Label deleteMethod;

    @FXML
    private Label deleteMessage;

    @FXML
    private Label findMethod;

    @FXML
    private Label findMessage;

    @FXML
    private Label updateMethod;

    @FXML
    private Label updateMessage;

    @FXML
    private Label exitMethod;

    @FXML
    private Label exitMessage;

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
        //Add Method
        addMethod.setText(ADD);
        addMethod.setStyle("-fx-font-size: 130%;");
        addMessage.setText(ADD_DESCRIPTION);

        //Delete Method
        deleteMethod.setText(DELETE);
        deleteMethod.setStyle("-fx-font-size: 130%;");
        deleteMessage.setText(DELETE_DESCRIPTION);

        //Update Method
        updateMethod.setText(UPDATE);
        updateMethod.setStyle("-fx-font-size: 130%;");
        updateMessage.setText(UPDATE_DESCRIPTION);

        //Find Method
        findMethod.setText(FIND);
        findMethod.setStyle("-fx-font-size: 130%;");
        findMessage.setText(FIND_DESCRIPTION);

        //Exit Method
        exitMethod.setText(EXIT);
        exitMethod.setStyle("-fx-font-size: 130%;");
        exitMessage.setText(EXIT_DESCRIPTION);


        //Disclaimer on where to click
        disclaimer.setText(DISCLAIMER);
        disclaimer.setStyle("-fx-font-size: 110%;");

        // setting the colours for the link
        url.setText(USERGUIDE_URL);
        url.setStyle("-fx-text-fill: #0b6ae0;");
        url.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Application a = new Application() {

                    @Override
                    public void start(Stage stage)
                    {
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
