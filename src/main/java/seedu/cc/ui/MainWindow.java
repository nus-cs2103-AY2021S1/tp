package seedu.cc.ui;

import static seedu.cc.logic.commands.ExitCommand.MESSAGE_EXIT_ACKNOWLEDGEMENT;

import java.util.logging.Logger;

import javafx.animation.PauseTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Side;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;
import seedu.cc.commons.core.GuiSettings;
import seedu.cc.commons.core.LogsCenter;
import seedu.cc.logic.Logic;
import seedu.cc.logic.commands.CommandResult;
import seedu.cc.logic.commands.exceptions.CommandException;
import seedu.cc.logic.parser.exceptions.ParseException;

/**
 * The Main Window. Provides the basic application layout containing
 * a menu bar and space where other JavaFX elements can be placed.
 */
public class MainWindow extends UiPart<Stage> {

    private static final String FXML = "MainWindow.fxml";

    private final Logger logger = LogsCenter.getLogger(getClass());

    private Stage primaryStage;
    private Logic logic;

    // Independent Ui parts residing in this Ui container
    private ExpenseListPanel expenseListPanel;
    private RevenueListPanel revenueListPanel;
    private ResultDisplay resultDisplay;
    private HelpWindow helpWindow;
    private LabeledPieChart pieChart;

    @FXML
    private HBox accountNameBar;

    @FXML
    private Label activeAccountName;

    @FXML
    private HBox commandBoxPlaceholder;

    @FXML
    private Circle accountDisplayPicture;

    @FXML
    private MenuItem helpMenuItem;

    @FXML
    private HBox entryListPlaceholder;

    @FXML
    private StackPane pieChartPlaceholder;

    @FXML
    private StackPane expenseListPanelPlaceholder;

    @FXML
    private StackPane revenueListPanelPlaceholder;

    @FXML
    private StackPane resultDisplayPlaceholder;

    @FXML
    private StackPane statusbarPlaceholder;

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
        // Image img = new Image(this.getClass().getResourceAsStream("/images/sampleDisplayPicture.png"));
        // accountDisplayPicture.setFill(new ImagePattern(img));
        // TODO: Dummy colour, to be changed later
        accountDisplayPicture.setFill(Color.web("ffb997"));

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
    public void fillInnerParts() {
        fillEntryDisplay();
        createPieChart();
        updateActiveAccountName();

        accountNameBar.getChildren().setAll(activeAccountName);

        resultDisplay = new ResultDisplay();
        resultDisplayPlaceholder.getChildren().add(resultDisplay.getRoot());

        StatusBarFooter statusBarFooter = new StatusBarFooter(logic.getCommonCentsFilePath());
        statusbarPlaceholder.getChildren().add(statusBarFooter.getRoot());

        CommandBox commandBox = new CommandBox(this::executeCommand);
        commandBoxPlaceholder.getChildren().addAll(commandBox.getRoot());
    }

    private void fillEntryDisplay() {
        expenseListPanel = new ExpenseListPanel(logic.getFilteredExpenseList());
        entryListPlaceholder.getChildren().add(expenseListPanel.getRoot());

        revenueListPanel = new RevenueListPanel(logic.getFilteredRevenueList());
        entryListPlaceholder.getChildren().add(revenueListPanel.getRoot());
    }

    void updateActiveAccountName() {
        activeAccountName.setText(logic.getActiveAccountName().toString());
    }

    private void updatePieChart() {
        ObservableList<PieChart.Data> pieChartData = createPieChartDataFromLogic();
        setPieChartData(pieChartData);
    }

    void createPieChart() {
        ObservableList<PieChart.Data> pieChartData = createPieChartDataFromLogic();
        pieChart = new LabeledPieChart();
        setPieChartData(pieChartData);
        pieChart.setLegendSide(Side.LEFT);
        pieChartPlaceholder.getChildren().add(pieChart);
    }

    private ObservableList<PieChart.Data> createPieChartDataFromLogic() {
        return FXCollections.observableArrayList(
            new PieChart.Data("Expense", logic.getTotalExpense()),
            new PieChart.Data("Revenue", logic.getTotalRevenue())
        );
    }

    private void setPieChartData(ObservableList<PieChart.Data> pieChartData) {
        pieChart.getData().setAll(pieChartData);
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
     * Closes the application.
     */
    @FXML
    private void handleExit() {
        resultDisplay.setFeedbackToUser(MESSAGE_EXIT_ACKNOWLEDGEMENT);
        PauseTransition pause = new PauseTransition(Duration.seconds(1.5f));
        pause.setOnFinished(event -> {
            GuiSettings guiSettings = new GuiSettings(primaryStage.getWidth(), primaryStage.getHeight(),
                    (int) primaryStage.getX(), (int) primaryStage.getY());
            logic.setGuiSettings(guiSettings);
            helpWindow.hide();
            primaryStage.hide();
        });
        commandBoxPlaceholder.setDisable(true);
        pause.play();
    }

    public ExpenseListPanel getEntryListPanel() {
        return expenseListPanel;
    }

    public RevenueListPanel getRevenueListPanel() {
        return revenueListPanel;
    }

    /**
     * Executes the command and returns the result.
     *
     * @see seedu.cc.logic.Logic#execute(String)
     */
    private CommandResult executeCommand(String commandText) throws CommandException, ParseException {
        try {
            CommandResult commandResult = logic.execute(commandText);
            logger.info("Result: " + commandResult.getFeedbackToUser());
            resultDisplay.setFeedbackToUser(commandResult.getFeedbackToUser());

            if (commandResult.isEntryListChange()) {
                updatePieChart();
            }

            updateActiveAccountName();

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
