package com.eva.model.util;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.eva.model.EvaDatabase;
import com.eva.model.ReadOnlyEvaDatabase;
import com.eva.model.comment.Comment;
import com.eva.model.person.Address;
import com.eva.model.person.Email;
import com.eva.model.person.Name;
import com.eva.model.person.Person;
import com.eva.model.person.Phone;
import com.eva.model.person.applicant.Applicant;
import com.eva.model.person.applicant.ApplicationStatus;
import com.eva.model.person.applicant.InterviewDate;
import com.eva.model.person.staff.Staff;
import com.eva.model.person.staff.leave.Leave;
import com.eva.model.person.staff.leave.LeaveTaken;
import com.eva.model.tag.Tag;

/**
 * Contains utility methods for populating {@code EvaDatabase} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                    new Address("Blk 30 Geylang Street 29, #06-40"),
                    getTagSet("marketing"), getCommentSet("title 1|2010-10-10|works well")),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                    new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                    getTagSet("business"), getCommentSet("title 2|2010-11-10|hi", "title 3|2010-12-10|hi")),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                    new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                    getTagSet("tech"), getCommentSet("title 4|2010-09-10|hi", "title 5|2010-08-10|hi")),
            new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                    new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                    getTagSet("tech"), getCommentSet("title 6|2010-10-10|hi")),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                    new Address("Blk 47 Tampines Street 20, #17-35"),
                    getTagSet("business"), getCommentSet("title 7|2010-07-10|hi")),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                    new Address("Blk 45 Aljunied Street 85, #11-31"),
                    getTagSet("tech"), getCommentSet("title 8|2011-11-10|hi"))
        };
    }

    public static Staff[] getSampleStaffs() {
        return new Staff[] {
            new Staff(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                    new Address("Blk 30 Geylang Street 29, #06-40"), new LeaveTaken(4),
                    getTagSet("marketing"),
                    getLeaveSet(
                            new String[] {"10/10/2020", "12/10/2020"},
                            new String[] {"24/02/2020"}
                            ),
                    getCommentSet("Character traits|2020-10-10|reassuring, tidy and punctual")),
            new Staff(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                    new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"), new LeaveTaken(3),
                    getTagSet("business"),
                    getLeaveSet(
                            new String[] {"14/09/2020"},
                            new String[] {"15/07/2020"},
                            new String[] {"29/06/2020"}
                    ),
                    getCommentSet("Work conflict|2020-11-10|Doesn't work well with David",
                            "Money owed|2020-12-10|Has yet to pay for office party")),
            new Staff(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                    new Address("Blk 11 Ang Mo Kio Street 74, #11-04"), new LeaveTaken(9),
                    getTagSet("tech"),
                    getLeaveSet(
                            new String[] {"10/08/2020", "17/08/2020"},
                            new String[] {"10/01/2020"}
                    ),
                    getCommentSet("Punctuality|2020-09-10|Often comes to work late",
                            "Gossip|2010-08-10|Likes to gossip and spill tea")),
            new Staff(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                    new Address("Blk 436 Serangoon Gardens Street 26, #16-43"), new LeaveTaken(5),
                    getTagSet("tech"),
                    getLeaveSet(
                            new String[] {"10/10/2020", "12/10/2020"},
                            new String[] {"23/12/2020", "24/12/2020"}
                    ),
                    getCommentSet("Work conflict|2010-10-10|Does not work well with Bernice")),
            new Staff(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                    new Address("Blk 47 Tampines Street 20, #17-35"), new LeaveTaken(7),
                    getTagSet("business"),
                    getLeaveSet(
                            new String[] {"04/03/2020", "07/03/2020"},
                            new String[] {"24/02/2020"},
                            new String[] {"02/01/2020"},
                            new String[] {"04/01/2020"}
                    ),
                    getCommentSet()),
            new Staff(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                    new Address("Blk 45 Aljunied Street 85, #11-31"), new LeaveTaken(12),
                    getTagSet("tech"),
                    getLeaveSet(
                            new String[] {"09/04/2020", "19/04/2020"},
                            new String[] {"24/12/2020"}
                    ),
                    getCommentSet("Gambling|2020-11-10|Has a problem with gambling"))
        };
    }

    public static Applicant[] getSampleApplicants() {
        // TODO: Add some meaningful interview date samples
        return new Applicant[] {
            new Applicant(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                    new Address("Blk 30 Geylang Street 29, #06-40"), getTagSet("late"),
                    getCommentSet("Interests|2010-10-10|passionate about marketing"),
                    new ApplicationStatus("received")),
            new Applicant(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                    new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"), getTagSet("tech"),
                    getCommentSet("Duration|2010-11-10|Internship from Jan to Jun"),
                    Optional.of(new InterviewDate("02/11/2020")), new ApplicationStatus("processing")),
            new Applicant(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                    new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                    getTagSet("summer"), getCommentSet(), new ApplicationStatus("received")),
            new Applicant(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                    new Address("Blk 436 Serangoon Gardens Street 26, #16-43"), getTagSet("NUS"),
                    getCommentSet("Review|2010-10-10|Have experience in marketing at big firm before"),
                    Optional.of(new InterviewDate("02/11/2020")), new ApplicationStatus("processing")),
            new Applicant(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                    new Address("Blk 47 Tampines Street 20, #17-35"),
                    getTagSet("business"), getCommentSet("title 7|2010-07-10|hi"),
                    new ApplicationStatus("received")),
            new Applicant(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                    new Address("Blk 45 Aljunied Street 85, #11-31"),
                    getTagSet("tech"), getCommentSet("title 8|2011-11-10|hi"),
                    new ApplicationStatus("received"))
        };
    }

    public static ReadOnlyEvaDatabase<Person> getSamplePersonDatabase() {
        EvaDatabase<Person> sampleAb = new EvaDatabase<>();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
    }

    public static ReadOnlyEvaDatabase<Staff> getSampleStaffDatabase() {
        EvaDatabase<Staff> sampleAb = new EvaDatabase<>();
        for (Staff samplePerson : getSampleStaffs()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
    }

    public static ReadOnlyEvaDatabase<Applicant> getSampleApplicantDatabase() {
        EvaDatabase<Applicant> sampleAb = new EvaDatabase<>();
        for (Applicant samplePerson : getSampleApplicants()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

    /**
     * Returns a comment set containing the list of strings given.
     */
    public static Set<Comment> getCommentSet(String... strings) {
        List<String> list = Arrays.asList(strings);
        return list.stream()
                .map(string -> new Comment(LocalDate.parse(string.split("\\|", 3)[1]),
                        string.split("\\|", 3)[2], string.split("\\|", 3)[0]))
                .collect(Collectors.toSet());
    }

    /**
     * Returns a leave set containing the list of strings given.
     */
    public static Set<Leave> getLeaveSet(String[]... strings) {
        return Arrays.stream(strings)
                .map(string -> {
                    if (string.length > 1) {
                        return new Leave(string[0], string[1]);
                    } else {
                        return new Leave(string[0]);
                    }
                })
                .collect(Collectors.toSet());
    }

}
