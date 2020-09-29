package seedu.address.model.schedule;

import seedu.address.model.client.Client;
import seedu.address.model.session.Session;

import java.util.Objects;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

public class Schedule {
    private final Client client;
    private final Session session;

    /**
     * Every field must be present and not null.
     */
    public Schedule(Client client, Session session) {
        requireAllNonNull(client, session);
        this.client = client;
        this.session = session;
    }

    public Client getClient() {
        return client;
    }

    public Session getSession() {
        return session;
    }

    /**
     * Returns true if both sessions have the same identifier.
     */
    public boolean isSameSchedule(Schedule otherSchedule) {
        if (otherSchedule == this) {
            return true;
        }

        if (otherSchedule == null) {
            return false;
        }

        return otherSchedule.client.equals(this.client) && otherSchedule.session.equals(this.session);
    }

    /**
     * Returns true if both Schedule have the same identity.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Schedule)) {
            return false;
        }

        Schedule otherSchedule = (Schedule) other;
        return otherSchedule.client.equals(this.client) && otherSchedule.session.equals(this.session);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(client, session);
    }

    @Override
    public String toString() {
        return "Client "
                + client
                + " with session "
                + session;
    }
}
