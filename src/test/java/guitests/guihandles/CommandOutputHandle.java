package guitests.guihandles;

import javafx.scene.control.TextArea;

/**
 * A handle to the {@code CommandOutput} in the GUI.
 */
public class CommandOutputHandle extends NodeHandle<TextArea> {

    public static final String RESULT_DISPLAY_ID = "#displayBox";

    /**
     * Constructs a {@code commandOutputHandle} with the given {@code commandOutputNode}.
     */
    public CommandOutputHandle(TextArea commandOutputNode) {
        super(commandOutputNode);
    }

    /**
     * Returns the text in the display box.
     */
    public String getText() {
        return getRootNode().getText();
    }
}
