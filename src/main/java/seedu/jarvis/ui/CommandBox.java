package seedu.jarvis.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Region;
import seedu.jarvis.commons.core.LogsCenter;
import seedu.jarvis.logic.commands.CommandResult;
import seedu.jarvis.logic.commands.exceptions.CommandException;
import seedu.jarvis.logic.parser.exceptions.ParseException;

/**
 * The UI component that is responsible for receiving user command inputs.
 */
public class CommandBox extends UiPart<Region> {

    public static final String ERROR_STYLE_CLASS = "error";
    private static final String FXML = "CommandBox.fxml";
    private List<String> historyCommands;
    private int inputPointer;

    private final Logger logger = LogsCenter.getLogger(getClass());

    private final CommandExecutor commandExecutor;

    @FXML
    private TextField commandTextField;

    /**
     * Creates a {@code CommandBox} with the given {@code CommandExecutor}.
     */
    public CommandBox(CommandExecutor commandExecutor) {
        super(FXML);
        this.commandExecutor = commandExecutor;

        // adds history command chain, initialised with an empty string
        this.historyCommands = new ArrayList<>();
        historyCommands.add("");
        this.inputPointer = 1;

        // calls #setStyleToDefault() whenever there is a change to the text of the command box.
        commandTextField.textProperty().addListener((unused1, unused2, unused3) -> setStyleToDefault());
    }

    /**
     * Handles the Enter button pressed event.
     */
    @FXML
    private void handleCommandEntered() {
        try {
            String newUserInput = commandTextField.getText();
            //Storing the command
            historyCommands.add(newUserInput);
            this.inputPointer = historyCommands.size();

            //Execute the command
            commandExecutor.execute(newUserInput);
            commandTextField.setText("");

        } catch (CommandException | ParseException e) {
            setStyleToIndicateCommandFailure();
        }
    }

    /**
     * Handles the UP and DOWN KeyCode pressed events.
     */
    @FXML
    private void handleKeyEvent() {
        commandTextField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.UP && inputPointer > 0) {
                goToPreviousInput();

            } else if (event.getCode() == KeyCode.DOWN && inputPointer < historyCommands.size()) {
                goToNextInput();
            }
        });
    }

    /**
     * Goes to the previous input.
     */
    private void goToPreviousInput() {
        logger.info("===== CommandBox detected UP key: Previous user input accessed.");
        inputPointer--;
        String prevInput = historyCommands.get(inputPointer);
        commandTextField.setText(prevInput);
    }

    /**
     * Goes to the next input.
     */
    private void goToNextInput() {
        logger.info("===== CommandBox detected DOWN key: Next user input accesssed.");
        inputPointer++;

        String nextInput;
        if (inputPointer >= historyCommands.size()) {
            nextInput = "";

        } else {
            nextInput = historyCommands.get(inputPointer);
        }

        commandTextField.setText(nextInput);
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
         * @see seedu.jarvis.logic.Logic#execute(String)
         */
        CommandResult execute(String commandText) throws CommandException, ParseException;
    }

}
