package seedu.resireg.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.resireg.logic.parser.CliSyntax.PREFIX_ROOM_INDEX;
import static seedu.resireg.logic.parser.CliSyntax.PREFIX_STUDENT_INDEX;

import java.util.List;

import seedu.resireg.commons.core.Messages;
import seedu.resireg.commons.core.index.Index;
import seedu.resireg.logic.CommandHistory;
import seedu.resireg.logic.CreateEditCopy;
import seedu.resireg.logic.commands.exceptions.CommandException;
import seedu.resireg.model.Model;
import seedu.resireg.model.allocation.Allocation;
import seedu.resireg.model.room.Room;
import seedu.resireg.model.student.Student;
import seedu.resireg.storage.Storage;


/**
 * Adds a student to ResiReg.
 */
public class AllocateCommand extends Command {

    public static final String COMMAND_WORD = "allocate";
    public static final Help HELP = new Help(COMMAND_WORD,
            "Allocates a student to a room.",
            "Parameters: "
                    + PREFIX_STUDENT_INDEX + "STUDENT INDEX "
                    + PREFIX_ROOM_INDEX + "ROOM INDEX\n"
                    + "Example: " + COMMAND_WORD + " "
                    + PREFIX_STUDENT_INDEX + "1 "
                    + PREFIX_ROOM_INDEX + "1");

    public static final String MESSAGE_SUCCESS = "Room %1$s allocated to %2$s.";
    public static final String MESSAGE_ROOM_NOT_FOUND = "This room does not exist in ResiReg";
    public static final String MESSAGE_STUDENT_NOT_FOUND = "This student is not registered in ResiReg";
    public static final String MESSAGE_STUDENT_ALREADY_ALLOCATED =
            "This student has already been allocated in Resireg.";
    public static final String MESSAGE_ROOM_ALREADY_ALLOCATED = "This room has already been allocated in Resireg.";
    public static final String MESSAGE_ALLOCATION_EXISTS = "This allocation has already been registered in ResiReg.";

    private final Index studentIndex;
    private final Index roomIndex;

    /**
     * @param studentIndex of the student in the filtered student list to allocate
     * @param roomIndex of the room in the filtered room list to be allocated to
     * Creates an AllocateCommand to allocate the specified {@code Student} to {@code Room}
     */
    public AllocateCommand(Index studentIndex, Index roomIndex) {
        requireNonNull(studentIndex);
        requireNonNull(roomIndex);

        this.studentIndex = studentIndex;
        this.roomIndex = roomIndex;
    }

    @Override
    public CommandResult execute(Model model, Storage storage, CommandHistory history) throws CommandException {
        requireNonNull(model);
        List<Student> lastShownListStudent = model.getFilteredStudentList();
        List<Room> lastShownListRoom = model.getFilteredRoomList();
        List<Allocation> lastShownListAllocation = model.getFilteredAllocationList();

        if (studentIndex.getZeroBased() >= lastShownListStudent.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
        } else if (roomIndex.getZeroBased() >= lastShownListRoom.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_ROOM_DISPLAYED_INDEX);
        }

        Student studentToAllocate = lastShownListStudent.get(studentIndex.getZeroBased());
        Room roomToAllocate = lastShownListRoom.get(roomIndex.getZeroBased());
        Allocation toAllocate = new Allocation(roomToAllocate.getFloor(),
                roomToAllocate.getRoomNumber(), studentToAllocate.getStudentId());

        if (model.hasAllocation(toAllocate)) {
            throw new CommandException(MESSAGE_ALLOCATION_EXISTS);
        } else if (model.isAllocated(studentToAllocate)) {
            throw new CommandException(MESSAGE_STUDENT_ALREADY_ALLOCATED);
        } else if (model.isAllocated(roomToAllocate)) {
            throw new CommandException(MESSAGE_ROOM_ALREADY_ALLOCATED);
        }

        Student studentToEdit = CreateEditCopy.createCopiedStudent(studentToAllocate);
        Room roomToEdit = CreateEditCopy.createCopiedRoom(roomToAllocate);

        model.setStudent(studentToAllocate, studentToEdit);
        model.setRoom(roomToAllocate, roomToEdit);
        model.addAllocation(toAllocate);

        model.saveStateResiReg();

        return new CommandResult(String.format(MESSAGE_SUCCESS, roomToAllocate.getRoomLabel(),
            studentToAllocate.getNameAsString()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AllocateCommand // instanceof handles nulls
                && studentIndex.equals(((AllocateCommand) other).studentIndex)
                && roomIndex.equals(((AllocateCommand) other).roomIndex));
    }
}
