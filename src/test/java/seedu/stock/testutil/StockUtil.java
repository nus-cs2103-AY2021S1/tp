
package seedu.stock.testutil;

import static seedu.stock.logic.parser.CliSyntax.PREFIX_INCREMENT_QUANTITY;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_LOW_QUANTITY;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_NEW_QUANTITY;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_QUANTITY;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_SOURCE;

import seedu.stock.logic.commands.AddCommand;
import seedu.stock.logic.commands.UpdateCommand;
import seedu.stock.model.stock.Stock;

/**
 * A utility class for Stock.
 */
public class StockUtil {

    /**
     * Returns an add command string for adding the {@code stock}.
     */
    public static String getAddCommand(Stock stock) {
        return AddCommand.COMMAND_WORD + " " + getStockDetails(stock);
    }

    /**
     * Returns the part of command string for the given {@code stock}'s details.
     */
    public static String getStockDetails(Stock stock) {
        StringBuilder sb = new StringBuilder();

        sb.append(PREFIX_NAME).append(stock.getName().fullName).append(" ");
        sb.append(PREFIX_SOURCE).append(stock.getSource().value).append(" ");
        sb.append(PREFIX_QUANTITY).append(stock.getQuantity().quantity).append(" ");
        sb.append(PREFIX_LOCATION).append(stock.getLocation().value).append(" ");
        sb.append(PREFIX_LOW_QUANTITY).append(stock.getQuantity().lowQuantity).append(" ");
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code UpdateStockDescriptor}'s details.
     */
    public static String getUpdateStockDescriptorDetailsIncrementedQuantity(
            UpdateCommand.UpdateStockDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();

        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getSource().ifPresent(source -> sb.append(PREFIX_SOURCE).append(source.value).append(" "));
        descriptor.getQuantity().ifPresent(quantity ->
                sb.append(PREFIX_QUANTITY).append(quantity.toString()).append(" "));
        descriptor.getLocation().ifPresent(location -> sb.append(PREFIX_LOCATION).append(location.value).append(" "));
        descriptor.getQuantityAdder().ifPresent(quantityAdder ->
                                    sb.append(PREFIX_INCREMENT_QUANTITY).append(quantityAdder.toString()).append(" "));
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code UpdateStockDescriptor}'s details.
     */
    public static String getUpdateStockDescriptorDetailsNewQuantity(UpdateCommand.UpdateStockDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();

        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getSource().ifPresent(source -> sb.append(PREFIX_SOURCE).append(source.value).append(" "));
        descriptor.getQuantity().ifPresent(quantity ->
                sb.append(PREFIX_NEW_QUANTITY).append(quantity.toString()).append(" "));
        descriptor.getLocation().ifPresent(location -> sb.append(PREFIX_LOCATION).append(location.value).append(" "));
        descriptor.getQuantityAdder().ifPresent(quantityAdder ->
                                    sb.append(PREFIX_NEW_QUANTITY).append(quantityAdder.toString()).append(" "));
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code UpdateStockDescriptor}'s details.
     */
    public static String getUpdateStockDescriptorDetailsNonQuantity(UpdateCommand.UpdateStockDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();

        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getSource().ifPresent(source -> sb.append(PREFIX_SOURCE).append(source.value).append(" "));
        descriptor.getLocation().ifPresent(location -> sb.append(PREFIX_LOCATION).append(location.value).append(" "));
        return sb.toString();
    }
}
