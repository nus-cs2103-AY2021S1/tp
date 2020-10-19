package seedu.address.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * The UI component that is responsible for receiving user command inputs.
 */
public class CommandBox extends UiPart<Region> {

    private static final Logger logger = LogsCenter.getLogger(CommandBox.class);

    public static final String ERROR_STYLE_CLASS = "error";
    public static final String AC_MODE_STYLE_CLASS = "autocomplete-mode";
    private static final String FXML = "CommandBox.fxml";


    private final CommandExecutor commandExecutor;
    private boolean isAutocompleteMode = false;
    private boolean hasSetPrefix = false;
    private int autoCompletePos;
    private final Logic logic;

    @FXML
    private TextField commandTextField;

    /**
     * Creates a {@code CommandBox} with the given {@code CommandExecutor}.
     */
    public CommandBox(CommandExecutor commandExecutor, Logic l) {
        super(FXML);
        this.logic = l;
        this.commandExecutor = commandExecutor;
        // calls #setStyleToDefault() whenever there is a change to the text of the command box.
        commandTextField.textProperty().addListener((unused1, unused2, unused3) -> setStyleToDefault());
        setAutoCompletionOn();
        setKeyboardShortcut("CTRL", KeyCode.U, () -> commandTextField.clear());
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
     * Enables Autocompletion for Names.
     */
    private void setAutoCompletionOn() {
        // Placeholder Class
        Autocomplete ac = new Autocomplete();

        // Add Text Listener
        commandTextField.caretPositionProperty().addListener((obs, oldPosition, newPosition) -> {
            String txt = commandTextField.getText();
            if (txt.length() > 1 && newPosition.intValue() > 2) {
                int currPos = newPosition.intValue();
                String lastTwo = txt.substring(currPos - 2);

                if (!isAutocompleteMode && lastTwo.equals("n/")) {
                    System.out.println("Autocomplete Triggered");
                    toggleAutocompleteMode();
                    autoCompletePos = newPosition.intValue();
                }
                if (isAutocompleteMode) {
                    if (txt.substring(currPos - 1).isBlank() || currPos < autoCompletePos) {
                        toggleAutocompleteMode();
                        hasSetPrefix = false;
                        System.out.println("Autocomplete Disabled; Prefix unset");
                    }
                }
            }

        });
        // Add Tab and Shift-Tab Listener
        commandTextField.addEventFilter(KeyEvent.KEY_PRESSED, (event) -> {
            if (!isAutocompleteMode) {
                // Hacky Way to disable focus traversal
                if (event.getCode() == KeyCode.TAB) {
                    event.consume();
                }
                return;
            }
            if (event.getCode() == KeyCode.TAB) {
                if (!hasSetPrefix) {
                    List<String> personList = logic.getFilteredPersonList().stream()
                            .map(p -> p.getName().fullName).collect(Collectors.toList());
                    ac.setList(personList);
                    String prefix = commandTextField.getText().substring(autoCompletePos);
                    ac.setPrefix(prefix);
                    hasSetPrefix = true;
                    System.out.println("Prefix set as : " + prefix);
                }

                if (event.isShiftDown()) {
                    // Previous Suggestion
                    String prev = ac.prevSuggestion();
                    int i = commandTextField.caretPositionProperty().getValue();
                    commandTextField.replaceText(autoCompletePos, i, prev);
                } else {
                    // Next Suggestion
                    String next = ac.nextSuggestion();
                    int i = commandTextField.caretPositionProperty().getValue();
                    commandTextField.replaceText(autoCompletePos, i, next);
                }

                if (ac.isBackToPrefix()) {
                    hasSetPrefix = false;
                    System.out.println("Iterated back to Prefix; resetting prefix");
                }
                event.consume();
            }
        });
        // Prevent other keystrokes in autocomplete mode
        commandTextField.addEventFilter(KeyEvent.ANY, (event) -> {
            if (hasSetPrefix
                    && event.getCode() != KeyCode.TAB
                    && event.getCode() != KeyCode.SPACE
            ) {
                event.consume();
            }
            if (hasSetPrefix && event.getCode() == KeyCode.SPACE) {
                System.out.println("Space consumed, Autocomplete Result Selected");
                commandTextField.appendText(" ");
            }

        });
        // Resetting prefix when backspace is pressed.
        commandTextField.addEventFilter(KeyEvent.KEY_PRESSED, (event -> {
            if (isAutocompleteMode && event.getCode() == KeyCode.BACK_SPACE) {
                hasSetPrefix = false;
                toggleAutocompleteMode();
                System.out.println("Disabled Autocomplete mode : Backspace Trigger");
            }
        }));
    }

    private void setKeyboardShortcut(String modifier, KeyCode keyCode, Runnable runnable) {
        commandTextField.addEventFilter(KeyEvent.KEY_PRESSED, (event -> {
            boolean isModifierDown;
            switch (modifier) {
                case "CTRL":
                    isModifierDown = event.isControlDown();
                    break;
                case "ALT":
                    isModifierDown = event.isAltDown();
                    break;
                default:
                    isModifierDown = false;
            }
            if (event.getCode() == keyCode && isModifierDown) {
                runnable.run();
            }
        }));
    }

    /**
     * To toggle autocompletion mode.
     */
    private void toggleAutocompleteMode() {
        // Add Visuals for Autocomplete mode
        if(!isAutocompleteMode) {
            setStyleToIndicateAutocompleteMode();
            isAutocompleteMode = true;
        } else {
            commandTextField.getStyleClass().remove(AC_MODE_STYLE_CLASS);
            isAutocompleteMode = false;
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
     * Sets the command box style to indicate a Autocomplete Mode.
     */
    private void setStyleToIndicateAutocompleteMode() {
        ObservableList<String> styleClass = commandTextField.getStyleClass();

        if (styleClass.contains(AC_MODE_STYLE_CLASS)) {
            return;
        }

        styleClass.add(AC_MODE_STYLE_CLASS);
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

    /**
     * Class that encapsulates Autocomplete Logic
     */
    private class Autocomplete {
        List<String> fullList;
        List<String> suggestions;
        String prefix = "";
        int index = 0;

        private void nextIndex() {
            index = index + 1 < suggestions.size() ? index + 1 : 0;
        }

        private void prevIndex() {
            index = index - 1 < 0 ? suggestions.size() - 1 : index - 1;
        }

        public String nextSuggestion() {
            if (!hasSuggestion()) {
                return prefix;
            }
            nextIndex();
            return suggestions.get(index);
        }

        public String prevSuggestion() {
            if (!hasSuggestion()) {
                return prefix;
            }
            prevIndex();
            return suggestions.get(index);
        }

        public boolean hasSuggestion() {
            return suggestions.size() != 0;
        }

        public void setList(List<String> fullList) {
            System.out.println("set list called");
            this.fullList = fullList;
            this.suggestions = fullList.stream().collect(Collectors.toList());
            this.suggestions.add(0, prefix);
        }

        public void setPrefix(String prefix) {
            this.index = 0;
            this.prefix = prefix;
            suggestions = fullList.stream().filter(x -> x.toLowerCase().startsWith(prefix.toLowerCase()))
                    .collect(Collectors.toList());
            suggestions.sort((o1, o2) -> o1.compareTo(o2));
            suggestions.add(0, prefix);
            System.out.println("set prefix called");
        }

        public boolean isBackToPrefix() {
            return index == 0;
        }
    }
}
