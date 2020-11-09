package seedu.address.ui;

import java.util.Stack;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Region;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * The UI component that is responsible for receiving user command inputs.
 */
public class CommandBox extends UiPart<Region> {

    public static final String ERROR_STYLE_CLASS = "error";
    private static final String FXML = "CommandBox.fxml";

    private final CommandExecutor commandExecutor;
    private final Stack<String> commandHistory = new Stack<>();
    private final Stack<String> commandFuture = new Stack<>();

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
        setListener();
    }

    /**
     * Handles the Enter button pressed event.
     */
    @FXML
    private void handleCommandEntered() {
        commandHistory.add(commandTextField.getText());
        try {
            commandExecutor.execute(commandTextField.getText());
            commandFuture.clear();
            commandTextField.setText("");
        } catch (CommandException | ParseException e) {
            setStyleToIndicateCommandFailure();
        }
    }

    private void setListener() {
        commandTextField.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.DOWN) {
                setText(getForwardCommand());
                keyEvent.consume();
            }
            if (keyEvent.getCode() == KeyCode.UP) {
                setText(getPreviousCommand());
                keyEvent.consume();
            }
        });
    }

    private String getPreviousCommand() {
        if (commandHistory.isEmpty()) {
            return commandTextField.getText();
        } else {
            if (commandTextField.getText() != "") {
                commandFuture.add(commandTextField.getText());
            }
            return commandHistory.pop();
        }
    }

    private String getForwardCommand() {
        if (commandFuture.isEmpty()) {
            return "";
        } else {
            if (commandTextField.getText() != "") {
                commandHistory.add(commandTextField.getText());
            }
            return commandFuture.pop();
        }
    }

    /**
     * Set commandTextField with userInput
     */
    private void setText(String command) {
        commandTextField.setText(command);
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
