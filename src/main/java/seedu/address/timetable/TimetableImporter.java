package seedu.address.timetable;

import java.io.IOException;

import org.json.simple.parser.ParseException;

public class TimetableImporter {
    private static final String TEST_TIMETABLE = "https://nusmods.com/timetable/sem-1/share?CS2100=TUT:01,LAB:11,LEC:1"
            + "&CS2101=&CS2103T=LEC:G16&CS2105=TUT:14,LEC:1&EC1301=TUT:S28,LEC:1&IS1103=";
    private TimetableUrlParser parser;
    private TimetableRetriever retriever;

    /**
     * Constructor for TimetableImporter.
     */
    public TimetableImporter() {
        this.parser = new TimetableUrlParser();
        this.retriever = new TimetableRetriever();
    }

    /**
     * Imports timetable.
     */
    public void importTimetable(String timetableUrl) throws IOException, ParseException {
        TimetableData timetableData = parser.parseTimetableUrl(timetableUrl);
        retriever.retrieveTimetable(timetableData);
    }
}
