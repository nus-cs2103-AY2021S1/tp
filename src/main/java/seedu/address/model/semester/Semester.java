package seedu.address.model.semester;

public enum Semester {
    Y1S1(11),
    Y1S2(12),
    Y2S1(21),
    Y2S2(22),
    Y3S1(31),
    Y3S2(32),
    Y4S1(41),
    Y4S2(42),
    Y5S1(51),
    Y5S2(52),
    NA(0);

    private final int semStringToInt;

    Semester(int semStringToInt) {
        this.semStringToInt = semStringToInt;
    }

    public int getSemStringToInt() {
        return semStringToInt;
    }
}
