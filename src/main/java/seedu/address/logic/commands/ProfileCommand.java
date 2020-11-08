package seedu.address.logic.commands;

import java.io.IOException;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.profile.Profile;
import seedu.address.model.vendor.Address;
import seedu.address.model.vendor.Phone;
import seedu.address.storage.Storage;


public class ProfileCommand extends Command {

    public static final String COMMAND_WORD = "profile";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Add delivery address and phone number for submission.\n"
            + "Format: profile PHONE ADDRESS\n"
            + "- PHONE represents your contact number and must be at least 3 digits long.\n"
            + "- ADDRESS represents your delivery address.\n"
            + "Examples:\n"
            + "profile 92030888 25 Lower Kent Ridge Rd, Singapore 119081: "
                + "Saves your address as '25 Lower Kent Ridge Rd, Singapore 119081' and phone number as '92030888'";

    public static final String MESSAGE_SUCCESS = "Profile has been set!";

    private final Address address;
    private final Phone phone;

    /**
     * ProfileCommand constructor
     * @param address address of Profile as String
     * @param phone phone number of Profile as String
     */
    public ProfileCommand(Phone phone, Address address) {
        this.phone = phone;
        this.address = address;
    }

    @Override
    public CommandResult execute(Model model, Storage storage) throws CommandException {
        try {
            Profile profile = new Profile(phone, address);
            storage.saveProfileManager(profile);
        } catch (IOException ie) {
            throw new CommandException(ProfileCommand.MESSAGE_USAGE);
        }

        return new CommandResult(ProfileCommand.MESSAGE_SUCCESS);
    }

    @Override
    public String toString() {
        return "Profile added:\nAddress: " + address + "\nPhone Number: " + phone;
    }

    @Override
    public boolean equals(Object other) {
        return other != null && other instanceof ProfileCommand
                && ((ProfileCommand) other).address.equals(address)
                && ((ProfileCommand) other).phone.equals(phone);
    }
}
