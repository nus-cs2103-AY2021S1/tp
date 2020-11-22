package guitests.guihandles;

import guitests.GuiRobot;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * A handle to the {@code HelpWindow} of the application.
 */
public class HelpWindowHandle extends StageHandle {

    public static final String HELP_WINDOW_TITLE = "Help [Press Esc to close]";

    public static final String USERGUIDE_URL_ID = "#userGuideUrl";

    public HelpWindowHandle(Stage helpWindowStage) {
        super(helpWindowStage);
    }

    /**
     * Returns true if a help window is currently present in the application.
     */
    public static boolean isWindowPresent() {
        return new GuiRobot().isWindowShown(HELP_WINDOW_TITLE);
    }

    public String getUgUrl() {
        return new GuiRobot().lookup(USERGUIDE_URL_ID).queryLabeled().getText();
    }

    /**
     * Clicks on the copy url button on the {@code HelpWindow}.
     */
    public void clickOnCopyUrlButton() {
        guiRobot.clickOn("#copyButton");
    }

    /**
     * Difference between this method and the other is the above method find the node straight from the
     * stage and immediately tries to clicks it.
     * This one looks up the node with the given id and tries to query it as a button first before clicking it.
     */
    public void accessCopyUrlButton() {
        /*
        * I am including this method here since my IDEA does not pickup the coverage for the test on the method
        * copyUrl of the help window. Two tests are need to display a full coverage.
        * */
        Button copyButton = guiRobot.lookup("#copyButton").queryButton();
        guiRobot.clickOn(copyButton);
    }

}
