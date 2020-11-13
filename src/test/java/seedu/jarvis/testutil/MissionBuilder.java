package seedu.jarvis.testutil;

import seedu.jarvis.model.mission.Mission;

/**
 * A utility class to help with building Mission objects.
 */
public class MissionBuilder {

    public static final String DEFAULT_TITLE = "Fractal Dimensions";
    public static final String DEFAULT_DEADLINE = "Due: 26th August, 23:59";
    public static final boolean DEFAULT_ISGRADED = true;

    private String title;
    private String deadline;
    private boolean isGraded;

    /**
     * Creates a {@code MissionBuilder} with the default details.
     */
    public MissionBuilder() {
        title = DEFAULT_TITLE;
        deadline = DEFAULT_DEADLINE;
        isGraded = DEFAULT_ISGRADED;
    }

    /**
     * Initializes the MissionBuilder with the data of {@code missionToCopy}.
     */
    public MissionBuilder(Mission missionToCopy) {
        title = missionToCopy.getTitle();
        deadline = missionToCopy.getDeadline();
        isGraded = missionToCopy.getIsGraded();
    }

    /**
     * Sets the {@code title} of the {@code Mission} that we are building.
     */
    public MissionBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    /**
     * Sets the {@code deadline} of the {@code Mission} that we are building.
     */
    public MissionBuilder withDeadline(String deadline) {
        this.deadline = deadline;
        return this;
    }

    /**
     * Sets the {@code isGraded} of the {@code Mission} that we are building.
     */
    public MissionBuilder withIsGraded(boolean isGraded) {
        this.isGraded = isGraded;
        return this;
    }

    public Mission build() {
        return new Mission(title, deadline, isGraded);
    }

}

