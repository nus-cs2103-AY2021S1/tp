package seedu.resireg.model.room.roomtype;

public enum RoomTypeEnum {
    CORRIDOR_AIRCON("CA", "$700"),
    CORRIDOR_NONAIRCON("CN", "$500"),
    NONCORRIDOR_AIRCON("NA", "$900"),
    NONCORRIDOR_NONAIRCON("NN", "$650");

    private String abbreviation;
    private String fees;

    RoomTypeEnum(String abbreviation, String fees) {
        this.abbreviation = abbreviation;
        this.fees = fees;
    }

    public boolean matchesRoomTypeAbbreviation(String test) {
        return test.equals(abbreviation);
    }

    @Override
    public String toString() {
        return abbreviation;
    }
}

