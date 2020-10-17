package seedu.address.testutil;

import seedu.address.model.person.Person;

public class PersonUtil {
    public static String getCommandInfo(Person p) {
        final StringBuilder builder = new StringBuilder();
        builder.append(p.getPersonName()).append(" ")
                .append(p.getPhone()).append(" ")
                .append(p.getEmail()).append(" ")
                .append(p.getAddress());
        return builder.toString();
    }
}
