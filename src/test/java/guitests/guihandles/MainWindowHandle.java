package guitests.guihandles;

import guitests.GuiRobot;
import javafx.stage.Stage;

/**
 * Provides a handle for {@code MainWindow}.
 */
public class MainWindowHandle extends StageHandle {
    private static final String MAIN_WINDOW_TITLE = "Travelling BusinessMan";
    private final ClientListPanelHandle clientListPanel;
    private final ResultDisplayHandle resultDisplay;
    private final CommandBoxHandle commandBox;
    private final StatusBarFooterHandle statusBarFooter;
    private final MainMenuHandle mainMenu;

    /**
     * Constructor for the handle.
     *
     * @param stage
     */
    public MainWindowHandle(Stage stage) {
        super(stage);

        clientListPanel = new ClientListPanelHandle(getChildNode(ClientListPanelHandle.CLIENT_LIST_VIEW_ID));
        resultDisplay = new ResultDisplayHandle(getChildNode(ResultDisplayHandle.RESULT_DISPLAY_ID));
        commandBox = new CommandBoxHandle(getChildNode(CommandBoxHandle.COMMAND_INPUT_FIELD_ID));
        statusBarFooter = new StatusBarFooterHandle(getChildNode(StatusBarFooterHandle.STATUS_BAR_PLACEHOLDER));
        mainMenu = new MainMenuHandle(getChildNode(MainMenuHandle.MENU_BAR_ID));
    }

    public ClientListPanelHandle getPersonListPanel() {
        return clientListPanel;
    }

    public ResultDisplayHandle getResultDisplay() {
        return resultDisplay;
    }

    public CommandBoxHandle getCommandBox() {
        return commandBox;
    }

    public StatusBarFooterHandle getStatusBarFooter() {
        return statusBarFooter;
    }

    public MainMenuHandle getMainMenu() {
        return mainMenu;
    }

    public boolean isShowing() {
        return new GuiRobot().isWindowShown(MAIN_WINDOW_TITLE);
    }

}
