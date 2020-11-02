package seedu.address.ui;

import java.util.HashMap;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javafx.collections.ObservableList;
import javafx.geometry.Point2D;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;


public class AutocompleteCommandBox extends CommandBox {

    public static final String AC_MODE_STYLE_CLASS = "autocomplete-mode";

    private boolean isAutocompleteMode = false;
    private boolean hasSetPrefix = false;
    private String modeType = "";
    private int autoCompletePos;
    private int commandPrefixPos;

    private HashMap<String, Suggestions> suggestionsList;
    ContextMenu fzfMenu;

    /**
     * Creates a {@code AutocompleteCommandBox} with the given {@code CommandExecutor}.
     */
    public AutocompleteCommandBox(CommandExecutor commandExecutor) {
        super(commandExecutor);
        disableFocusTraversal();
        setupAutocompleteTriggers();
        setupAutocompleteActionKeys();
        setupBlockOtherKeystrokesInAcMode();
        setupExitKeys();
        suggestionsList = new HashMap<>();
        fzfMenu = new ContextMenu();
    }

    /*
    public void fzf() {
        commandBox.setupAutocompletionListeners("@", () ->
        {
            Stream<String> persons = logic.getFilteredPersonList().stream().map(p -> p.getName().fullName);
            Stream<String> modules = logic.getFilteredModuleList().stream().map(m -> m.getModuleName().getModuleName());
            Stream<String> meetings = logic.getFilteredMeetingList().stream().map(m -> m.getMeetingName().meetingName);

            return Stream.of(persons, modules, meetings).flatMap(s -> s).collect(Collectors.toList());
        });

        this.getCommandTextField().addEventFilter(KeyEvent.KEY_PRESSED, (event) -> {
                    TextInputControl control = (TextInputControl) event.getSource();
                    Point2D pos = control.getInputMethodRequests().getTextLocation(0);


                    fzfMenu.getItems().clear();
                    fzfMenu.getItems().addAll(
                            suggestions
                                    .getFilteredList()
                                    .stream()
                                    .map(
                                            x -> {
                                                return new MenuItem(x);
                                            }
                                    ).collect(Collectors.toList()));


                    fzfMenu.show(control, pos.getX(), pos.getY());
                    this.getCommandTextField().requestFocus();
                }
        );


    }


     */
    /**
     * Setups Autocompletion Generator for stipulated command prefix.
     */

    public void setupAutocompletionListeners(String commandPrefix, Supplier<List<String>> suggestionsDataGenerator) {
        Suggestions suggestions = new Suggestions(suggestionsDataGenerator);
        suggestionsList.put(commandPrefix, suggestions);
    }
    private void setupAutocompleteTriggers() {
        this.getCommandTextField().caretPositionProperty().addListener((unused1, unused2, newPosition) -> {
            String userInput = this.getCommandTextField().getText();
            int caretPos = newPosition.intValue();
            if (caretPos == 0) {
                toggleAutocompleteModeOff();
                hasSetPrefix = false;
                return;
            }

            for(String cmdP : suggestionsList.keySet().toArray(new String[]{})){
                int prefixLength = cmdP.length();
                if (userInput.length() >= prefixLength && caretPos >= prefixLength - 1) {
                    String substring = userInput.substring(caretPos - prefixLength);
                    if (!isAutocompleteMode && substring.equals(cmdP)) {
                        commandPrefixPos = caretPos;
                        toggleAutocompleteModeOn(cmdP);
                        autoCompletePos = newPosition.intValue();
                    }
                    if (isAutocompleteMode) {
                        if (caretPos < autoCompletePos) {
                            toggleAutocompleteModeOff();
                            hasSetPrefix = false;
                        }
                    }
                }
            }


        });
    }
    private void setupAutocompleteActionKeys() {
        this.getCommandTextField().addEventFilter(KeyEvent.KEY_PRESSED, (event) -> {
            if (isAutocompleteMode && event.getCode() == KeyCode.TAB) {
                Suggestions suggestions = suggestionsList.get(modeType);
                if (!hasSetPrefix) {
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
                    int endIndex = this.getCommandTextField().caretPositionProperty().getValue();
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
                    && event.getCode() != KeyCode.ENTER
            ) {
                event.consume();
            }
        });
    }
    private void setupExitKeys() {
        // Exit Autocomplete Mode when backspace / enter is pressed.
        this.getCommandTextField().addEventFilter(KeyEvent.KEY_PRESSED, (event -> {
            if (isAutocompleteMode && (
                    event.getCode() == KeyCode.BACK_SPACE
                            || event.getCode() == KeyCode.ENTER)) {
                hasSetPrefix = false;
                if (event.getCode() == KeyCode.BACK_SPACE) {
                    return;
                }
                toggleAutocompleteModeOff();
                removePrefixFromCompletion();
                event.consume();
            }
        }));
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
    private void toggleAutocompleteModeOn(String commandPrefix) {
        if (isAutocompleteMode) {
            return;
        }
        setStyleToIndicateAutocompleteMode();
        isAutocompleteMode = true;
        modeType = commandPrefix;
    }
    private void toggleAutocompleteModeOff() {
        if (!isAutocompleteMode) {
            return;
        }
        isAutocompleteMode = false;
        this.getCommandTextField().getStyleClass().remove(AC_MODE_STYLE_CLASS);
    }
    private void removePrefixFromCompletion() {
        String currentText = getCommandTextField().getText();
        String result = currentText.substring(0 , commandPrefixPos - modeType.length())
                + currentText.substring(commandPrefixPos);
        int caretPosition = getCommandTextField().getCaretPosition();
        getCommandTextField().setText(result);
        getCommandTextField().positionCaret(caretPosition - modeType.length());
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
        private List<String> suggestions;
        private String prefix = "";
        private int index = 0;
        private final Supplier<List<String>> listSupplier;

        public Suggestions(Supplier<List<String>> listSupplier) {
            this.listSupplier = listSupplier;
        }

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

        public void setPrefix(String prefix) {
            this.index = 0;
            this.prefix = prefix;
            suggestions = listSupplier.get().stream().filter(x -> x.toLowerCase().startsWith(prefix.toLowerCase()))
                    .collect(Collectors.toList());
            suggestions.sort((o1, o2) -> o1.compareTo(o2));
            suggestions.add(0, prefix);
        }

        public boolean isBackToPrefix() {
            return index == 0;
        }

        public List<String> getFilteredList() {
            return suggestions;
        }

        public Supplier<List<String>> getListSupplier() {
            return listSupplier;
        }
    }
}

