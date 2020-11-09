package seedu.address.testutil.seller;

import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;

import seedu.address.logic.commands.sellercommands.AddSellerCommand;
import seedu.address.logic.commands.sellercommands.EditSellerCommand.EditSellerDescriptor;
import seedu.address.model.person.seller.Seller;

/**
 * A utility class for Seller.
 */
public class SellerUtil {

    /**
     * Returns an add command string for adding the {@code seller}.
     */
    public static String getAddSellerCommand(Seller seller) {
        return AddSellerCommand.COMMAND_WORD + " " + getSellerDetails(seller);
    }

    /**
     * Returns the part of command string for the given {@code seller}'s details.
     */
    public static String getSellerDetails(Seller seller) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + seller.getName().fullName + " ");
        sb.append(PREFIX_PHONE + seller.getPhone().value + " ");
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditSellerDescriptor}'s details.
     */
    public static String getEditSellerDescriptorDetails(EditSellerDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.value).append(" "));
        return sb.toString();
    }
}
