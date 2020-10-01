package seedu.address.model.applicant;

import seedu.address.model.person.*;
import seedu.address.model.tag.Tag;

import java.util.Date;
import java.util.Set;

public class Applicant extends Person {
    protected InterviewDate interviewDate;
    protected String status;

    /**
     * Every field must be present and not null.
     */
    public Applicant(Name name,
                     Phone phone,
                     Email email,
                     Address address,
                     Set<Tag> tags,
                     InterviewDate interviewDate,
                     String status) {
        super(name, phone, email, address, tags);
    }
}
