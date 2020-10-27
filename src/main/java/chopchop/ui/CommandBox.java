package chopchop.ui;

import chopchop.logic.Logic;
import chopchop.logic.commands.exceptions.CommandException;
import chopchop.logic.parser.exceptions.ParseException;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Region;

/**
 * The UI component that is responsible for receiving user command inputs.
 */
public class CommandBox extends UiPart<Region> {

    public static final String ERROR_STYLE_CLASS = "error";
    private static final String FXML = "CommandBox.fxml";

    private final CommandExecutor commandExecutor;
    private final Logic logic;

    private int historyPointer;

    @FXML
    private TextField commandTextField;

    /**
     * Constructs {@code CommandBox}
     */
    public CommandBox(CommandExecutor commandExecutor, Logic logic) {
        super(FXML);
        this.commandExecutor = commandExecutor;
        this.logic = logic;
        // calls #setStyleToDefault() whenever there is a change to the text of the command box.
        commandTextField.textProperty().addListener((unused1, unused2, unused3) -> setStyleToDefault());
        // No commands entered yet.
        historyPointer = 0;

        commandTextField.addEventFilter(KeyEvent.KEY_PRESSED, event -> {

            if (event.getCode().equals(KeyCode.DOWN) && historyPointer < logic.getInputHistory().size()) {
                historyPointer++;

                if (historyPointer == logic.getInputHistory().size()) {
                    commandTextField.clear();
                } else {
                    var command = logic.getInputHistory().get(historyPointer);
                    commandTextField.setText(command);
                    commandTextField.positionCaret(command.length());
                }

                event.consume();

            } else if (event.getCode().equals(KeyCode.UP) && historyPointer > 0) {

                historyPointer--;
                String command = logic.getInputHistory().get(historyPointer);
                commandTextField.setText(command);
                commandTextField.positionCaret(command.length());
                event.consume();

            } else if (event.getCode().equals(KeyCode.TAB)) {

                var text = commandTextField.getText();
                var cursor = commandTextField.getCaretPosition();

                // first split the thing by cursor position -- only complete the first portion.
                var fst = text.substring(0, cursor);
                var snd = text.substring(cursor);

                var completion = logic.getCompletionForInput(fst);

                // now, we can change the cursor position accordingly.
                cursor = completion.length();

                commandTextField.setText(completion + snd);
                commandTextField.positionCaret(cursor);
                event.consume();

            } else {

                // when the user presses any other key, just reset the completion state.
                logic.resetCompletionState();
            }
        });
    }

    /**
     * Handles the Enter button pressed event.
     */
    @FXML
    private void handleCommandEntered() {
        var command = commandTextField.getText();
        if (command.isEmpty()) {
            return;
        }

        try {
            commandExecutor.execute(command);
        } catch (CommandException | ParseException e) {
            setStyleToIndicateCommandFailure();
        } finally {
            historyPointer = logic.getInputHistory().size();
            commandTextField.clear();
        }
    }

    protected void setFocus(String keypress) {
        if (!commandTextField.isFocused()) {
            commandTextField.appendText(keypress);
            commandTextField.requestFocus();
            commandTextField.end();
        }
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
         * @see chopchop.logic.Logic#execute(String)
         */
        chopchop.logic.commands.CommandResult execute(String commandText) throws CommandException, ParseException;
    }

}
