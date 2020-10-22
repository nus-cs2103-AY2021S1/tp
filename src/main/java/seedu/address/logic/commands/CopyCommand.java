package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.List;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonPredicate;

public class CopyCommand extends Command {
    public static final String COMMAND_WORD = "copy";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a label to the person specified.\n"
            + "Parameters: NAME (must be name of person existing in ModDuke) "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + "Roy "
            + PREFIX_TAG + "classmate";
    public static final String MESSAGE_EMAIL_COPIED_TO_CLIPBOARD = "Contact emails have been copied to your clipboard.";
    public static final String MESSAGE_PHONE_COPIED_TO_CLIPBOARD =
            "Contact phone numbers have been copied to your clipboard.";
    private final PersonPredicate predicate;
    private final boolean isEmail;

    /**
     * @param predicate the predicate based on the names, modules and tags given
     * @param isEmail boolean to check if email or phone number is to be copied
     */
    public CopyCommand(PersonPredicate predicate, boolean isEmail) {
        this.predicate = predicate;
        this.isEmail = isEmail;
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> people = model.getUpdatedFilteredPersonList(predicate);
        String results;
        if (isEmail) {
            results = people.stream()
                    .map(p -> p.getEmail().toString())
                    .reduce("", (x, y) -> x + " " + y);
        } else {
            results = people.stream()
                    .map(p -> p.getPhone().toString())
                    .reduce("", (x, y) -> x + " " + y);
        }
        StringSelection selection = new StringSelection(results);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(selection, selection);

        if (isEmail) {
            return new CommandResult(MESSAGE_EMAIL_COPIED_TO_CLIPBOARD);
        } else {
            return new CommandResult(MESSAGE_PHONE_COPIED_TO_CLIPBOARD);
        }
    }
}
