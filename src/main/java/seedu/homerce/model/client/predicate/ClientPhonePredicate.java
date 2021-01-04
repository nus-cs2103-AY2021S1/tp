package seedu.homerce.model.client.predicate;

import java.util.function.Predicate;

import seedu.homerce.model.client.Client;
import seedu.homerce.model.client.Phone;


public class ClientPhonePredicate implements Predicate<Client> {
    private final Phone phoneOfClient;

    public ClientPhonePredicate(Phone phone) {
        this.phoneOfClient = phone;
    }

    @Override
    public boolean test(Client client) {
        return client.getPhone().value.contains(phoneOfClient.value);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof ClientPhonePredicate // instanceof handles nulls
            && phoneOfClient.equals(((ClientPhonePredicate) other).phoneOfClient)); // state check
    }
}
