package seedu.homerce.model.client.predicate;

import java.util.List;
import java.util.function.Predicate;

import seedu.homerce.commons.util.StringUtil;
import seedu.homerce.model.client.Client;

public class ClientNamePredicate implements Predicate<Client> {
    private final List<String> keywords;

    public ClientNamePredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Client client) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(client.getName().fullName, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ClientNamePredicate // instanceof handles nulls
                && keywords.equals(((ClientNamePredicate) other).keywords)); // state check
    }
}
