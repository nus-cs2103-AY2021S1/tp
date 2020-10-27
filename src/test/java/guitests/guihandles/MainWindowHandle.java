package guitests.guihandles;

import javafx.stage.Stage;

/**
 * Provides a handle for {@code MainWindow}.
 */
public class MainWindowHandle extends StageHandle {

    private final CommandBoxHandle commandBox;
    private final CommandOutputHandle commandOutput;
    private final StatsBoxHandle statsBox;
    private final MainMenuHandle mainMenu;

    /**
     * Constructs a {@code MainWindowHandle} from the given {@code Stage}.
     */
    public MainWindowHandle(Stage stage) {
        super(stage);

        commandBox = new CommandBoxHandle(getChildNode(CommandBoxHandle.COMMAND_INPUT_FIELD_ID));
        commandOutput = new CommandOutputHandle(getChildNode(CommandOutputHandle.RESULT_DISPLAY_ID));
        statsBox = new StatsBoxHandle(getChildNode(StatsBoxHandle.STATS_FIELD_ID));
        mainMenu = new MainMenuHandle(getChildNode(MainMenuHandle.MENU_BAR_FIELD_ID));
    }

    /**
     * Retrieves the command box.
     */
    public CommandBoxHandle getCommandBox() {
        return commandBox;
    }

    /**
     * Retrieves the command output box.
     */
    public CommandOutputHandle getCommandOutput() {
        return commandOutput;
    }

    /**
     * Retrieves the stats box.
     */
    public StatsBoxHandle getStatsBox() {
        return statsBox;
    }

    /**
     * Retrieves the main menu.
     */
    public MainMenuHandle getMainMenu() {
        return mainMenu;
    }
}
