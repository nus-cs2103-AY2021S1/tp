package seedu.address.logic.commands.statistics;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.view.View;

public class StatisticsCommand extends Command {

    public static final String COMMAND_WORD = "stats";
    private static String messageSuccess = "Viewing statistics.";
    public final int deckIndex;

    public StatisticsCommand(int deckIndex) {
        this.deckIndex = deckIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        int currentDeckIndex = model
                .setStatisticsDeckId(deckIndex - 1);

        if (currentDeckIndex == -1) { // invalid deck, or none
            messageSuccess = "No deck / invalid deck ID selected. Showing statistics across all decks.";
            // messageSuccess = "Either invalid deck index or index out of bounds. \n"
            //         + "Please choose an index that is listed and ensure it is a positive integer that is less than "
            //         + "2,147,483,648 "
            //         + "Viewing statistics across all decks instead.";
        } else {
            messageSuccess = String.format("Viewing statistics for deck %s (id=%d)",
                    model.getFilteredDeckList().get(currentDeckIndex).getDeckName(), currentDeckIndex + 1);
        }

        model.setCurrentView(View.STATISTICS_VIEW);
        return new CommandResult(messageSuccess);
    }
}
