package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.patient.NricPredicate;

/**
 * Displays all appointments for a patient in Hospify.
 */
public class ShowMrCommand extends Command {

    public static final String COMMAND_WORD = "showMr";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Open the medical record of a patient in your system's default browser\n"
            + "Parameters: NRIC\n"
            + "Example: " + COMMAND_WORD + " S1234567A\n";

    private final NricPredicate predicate;

    public ShowMrCommand(NricPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredPatientList(predicate);
        assert model.getFilteredPatientList().size() <= 1 : "Found more than 1 person for showMr";
        if (model.getFilteredPatientList().size() != 1) {
            throw new CommandException("No patient with the NRIC found!");
        }

        try {
            String url = model.getFilteredPatientList().get(0).getMedicalRecord().value;
            if (!url.startsWith("http")) {
                url = "https://" + url;
            }
            Desktop.getDesktop().browse(new URL(url).toURI());
        } catch (IOException e) {
            throw new CommandException(String.format(Messages.MESSAGE_ERROR_OPENING_URL,
                    "The URL cannot be opened on your machine. Please copy the URL Link and open it manually."));
        } catch (URISyntaxException e) {
            throw new CommandException(String.format(
                    Messages.MESSAGE_ERROR_OPENING_URL,
                    "The Mr URL is bad. Please check the URL again."
            ));
        }

        return new CommandResult(
                String.format(Messages.MESSAGE_OPEN_URL_SUCCESS, "MR of patient below opened successfully!")
        );
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ShowMrCommand // instanceof handles nulls
                && predicate.equals(((ShowMrCommand) other).predicate)); // state check
    }
}
