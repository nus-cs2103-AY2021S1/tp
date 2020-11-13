package seedu.jarvis.model.mission;

public class Mission {
    private final String title;
    private final String deadline;
    private final boolean isGraded;

    /**
     * Creates a mission object.
     *
     * @param title
     * @param deadline
     */
    public Mission(String title, String deadline) {
        this.title = title;
        this.deadline = deadline;
        this.isGraded = true;
    }

    /**
     * Creates a mission object with updated isGraded field.
     *
     * @param title
     * @param deadline
     * @param isGraded
     */
    public Mission(String title, String deadline, boolean isGraded) {
        this.title = title;
        this.deadline = deadline;
        this.isGraded = isGraded;
    }

    public String getTitle() {
        return title;
    }

    public String getDeadline() {
        return deadline;
    }

    public boolean getIsGraded() {
        return isGraded;
    }

    public Mission setIsGraded(boolean isGraded) {
        return new Mission(title, deadline, isGraded);
    }

    /**
     * Returns true if both missions have the same title and deadline.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Mission)) {
            return false;
        }

        Mission otherMission = (Mission) other;
        return otherMission.getTitle().equals(getTitle())
                && otherMission.getDeadline().equals(getDeadline())
                && otherMission.getIsGraded() == getIsGraded();

    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getTitle())
                .append(" Deadline: ")
                .append(getDeadline());

        return builder.toString();
    }
}
