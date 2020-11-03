package seedu.address.testutil.bidder;

import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;

import seedu.address.logic.commands.biddercommands.AddBidderCommand;
import seedu.address.logic.commands.biddercommands.EditBidderCommand.EditBidderDescriptor;
import seedu.address.model.person.bidder.Bidder;

/**
 * A utility class for Bidder.
 */
public class BidderUtil {

    /**
     * Returns an add command string for adding the {@code bidder}.
     */
    public static String getAddBidderCommand(Bidder bidder) {
        return AddBidderCommand.COMMAND_WORD + " " + getBidderDetails(bidder);
    }

    /**
     * Returns the part of command string for the given {@code bidder}'s details.
     */
    public static String getBidderDetails(Bidder bidder) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + bidder.getName().fullName + " ");
        sb.append(PREFIX_PHONE + bidder.getPhone().value + " ");
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditBidderDescriptor}'s details.
     */
    public static String getEditBidderDescriptorDetails(EditBidderDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.value).append(" "));
        return sb.toString();
    }
}
