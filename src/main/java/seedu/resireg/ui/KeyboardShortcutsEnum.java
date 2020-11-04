package seedu.resireg.ui;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;

/**
 * Enums representing all the keyboard shortcuts in ResiReg. Each enum is bound to its command text (that
 * is the command word) and keyboard shortcut. This enum is the single source of truth about all keyboard
 * shortcuts in ResiReg, so developers who want to add new keyboard shortcuts must add an enum to this class.
 */
public enum KeyboardShortcutsEnum {

    UNDO_SHORTCUT(new KeyCodeCombination(KeyCode.Z, KeyCombination.CONTROL_DOWN), "undo"),
    REDO_SHORTCUT(new KeyCodeCombination(KeyCode.Y, KeyCombination.CONTROL_DOWN), "redo"),
    HIST_SHORTCUT(new KeyCodeCombination(KeyCode.H, KeyCombination.CONTROL_DOWN), "history");

    private String commandText;
    private KeyCombination keyComb;

    KeyboardShortcutsEnum(KeyCombination kc, String ct) {
        keyComb = kc;
        commandText = ct;
    }

    public String getCommandText() {
        return commandText;
    }

    public KeyCombination getKeyCombination() {
        return keyComb;
    }
}
