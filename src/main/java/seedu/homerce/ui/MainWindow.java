package seedu.homerce.ui;

import java.util.Optional;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import seedu.homerce.commons.core.GuiSettings;
import seedu.homerce.commons.core.LogsCenter;
import seedu.homerce.logic.Logic;
import seedu.homerce.logic.commands.CommandResult;
import seedu.homerce.logic.commands.exceptions.CommandException;
import seedu.homerce.logic.parser.exceptions.ParseException;
import seedu.homerce.ui.appointmentpanel.AppointmentListPanel;
import seedu.homerce.ui.clientpanel.ClientListPanel;
import seedu.homerce.ui.expensepanel.ExpenseListPanel;
import seedu.homerce.ui.financialpanel.FinanceWindow;
import seedu.homerce.ui.revenuepanel.RevenueListPanel;
import seedu.homerce.ui.schedulepanel.SchedulePanel;
import seedu.homerce.ui.servicepanel.ServiceListPanel;

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
    private ResultDisplay resultDisplay;
    private HelpWindow helpWindow;
    private FinanceWindow financeWindow;

    // Panels for each component
    private ServiceListPanel serviceListPanel;
    private ClientListPanel clientListPanel;
    private AppointmentListPanel appointmentListPanel;
    private RevenueListPanel revenueListPanel;
    private ExpenseListPanel expenseListPanel;
    private SchedulePanel schedulePanel;

    @FXML
    private StackPane commandBoxPlaceholder;

    @FXML
    private MenuItem helpMenuItem;

    @FXML
    private StackPane resultDisplayPlaceholder;

    @FXML
    private StackPane statusbarPlaceholder;

    @FXML
    private StackPane sideTabsBarPlaceholder;

    @FXML
    private StackPane tabPanelPlaceholder;

    /**
     * Creates a {@code MainWindow} with the given {@code Stage} and {@code Logic}.
     */
    public MainWindow(Stage primaryStage, Logic logic) {
        super(FXML, primaryStage);

        // Set dependencies
        this.primaryStage = primaryStage;
        this.logic = logic;

        // Configure the UI
        //setWindowDefaultSize(logic.getGuiSettings());
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        primaryStage.setWidth((primScreenBounds.getWidth() * 0.55));
        primaryStage.setHeight((primScreenBounds.getHeight() * 0.90));

        //setAccelerators();

        helpWindow = new HelpWindow();
        financeWindow = new FinanceWindow();
        financeWindow.setWindowDefaultSize(logic.getGuiSettings());
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    /**
     * Fills up all the placeholders of this window.
     */
    void fillInnerParts() {
        serviceListPanel = new ServiceListPanel(logic.getFilteredServiceList());

        clientListPanel = new ClientListPanel(logic.getFilteredClientList());

        revenueListPanel = new RevenueListPanel(logic.getFilteredRevenueList());

        expenseListPanel = new ExpenseListPanel(logic.getFilteredExpenseList());

        appointmentListPanel = new AppointmentListPanel(logic.getFilteredAppointmentList());

        // Default view for user on app startup
        switchTab(SchedulePanel.TAB_NAME);
        sideTabsBarPlaceholder.getChildren().add(new SideTabsBar(this::switchTab).getRoot());

        resultDisplay = new ResultDisplay();
        resultDisplayPlaceholder.getChildren().add(resultDisplay.getRoot());

        CommandBox commandBox = new CommandBox(this::executeCommand);
        commandBoxPlaceholder.getChildren().add(commandBox.getRoot());
    }

    /**
     * Switches tab to the specified tab name.
     */
    private void switchTab(String tabName) {
        logger.info("Switching tab to: " + tabName);
        tabPanelPlaceholder.getChildren().clear();
        statusbarPlaceholder.getChildren().clear();
        statusbarPlaceholder.getChildren().add(new StatusBarFooter(tabName).getRoot());

        switch (tabName) {
        case ClientListPanel.TAB_NAME:
            tabPanelPlaceholder.getChildren().add(clientListPanel.getRoot());
            break;
        case ServiceListPanel.TAB_NAME:
            tabPanelPlaceholder.getChildren().add(serviceListPanel.getRoot());
            break;
        case AppointmentListPanel.TAB_NAME:
            tabPanelPlaceholder.getChildren().add(appointmentListPanel.getRoot());
            break;
        case RevenueListPanel.TAB_NAME:
            tabPanelPlaceholder.getChildren().add(revenueListPanel.getRoot());
            break;
        case ExpenseListPanel.TAB_NAME:
            tabPanelPlaceholder.getChildren().add(expenseListPanel.getRoot());
            break;
        case SchedulePanel.TAB_NAME:
            schedulePanel = new SchedulePanel(logic.getFilteredSchedule(), logic.getAppointmentManager());
            schedulePanel.construct();
            tabPanelPlaceholder.getChildren().add(schedulePanel.getRoot());
            break;
        default:
            throw new AssertionError("No such tab name: " + tabName);
        }
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
     * Opens the finance breakdown window or focuses on it if it's already opened.
     */
    @FXML
    public void handleFinance() {
        if (!financeWindow.isShowing()) {
            financeWindow.construct(logic.getFilteredExpenseList(), logic.getFilteredRevenueList());
            financeWindow.show();
        } else {
            financeWindow.focus();
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

    /**
     * Executes the command and returns the result.
     *
     * @see seedu.homerce.logic.Logic#execute(String)
     */
    private CommandResult executeCommand(String commandText) throws CommandException, ParseException {
        try {
            CommandResult commandResult = logic.execute(commandText);
            logger.info("Result: " + commandResult.getFeedbackToUser());
            resultDisplay.setFeedbackToUser(commandResult.getFeedbackToUser());
            //If a tab name to switch is present in command result, switch the tab.
            Optional<String> tabNameToSwitch = commandResult.getTabNameToNavigate();
            if (tabNameToSwitch.isPresent()) {
                logger.info("Found tab name in command result.");
                String tabName = tabNameToSwitch.get();
                switchTab(tabName);
            }
            if (commandResult.isShowHelp()) {
                handleHelp();
            }
            if (commandResult.isShowFinance()) {
                handleFinance();
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

    public void showWelcomeText() {
        resultDisplay.setFeedbackToUser("To see the list of commands, type and enter the command 'help'.");
    }
}
