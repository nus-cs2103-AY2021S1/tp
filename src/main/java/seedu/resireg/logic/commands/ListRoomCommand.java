package seedu.resireg.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.resireg.logic.parser.CliSyntax.PREFIX_ROOM_FLOOR;
import static seedu.resireg.logic.parser.CliSyntax.PREFIX_ROOM_NUMBER;
import static seedu.resireg.logic.parser.CliSyntax.PREFIX_ROOM_TYPE;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

import seedu.resireg.logic.CommandHistory;
import seedu.resireg.model.Model;
import seedu.resireg.model.ModelPredicate;
import seedu.resireg.model.room.Floor;
import seedu.resireg.model.room.Room;
import seedu.resireg.model.room.RoomNumber;
import seedu.resireg.model.room.roomtype.RoomType;
import seedu.resireg.storage.Storage;

/**
 * Lists rooms in ResiReg to the user, optionally filtered by some criteria.
 */
public class ListRoomCommand extends Command {
    public static final String COMMAND_WORD = "rooms";
    public static final String COMMAND_VACANT_FLAG = "vacant";
    public static final String COMMAND_ALLOCATED_FLAG = "allocated";

    public static final String MESSAGE_SUCCESS = "Listed all rooms";
    public static final String MESSAGE_FILTERED_SUCCESS = "Listed rooms filtered by given criteria";

    public static final Help HELP = new Help(COMMAND_WORD,
            "Lists all rooms within the system, optionally filtered by some criteria.",
            "Parameters: "
            + "[--" + COMMAND_VACANT_FLAG + " | --" + COMMAND_ALLOCATED_FLAG + "] "
            + "[" + PREFIX_ROOM_FLOOR + "FLOOR]... "
            + "[" + PREFIX_ROOM_NUMBER + "ROOM_NUMBER]... "
            + "[" + PREFIX_ROOM_TYPE + "ROOM_TYPE]...\n"
            + "Example: " + COMMAND_WORD
            + " --" + COMMAND_VACANT_FLAG + " " + PREFIX_ROOM_FLOOR + "10 " + PREFIX_ROOM_FLOOR + "11");

    private final RoomFilter filter;

    public ListRoomCommand(RoomFilter filter) {
        this.filter = filter;
    }

    @Override
    public CommandResult execute(Model model, Storage storage, CommandHistory history) {
        requireNonNull(model);

        model.updateFilteredRoomList(filter.getRoomPredicate());
        String message = filter.equals(new RoomFilter()) ? MESSAGE_SUCCESS : MESSAGE_FILTERED_SUCCESS;
        return new ToggleCommandResult(message, TabView.ROOMS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ListRoomCommand) // instanceof handles nulls
                && filter.equals(((ListRoomCommand) other).filter);
    }

    /**
     * Only used internally in {@Code RoomFilter}
     */
    private enum VacancyFilter {
        ALL,
        ALLOCATED,
        VACANT
    }

    /**
     * Stores the criteria by which to filter the list of Rooms displayed.
     */
    public static class RoomFilter {
        private Set<RoomNumber> validRoomNumbers;
        private Set<Floor> validFloors;
        private Set<RoomType> validRoomTypes;
        private VacancyFilter vacancyFilter;

        /**
         * Creates a new RoomFilter which will show all Rooms.
         */
        public RoomFilter() {
            validRoomNumbers = new HashSet<>();
            validFloors = new HashSet<>();
            validRoomTypes = new HashSet<>();
            vacancyFilter = VacancyFilter.ALL;
        }

        /**
         * Adds the given room numbers to the collection of valid room numbers to allow.
         * @param numbers Room numbers.
         */
        public void addRoomNumbers(Collection<RoomNumber> numbers) {
            validRoomNumbers.addAll(numbers);
        }

        /**
         * Adds the given floors to the collection of valid floors to allow.
         * @param floors Room floors.
         */
        public void addFloors(Collection<Floor> floors) {
            validFloors.addAll(floors);
        }

        /**
         * Adds the given room types to the collection of valid room types to allow.
         * @param types Room types.
         */
        public void addRoomTypes(Collection<RoomType> types) {
            validRoomTypes.addAll(types);
        }

        /**
         * Makes the filter show only rooms which have been allocated to a student.
         */
        public void onlyAllocated() {
            vacancyFilter = VacancyFilter.ALLOCATED;
        }

        /**
         * Makes the filter show only vacant rooms.
         */
        public void onlyVacant() {
            vacancyFilter = VacancyFilter.VACANT;
        }

        /**
         * Returns predicate to apply based on the given collection. If the collection is empty, the predicate
         * will always return true, otherwise, only allow attributes in the collection are allowed.
         *
         * @param collection Collection of valid room attributes.
         */
        private <T> Predicate<T> getPredicateFromCollection(Collection<T> collection) {
            return t -> collection.isEmpty() || collection.contains(t);
        }

        /**
         * Returns predicate to use to filter rooms.
         */
        ModelPredicate<Room> getRoomPredicate() {
            Predicate<RoomNumber> roomNumberPredicate = getPredicateFromCollection(validRoomNumbers);
            Predicate<Floor> floorPredicate = getPredicateFromCollection(validFloors);
            Predicate<RoomType> roomTypePredicate = getPredicateFromCollection(validRoomTypes);
            ModelPredicate<Room> vacancyPredicate = (room, model) -> {
                switch (vacancyFilter) {
                case ALL:
                    return true;
                case ALLOCATED:
                    return model.isAllocated(room);
                case VACANT:
                    return !model.isAllocated(room);
                default:
                    assert false : "Unexpected type of vacancy filter in RoomFilter";
                    return true;
                }
            };

            return (room, model) -> roomNumberPredicate.test(room.getRoomNumber())
                    && floorPredicate.test(room.getFloor())
                    && roomTypePredicate.test(room.getRoomType())
                    && vacancyPredicate.test(room, model);
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }
            if (!(other instanceof RoomFilter)) {
                return false;
            }
            RoomFilter otherFilter = (RoomFilter) other;
            return vacancyFilter.equals(otherFilter.vacancyFilter)
                    && validFloors.equals(otherFilter.validFloors)
                    && validRoomNumbers.equals(otherFilter.validRoomNumbers)
                    && validRoomTypes.equals(otherFilter.validRoomTypes);
        }
    }
}
