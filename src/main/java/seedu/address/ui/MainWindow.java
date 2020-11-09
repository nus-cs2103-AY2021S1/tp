package seedu.address.ui;

import java.util.Optional;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextInputControl;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.information.Job;
import seedu.address.model.information.Person;

/**
 * The Main Window. Provides the basic application layout containing
 * a menu bar and space where other JavaFX elements can be placed.
 */
public class MainWindow extends UiPart<Stage> {

    private static final String FXML = "MainWindow.fxml";

    private final Logger logger = LogsCenter.getLogger(getClass());
    private final Image image = new Image(this.getClass().getResourceAsStream("/images/CANdidates1.png"));

    private Stage primaryStage;
    private Logic logic;

    // Independent Ui parts residing in this Ui container
    private ResultDisplay resultDisplay;
    private HelpWindow helpWindow;

    @FXML
    private StackPane commandBoxPlaceholder;

    @FXML
    private MenuItem helpMenuItem;

    @FXML
    private StackPane resultDisplayPlaceholder;

    @FXML
    private TabPane tabPane;

    @FXML
    private StackPane personListPanelPlaceholder;

    @FXML
    private StackPane jobListPanelPlaceholder;

    @FXML
    private StackPane detailedView;

    @FXML
    private ListView<Person> personListView;

    @FXML
    private ScrollPane scrollPane;


    /**
     * Creates a {@code MainWindow} with the given {@code Stage} and {@code Logic}.
     */
    public MainWindow(Stage primaryStage, Logic logic) {
        super(FXML, primaryStage);

        // Set dependencies
        this.primaryStage = primaryStage;
        this.logic = logic;

        // Configure the UI
        setWindowDefaultSize(logic.getGuiSettings());

        setAccelerators();

        helpWindow = new HelpWindow();


    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    private void setAccelerators() {
        setAccelerator(helpMenuItem, KeyCombination.valueOf("F1"));
    }

    /**
     * Sets the accelerator of a MenuItem.
     * @param keyCombination the KeyCombination value of the accelerator
     */
    private void setAccelerator(MenuItem menuItem, KeyCombination keyCombination) {
        menuItem.setAccelerator(keyCombination);

        /*
         * TODO: the code below can be removed once the bug reported here
         * https://bugs.openjdk.java.net/browse/JDK-8131666
         * is fixed in later version of SDK.
         *
         * According to the bug report, TextInputControl (TextField, TextArea) will
         * consume function-key events. Because CommandBox contains a TextField, and
         * ResultDisplay contains a TextArea, thus some accelerators (e.g F1) will
         * not work when the focus is in them because the key event is consumed by
         * the TextInputControl(s).
         *
         * For now, we add following event filter to capture such key events and open
         * help window purposely so to support accelerators even when focus is
         * in CommandBox or ResultDisplay.
         */
        getRoot().addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getTarget() instanceof TextInputControl && keyCombination.match(event)) {
                menuItem.getOnAction().handle(new ActionEvent());
                event.consume();
            }
        });
    }

    /**
     * Fills up all the placeholders of this window.
     */
    void fillInnerParts() {

        resultDisplay = new ResultDisplay();
        resultDisplayPlaceholder.getChildren().add(resultDisplay.getRoot());

        PersonListPanel personListPanel = new PersonListPanel(logic.getFilteredPersonList(), this);
        JobListPanel jobListPanel = new JobListPanel(logic.getFilteredJobList(), this);
        personListPanelPlaceholder.getChildren().add(personListPanel.getRoot());
        jobListPanelPlaceholder.getChildren().add(jobListPanel.getRoot());

        CommandBox commandBox = new CommandBox(this::executeCommand);
        commandBoxPlaceholder.getChildren().add(commandBox.getRoot());

        setUpRightPanelLogo();
    }

    /**
     * Sets the default size based on {@code guiSettings}.
     */
    private void setWindowDefaultSize(GuiSettings guiSettings) {
        primaryStage.setHeight(guiSettings.getWindowHeight());
        primaryStage.setWidth(guiSettings.getWindowWidth());
        if (guiSettings.getWindowCoordinates() != null) {
            primaryStage.setX(guiSettings.getWindowCoordinates().getX());
            primaryStage.setY(guiSettings.getWindowCoordinates().getY());
        }
    }


    /**
     * Opens the help window or focuses on it if it's already opened.
     */
    @FXML
    public void handleHelp() {
        if (!helpWindow.isShowing()) {
            helpWindow.show();
        } else {
            helpWindow.focus();
        }
    }

    void show() {
        primaryStage.show();
    }

    /**
     * Updates the detailed view on the right panel with the supplied {@code Person}.
     * @param person
     */
    public void updateDetailedPersonPanel(Person person) {
        PersonDetailedView personDetailedView = new PersonDetailedView(person);
        detailedView.getChildren().clear();
        detailedView.getChildren().add(personDetailedView.getRoot());
    }

    /**
     * Updates the detailed view on the right panel with the supplied {@code Job}.
     */
    public void updateDetailedJobPanel(Job job) {
        JobDetailedView jobDetailedView = new JobDetailedView(job);
        detailedView.getChildren().clear();
        detailedView.getChildren().add(jobDetailedView.getRoot());
    }

    /**
     * Sets up and display CANdidates logo on the right panel upon launching of the app.
     */
    private void setUpRightPanelLogo() {
        ImageView logoPlaceHolder = new ImageView();
        logoPlaceHolder.setFitWidth(550);
        logoPlaceHolder.setTranslateY(-340);
        logoPlaceHolder.setTranslateX(20);
        logoPlaceHolder.setPreserveRatio(true);
        logoPlaceHolder.setY(10);
        logoPlaceHolder.setImage(image);
        detailedView.getChildren().add(logoPlaceHolder);
    }
    /**
     * Switches tab to the desired tab.
     */
    private void switchTab(String tabName) {
        switch (tabName) {
        case Person.TAB_NAME:
            tabPane.getSelectionModel().select(0);
            break;
        case Job.TAB_NAME:
            tabPane.getSelectionModel().select(1);
            break;
        default:
            throw new AssertionError("No such tab name " + tabName);
        }
    }

    /**
     * Closes the application.
     */
    @FXML
    private void handleExit() {
        GuiSettings guiSettings = new GuiSettings(primaryStage.getWidth(), primaryStage.getHeight(),
                (int) primaryStage.getX(), (int) primaryStage.getY());
        logic.setGuiSettings(guiSettings);
        helpWindow.hide();
        primaryStage.hide();
    }

    /**
     * Executes the command and returns the result.
     *
     * @see seedu.address.logic.Logic#execute(String)
     */
    private CommandResult executeCommand(String commandText) throws CommandException, ParseException {
        try {
            CommandResult commandResult = logic.execute(commandText);
            logger.info("Result: " + commandResult.getFeedbackToUser());
            resultDisplay.setFeedbackToUser(commandResult.getFeedbackToUser());
            Optional<String> tabNameToDisplay = commandResult.getTabName();
            if (tabNameToDisplay.isPresent()) {
                System.out.println(tabNameToDisplay.get());
                switchTab(tabNameToDisplay.get());
            }
            if (commandResult.isPersonRightPanelView()) {
                updateDetailedPersonPanel(logic.getDisplayedPerson());
            }
            if (commandResult.isJobRightPanelView()) {
                updateDetailedJobPanel(logic.getDisplayedJob());
            }
            if (!commandResult.isJobRightPanelView() && !commandResult.isPersonRightPanelView()) {
                detailedView.getChildren().clear();
            }
            if (commandResult.isShowHelp()) {
                handleHelp();
            }
            if (commandResult.isExit()) {
                handleExit();
            }
            return commandResult;
        } catch (CommandException | ParseException e) {
            logger.info("Invalid command: " + commandText);
            resultDisplay.setFeedbackToUser(e.getMessage());
            throw e;
        }
    }
}
