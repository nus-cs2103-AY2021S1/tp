package seedu.address.logic.commands.biddercommands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_BIDDERS;

import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.EntityType;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.id.BidderId;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.bidder.Bidder;
import seedu.address.model.tag.Tag;

public class EditBidderCommand extends Command {

    public static final String COMMAND_WORD = "edit-b";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the bidder identified "
            + "by the index number used in the displayed bidder list. "
            + "\nExisting values will be overwritten by the input values.\n"
            + "\n\nParameters: \nINDEX (must be a positive integer) "
            + "\n[" + PREFIX_NAME + "NAME] "
            + "\n[" + PREFIX_PHONE + "PHONE] "
            + "\nExample: " + COMMAND_WORD + " 1 "
            + PREFIX_PHONE + "91234567 ";

    public static final String MESSAGE_EDIT_BIDDER_SUCCESS = "Edited Bidder: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_BIDDER = "This bidder already exists in the address book.";

    private final Index index;
    private final EditBidderDescriptor editBidderDescriptor;

    /**
     * @param index of the person in the filtered person list to edit
     * @param editBidderDescriptor details to edit the person with
     */
    public EditBidderCommand(Index index, EditBidderDescriptor editBidderDescriptor) {
        requireNonNull(index);
        requireNonNull(editBidderDescriptor);
        this.index = index;
        this.editBidderDescriptor = new EditBidderDescriptor(editBidderDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Bidder> lastShownList = model.getFilteredBidderList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_BIDDER_DISPLAYED_INDEX);
        }

        Bidder bidderToEdit = lastShownList.get(index.getZeroBased());
        Bidder editedBidder = createEditedBidder(bidderToEdit, editBidderDescriptor);

        if (!bidderToEdit.isSameBidder(editedBidder) || model.hasBidder(editedBidder)) {
            throw new CommandException(MESSAGE_DUPLICATE_BIDDER);
        }

        model.setBidder(bidderToEdit, editedBidder);
        model.updateFilteredBidderList(PREDICATE_SHOW_ALL_BIDDERS);
        return new CommandResult(
                String.format(MESSAGE_EDIT_BIDDER_SUCCESS, editedBidder)).setEntity(EntityType.BIDDER);
    }

    /**
     * Creates and returns a {@code Bidder} with the details of {@code bidderToEdit}
     * edited with {@code editBidderDescriptor}.
     */
    private static Bidder createEditedBidder(Bidder bidderToEdit, EditBidderDescriptor editBidderDescriptor) {
        assert bidderToEdit != null;
        Name updatedName = editBidderDescriptor.getName().orElse(bidderToEdit.getName());
        Phone updatedPhone = editBidderDescriptor.getPhone().orElse(bidderToEdit.getPhone());
        BidderId unmodifiedBidderId = (BidderId) bidderToEdit.getId();
        return new Bidder(updatedName, updatedPhone, unmodifiedBidderId);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditBidderCommand)) {
            return false;
        }

        // state check
        EditBidderCommand e = (EditBidderCommand) other;
        return index.equals(e.index)
                && editBidderDescriptor.equals(e.editBidderDescriptor);
    }


    /**
     * Stores the details to edit the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class EditBidderDescriptor {

        private Name name;
        private Phone phone;
        private Tag tag;
        private BidderId id;

        public EditBidderDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditBidderDescriptor(EditBidderDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setTag(toCopy.tag);
            setBidderId(toCopy.id);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, tag, id);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setPhone(Phone phone) {
            this.phone = phone;
        }

        public Optional<Phone> getPhone() {
            return Optional.ofNullable(phone);
        }

        /**
         * Sets {@code tag} to this object's {@code tag}.
         * A defensive copy of {@code tag} is used internally.
         */
        public void setTag(Tag tag) {
            this.tag = tag;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Tag> getTag() {
            return Optional.ofNullable(tag);
        }

        public void setBidderId(BidderId id) {
            this.id = id;
        }

        public Optional<BidderId> getId() {
            return (id != null) ? Optional.of(id) : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditBidderDescriptor)) {
                return false;
            }

            // state check
            EditBidderDescriptor e = (EditBidderDescriptor) other;

            return getName().equals(e.getName())
                    && getPhone().equals(e.getPhone())
                    && getTag().equals(e.getTag())
                    && getId().equals(e.getId());
        }
    }


}
