package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * The UI component that is responsible for receiving user command inputs.
 */
public class CommandBox extends UiPart<Region> {

    public static final String ERROR_STYLE_CLASS = "error";
    private static final String FXML = "CommandBox.fxml";

    private final Logger logger = LogsCenter.getLogger(CommandBox.class);
    private final CommandExecutor commandExecutor;

    @FXML
    private TextField commandTextField;

    /**
     * Creates a {@code CommandBox} with the given {@code CommandExecutor}.
     */
    public CommandBox(CommandExecutor commandExecutor) {
        super(FXML);
        this.commandExecutor = commandExecutor;
        // calls #setStyleToDefault() whenever there is a change to the text of the command box.
        commandTextField.textProperty().addListener((unused1, unused2, unused3) -> setStyleToDefault());
        commandTextField.addEventHandler(KeyEvent.KEY_PRESSED, (KeyEvent keyEvent) -> {
            switch (keyEvent.getCode()) {
            case UP:
                logger.fine("Key Pressed: UP");
                fillWithPreviousCommandIfPresent();
                keyEvent.consume();
                break;
            case DOWN:
                logger.fine("Key Pressed: DOWN");
                fillWithNextCommandIfPresent();
                keyEvent.consume();
                break;
            default:
                break;
            }
        });
    }

    private void fillWithPreviousCommandIfPresent() {
        CommandHistory.getInstance().fetchPrevious().ifPresent(command -> {
            commandTextField.setText(command);
            commandTextField.positionCaret(commandTextField.getText().length());
            logger.fine("Command Box set to previous command: " + command);
        });
    }

    private void fillWithNextCommandIfPresent() {
        CommandHistory.getInstance().fetchNext().ifPresent(command -> {
            commandTextField.setText(command);
            commandTextField.positionCaret(commandTextField.getText().length());
            logger.fine("Command Box set to next command: " + command);
        });
    }

    /**
     * Handles the Enter button pressed event.
     */
    @FXML
    private void handleCommandEntered() {
        try {
            String enteredCommand = commandTextField.getText();
            CommandHistory.getInstance().push(enteredCommand);
            commandExecutor.execute(enteredCommand);
            commandTextField.setText("");
        } catch (CommandException | ParseException e) {
            setStyleToIndicateCommandFailure();

            // Force the command history to point at the previous command (the errored command)
            CommandHistory.getInstance().fetchPrevious();
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
         * @see seedu.address.logic.Logic#execute(String)
         */
        CommandResult execute(String commandText) throws CommandException, ParseException;
    }

}
