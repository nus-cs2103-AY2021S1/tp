package seedu.address.commons.core;

/**
 * Container for user visible messages.
 */
public class Messages {
    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_INDEX = "%s is not a non-zero unsigned integer.";
    public static final String MESSAGE_INVALID_QUANTITY = "Quantity given is invalid.";

    public static final String MESSAGE_FOOD_LISTED_OVERVIEW = "%1$d food listed!\n"
            + "1. You can further chain the current filter with another `find` or `price` command.\n"
            + "2. Filters can be reset with the `menu` command.";
    public static final String MESSAGE_FOOD_LISTED_PRICE_CONTEXT = "%1$d food with price %2$s listed!";
    public static final String MESSAGE_MENU_LIST = "All food listed!";
    public static final String MESSAGE_ORDERITEM_QUANTITY_EXCEED = "Cannot have more than 100 of the an order item.";

    public static final String MESSAGE_AMBIGUOUS_COMMAND = "Ambiguous command. The following commands matches the "
            + "prefix: %s";
    public static final String MESSAGE_PRESET_SAVE_SUCCESS = "Preset has been saved.";
    public static final String MESSAGE_PRESET_OVERWRITE_SUCCESS = "Preset has been overwritten.";
    public static final String MESSAGE_PRESET_LOAD_SUCCESS = "Preset %s has been loaded.";
    public static final String MESSAGE_PRESET_LOAD_ERROR = "Presets cannot be read.";
    public static final String MESSAGE_PRESET_NOT_FOUND = "Preset %s cannot be found.";
}
