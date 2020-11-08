package seedu.address.commons.core;

/**
 * Container for user visible messages.
 */
public class Messages {
    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format!\n%1$s";
    public static final String MESSAGE_INVALID_INDEX = "%s is not a non-zero unsigned integer.";
    public static final String MESSAGE_INVALID_QUANTITY = "Quantity given is invalid.";
    public static final String MESSAGE_INVALID_VENDOR_DISPLAYED_INDEX = "The vendor index provided is invalid";
    public static final String MESSAGE_INVALID_ORDERITEM_DISPLAYED_INDEX = "The order item index provided is invalid";
    public static final String MESSAGE_INVALID_ORDERITEM_DISPLAYED_QUANTITY = "The order item quantity "
            + "provided is invalid";
    public static final String MESSAGE_INVALID_PRICE = "%s is not a non-negative unsigned real number.";
    public static final String MESSAGE_PRICE_GREATER_THAN_LIMIT = "$%.2f is too large of a price!";
    public static final String MESSAGE_INVALID_INEQUALITY = "%s is not a valid inequality sign. It must be either "
            + "\"<\", \"<=\", \">\", or \">=\" (without quotes). See User Guide for more info.";

    public static final String MESSAGE_VENDOR_NOT_SELECTED = "A vendor has not been selected yet,"
            + " please choose a vendor.";
    public static final String MESSAGE_EMPTY_ORDER = "The order is currently empty,"
            + " please add an order.";
    public static final String MESSAGE_INSUFFICIENT_ARGUMENTS = "%s command requires at least %s argument(s).\n%s";
    public static final String MESSAGE_TOO_MANY_ARGUMENTS = "%s command should not have more than %s arguments.\n%s";
    public static final String MESSAGE_CHAIN =
            "1. You can further chain the current filter with another `find` or `price` or `sort` command.\n"
            + "2. Filters can be reset with the `menu` command.";
    public static final String MESSAGE_FOOD_LISTED_OVERVIEW = "%1$d food listed!\n" + MESSAGE_CHAIN;
    public static final String MESSAGE_FOOD_LISTED_PRICE_CONTEXT = "%1$d food with price %2$s listed!\n"
            + MESSAGE_CHAIN;
    public static final String MESSAGE_FOOD_SORTED = "Food successfully sorted!\n" + MESSAGE_CHAIN;
    public static final String MESSAGE_MENU_LIST = "All food listed!";
    public static final String MESSAGE_ORDERITEM_QUANTITY_EXCEED = "Cannot have more than 100 of the an order item.";
    public static final String MESSAGE_EMPTY_PROFILE = "Please create a profile first before using the submit command.";

    public static final String MESSAGE_AMBIGUOUS_COMMAND = "Ambiguous command. The following commands matches the "
            + "prefix: %s";
    public static final String MESSAGE_PRESET_SAVE_NO_ORDER = "You have not added any items to your order to be saved!";
    public static final String MESSAGE_PRESET_NO_SAVED_PRESETS = "You have not saved any presets for this vendor!";
    public static final String MESSAGE_PRESET_OVERWRITE_SUCCESS = "Preset %s has been overwritten.";
    public static final String MESSAGE_PRESET_SAVE_SUCCESS = "Preset %s has been saved.";
    public static final String MESSAGE_PRESET_LOAD_SUCCESS = "Preset %s has been loaded.";
    public static final String MESSAGE_PRESET_DELETE_SUCCESS = "Preset %s has been deleted.";
    public static final String MESSAGE_PRESET_LOAD_ERROR = "Presets cannot be read.";
    public static final String MESSAGE_PRESET_NOT_FOUND = "Preset %s cannot be found.";
    public static final String MESSAGE_NO_INPUT_NAME = "You must specify a preset name to delete!";
    public static final String MESSAGE_EXISTING_TAG = "'%s' is already tagged to the order item!";
}
