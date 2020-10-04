package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.TutorialGroup;
import seedu.address.model.person.Person;

import static java.util.Objects.requireNonNull;

public class AddTGCommand extends Command {

    public static final String COMMAND_WORD = "addTG";
    public static final String MESSAGE_SUCCESS = "Tutorial Grp has been added";
    public static final String MESSAGE_DUPLICATE_TUTGRP = "This Tutorial Group already exists";
    public static final String MESSAGE_USAGE = "This is the message usage";


    private final TutorialGroup toAdd;

    public AddTGCommand(TutorialGroup tutorialGroup) {
        requireNonNull(tutorialGroup);
        toAdd = tutorialGroup;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        throw new CommandException("TG Addition not implemented yet");
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && toAdd.equals(((AddTGCommand) other).toAdd));
    }
}
