package seedu.address.testutil;

import seedu.address.model.day.Date;
import seedu.address.model.day.Weight;
import seedu.address.model.person.Height;
import seedu.address.model.person.ID;
import seedu.address.model.person.Name;
import seedu.address.model.person.Profile;


public class TypicalProfiles {

    public static final Profile DEFAULT_PROFILE = new Profile(new Name("DEFAULT"),
            new ID(), new Height(), new Weight());
    public static final Profile PROFILE1 = new Profile(new Name("First"), new ID("1111"),
            new Height("171"), new Weight("61"), new Date("2020-01-01"));
    public static final Profile PROFILE2 = new Profile(new Name("Second"), new ID("1112"),
            new Height("172"), new Weight("62"), new Date("2020-02-02"));
    public static final Profile PROFILE3 = new Profile(new Name("Third"), new ID("1113"),
            new Height("173"), new Weight("63"), new Date("2020-03-03"));
    public static final Profile PROFILE4 = new Profile(new Name("Fourth"), new ID("1114"),
            new Height("174"), new Weight("64"), new Date("2020-04-04"));
    public static final Profile PROFILE5 = new Profile(new Name("Fifth"), new ID("1115"),
            new Height("175"), new Weight("65"), new Date("2020-05-05"));
    public static final Profile PROFILE6 = new Profile(new Name("Sixth"), new ID("1116"),
            new Height("176"), new Weight("66"), new Date("2020-06-06"));
    public static final Profile PROFILE7 = new Profile(new Name("Seventh"), new ID("1117"),
            new Height("177"), new Weight("67"), new Date("2020-07-07"));
    public static final Profile PROFILE8 = new Profile(new Name("Eighth"), new ID("1118"),
            new Height("178"), new Weight("68"), new Date("2020-08-08"));
}
