package seedu.address.logic.commands;

import java.io.IOException;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.profile.Profile;
import seedu.address.storage.Storage;


public class ProfileCommand extends Command {

    public static final String COMMAND_WORD = "profile";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Add delivery address and phone number for submission.\n"
            + "Format: profile a/ADDRESS p/PHONE\n"
            + "- ADDRESS represents your delivery address.\n"
            + "- PHONE represents your contact number and must be at least 3 digits long.\n"
            + "Examples:\n"
            + "profile a/25 Lower Kent Ridge Rd, Singapore 119081 p/92030888: "
                + "Saves your address as '25 Lower Kent Ridge Rd, Singapore 119081' and phone number as '92030888'";

    public static final String MESSAGE_SUCCESS = "Profile Successfully added!";

    private final String address;
    private final String phone;

    /**
     * ProfileCommand constructor
     * @param address address of Profile as String
     * @param phone phone number of Profile as String
     */
    public ProfileCommand(String address, String phone) {
        this.address = address;
        this.phone = phone;
    }

    @Override
    public CommandResult execute(Model model, Storage storage) throws CommandException {
        try {
            Profile profile = new Profile(address, phone);
            storage.saveProfileManager(profile);
        } catch (IOException ie) {
            throw new CommandException(ProfileCommand.MESSAGE_USAGE);
        }

        return new CommandResult(ProfileCommand.MESSAGE_SUCCESS);
    }
}
