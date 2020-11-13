package seedu.bagel.ui;

import static seedu.bagel.commons.util.CommandSuggestionUtil.initSuggestions;

import java.util.LinkedList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Side;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import seedu.bagel.logic.commands.CommandResult;
import seedu.bagel.logic.commands.exceptions.CommandException;
import seedu.bagel.logic.parser.exceptions.ParseException;

/**
 * The UI component that is responsible for receiving user command inputs.
 */
public class CommandBox extends UiPart<Region> {

    public static final String ERROR_STYLE_CLASS = "error";
    private static final String FXML = "CommandBox.fxml";

    private final CommandExecutor commandExecutor;
    // The existing autocomplete commands.
    private final SortedSet<String> commandSuggestions;
    // The popup used to select a commands.
    private ContextMenu commandsPopup;

    @FXML
    private TextField commandTextField;

    /**
     * Creates a {@code CommandBox} with the given {@code CommandExecutor}.
     */
    public CommandBox(CommandExecutor commandExecutor) {
        super(FXML);
        this.commandExecutor = commandExecutor;
        commandTextField.textProperty().addListener((unused1, unused2, unused3) -> setStyleToDefault());
        // @@author rgabelarde
        // Solution below for ui command suggestions adapted and modified from
        // https://gist.github.com/floralvikings/10290131
        this.commandSuggestions = new TreeSet<>();
        this.commandsPopup = new ContextMenu();
        commandSuggestions.addAll(initSuggestions());
        commandTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String s2) {
                if (commandTextField.getText().length() != 0) {
                    LinkedList<String> searchResult = new LinkedList<>();
                    if (commandTextField.getText().contains(" ")) {
                        String[] parts = commandTextField.getText().split(" ", 2);
                        searchResult.addAll(commandSuggestions.subSet(
                                parts[0].toLowerCase(),
                                parts[0].toLowerCase() + Character.MAX_VALUE));
                    } else {
                        searchResult.addAll(commandSuggestions.subSet(commandTextField.getText(),
                                commandTextField.getText() + Character.MAX_VALUE));
                    }
                    if (commandSuggestions.size() > 0) {
                        populatePopup(searchResult);
                        if (!commandsPopup.isShowing()) {
                            commandsPopup.show(commandTextField, Side.BOTTOM, 0, 0);
                        }
                    } else {
                        commandsPopup.hide();
                    }
                }
            }
        });

        commandTextField.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue,
                                Boolean aBoolean, Boolean aBoolean2) {
                commandsPopup.hide();
            }
        });
        //@@author
    }

    //  /**
    //   * Get the existing set of autocomplete entries.
    //   * @return The existing autocomplete entries.
    //   */
    //  public SortedSet<String> getCommandSuggestions() {
    //      return commandSuggestions;
    // }

    /**
     * Populate the suggestions set with the given search results.  Display is limited to 10 suggestions,
     * for performance.
     * @param searchResult The set of matching strings.
     */
    private void populatePopup(List<String> searchResult) {
        //@@author rgabelarde-reused
        //Reused from https://gist.github.com/floralvikings/10290131 with minor modifications
        List<CustomMenuItem> menuItems = new LinkedList<>();
        // To add more suggestions, modify this line.
        int maxSuggestions = 3;
        int count = Math.min(searchResult.size(), maxSuggestions);
        if (count == 1) {
            final String result = searchResult.get(0);
            Label entryLabel = new Label(result);
            CustomMenuItem item = new CustomMenuItem(entryLabel, true);
            menuItems.add(item);
        } else {
            for (int i = 0; i < count; i++) {
                final String result = searchResult.get(i);
                Label commandLabel = new Label(result);
                CustomMenuItem item = new CustomMenuItem(commandLabel, true);
                menuItems.add(item);
            }
        }
        commandsPopup.getItems().clear();
        commandsPopup.getItems().addAll(menuItems);
        //@@author
    }

    /**
     * Handles the Enter button pressed event.
     */
    @FXML
    private void handleCommandEntered() {
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
         * @see seedu.bagel.logic.Logic#execute(String)
         */
        CommandResult execute(String commandText) throws CommandException, ParseException;
    }

}
