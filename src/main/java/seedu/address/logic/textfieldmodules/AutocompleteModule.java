package seedu.address.logic.textfieldmodules;

import static java.util.Objects.requireNonNull;

import java.util.HashMap;
import java.util.List;
import java.util.function.Supplier;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javafx.collections.ObservableList;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import seedu.address.commons.core.LogsCenter;


public class AutocompleteModule {
    public static final String AC_MODE_STYLE_CLASS = "autocomplete-mode";
    private final Logger logger = LogsCenter.getLogger(getClass());

    private HashMap<String, Suggestions> suggestionsList;
    private boolean isAutocompleteMode = false;
    private boolean hasSetPrefix = false;
    private String modeType = "";
    private int commandPrefixPos;

    private final TextField attachedTextField;

    private AutocompleteModule(TextField textField) {
        attachedTextField = textField;
        suggestionsList = new HashMap<>();

        disableFocusTraversal();
        setupAutocompleteTriggers();
        setupAutocompleteActionKeys();
        setupBlockOtherKeystrokesInAcMode();
        setupExitKeys();
    }

    /**
     * Attaches autocomplete module to a TextField component which generates suggestions from the supplied list
     * @param tf  TextField to be attached.
     */
    public static AutocompleteModule attachTo(TextField tf) {
        requireNonNull(tf);
        return new AutocompleteModule(tf);
    }

    /**
     * Setups Autocompletion Generator for stipulated command prefix.
     */
    public void addSuggestions(String commandPrefix, Supplier<List<String>> suggestionsDataGenerator) {
        requireNonNull(commandPrefix);
        requireNonNull(suggestionsDataGenerator);

        Suggestions suggestions = new Suggestions(suggestionsDataGenerator);
        suggestionsList.put(commandPrefix, suggestions);
    }
    private void setupAutocompleteTriggers() {
        attachedTextField.caretPositionProperty().addListener((unused1, unused2, newPosition) -> {
            String userInput = attachedTextField.getText();
            int caretPos = newPosition.intValue();
            if (isAutocompleteMode) {
                if (caretPos < commandPrefixPos) {
                    toggleAutocompleteModeOff();
                    unsetPrefix();
                }
            }
            for (String cmdP : suggestionsList.keySet().toArray(new String[]{})) {
                int prefixLength = cmdP.length();
                if (userInput.length() >= prefixLength && caretPos >= prefixLength) {
                    String substring = userInput.substring(caretPos - prefixLength);
                    if (!isAutocompleteMode && substring.equals(cmdP)) {
                        commandPrefixPos = caretPos;
                        toggleAutocompleteModeOn(cmdP);
                    }
                }
            }
        });
    }
    private void setupAutocompleteActionKeys() {
        attachedTextField.addEventFilter(KeyEvent.KEY_PRESSED, (event) -> {
            if (isAutocompleteMode && event.getCode() == KeyCode.TAB) {
                Suggestions suggestions = suggestionsList.get(modeType);
                if (!hasSetPrefix) {
                    String prefix = attachedTextField.getText().substring(commandPrefixPos);
                    setPrefix(suggestions, prefix);
                }
                if (event.isShiftDown()) {
                    // Shift + TAB : Previous Suggestion
                    String prev = suggestions.prevSuggestion();
                    int endIndex = attachedTextField.caretPositionProperty().getValue();
                    attachedTextField.replaceText(commandPrefixPos, endIndex, prev);
                } else {
                    // TAB : Next Suggestion
                    String next = suggestions.nextSuggestion();
                    int endIndex = attachedTextField.caretPositionProperty().getValue();
                    attachedTextField.replaceText(commandPrefixPos, endIndex, next);
                }
                if (suggestions.isBackToPrefix()) {
                    unsetPrefix();
                }
                event.consume();
            }
        });
    }
    private void setupBlockOtherKeystrokesInAcMode() {
        attachedTextField.addEventFilter(KeyEvent.ANY, (event) -> {
            if (hasSetPrefix
                    && event.getCode() != KeyCode.TAB
                    && event.getCode() != KeyCode.ENTER
            ) {
                event.consume();
            }
        });
    }
    private void setupExitKeys() {
        // Exit Autocomplete Mode when backspace / enter is pressed.
        attachedTextField.addEventFilter(KeyEvent.KEY_PRESSED, (event -> {
            if (isAutocompleteMode && (
                    event.getCode() == KeyCode.BACK_SPACE
                            || event.getCode() == KeyCode.ENTER
                            || (event.getCode() == KeyCode.W && event.isControlDown()))) {
                unsetPrefix();
                if (event.getCode() == KeyCode.ENTER) {
                    toggleAutocompleteModeOff();
                    removeCommandPrefixFromCompletion();
                    event.consume();
                }
            }
        }));
    }
    private void disableFocusTraversal() {
        attachedTextField.addEventFilter(KeyEvent.KEY_PRESSED, (event) -> {
            if (!isAutocompleteMode) {
                if (event.getCode() == KeyCode.TAB) {
                    event.consume();
                }
                return;
            }
        });
    }
    private void toggleAutocompleteModeOn(String commandPrefix) {
        if (isAutocompleteMode) {
            return;
        }
        logger.info("Autocomplete Mode toggled ON");
        setStyleToIndicateAutocompleteMode();
        isAutocompleteMode = true;
        modeType = commandPrefix;
    }
    private void toggleAutocompleteModeOff() {
        if (!isAutocompleteMode) {
            return;
        }
        isAutocompleteMode = false;
        logger.info("Autocomplete Mode toggled OFF");
        attachedTextField.getStyleClass().remove(AC_MODE_STYLE_CLASS);
    }
    private void removeCommandPrefixFromCompletion() {
        String currentText = attachedTextField.getText();
        String result = currentText.substring(0 , commandPrefixPos - modeType.length())
                + currentText.substring(commandPrefixPos);
        int caretPosition = attachedTextField.getCaretPosition();
        attachedTextField.setText(result);
        attachedTextField.positionCaret(caretPosition - modeType.length());
    }
    private void unsetPrefix() {
        if (hasSetPrefix) {
            hasSetPrefix = false;
            logger.info("Autocomplete prefix has been unset");
        }
    }
    private void setPrefix(Suggestions suggestions, String prefix) {
        suggestions.setPrefix(prefix);
        hasSetPrefix = true;
        logger.info("Autocomplete prefix has set to :" + prefix);
    }

    /**
     * Sets the command box style to indicate a Autocomplete Mode.
     */
    private void setStyleToIndicateAutocompleteMode() {
        ObservableList<String> styleClass = attachedTextField.getStyleClass();

        if (styleClass.contains(AC_MODE_STYLE_CLASS)) {
            return;
        }

        styleClass.add(AC_MODE_STYLE_CLASS);
    }

    /**
     * Class that generates suggestions based on given prefix and given list.
     */
    public class Suggestions {
        private List<String> suggestions;
        private String prefix = "";
        private int index = 0;
        private final Supplier<List<String>> listSupplier;

        /**
         * Encapsulations a Suggestions object that allows users to generate suggestions based on
         * given prefix
         * @param listSupplier  List supplier from which suggestions will be generated
         */
        public Suggestions(Supplier<List<String>> listSupplier) {
            this.listSupplier = listSupplier;
            this.suggestions = listSupplier.get().stream().sorted().collect(Collectors.toList());
        }

        private void nextIndex() {
            index = index + 1 < suggestions.size() ? index + 1 : 0;
        }

        private void prevIndex() {
            index = index - 1 < 0 ? suggestions.size() - 1 : index - 1;
        }

        /**
         * Generates the next best suggestion.
         */
        public String nextSuggestion() {
            if (!hasSuggestion()) {
                return prefix;
            }
            nextIndex();
            return suggestions.get(index);
        }

        /**
         * Generates the previous best suggestion.
         */
        public String prevSuggestion() {
            if (!hasSuggestion()) {
                return prefix;
            }
            prevIndex();
            return suggestions.get(index);
        }

        private boolean hasSuggestion() {
            return suggestions.size() != 0;
        }

        private void setPrefix(String prefix) {
            this.index = 0;
            this.prefix = prefix;
            suggestions = listSupplier.get().stream().filter(x -> x.toLowerCase().startsWith(prefix.toLowerCase()))
                    .collect(Collectors.toList());
            suggestions.sort((o1, o2) -> o1.compareTo(o2));
            suggestions.add(0, prefix);
        }

        private boolean isBackToPrefix() {
            return index == 0;
        }
    }
}
