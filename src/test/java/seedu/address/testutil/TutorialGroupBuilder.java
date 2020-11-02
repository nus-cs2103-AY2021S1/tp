package seedu.address.testutil;

import seedu.address.model.tutorialgroup.DayOfWeek;
import seedu.address.model.tutorialgroup.TimeOfDay;
import seedu.address.model.tutorialgroup.TutorialGroup;
import seedu.address.model.tutorialgroup.TutorialGroupId;

public class TutorialGroupBuilder {

    public static final String DEFAULT_TUTORIAL_GROUP_ID = "B014";
    public static final TimeOfDay DEFAULT_START_TIME = new TimeOfDay("15:00");
    public static final TimeOfDay DEFAULT_END_TIME = new TimeOfDay("17:00");
    public static final DayOfWeek DEFAULT_DAY_OF_WEEK = new DayOfWeek("MON");

    private TutorialGroupId tutorialGroupId;
    private TimeOfDay startTime;
    private TimeOfDay endTime;
    private DayOfWeek dayOfWeek;



    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public TutorialGroupBuilder() {
        tutorialGroupId = new TutorialGroupId(DEFAULT_TUTORIAL_GROUP_ID);
        startTime = DEFAULT_START_TIME;
        endTime = DEFAULT_END_TIME;
        dayOfWeek = DEFAULT_DAY_OF_WEEK;
    }

    /**
     * Initializes the ModuleBuilder with the data of {@code moduleToCopy}.
     */
    public TutorialGroupBuilder(TutorialGroup tutorialGroupToCopy) {
        tutorialGroupId = tutorialGroupToCopy.getId();
        startTime = tutorialGroupToCopy.getStartTime();
        endTime = tutorialGroupToCopy.getEndTime();
        dayOfWeek = tutorialGroupToCopy.getDayOfWeek();
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public TutorialGroupBuilder withTutorialGroupId(String tutorialGroupId) {
        this.tutorialGroupId = new TutorialGroupId(tutorialGroupId);
        return this;
    }

    public TutorialGroupBuilder withStartTime(String startTimeString) {
        this.startTime = new TimeOfDay(startTimeString);
        return this;
    }

    public TutorialGroupBuilder withEndTime(String endTimeString) {
        this.startTime = new TimeOfDay(endTimeString);
        return this;
    }


    public TutorialGroup build() {
        return new TutorialGroup(tutorialGroupId, dayOfWeek, startTime, endTime);
    }

}
