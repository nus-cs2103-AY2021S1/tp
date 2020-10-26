package seedu.resireg.ui;

import java.util.List;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Region;
import seedu.resireg.logic.commands.CommandResult;
import seedu.resireg.logic.commands.exceptions.CommandException;
import seedu.resireg.logic.parser.exceptions.ParseException;

/**
 * The UI component that is responsible for receiving user command inputs.
 */
public class CommandBox extends UiPart<Region> {

    public static final String ERROR_STYLE_CLASS = "error";
    private static final String FXML = "CommandBox.fxml";

    // keyboard shortcuts (can consider KeyboardCommandMapper similar to CommandMapper)
    private static final KeyCombination undo =
            new KeyCodeCombination(KeyCode.Z, KeyCombination.CONTROL_DOWN);
    private static final KeyCombination redo =
            new KeyCodeCombination(KeyCode.Y, KeyCombination.CONTROL_DOWN);

    private final CommandExecutor commandExecutor;
    private final List<String> history;
    private ListPtr iterator;

    @FXML
    private TextField commandTextField;

    /**
     * Creates a {@code CommandBox} with the given {@code CommandExecutor}.
     */
    public CommandBox(CommandExecutor commandExecutor, List<String> history) {
        super(FXML);
        this.commandExecutor = commandExecutor;
        this.history = history;
        // calls #setStyleToDefault() whenever there is a change to the text of the command box.
        commandTextField.textProperty().addListener((unused1, unused2, unused3) -> setStyleToDefault());
        iterator = new ListPtr(history);
    }

    @FXML
    private void handleOnKeyPressed(KeyEvent event) {

        if (undo.match(event)) {
            event.consume();
            handleCommandEntered("undo");
        }

        if (redo.match(event)) {
            event.consume();
            handleCommandEntered("redo");
        }

        switch (event.getCode()) {
        case UP:
            event.consume();
            navigateToPrevInput();
            break;
        case DOWN:
            event.consume();
            navigateToNextInput();
            break;
        default:

        }
    }

    /**
     * Tries to set the focus on this command box, which will allow the user type into the command box.
     */
    void requestFocus() {
        commandTextField.requestFocus();
    }

    /**
     * Handles the Enter button pressed event.
     */
    @FXML
    private void handleCommandEntered() {
        try {
            commandExecutor.execute(commandTextField.getText());
            commandTextField.setText("");
            initHistory();
            iterator.next();
        } catch (CommandException | ParseException e) {
            initHistory();
            setStyleToIndicateCommandFailure();
        }
    }

    /**
     * Handles the Enter button pressed event
     */
    public void handleCommandEntered(String commandText) {
        try {
            commandExecutor.execute(commandText);
            commandTextField.setText("");
            initHistory();
            iterator.next();
        } catch (CommandException | ParseException e) {
            initHistory();
            setStyleToIndicateCommandFailure();
        }
    }

    /**
     * Updates the text field with the previous input in {@code iterator},
     * if there exists a previous input in {@code iterator}
     */
    private void navigateToPrevInput() {
        assert iterator != null;
        if (!iterator.hasPrevious()) {
            return;
        }

        replaceText(iterator.previous());
    }

    /**
     * Updates the text field with the next input in {@code historySnapshot},
     * if there exists a next input in {@code historySnapshot}
     */
    private void navigateToNextInput() {
        assert iterator != null;
        if (!iterator.hasNext()) {
            return;
        }

        replaceText(iterator.next());
    }

    /**
     * Sets {@code CommandBox}'s text field with {@code text} and
     * positions the caret to the end of the {@code text}.
     */
    private void replaceText(String text) {
        commandTextField.setText(text);
        commandTextField.positionCaret(commandTextField.getText().length());
    }

    /**
     * Initializes the iterator
     */
    private void initHistory() {
        iterator = new ListPtr(history);
        // add empty string to represent most recent end of iterator
        iterator.add("");
    }

    /**
     * Sets the command box style to use the default style.
     */
    private void setStyleToDefault() {
        commandTextField.getStyleClass().remove(ERROR_STYLE_CLASS);
    }

    /**
     * Sets the command box style to indicate a failed command.
     */
    private void setStyleToIndicateCommandFailure() {
        ObservableList<String> styleClass = commandTextField.getStyleClass();

        if (styleClass.contains(ERROR_STYLE_CLASS)) {
            return;
        }

        styleClass.add(ERROR_STYLE_CLASS);
    }

    /**
     * Represents a function that can execute commands.
     */
    @FunctionalInterface
    public interface CommandExecutor {
        /**
         * Executes the command and returns the result.
         *
         * @see seedu.resireg.logic.Logic#execute(String)
         */
        CommandResult execute(String commandText) throws CommandException, ParseException;
    }

}
