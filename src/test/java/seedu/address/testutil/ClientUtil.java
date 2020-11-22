package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTRACT_EXPIRY_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COUNTRY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIMEZONE;

import seedu.address.logic.commands.ClientAddCommand;
import seedu.address.logic.commands.ClientEditCommand.EditClientDescriptor;
import seedu.address.model.client.Client;

/**
 * A utility class for Client.
 */
public class ClientUtil {

    /**
     * Returns an add command string for adding the {@code client}.
     */

    public static String getAddCommand(Client client) {
        return ClientAddCommand.COMMAND_WORD + " " + getClientDetails(client);
    }

    /**
     * Returns the part of command string for the given {@code client}'s details.
     */
    public static String getClientDetails(Client client) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + client.getName().fullName + " ");
        sb.append(PREFIX_PHONE + client.getPhone().value + " ");
        sb.append(PREFIX_EMAIL + client.getEmail().value + " ");
        sb.append(PREFIX_ADDRESS + client.getAddress().value + " ");
        sb.append(PREFIX_COUNTRY + client.getCountry().getCountryCode() + " ");
        sb.append(PREFIX_TIMEZONE + client.getTimezone().toString() + " ");
        sb.append(PREFIX_CONTRACT_EXPIRY_DATE + client.getContractExpiryDate().value + " ");
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditClientDescriptor}'s details.
     */
    public static String getEditClientDescriptorDetails(EditClientDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.value).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
        descriptor.getAddress().ifPresent(address -> sb.append(PREFIX_ADDRESS).append(address.value).append(" "));
        descriptor.getCountry().ifPresent(country -> sb.append(PREFIX_COUNTRY).append(country.getCountryCode())
                .append(" "));
        descriptor.getTimezone().ifPresent(timezone -> sb.append(PREFIX_TIMEZONE)
                .append(timezone.toString()).append(" "));
        descriptor.getContractExpiryDate().ifPresent(contractExpiryDate ->
                sb.append(PREFIX_CONTRACT_EXPIRY_DATE).append(contractExpiryDate.value).append(" "));

        return sb.toString();
    }
}
