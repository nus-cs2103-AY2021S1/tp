package seedu.address.logic.commands.bidcommands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLIENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MONEY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROPERTY_ID;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_BIDS;

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
import seedu.address.model.bid.Bid;
import seedu.address.model.id.BidderId;
import seedu.address.model.id.PropertyId;
import seedu.address.model.price.Price;

/**
 * Edits the details of an existing person in the address book.
 */
public class EditBidCommand extends Command {

    public static final String COMMAND_WORD = "edit-bid";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the bid identified "
            + "\nby the index number used in the displayed bid list. "
            + "\nExisting values will be overwritten by the input values.\n"
            + "\n\nParameters: \nINDEX (positive integer) "
            + "\n[" + PREFIX_PROPERTY_ID + "PROPERTY ID] "
            + "\n[" + PREFIX_CLIENT + "BIDDER ID] "
            + "\n[" + PREFIX_MONEY + "BID AMOUNT]\n"
            + "\n\nExample: " + COMMAND_WORD + " 1 "
            + PREFIX_CLIENT + "B35";

    public static final String MESSAGE_EDIT_BID_SUCCESS = "Edited Bid:\n\nFROM: %1$s \n\nTO: %2$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_BID = "This bid already exists in the address book.";

    private final Index index;
    private final EditBidDescriptor editBidDescriptor;

    /**
     * @param index of the person in the filtered bid list to edit
     * @param editBidDescriptor details to edit the bid with
     */
    public EditBidCommand(Index index, EditBidDescriptor editBidDescriptor) {
        requireNonNull(index);
        requireNonNull(editBidDescriptor);

        this.index = index;
        this.editBidDescriptor = new EditBidDescriptor(editBidDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Bid> lastShownList = model.getFilteredBidList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_BID_DISPLAYED_INDEX);
        }

        Bid bidToEdit = lastShownList.get(index.getZeroBased());
        Bid editedBid = createEditedBid(bidToEdit, editBidDescriptor);

        if (!bidToEdit.isSameBid(editedBid) && model.hasBid(editedBid)) {
            throw new CommandException(MESSAGE_DUPLICATE_BID);
        }
        if (bidToEdit.isBidBeenEdited(editedBid)) {
            throw new CommandException(MESSAGE_NOT_EDITED);
        }


        model.setBid(bidToEdit, editedBid);
        model.updateFilteredBidList(PREDICATE_SHOW_ALL_BIDS);
        return new CommandResult(String.format(MESSAGE_EDIT_BID_SUCCESS, bidToEdit, editedBid))
                .setEntity(EntityType.BID);
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editBidDescriptor}.
     */
    private static Bid createEditedBid(Bid bidToEdit, EditBidDescriptor editBidDescriptor) {
        assert bidToEdit != null;

        PropertyId updatedPropertyId = editBidDescriptor.getPropertyId().orElse(bidToEdit.getPropertyId());
        BidderId updatedBidderId = editBidDescriptor.getBidderId().orElse(bidToEdit.getBidderId());
        Price updatedBidAmount = editBidDescriptor.getBidAmount().orElse(bidToEdit.getBidAmount());

        return new Bid(updatedPropertyId, updatedBidderId, updatedBidAmount);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditBidCommand)) {
            return false;
        }

        // state check
        EditBidCommand e = (EditBidCommand) other;
        return index.equals(e.index)
                && editBidDescriptor.equals(e.editBidDescriptor);
    }

    /**
     * Stores the details to edit the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class EditBidDescriptor {

        private PropertyId propertyId;
        private BidderId bidderId;
        private Price bidAmount;


        public EditBidDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditBidDescriptor(EditBidDescriptor toCopy) {
            setPropertyId(toCopy.propertyId);
            setBidderId(toCopy.bidderId);
            setBidAmount(toCopy.bidAmount);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(propertyId, bidderId, bidAmount);
        }

        public void setPropertyId(PropertyId id) {
            this.propertyId = id;
        }

        public Optional<PropertyId> getPropertyId() {
            return Optional.ofNullable(propertyId);
        }

        public void setBidderId(BidderId id) {
            this.bidderId = id;
        }

        public Optional<BidderId> getBidderId() {
            return Optional.ofNullable(bidderId);
        }

        public void setBidAmount(Price bidAmount) {
            this.bidAmount = bidAmount;
        }

        public Optional<Price> getBidAmount() {
            return Optional.ofNullable(bidAmount);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditBidDescriptor)) {
                return false;
            }

            // state check
            EditBidDescriptor e = (EditBidDescriptor) other;

            return getPropertyId().equals(e.getPropertyId())
                    && getBidderId().equals(e.getBidderId())
                    && getBidAmount().equals(e.getBidAmount());
        }
    }
}
