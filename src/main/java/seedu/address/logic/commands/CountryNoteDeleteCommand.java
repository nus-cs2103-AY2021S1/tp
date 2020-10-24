package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDEX;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.note.CountryNote;
import seedu.address.ui.WidgetViewOption;

public class CountryNoteDeleteCommand extends Command {

    public static final String COMMAND_WORD = "country ndelete"; // TODO: change to country note delete
    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Deletes the country note that are associated with the last viewed country at the given index.\n"
        + "Parameters: "
        + PREFIX_INDEX + "INDEX"
        + "Example: " + COMMAND_WORD + " " + PREFIX_INDEX + "1";
    private static final String MESSAGE_SUCCESS = "Deleted country note at index %1$s: %2$s";
    private final Index targetIndex;

    public CountryNoteDeleteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<CountryNote> lastShownList = model.getFilteredCountryNoteList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX);
        }

        CountryNote countryNoteToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteCountryNote(countryNoteToDelete);
        return new CommandResult(String.format(MESSAGE_SUCCESS, targetIndex.getOneBased(), countryNoteToDelete), false, false,
            WidgetViewOption.generateCountryNoteWidgetOption(null));
    }
}
