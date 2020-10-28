package seedu.resireg.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.resireg.logic.parser.CliSyntax.PREFIX_ROOM_TYPE;
import static seedu.resireg.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.resireg.commons.core.Messages;
import seedu.resireg.commons.core.index.Index;
import seedu.resireg.commons.util.CollectionUtil;
import seedu.resireg.logic.CommandHistory;
import seedu.resireg.logic.commands.exceptions.CommandException;
import seedu.resireg.model.Model;
import seedu.resireg.model.allocation.Allocation;
import seedu.resireg.model.room.Room;
import seedu.resireg.model.room.roomtype.RoomType;
import seedu.resireg.model.tag.Tag;
import seedu.resireg.storage.Storage;

/**
 * Edits the details of an existing room in ResiReg.
 */
public class EditRoomCommand extends Command {
    public static final String COMMAND_WORD = "edit-room";

    public static final String MESSAGE_EDIT_ROOM_SUCCESS = "Edited Room: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_ROOM = "This room already exists in ResiReg.";

    public static final Help HELP = new Help(COMMAND_WORD,
            "Edits the details of the room identified by the index number used in the displayed room list.",
            "Parameters: INDEX (must be a positive integer) "
                    + "[" + PREFIX_ROOM_TYPE + "ROOM_TYPE] "
                    + "[" + PREFIX_TAG + "TAG]...\n"
                    + "Example: " + COMMAND_WORD + " 1 "
                    + PREFIX_ROOM_TYPE + "CA ");

    private final Index index;
    private final EditRoomDescriptor editRoomDescriptor;

    /**
     * @param index of the room in the filtered room list to edit
     * @param editRoomDescriptor details to edit the room with
     */
    public EditRoomCommand(Index index, EditRoomDescriptor editRoomDescriptor) {
        requireNonNull(index);
        requireNonNull(editRoomDescriptor);

        this.index = index;
        this.editRoomDescriptor = new EditRoomDescriptor(editRoomDescriptor);
    }

    @Override
    public CommandResult execute(Model model, Storage storage, CommandHistory history) throws CommandException {
        requireNonNull(model);
        List<Room> lastShownList = model.getFilteredRoomList();
        List<Allocation> lastShownAllocationList = model.getFilteredAllocationList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Room roomToEdit = lastShownList.get(index.getZeroBased());
        Room editedRoom = createEditedRoom(roomToEdit, editRoomDescriptor);

        if (!roomToEdit.isSameRoom(editedRoom) && model.hasRoom(editedRoom)) {
            throw new CommandException(MESSAGE_DUPLICATE_ROOM);
        }

        if (model.isAllocated(roomToEdit)) {
            for (Allocation allocation : lastShownAllocationList) {
                if (allocation.isRelatedTo(roomToEdit)) {
                    Allocation editedAllocation = new Allocation(editedRoom.getFloor(),
                            editedRoom.getRoomNumber(),
                            allocation.getStudentId());
                    model.setAllocation(allocation, editedAllocation);
                    // model.updateFilteredAllocationList(PREDICATE_SHOW_ALL_ALLOCATIONS);
                }
            }
        }

        model.setRoom(roomToEdit, editedRoom);
        model.saveStateResiReg();
        return new CommandResult(String.format(MESSAGE_EDIT_ROOM_SUCCESS, editedRoom.toString()));
    }

    /**
     * Creates and returns a {@code Room} with the details of {@code roomToEdit}
     * edited with {@code editRoomDescriptor}.
     */
    private static Room createEditedRoom(Room roomToEdit, EditRoomDescriptor editRoomDescriptor) {
        assert roomToEdit != null;
        RoomType updatedRoomType = editRoomDescriptor.getRoomType().orElse(roomToEdit.getRoomType());
        Set<Tag> updatedTags = editRoomDescriptor.getTags().orElse(roomToEdit.getTags());
        return new Room(roomToEdit.getFloor(), roomToEdit.getRoomNumber(), updatedRoomType, updatedTags);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditRoomCommand)) {
            return false;
        }

        // state check
        EditRoomCommand e = (EditRoomCommand) other;
        return index.equals(e.index)
                && editRoomDescriptor.equals(e.editRoomDescriptor);
    }

    /**
     * Stores the details to edit the room with. Each non-empty field value will replace the
     * corresponding field value of the room.
     */
    public static class EditRoomDescriptor {
        private RoomType roomType;
        private Set<Tag> tags;

        public EditRoomDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditRoomDescriptor(EditRoomDescriptor toCopy) {
            setRoomType(toCopy.roomType);
            setTags(toCopy.tags);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(roomType, tags);
        }

        public void setRoomType(RoomType roomType) {
            this.roomType = roomType;
        }

        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setTags(Set<Tag> tags) {
            this.tags = (tags != null) ? new HashSet<>(tags) : null;
        }

        public Optional<RoomType> getRoomType() {
            return Optional.ofNullable(roomType);
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
            if (!(other instanceof EditRoomCommand)) {
                return false;
            }

            // state check
            EditRoomDescriptor e = (EditRoomDescriptor) other;

            return getRoomType().equals(e.getRoomType())
                    && getTags().equals(e.getTags());
        }
    }
}
