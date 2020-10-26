package seedu.taskmaster.model.record;

public class ClassParticipation {

    public String description() {
        return "To be implemented";
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ClassParticipation)) {
            return false;
        }

        // always return true for now (until class is implemented)
        return true;
    }
}
