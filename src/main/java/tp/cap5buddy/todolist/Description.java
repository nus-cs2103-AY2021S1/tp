package tp.cap5buddy.todolist;

public class Description {
    private final String value;

    public Description(String description) {
        this.value = description;
    }

    public static boolean isValidDescription(String test) {
        return test.length() <= 30;
    }

    public String getValue() {
        return this.value;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (!(other instanceof Description)) {
            return false;
        }

        return value.equals(((Description) other).value);
    }

    @Override
    public String toString() {
        return value;
    }
}
