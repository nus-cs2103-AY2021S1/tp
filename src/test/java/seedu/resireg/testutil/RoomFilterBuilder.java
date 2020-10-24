package seedu.resireg.testutil;

import java.util.Arrays;
import java.util.Collection;

import seedu.resireg.logic.commands.ListRoomCommand.RoomFilter;
import seedu.resireg.model.room.Floor;
import seedu.resireg.model.room.RoomNumber;
import seedu.resireg.model.room.roomtype.RoomType;

/**
 * A utility class to help with building RoomFilter objects.
 */
public class RoomFilterBuilder {
    private RoomFilter filter;

    public RoomFilterBuilder() {
        filter = new RoomFilter();
    }

    /**
     * Adds a {@code RoomNumber} to the {@code RoomFilter} that we are building.
     */
    public RoomFilterBuilder addRoomNumber(RoomNumber numbers) {
        return addRoomNumbers(Arrays.asList(numbers));
    }

    /**
     * Adds a {@code RoomNumber} to the {@code RoomFilter} that we are building.
     */
    public RoomFilterBuilder addRoomNumber(String roomNumber) {
        return addRoomNumber(new RoomNumber(roomNumber));
    }

    /**
     * Adds {@code RoomNumber}s to the {@code RoomFilter} that we are building.
     */
    public RoomFilterBuilder addRoomNumbers(Collection<RoomNumber> numbers) {
        filter.addRoomNumbers(numbers);
        return this;
    }

    /**
     * Adds a {@code RoomType} to the {@code RoomFilter} that we are building.
     */
    public RoomFilterBuilder addRoomType(RoomType type) {
        return addRoomTypes(Arrays.asList(type));
    }

    /**
     * Adds a {@code RoomType} to the {@code RoomFilter} that we are building.
     */
    public RoomFilterBuilder addRoomType(String roomType) {
        return addRoomType(new RoomType(roomType));
    }

    /**
     * Adds {@code RoomType}s to the {@code RoomFilter} that we are building.
     */
    public RoomFilterBuilder addRoomTypes(Collection<RoomType> types) {
        filter.addRoomTypes(types);
        return this;
    }

    /**
     * Adds a {@code Floor} to the {@code RoomFilter} that we are building.
     */
    public RoomFilterBuilder addFloor(Floor floor) {
        return addFloors(Arrays.asList(floor));
    }

    /**
     * Adds a {@code Floor} to the {@code RoomFilter} that we are building.
     */
    public RoomFilterBuilder addFloor(String floor) {
        return addFloor(new Floor(floor));
    }

    /**
     * Adds {@code Floors}s to the {@code RoomFilter} that we are building.
     */
    public RoomFilterBuilder addFloors(Collection<Floor> floors) {
        filter.addFloors(floors);
        return this;
    }

    /**
     * Makes the {@code RoomFilter} we are building show only vacant rooms.
     */
    public RoomFilterBuilder onlyVacant() {
        filter.onlyVacant();
        return this;
    }

    /**
     * Makes the {@code RoomFilter} we are building show only allocated rooms.
     */
    public RoomFilterBuilder onlyAllocated() {
        filter.onlyAllocated();
        return this;
    }

    public RoomFilter build() {
        return filter;
    }
}
