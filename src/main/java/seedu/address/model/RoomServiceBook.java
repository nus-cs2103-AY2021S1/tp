package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.roomservice.RoomService;


/**
 * Manages model for room services.
 */
public class RoomServiceBook implements ReadOnlyRoomServiceBook {

    private final ObservableList<RoomService> internalRoomServiceList = FXCollections.observableArrayList();
    private final ObservableList<RoomService> internalUnmodifiableRoomServiceList =
            FXCollections.unmodifiableObservableList(internalRoomServiceList);

    public RoomServiceBook() {}

    /**
     * Creates a RoomServiceBook using the RoomService in the {@code toBeCopied}
     */
    public RoomServiceBook(ReadOnlyRoomServiceBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    /**
     * Adds room service to model
     */
    public void addRoomService(RoomService roomService) {
        requireNonNull(roomService);
        internalRoomServiceList.add(roomService);
    }

    /**
     * Returns all the room services for this booking, identified by bookingId
     */
    public ObservableList<RoomService> getRoomServicesForBooking(Integer bookingId) {
        requireNonNull(bookingId);
        assert bookingId >= 0;
        return internalRoomServiceList.stream().filter(rs -> rs.belongsToBooking(bookingId)).collect(
                Collectors.toCollection(FXCollections::observableArrayList)
        );
    }

    /**
     * Resets the existing data of this {@code RoomServiceBook} with {@code newData}.
     */
    public void resetData(ReadOnlyRoomServiceBook newData) {
        requireNonNull(newData);
        internalRoomServiceList.setAll(newData.getRoomServiceList());
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    @Override
    public ObservableList<RoomService> getRoomServiceList() {
        return internalUnmodifiableRoomServiceList;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RoomServiceBook // instanceof handles nulls
                && internalRoomServiceList.equals(((RoomServiceBook) other).internalRoomServiceList));
    }
}
