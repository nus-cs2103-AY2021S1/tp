package seedu.address.testutil;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class SimulatedKeyPress {
    public static final KeyEvent TAB_EVENT = new KeyEvent(
            KeyEvent.KEY_PRESSED,
            KeyEvent.CHAR_UNDEFINED,
            "",
            KeyCode.TAB,
            false,
            false,
            false,
            false
    );
    public static final KeyEvent SHIFT_TAB_EVENT = new KeyEvent(
            KeyEvent.KEY_PRESSED,
            KeyEvent.CHAR_UNDEFINED,
            "",
            KeyCode.TAB,
            true,
            false,
            false,
            false
    );
    public static final KeyEvent ENTER_EVENT = new KeyEvent(
            KeyEvent.KEY_PRESSED,
            KeyEvent.CHAR_UNDEFINED,
            "",
            KeyCode.ENTER,
            false,
            false,
            false,
            false
    );
    public static final KeyEvent CTRL_SPACE_EVENT = new KeyEvent(
            KeyEvent.KEY_PRESSED,
            KeyEvent.CHAR_UNDEFINED,
            "",
            KeyCode.SPACE,
            false,
            true,
            false,
            false
    );

    public static final KeyEvent DOWN_ARROW_EVENT = new KeyEvent(
            KeyEvent.KEY_PRESSED,
            KeyEvent.CHAR_UNDEFINED,
            "",
            KeyCode.DOWN,
            false,
            false,
            false,
            false
    );
}
