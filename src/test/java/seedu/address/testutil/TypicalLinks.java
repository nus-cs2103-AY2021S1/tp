package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_TIME_TUTOR_MEETING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_TUTORIAL_DOCUMENT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_TUTOR_MEETING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_URL_TUTORIAL_DOCUMENT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_URL_TUTOR_MEETING;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.task.CollaborativeLink;
import seedu.address.model.task.Link;
import seedu.address.model.task.MeetingLink;



public class TypicalLinks {
    public static final MeetingLink PROJECT_MEETING = new MeetingLinkBuilder()
            .withDescription("Project meeting").withDatetime("02-02-2018 1900")
            .withUrl("https://nus-sg.zoom.us/j/12350904475?pwd=T0JwTEIwNjRuMnNKUEt4L2RBMFJWZz09").build();
    public static final CollaborativeLink PROJECT_DOCUMENT = new CollaborativeLinkBuilder()
            .withDescription("Project Document")
            .withUrl("https://docs.google.com/document/d/1oAObtne793B1nDX123hrbAdEy1aoeua5cTuabc326L4c/edit")
            .build();

    public static final MeetingLink TUTOR_MEETING = new MeetingLinkBuilder()
            .withDescription(VALID_DESCRIPTION_TUTOR_MEETING).withDatetime(VALID_DATE_TIME_TUTOR_MEETING)
            .withUrl(VALID_URL_TUTOR_MEETING).build();
    public static final CollaborativeLink TUTORIAL_DOCUMENT = new CollaborativeLinkBuilder()
            .withDescription(VALID_DESCRIPTION_TUTORIAL_DOCUMENT)
            .withUrl(VALID_URL_TUTORIAL_DOCUMENT).build();

    public static List<Link> getTypicalLinks() {
        return new ArrayList<>(Arrays.asList(PROJECT_MEETING, PROJECT_DOCUMENT));
    }
}
