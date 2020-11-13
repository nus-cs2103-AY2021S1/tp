package seedu.jarvis.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.jarvis.model.AddressBook;
import seedu.jarvis.model.mission.Mission;

/**
 * A utility class containing a list of {@code Mission} objects to be used in tests.
 */
public class TypicalMissions {

    public static final Mission FRACTAL_DIMENSIONS = new MissionBuilder().withTitle("Fractal Dimensions")
            .withDeadline("Due: 26th August, 23:59").withIsGraded(false).build();
    public static final Mission MUSICAL_NOTES = new MissionBuilder().withTitle("Musical Notes")
            .withDeadline("Due: 12th October, 23:59").withIsGraded(true).build();
    public static final Mission REUSE_PAIRS = new MissionBuilder().withTitle("Reuse Pairs")
            .withDeadline("Due: 18th October, 23:59").withIsGraded(true).build();
    public static final Mission STREAMS = new MissionBuilder().withTitle("Streams")
            .withDeadline("Due: 20th October, 23:59").withIsGraded(false).build();
    public static final Mission STREAM_ANOMALY = new MissionBuilder().withTitle("Stream Anomaly")
            .withDeadline("Deadline is over").withIsGraded(false).build();

    private TypicalMissions() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical missions.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Mission mission : getTypicalMissions()) {
            ab.addMission(mission);
        }
        return ab;
    }

    public static List<Mission> getTypicalMissions() {
        return new ArrayList<>(Arrays.asList(FRACTAL_DIMENSIONS, MUSICAL_NOTES, REUSE_PAIRS, STREAMS, STREAM_ANOMALY));
    }

}
