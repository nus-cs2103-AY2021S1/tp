package seedu.address.model.person.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.commons.util.StringUtil.getStringJoinedBySeparator;

import java.util.Arrays;

/**
 * Represents a Person's role in the address book.
 * Guarantees: immutable;
 */
public enum Role {

    DEVELOPER("Developer", "dev"),
    STAKEHOLDER("Stakeholder", "stk");

    public static final String MESSAGE_CONSTRAINTS =
            "Role should only take " + getStringJoinedBySeparator(Arrays.stream(Role.values())
                    .map(role -> role.getArgument() + " (" + role.toString().toLowerCase() + ")"), " or ") + ".";
    public static final String VALIDATION_REGEX =
            getStringJoinedBySeparator(Arrays.stream(Role.values()).map(role -> role.getArgument()), "|");

    private final String name;
    private final String argument;

    private Role(String name, String argument) {
        this.name = name;
        this.argument = argument;
    }

    public static Role getRole(String arg) {
        requireNonNull(arg);
        checkArgument(isValidRole(arg), MESSAGE_CONSTRAINTS);
        Role role = Role.getEnumByArgument(arg);
        requireNonNull(role);
        return role;
    }

    /**
     * Returns true if a given string is a valid role.
     */
    public static boolean isValidRole(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public static Role getEnumByArgument(String argument) {
        for (Role role : Role.values()) {
            if (role.argument.equals(argument)) {
                return role;
            }
        }
        return null;
    }

    public String getArgument() {
        return argument;
    }

    @Override
    public String toString() {
        return name;
    }

}
