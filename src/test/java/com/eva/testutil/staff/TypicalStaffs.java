package com.eva.testutil.staff;

import static com.eva.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static com.eva.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static com.eva.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static com.eva.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static com.eva.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static com.eva.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static com.eva.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static com.eva.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static com.eva.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static com.eva.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.eva.commons.util.DateUtil;
import com.eva.model.EvaDatabase;
import com.eva.model.comment.Comment;
import com.eva.model.person.Address;
import com.eva.model.person.Email;
import com.eva.model.person.Name;
import com.eva.model.person.Phone;
import com.eva.model.person.staff.Staff;
import com.eva.model.person.staff.leave.Leave;
import com.eva.model.tag.Tag;
import com.eva.model.util.SampleDataUtil;


public class TypicalStaffs {
    public static final Staff ALEX = new StaffBuilder().withName("Alex Yeoh").withPhone("87438807")
            .withEmail("alexyeoh@example.com").withAddress("Blk 30 Geylang Street 29, #06-40").withTags("marketing")
            .withLeaves(
                    new String[] {"10/10/2020", "12/10/2020"},
                    new String[] {"24/02/2020"}
                    )
            .withComments("Character traits|2020-10-10|reassuring, tidy and punctual")
            .build();
    public static final Staff BERNICE = new StaffBuilder().withName("Bernice Yu").withPhone("99272758")
            .withEmail("berniceyu@example.com").withAddress("Blk 30 Lorong 3 Serangoon Gardens, #07-18")
            .withTags("business")
            .withLeaves(
                    new String[] {"14/09/2020"},
                    new String[] {"15/07/2020"},
                    new String[] {"29/06/2020"}
                    )
            .withComments("Work conflict|2020-11-10|Doesn't work well with David",
                          "Money owed|2020-12-10|Has yet to pay for office party")
            .build();

    public static final Staff IRFAN = new StaffBuilder().withName("Irfan Ibrahim").withPhone("92492021")
            .withEmail("irfan@example.com").withAddress("Blk 47 Tampines Street 20, #17-35").withTags("business")
            .withLeaves(
                    new String[] {"04/03/2020", "07/03/2020"},
                    new String[] {"24/02/2020"},
                    new String[] {"02/01/2020"},
                    new String[] {"04/01/2020"}
                    )
            .withComments()
            .build();
    public static final Staff ROY = new StaffBuilder().withName("Roy Balakrishnan").withPhone("92624417")
            .withEmail("royb@example.com").withAddress("Blk 45 Aljunied Street 85, #11-31").withTags("tech")
            .withLeaves(
                    new String[] {"09/04/2020", "19/04/2020"},
                    new String[] {"24/12/2020"}
                    )
            .withComments("Gambling|2020-11-10|Has a problem with gambling")
            .build();
    public static final Staff AMY = new StaffBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY).withTags(VALID_TAG_FRIEND).build();
    public static final Staff BOB = new StaffBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .build();

    private TypicalStaffs() {}

    public static EvaDatabase<Staff> getTypicalStaffDatabase() {
        EvaDatabase<Staff> ab = new EvaDatabase<>();
        for (Staff staff : getTypicalStaffs()) {
            Staff copy = makeCopy(staff);
            ab.addPerson(copy);
        }
        return ab;
    }

    private static Staff makeCopy(Staff toCopy) {
        String name = toCopy.getName().fullName;
        String email = toCopy.getEmail().value;
        String phone = toCopy.getPhone().value;
        String address = toCopy.getAddress().value;
        List<String> tags = toCopy.getTags().stream().map(tag -> tag.tagName).collect(Collectors.toList());
        List<String[]> leaveDates = toCopy.getLeaves().stream()
                .map(leave -> new String[] {
                        DateUtil.dateToString(leave.getStartDate()),
                        DateUtil.dateToString(leave.getEndDate())
                })
                .collect(Collectors.toList());
        List<String> comments = toCopy.getComments().stream()
                .map(comment -> {
                    String date = comment.getDate().toString();
                    String title = comment.title.getTitleDescription();
                    String description = comment.getDescription();
                    return String.format("%1$s|%2$s|%3$s", title, date, description);
                })
                .collect(Collectors.toList());
        Set<Tag> tagSet = SampleDataUtil.getTagSet(tags);
        Set<Comment> commentSet = SampleDataUtil.getCommentSet(comments);
        Set<Leave> leaveSet = SampleDataUtil.getLeaveSet(leaveDates);

        return new Staff(new Name(name), new Phone(phone), new Email(email), new Address(address),
                tagSet, leaveSet, commentSet);
    }

    public static List<Staff> getTypicalStaffs() {
        return new ArrayList<>(Arrays.asList(ALEX, BERNICE, IRFAN, ROY));
    }
}
