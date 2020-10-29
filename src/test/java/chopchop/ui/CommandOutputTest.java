package chopchop.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import chopchop.logic.commands.CommandResult;
import guitests.guihandles.CommandOutputHandle;
import javafx.scene.Node;
import javafx.scene.text.Text;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class CommandOutputTest extends GuiUnitTest {

    private CommandOutput commandOutput;
    private CommandOutputHandle commandOutputHandle;

    @BeforeEach
    public void setup() {
        commandOutput = new CommandOutput();
        uiPartExtension.setUiPart(commandOutput);

        commandOutputHandle = new CommandOutputHandle(getChildNode(commandOutput.getRoot(),
                CommandOutputHandle.RESULT_DISPLAY_ID));
    }

    @Test
    public void displayValidMessage() {
        guiRobot.pauseForHuman();
        assertEquals("", parseTextToString(commandOutputHandle.getText()));

        // Command output received
        String testFeedback = "test feedback to user";
        guiRobot.interact(() -> commandOutput.setFeedbackToUser(CommandResult.message(testFeedback)));
        guiRobot.pauseForHuman();
        // Command output adds a newline to the end of each text
        assertEquals(testFeedback + "\n", parseTextToString(commandOutputHandle.getText()));
    }

    @Test
    public void displayErrorMessage() {
        String testError = "test error to user.";
        guiRobot.interact(() -> commandOutput.setFeedbackToUser(CommandResult.message(testError)));
        guiRobot.pauseForHuman();
        // Command output adds a newline to the end of each text
        assertEquals(testError + "\n", parseTextToString(commandOutputHandle.getText()));
    }

    private String parseTextToString(List<Node> nodeList) {
        StringBuilder str = new StringBuilder();
        for (Node node : nodeList) {
            if (node instanceof Text) {
                str.append(((Text) node).getText());
            }
        }
        return str.toString();
    }
}
