package seedu.address.ui;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import seedu.address.logic.textFieldModules.AutocompleteModule;
import seedu.address.logic.textFieldModules.FzfModule;


public class AutocompleteCommandBox extends CommandBox {

    AutocompleteModule autocompleteModule;

    /**
     * Creates a {@code AutocompleteCommandBox} with the given {@code CommandExecutor}.
     */
    public AutocompleteCommandBox(CommandExecutor commandExecutor) {
        super(commandExecutor);

        autocompleteModule = AutocompleteModule.attachTo(this.getCommandTextField());

        FzfModule.attachTo(this.getCommandTextField(), () ->
                autocompleteModule
                        .getSuggestionsList()
                        .values()
                        .stream()
                        .flatMap(x -> x.getFilteredList().stream())
                        .collect(Collectors.toList()));
    }

    public void setupAutocompletionListeners(String commandPrefix, Supplier<List<String>> suggestionsDataGenerator) {
        requireNonNull(commandPrefix);
        requireNonNull(suggestionsDataGenerator);

        autocompleteModule.addSuggestions(commandPrefix, suggestionsDataGenerator);
    }
}

