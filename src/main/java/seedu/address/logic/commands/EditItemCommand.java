package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ITEM_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ITEM_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ITEM_QUANTITY;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_ITEMS;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.item.Item;
import seedu.address.model.item.Quantity;
import seedu.address.model.tag.Tag;

/**
 * Edits the details of an existing item in the inventory.
 */
public class EditItemCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the item "
            + "at the index number used in the displayed item list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_ITEM_NAME + "NAME] "
            + "[" + PREFIX_ITEM_QUANTITY + "QUANTITY] "
            + "[" + PREFIX_ITEM_DESCRIPTION + "DESCRIPTION] \n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_ITEM_QUANTITY + "20 ";

    public static final String MESSAGE_EDIT_ITEM_SUCCESS = "Edited Item: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_ITEM = "This item already exists in the item list.";
    public static final String MESSAGE_INVALID_ITEM_DISPLAYED_INDEX = "The index must be a positive integer.";

    private final Index index;
    private final EditItemDescriptor editItemDescriptor;

    /**
     * @param index of the item in the filtered item list to edit
     * @param editItemDescriptor details to edit the item with
     */
    public EditItemCommand(Index index, EditItemDescriptor editItemDescriptor) {
        requireNonNull(index);
        requireNonNull(editItemDescriptor);

        this.index = index;
        this.editItemDescriptor = new EditItemDescriptor(editItemDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Item> lastShownList = model.getFilteredItemList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(MESSAGE_INVALID_ITEM_DISPLAYED_INDEX);
        }

        Item itemToEdit = lastShownList.get(index.getZeroBased());
        Item editedItem = createEditedItem(itemToEdit, editItemDescriptor);

        if (!editItemDescriptor.isAnyFieldEdited()) {
            throw new CommandException(MESSAGE_NOT_EDITED);
        }

        if (!itemToEdit.isSameItem(editedItem) && model.hasItem(editedItem)) {
            throw new CommandException(MESSAGE_DUPLICATE_ITEM);
        }

        // update affected recipes
        if (editItemDescriptor.getName().isPresent()) {
            model.updateRecipeNames(itemToEdit.getName(), editItemDescriptor.getName().get());
        }

        model.setItem(itemToEdit, editedItem);
        model.updateFilteredItemList(PREDICATE_SHOW_ALL_ITEMS);

        return new CommandResult(String.format(MESSAGE_EDIT_ITEM_SUCCESS, editedItem));
    }

    /**
     * Creates and returns a {@code Item} with the details of {@code itemToEdit}
     * edited with {@code editItemDescriptor}.
     */
    private static Item createEditedItem(Item itemToEdit, EditItemDescriptor editItemDescriptor) {
        assert itemToEdit != null;

        String updatedName = editItemDescriptor.getName().orElse(itemToEdit.getName());
        Quantity updatedQuantity = editItemDescriptor.getQuantity().orElse(itemToEdit.getQuantity());
        String updatedDescription = editItemDescriptor.getDescription().orElse(itemToEdit.getDescription());
        // ID, locations, recipe ids, and tags cannot be changed
        int id = itemToEdit.getId();
        Set<Integer> locations = Set.copyOf(itemToEdit.getLocationIds());
        Set<Integer> recipes = Set.copyOf(itemToEdit.getRecipeIds());
        Set<Tag> tags = itemToEdit.getTags();

        return new Item(id, updatedName, updatedQuantity, updatedDescription, locations, recipes, tags, false);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditItemCommand)) {
            return false;
        }

        // state check
        EditItemCommand e = (EditItemCommand) other;
        return index.equals(e.index)
                && editItemDescriptor.equals(e.editItemDescriptor);
    }

    /**
     * Stores the details to edit the item with. Each non-empty field value will replace the
     * corresponding field value of the item.
     */
    public static class EditItemDescriptor {
        private String updatedName;
        private Quantity updatedQuantity;
        private String updatedDescription;
        private Set<Tag> tags;

        public EditItemDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditItemDescriptor(EditItemDescriptor toCopy) {
            setName(toCopy.updatedName);
            setQuantity(toCopy.updatedQuantity);
            setDescription(toCopy.updatedDescription);
            setTags(toCopy.tags);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(updatedName, updatedQuantity, updatedDescription, tags);
        }

        public void setName(String updatedName) {
            this.updatedName = updatedName;
        }

        public Optional<String> getName() {
            return Optional.ofNullable(updatedName);
        }

        public void setQuantity(Quantity updatedQuantity) {
            this.updatedQuantity = updatedQuantity;
        }

        public Optional<Quantity> getQuantity() {
            return Optional.ofNullable(updatedQuantity);
        }
        public void setDescription(String updatedDescription) {
            this.updatedDescription = updatedDescription;
        }

        public Optional<String> getDescription() {
            return Optional.ofNullable(updatedDescription);
        }

        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setTags(Set<Tag> tags) {
            this.tags = (tags != null) ? new HashSet<>(tags) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Tag>> getTags() {
            return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditItemDescriptor)) {
                return false;
            }

            // state check
            EditItemDescriptor e = (EditItemDescriptor) other;

            return getName().equals(e.getName())
                    && getQuantity().equals(e.getQuantity())
                    && getDescription().equals(e.getDescription())
                    && getTags().equals(e.getTags());
        }
    }
}
