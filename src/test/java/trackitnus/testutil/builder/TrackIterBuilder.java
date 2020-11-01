package trackitnus.testutil.builder;

import trackitnus.model.TrackIter;
import trackitnus.model.contact.Contact;

/**
 * A utility class to help with building TrackIter objects.
 * Example usage: <br>
 * {@code TrackIter ab = new TrackIterBuilder().withContact("John", "Doe").build();}
 */
public class TrackIterBuilder {

    private final TrackIter trackIter;

    public TrackIterBuilder() {
        trackIter = new TrackIter();
    }

    public TrackIterBuilder(TrackIter trackIter) {
        this.trackIter = trackIter;
    }

    /**
     * Adds a new {@code Contact} to the {@code TrackIter} that we are building.
     */
    public TrackIterBuilder withContact(Contact contact) {
        trackIter.addContact(contact);
        return this;
    }

    public TrackIter build() {
        return trackIter;
    }
}
