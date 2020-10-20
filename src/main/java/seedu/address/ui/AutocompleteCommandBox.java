package seedu.address.ui;

import javafx.collections.ObservableList;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import seedu.address.logic.Logic;
import seedu.address.logic.parser.CliSyntax;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class AutocompleteCommandBox extends CommandBox {

    public static final String AC_MODE_STYLE_CLASS = "autocomplete-mode";

    private boolean isAutocompleteMode = false;
    private boolean hasSetPrefix = false;
    private int autoCompletePos;
    private final Logic logic;

    /**
     * Creates a {@code AutocompleteCommandBox} with the given {@code CommandExecutor}.
     */
    public AutocompleteCommandBox(CommandExecutor commandExecutor, Logic l) {
        super(commandExecutor);
        this.logic = l;
        setupAutocompletionListeners(CliSyntax.PREFIX_NAME.getPrefix(), () -> logic.getFilteredPersonList().stream()
                .map(p -> p.getName().getFirstName()).collect(Collectors.toList()));
    }

    /**
     * Setups Autocompletion Generator for stipulated command.
     */
    private void setupAutocompletionListeners(String commandPrefix, Supplier<List<String>> suggestionsDataGenerator) {
        disableFocusTraversal();
        setupAutocompleteTriggers(commandPrefix);
        setupAutocompleteActionKeys(suggestionsDataGenerator);
        setupBlockOtherKeystrokesInAcMode();

        // Exit Autocomplete Mode when backspace / enter is pressed.
        this.getCommandTextField().addEventFilter(KeyEvent.KEY_PRESSED, (event -> {
            if (isAutocompleteMode && (
                    event.getCode() == KeyCode.BACK_SPACE || event.getCode() == KeyCode.ENTER
            )) {
                hasSetPrefix = false;
                toggleAutocompleteModeOff();
            }
        }));
    }
    private void setupAutocompleteTriggers(String commandPrefix) {
        this.getCommandTextField().caretPositionProperty().addListener((unused1, unused2, newPosition) -> {
            String userInput = this.getCommandTextField().getText();
            int caretPos = newPosition.intValue();
            int prefixLength = commandPrefix.length();
            if (caretPos == 0) {
                toggleAutocompleteModeOff();
                hasSetPrefix = false;
                return;
            }

            if (userInput.length() > prefixLength - 1 && caretPos > prefixLength) {
                String substring = userInput.substring(caretPos - prefixLength);
                if (!isAutocompleteMode && substring.equals(commandPrefix)) {
                    toggleAutocompleteModeOn();
                    autoCompletePos = newPosition.intValue();
                }
                if (isAutocompleteMode) {
                    if (userInput.substring(caretPos - 1).isBlank() || caretPos < autoCompletePos) {
                        toggleAutocompleteModeOff();
                        hasSetPrefix = false;
                    }
                }
            }
        });
    }
    private void setupAutocompleteActionKeys(Supplier<List<String>> suggestionsDataRetriever) {
        Suggestions suggestions = new Suggestions();
        this.getCommandTextField().addEventFilter(KeyEvent.KEY_PRESSED, (event) -> {
            if (isAutocompleteMode && event.getCode() == KeyCode.TAB) {
                if (!hasSetPrefix) {
                    suggestions.setList(suggestionsDataRetriever.get());
                    String prefix = this.getCommandTextField().getText().substring(autoCompletePos);
                    suggestions.setPrefix(prefix);
                    hasSetPrefix = true;
                }
                if (event.isShiftDown()) {
                    // Shift + TAB : Previous Suggestion
                    String prev = suggestions.prevSuggestion();
                    int endIndex = this.getCommandTextField().caretPositionProperty().getValue();
                    this.getCommandTextField().replaceText(autoCompletePos, endIndex, prev);
                } else {
                    // TAB : Next Suggestion
                    String next = suggestions.nextSuggestion();
                    int endIndex= this.getCommandTextField().caretPositionProperty().getValue();
                    this.getCommandTextField().replaceText(autoCompletePos, endIndex, next);
                }
                if (suggestions.isBackToPrefix()) {
                    hasSetPrefix = false;
                }
                event.consume();
            }
        });
    }
    private void setupBlockOtherKeystrokesInAcMode() {
        this.getCommandTextField().addEventFilter(KeyEvent.ANY, (event) -> {
            if (hasSetPrefix
                    && event.getCode() != KeyCode.TAB
                    && event.getCode() != KeyCode.SPACE
                    && event.getCode() != KeyCode.ENTER
            ) {
                event.consume();
            }
            if (hasSetPrefix && event.getCode() == KeyCode.SPACE) {
                this.getCommandTextField().appendText(" ");
            }

        });
    }
    private void disableFocusTraversal() {
        this.getCommandTextField().addEventFilter(KeyEvent.KEY_PRESSED, (event) -> {
            if (!isAutocompleteMode) {
                if (event.getCode() == KeyCode.TAB) {
                    event.consume();
                }
                return;
            }
        });
    }
    private void toggleAutocompleteModeOn() {
        if (isAutocompleteMode) {
            return;
        }
        setStyleToIndicateAutocompleteMode();
        isAutocompleteMode = true;
    }
    private void toggleAutocompleteModeOff() {
        this.getCommandTextField().getStyleClass().remove(AC_MODE_STYLE_CLASS);
        isAutocompleteMode = false;
    }

    /**
     * Sets the command box style to indicate a Autocomplete Mode.
     */
    private void setStyleToIndicateAutocompleteMode() {
        ObservableList<String> styleClass = this.getCommandTextField().getStyleClass();

        if (styleClass.contains(AC_MODE_STYLE_CLASS)) {
            return;
        }

        styleClass.add(AC_MODE_STYLE_CLASS);
    }

    /**
     * Class that generates suggestions based on given prefix and given list.
     */
    private class Suggestions {
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
        }

        public boolean isBackToPrefix() {
            return index == 0;
        }
    }
}

