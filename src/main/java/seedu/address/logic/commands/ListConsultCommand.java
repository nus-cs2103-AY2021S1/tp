package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_CONSULTS;

import seedu.address.model.Model;

public class ListConsultCommand extends Command {
    public static final String COMMAND_WORD = "list-consult";

    public static final String MESSAGE_SUCCESS = "Listed all consultations!\nYou may view results in the "
        + "Consultations tab.";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredConsultList(PREDICATE_SHOW_ALL_CONSULTS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
