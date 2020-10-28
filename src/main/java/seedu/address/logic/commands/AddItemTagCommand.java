package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ITEM_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ITEM_TAG;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.EditItemCommand.EditItemDescriptor;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.item.Item;
import seedu.address.model.tag.Tag;

public class AddItemTagCommand extends Command {
    public static final String COMMAND_WORD = "addt";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a set of tags to an item "
            + "at the index number used in the displayed item list. "
            + "Existing tags added will be ignored by the command.\n"
            + "Parameters: "
            + "[" + PREFIX_ITEM_NAME + "NAME] "
            + "[" + PREFIX_ITEM_TAG + "TAG(s) TO ADD] "
            + "Example: " + COMMAND_WORD + " " + PREFIX_ITEM_NAME + "Iron "
            + PREFIX_ITEM_TAG + "delicious, tuturu";

    public static final String MESSAGE_TAG_NOT_ADDED = "No new tag is added.";
    public static final String MESSAGE_TAG_NOT_PROVIDED = "Item Tag was not provided";

    private static final Logger logger = LogsCenter.getLogger(AddItemTagCommand.class);

    private final String itemName;
    private final Set<Tag> tags;

    /**
     * Constructs AddItemTagCommand to execute command
     * @param itemName item name to identify item in the list
     * @param tags set of tags
     */
    public AddItemTagCommand(String itemName, Set<Tag> tags) {
        requireNonNull(itemName);

        this.itemName = itemName;
        this.tags = new HashSet<>();
        this.tags.addAll(tags);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Item> itemList = new ArrayList<>(model.getFilteredItemList());

        // filter to only get matching and not deleted items
        itemList.removeIf(x -> !x.getName().equals(itemName));

        Item itemToEdit = itemList.stream()
                .findFirst() // Get the first (and only) item matching or else throw Error
                .orElseThrow(() -> new CommandException(String.format(Messages.MESSAGE_RECIPE_NOT_FOUND, itemName)));

        assert (itemToEdit != null);
        assert (itemToEdit.getTags() != null);

        if (itemToEdit.getTags().equals(tags)) {
            throw new CommandException(MESSAGE_TAG_NOT_ADDED);
        }

        // adds all tags including overlaps, as the datastructure underlying is a set
        tags.addAll(itemToEdit.getTags());

        if (itemToEdit.getTags().equals(tags)) {
            throw new CommandException(MESSAGE_TAG_NOT_ADDED);
        }

        EditItemDescriptor editItemDescriptor = new EditItemDescriptor();

        editItemDescriptor.setName(itemToEdit.getName());
        editItemDescriptor.setDescription(itemToEdit.getDescription());
        editItemDescriptor.setQuantity(itemToEdit.getQuantity());
        editItemDescriptor.setTags(tags);

        EditItemCommand editItemCommand = new EditItemCommand(itemName, editItemDescriptor);

        logger.info(itemToEdit.getName() + "'s tags changed to " + tags + ".");
        return editItemCommand.execute(model);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddItemTagCommand)) {
            return false;
        }

        // check if itemName and quantity are the same
        AddItemTagCommand a = (AddItemTagCommand) other;
        return itemName.equals(a.itemName)
                && tags.equals(a.tags);
    }
}
