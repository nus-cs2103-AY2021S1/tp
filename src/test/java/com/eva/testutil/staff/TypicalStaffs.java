package com.eva.testutil.staff;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.eva.model.EvaDatabase;
import com.eva.model.person.staff.Staff;

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

    private TypicalStaffs() {}

    public static EvaDatabase<Staff> getTypicalStaffDatabase() {
        EvaDatabase<Staff> ab = new EvaDatabase<>();
        for (Staff staff : getTypicalStaffs()) {
            ab.addPerson(staff);
        }
        return ab;
    }

    public static List<Staff> getTypicalStaffs() {
        return new ArrayList<>(Arrays.asList(ALEX, BERNICE, IRFAN, ROY));
    }
}
