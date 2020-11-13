package seedu.jarvis.model.quest;

public class Quest {
    private final String title;
    private final String deadline;
    private final boolean isGraded;
    /**
     * Creates a Quest object.
     *
     * @param title
     * @param deadline
     */
    public Quest(String title, String deadline) {
        this.title = title;
        this.deadline = deadline;
        this.isGraded = true;
    }

    /**
     * Creates a quest object with updated isGraded field.
     *
     * @param title
     * @param deadline
     * @param isGraded
     */
    public Quest(String title, String deadline, boolean isGraded) {
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

    public Quest setIsGraded(boolean isGraded) {
        return new Quest(title, deadline, isGraded);
    }

    /**
     * Returns true if both quests have the same title and deadline.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Quest)) {
            return false;
        }

        Quest otherQuest = (Quest) other;
        return otherQuest.getTitle().equals(getTitle())
                && otherQuest.getDeadline().equals(getDeadline())
                && otherQuest.getIsGraded() == getIsGraded();

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

