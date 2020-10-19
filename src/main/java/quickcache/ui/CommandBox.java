package quickcache.ui;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import quickcache.logic.Logic;
import quickcache.logic.commands.CommandResult;
import quickcache.logic.commands.exceptions.CommandException;
import quickcache.logic.parser.exceptions.ParseException;

/**
 * The UI component that is responsible for receiving user command inputs.
 */
public class CommandBox extends UiPart<Region> {

    public static final String ERROR_STYLE_CLASS = "error";
    private static final String FXML = "CommandBox.fxml";

    private final CommandExecutor commandExecutor;
    private List<String> pastCommands;
    private int pointer = 0;

    @FXML
    private TextField commandTextField;

    /**
     * Creates a {@code CommandBox} with the given {@code CommandExecutor}.
     */
    public CommandBox(CommandExecutor commandExecutor) {
        super(FXML);
        this.commandExecutor = commandExecutor;
        this.pastCommands = new ArrayList<>(16);
        pastCommands.add("");
        // calls #setStyleToDefault() whenever there is a change to the text of the command box.
        commandTextField.textProperty().addListener((unused1, unused2, unused3) -> setStyleToDefault());
    }

    /**
     * Handles the Enter button pressed event.
     */
    @FXML
    private void handleCommandEntered() {
        try {
            String input = commandTextField.getText();
            updatePointerAndPastCommandsList(input);
            commandExecutor.execute(input);
            commandTextField.setText("");
        } catch (CommandException | ParseException e) {
            setStyleToIndicateCommandFailure();
        }
    }

    /**
     * Handles the updating of past commands and pointer position after executing a particular input.
     *
     * @param input The input executed.
     */
    private void updatePointerAndPastCommandsList(String input) {
        if (pointer < pastCommands.size() - 1) {
            pointer = pastCommands.size() - 1;
        }
        if (!input.isBlank()) {
            this.pastCommands.set(pastCommands.size() - 1, input);
            pastCommands.add("");
            pointer++;
        }
    }

    /**
     * Handles the Up and Down arrow buttons pressed events.
     */
    @FXML
    private void handleKeyEvent() {
        commandTextField.setOnKeyPressed(event -> {
            switch(event.getCode()) {
            case UP:
                decrementPointer();
                break;
            case DOWN:
                incrementPointer();
                break;
            default:
                // Do Nothing
            }
        });
    }

    /**
     * Move pointer one position front
     */
    private void incrementPointer() {
        if (pointer < pastCommands.size() - 1) {
            pointer++;
            String textToDisplay = pastCommands.get(pointer);
            commandTextField.setText(textToDisplay);
            commandTextField.positionCaret(textToDisplay.length());
        }
    }

    /**
     * Move pointer one position back
     */
    private void decrementPointer() {
        if (pointer > 0) {
            pointer--;
            String textToDisplay = pastCommands.get(pointer);
            commandTextField.setText(textToDisplay);
            commandTextField.positionCaret(textToDisplay.length());
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
         * @see Logic#execute(String)
         */
        CommandResult execute(String commandText) throws CommandException, ParseException;
    }

}
