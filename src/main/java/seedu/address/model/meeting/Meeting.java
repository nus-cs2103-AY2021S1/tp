package seedu.address.model.meeting;

import seedu.address.model.person.Person;
import seedu.address.model.util.SampleDataUtil;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Meeting {
    private String name;
    private String time;
    private Set<Person> participants;

    public Meeting(String name, String time) {
        this.name = name;
        this.time = time;
        this.participants = new HashSet<>();

        HashSet<Person> persons = new HashSet<>(Arrays.asList(SampleDataUtil.getSamplePersons()));
        participants.addAll(persons);
    }

    public String getName() {
        return name;
    }

    public String getTime() {
        return time;
    }

    public Set<Person> getParticipants() {
        return participants;
    }
}
