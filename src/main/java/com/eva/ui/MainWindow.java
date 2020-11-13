package com.eva.ui;

import java.io.FileNotFoundException;
import java.util.logging.Logger;

import com.eva.commons.core.GuiSettings;
import com.eva.commons.core.LogsCenter;
import com.eva.commons.core.PanelState;
import com.eva.logic.Logic;
import com.eva.logic.commands.CommandResult;
import com.eva.logic.commands.exceptions.CommandException;
import com.eva.logic.parser.exceptions.ParseException;
import com.eva.ui.list.view.ApplicantListPanel;
import com.eva.ui.list.view.StaffListPanel;
import com.eva.ui.profile.applicant.view.ApplicantProfilePanel;
import com.eva.ui.profile.staff.view.StaffProfilePanel;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

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
    private StaffListPanel staffListPanel;
    private StaffProfilePanel staffProfilePanel;
    private ApplicantListPanel applicantListPanel;
    private ApplicantProfilePanel applicantProfilePanel;
    private ResultDisplay resultDisplay;
    private HelpWindow helpWindow;

    @FXML
    private StackPane commandBoxPlaceholder;

    @FXML
    private MenuItem helpMenuItem;

    @FXML
    private StackPane sideBarPlaceholder;

    @FXML
    private StackPane panelPlaceholder;

    @FXML
    private StackPane resultDisplayPlaceholder;

    @FXML
    private StackPane headerNamePlaceholder;

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
        staffListPanel = new StaffListPanel(logic.getFilteredStaffList());

        applicantListPanel = new ApplicantListPanel(logic.getFilteredApplicantList());

        switchPanel(logic.getPanelState());

        resultDisplay = new ResultDisplay();
        resultDisplayPlaceholder.getChildren().add(resultDisplay.getRoot());

        CommandBox commandBox = new CommandBox(this::executeCommand);
        commandBoxPlaceholder.getChildren().add(commandBox.getRoot());
    }

    /**
     * Switches current panel to the specified panel.
     */
    private void switchPanel(PanelState panelState) {
        logger.info("Switch panel to: " + panelState.toString());
        panelPlaceholder.getChildren().clear();
        headerNamePlaceholder.getChildren().clear();
        headerNamePlaceholder.getChildren().add(new CurrentPanelHeader(panelState.toString()).getRoot());

        switch (panelState) {
        case STAFF_LIST:
            panelPlaceholder.getChildren().add(staffListPanel.getRoot());
            break;
        case APPLICANT_LIST:
            panelPlaceholder.getChildren().add(applicantListPanel.getRoot());
            break;
        case STAFF_PROFILE:
            staffProfilePanel = new StaffProfilePanel(logic.getCurrentViewStaff());
            staffProfilePanel.fillInnerParts();
            panelPlaceholder.getChildren().add(staffProfilePanel.getRoot());
            break;
        case APPLICANT_PROFILE:
            applicantProfilePanel = new ApplicantProfilePanel(logic.getCurrentViewApplicant());
            applicantProfilePanel.fillInnerParts();
            panelPlaceholder.getChildren().add(applicantProfilePanel.getRoot());
            break;
        default:
            throw new AssertionError("No such tab name: " + panelState);
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

    void show() {
        primaryStage.show();
    }

    /**
     * Closes the application.
     */
    @FXML
    private void handleExit() {
        PanelState currentPanelState = logic.getPanelState();
        // if user is working on staffs, start app on staff list, else, start on applicant list.
        PanelState savePanelState = (currentPanelState.equals(PanelState.STAFF_LIST)
                        || currentPanelState.equals(PanelState.STAFF_PROFILE))
                ? PanelState.STAFF_LIST : PanelState.APPLICANT_LIST;

        GuiSettings guiSettings = new GuiSettings(primaryStage.getWidth(), primaryStage.getHeight(),
                (int) primaryStage.getX(), (int) primaryStage.getY(), savePanelState);
        logic.setGuiSettings(guiSettings);
        helpWindow.hide();
        primaryStage.hide();
    }

    @FXML
    private void handleChangePanel() {
        PanelState requestedPanelState = logic.getPanelState();
        switchPanel(requestedPanelState);
    }

    public StaffListPanel getStaffListPanel() {
        return staffListPanel;
    }

    /**
     * Executes the command and returns the result.
     *
     * @see com.eva.logic.Logic#execute(String)
     */
    private CommandResult executeCommand(String commandText) throws CommandException, ParseException,
            FileNotFoundException {
        try {
            CommandResult commandResult = logic.execute(commandText);
            logger.info("Result: " + commandResult.getFeedbackToUser());
            resultDisplay.setFeedbackToUser(commandResult.getFeedbackToUser());

            if (commandResult.isShowHelp()) {
                handleHelp();
            }

            if (commandResult.isExit()) {
                handleExit();
            }

            if (commandResult.isChangePanel()) {
                handleChangePanel();
            }

            return commandResult;
        } catch (CommandException | ParseException | FileNotFoundException e) {
            logger.info("Invalid command: " + commandText);
            resultDisplay.setFeedbackToUser(e.getMessage());
            throw e;
        }
    }
}
