package seedu.address.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import seedu.address.history.CommandHistory;
import seedu.address.history.exception.HistoryException;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.results.CommandResult;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * The UI component that is responsible for receiving user command inputs.
 */
public class CommandBox extends UiPart<Region> {

    public static final String ERROR_STYLE_CLASS = "error";
    private static final String FXML = "CommandBox.fxml";

    private final CommandExecutor commandExecutor;
    private final CommandHistory history;

    @FXML
    private TextField commandTextField;

    @FXML
    private Button submitCommand;

    /**
     * Creates a {@code CommandBox} with the given {@code CommandExecutor}.
     */
    public CommandBox(CommandExecutor commandExecutor) {
        super(FXML);
        this.commandExecutor = commandExecutor;
        history = makeCommandHistory();

        // calls #setStyleToDefault() whenever there is a change to the text of the command box.
        commandTextField.textProperty().addListener((unused1, unused2, unused3) -> setStyleToDefault());

        commandTextField.setOnKeyPressed(event -> {
            switch (event.getCode()) {
            case UP:
                commandTextField.setText(history.previousCommand().orElse(""));
                break;

            case DOWN:
                commandTextField.setText(history.nextCommand().orElse(""));
                break;

            default:
                break;
            }
        });
    }

    /**
     * Makes a {@code CommandHistory} object with limit 20
     * @return {@code CommandHistory} object
     */
    private CommandHistory makeCommandHistory() {
        CommandHistory tempHistory;
        try {
            tempHistory = new CommandHistory(20);
        } catch (HistoryException historyException) {
            tempHistory = null;
            System.err.println(historyException);
            System.exit(1);
        }

        return tempHistory;
    }


    /**
     * Handles the Enter button pressed event.
     */
    @FXML
    private void handleCommandEntered() {
        history.addToHistory(commandTextField.getText());

        try {
            commandExecutor.execute(commandTextField.getText());
            commandTextField.setText("");
        } catch (CommandException | ParseException e) {
            setStyleToIndicateCommandFailure();
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
