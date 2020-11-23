package seedu.address.logic.commands;

import java.util.function.Predicate;

import seedu.address.model.Model;

public abstract class FilterEntityCommand<T extends Predicate> extends Command {
    protected final T predicate;

    public FilterEntityCommand(T predicate) {
        this.predicate = predicate;
    }

    public abstract CommandResult execute(Model model);

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FilterEntityCommand // instanceof handles nulls
                && predicate.equals(((FilterEntityCommand) other).predicate)); // state check
    }
}
