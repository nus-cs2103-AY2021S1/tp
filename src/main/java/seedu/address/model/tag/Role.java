package seedu.address.model.tag;

/**
 * Represents a Worker Role in the App.
 * Guarantees: immutable, name is valid as declared in
 */
public class Role extends Tag {

    public static final String MESSAGE_CONSTRAINTS =
            "Role names should be alphanumeric";

    protected Role(String roleName) {
        super(roleName);
    }

    /**
     * Factory method for creating a {@code Role}. Returns a {@code Leave} if {@code roleName} is {@code "Leave"}.
     */
    public static Role createRole(String roleName) {
        if (roleName.equals(Leave.ROLE_NAME)) {
            return new Leave();
        }
        return new Role(roleName);
    }

    public String getRole() {
        return tagName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof Role
                && tagName.equals(((Role) other).tagName));
    }

}
