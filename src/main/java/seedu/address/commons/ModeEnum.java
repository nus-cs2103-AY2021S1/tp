package seedu.address.commons;

import static seedu.address.commons.util.StringUtil.getStringJoinedBySeparator;

import java.util.Arrays;

public enum ModeEnum {

    DASHBOARD("Dashboard", "db"),
    DELIVERABLE("Deliverable", "dv"),
    MEETING("Meeting", "m"),
    PERSON("Contact", "c");

    private final String name;
    private final String argument;

    ModeEnum(String name, String argument) {
        this.name = name;
        this.argument = argument;
    }

    public static ModeEnum getEnumByArgument(String argument) {
        for (ModeEnum modeEnum : ModeEnum.values()) {
            if (modeEnum.argument.equals(argument)) {
                return modeEnum;
            }
        }
        return null;
    }

    public static String getModeOptions() {
        return getStringJoinedBySeparator(Arrays.stream(ModeEnum.values())
                .map(mode -> mode.getArgument() + " (" + mode.toString() + ")"), " or ");
    }

    public String getArgument() {
        return argument;
    }

    @Override
    public String toString() {
        return name;
    }

}
