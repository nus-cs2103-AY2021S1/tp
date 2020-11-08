package seedu.stock.ui;

import static seedu.stock.logic.commands.statisticsutil.GenerateStatisticsData.generateSourceQuantityDistributionStatisticsData;
import static seedu.stock.logic.commands.statisticsutil.GenerateStatisticsData.generateSourceStatisticsData;

import java.util.Map;
import java.util.Optional;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.TabPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import seedu.stock.commons.core.GuiSettings;
import seedu.stock.commons.core.LogsCenter;
import seedu.stock.logic.Logic;
import seedu.stock.logic.commands.CommandResult;
import seedu.stock.logic.commands.SourceQuantityDistributionStatisticsCommand;
import seedu.stock.logic.commands.SourceStatisticsCommand;
import seedu.stock.logic.commands.exceptions.CommandException;
import seedu.stock.logic.commands.exceptions.SerialNumberNotFoundException;
import seedu.stock.logic.commands.exceptions.SourceCompanyNotFoundException;
import seedu.stock.logic.parser.exceptions.ParseException;
import seedu.stock.model.stock.Stock;


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
    private StockListPanel stockListPanel;
    private StockViewWindow stockViewWindow;
    private ResultDisplay resultDisplay;
    private HelpWindow helpWindow;
    private StatisticsWindow statisticsWindow;

    @FXML
    private Scene scene;

    @FXML
    private TabPane tabPane;

    @FXML
    private StackPane statisticsWindowPlaceHolder;

    @FXML
    private StackPane commandBoxPlaceholder;

    @FXML
    private StackPane stockListPanelPlaceholder;

    @FXML
    private StackPane stockViewWindowPlaceholder;

    @FXML
    private StackPane resultDisplayPlaceholder;

    @FXML
    private StackPane statusbarPlaceholder;

    @FXML
    private PieChart defaultStockViewPie;

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

        helpWindow = new HelpWindow();
        //custom fonts
        scene.getStylesheets().add("https://fonts.googleapis.com/css2?family=Montserrat:wght@400;600;700&display=swap");
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    /**
     * Fills up all the placeholders of this window.
     */
    void fillInnerParts() {

        stockListPanel = new StockListPanel(logic.getFilteredStockList());
        stockListPanelPlaceholder.getChildren().add(stockListPanel.getRoot());

        resultDisplay = new ResultDisplay();
        resultDisplayPlaceholder.getChildren().add(resultDisplay.getRoot());

        StatusBarFooter statusBarFooter = new StatusBarFooter(logic.getStockBookFilePath());
        statusbarPlaceholder.getChildren().add(statusBarFooter.getRoot());

        CommandBox commandBox = new CommandBox(this::executeCommand);
        commandBoxPlaceholder.getChildren().add(commandBox.getRoot());

        statisticsWindow = new StatisticsWindow();
        statisticsWindowPlaceHolder.getChildren().add(statisticsWindow.getRoot());

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

    /**
     * Switches to and updates the statistics window.
     */
    @FXML
    public void handleStatistics(Map<String, Integer> statisticsData, String[] otherStatisticsDetails) {
        //jump to statistics tab
        tabPane.getSelectionModel().select(1);
        statisticsWindow.updateData(statisticsData, otherStatisticsDetails);
    }

    /**
     * Updates the statistics window.
     */
    @FXML
    public void updateStatistics(Map<String, Integer> statisticsData, String[] otherStatisticsDetails) {
        statisticsWindow.updateData(statisticsData, otherStatisticsDetails);
    }

    /**
     * Switches to and updates the stock view window.
     */
    @FXML
    public void handleStockView(Optional<Stock> stockToView) {
        //jump to stock view tab
        tabPane.getSelectionModel().select(2);
        updateStockView(stockToView);
    }

    /**
     * Updates the stock view window.
     */
    @FXML
    public void updateStockView(Optional<Stock> optionalStockToView) {

        if (optionalStockToView.isPresent()) {

            Stock stockToView = optionalStockToView.get();

            String nameString = "Name: " + stockToView.getName().fullName;
            String serialNumberString = "Serial Number: " + stockToView.getSerialNumber().toString();
            String sourceString = "Source: " + stockToView.getSource().value;
            String quantityString = "Quantity: " + "Quantity left: " + stockToView.getQuantity().quantity
                    + "\nLow Quantity: " + stockToView.getQuantity().lowQuantity;
            String location = "Location: " + stockToView.getLocation().value;
            String notes = "Notes: " + stockToView.notesToString(stockToView.getNotes());

            ObservableList<String> fieldList = FXCollections.observableArrayList();
            fieldList.addAll(nameString, serialNumberString, sourceString, quantityString,
                    location, notes);

            stockViewWindowPlaceholder.getChildren().remove(defaultStockViewPie);
            stockViewWindow = new StockViewWindow(fieldList);
            stockViewWindowPlaceholder.getChildren().add(stockViewWindow.getRoot());

        } else {

            stockViewWindow = new StockViewWindow();
            stockViewWindowPlaceholder.getChildren().add(stockViewWindow.getRoot());

        }

    }

    /**
     * Toggles the tabs in Warenager.
     */
    @FXML
    public void handleTab() {
        if (tabPane.getSelectionModel().getSelectedIndex() == 2) {
            tabPane.getSelectionModel().select(0);
        } else {
            tabPane.getSelectionModel().selectNext();
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
        GuiSettings guiSettings = new GuiSettings(primaryStage.getWidth(), primaryStage.getHeight(),
                (int) primaryStage.getX(), (int) primaryStage.getY());
        logic.setGuiSettings(guiSettings);
        helpWindow.hide();
        primaryStage.hide();
    }

    public StockListPanel getStockListPanel() {
        return stockListPanel;
    }

    /**
     * Executes the command and returns the result.
     *
     * @see seedu.stock.logic.Logic#execute(String)
     */
    @FXML
    private CommandResult executeCommand(String commandText) throws CommandException, ParseException,
            SourceCompanyNotFoundException, SerialNumberNotFoundException {
        try {
            CommandResult commandResult = logic.execute(commandText);
            logger.info("Result: " + commandResult.getFeedbackToUser());
            resultDisplay.setFeedbackToUser(commandResult.getFeedbackToUser());
            stockListPanel = new StockListPanel(logic.getFilteredStockList());
            stockListPanelPlaceholder.getChildren().add(stockListPanel.getRoot());

            if (commandResult.isSwitchTab()) {
                handleTab();
                return commandResult;
            }

            if (commandResult.isShowStatistics()) {
                String[] otherStatisticsDetails = commandResult.getOtherStatisticsDetails();
                Map<String, Integer> statisticsData = commandResult.getStatisticsData();
                handleStatistics(statisticsData, otherStatisticsDetails);
                return commandResult;
            }

            //jump back to main tab
            tabPane.getSelectionModel().select(0);
            String[] otherStatisticsDetails = statisticsWindow.getOtherStatisticsDetails();

            //when statistics command has been called at least once
            if (otherStatisticsDetails != null) {
                String statisticsType = otherStatisticsDetails[0];
                Map<String, Integer> data;
                switch (statisticsType) {

                case SourceStatisticsCommand.STATISTICS_TYPE:
                    data = generateSourceStatisticsData(logic.getModel());
                    updateStatistics(data, otherStatisticsDetails);
                    break;

                case SourceQuantityDistributionStatisticsCommand.STATISTICS_TYPE:
                    String targetSource = otherStatisticsDetails[1];
                    data = generateSourceQuantityDistributionStatisticsData(logic.getModel(), targetSource);
                    updateStatistics(data, otherStatisticsDetails);
                    break;

                default:
                    break;
                }
            }

            if (commandResult.isShowStockView()) {
                Stock stockToView = commandResult.getStockToView();
                handleStockView(Optional.of(stockToView));
            } else {
                // stock view window used before
                if (stockViewWindow != null) {
                    ObservableList<Stock> stockList = logic.getModel().getStockBook().getStockList();
                    Optional<Stock> stockToView = stockViewWindow.getStockToView(stockList);
                    updateStockView(stockToView);
                }
            }

            if (commandResult.isShowHelp()) {
                handleHelp();
            }

            if (commandResult.isExit()) {
                handleExit();
            }

            return commandResult;
        } catch (CommandException | ParseException | SourceCompanyNotFoundException
                | SerialNumberNotFoundException e) {

            tabPane.getSelectionModel().select(0);
            logger.info("Invalid command: " + commandText);
            resultDisplay.setFeedbackToUser(e.getMessage());
            throw e;
        }
    }

}

