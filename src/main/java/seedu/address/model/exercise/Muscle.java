package seedu.address.model.exercise;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Represents the exercise's muscles worked.
 * Based on major muscle groups.
 */
public enum Muscle {
    SHOULDERS("shoulder"),
    CHEST("chest"),
    ARMS("arm"),
    BACK("back"),
    ABDOMINALS("ab"),
    LEGS("leg");

    private static final Map<String, Muscle> ENUM_MAP;
    private String name;

    Muscle(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    // Build an immutable map of String name to enum pairs.
    // Any Map impl can be used.

    static {
        Map<String, Muscle> map = new HashMap<String, Muscle>();
        for (Muscle muscle : Muscle.values()) {
            map.put(muscle.getName(), muscle);
        }
        ENUM_MAP = Collections.unmodifiableMap(map);
    }

    public static Muscle get(String name) {
        return ENUM_MAP.get(name);
    }

    /**
     * Utility method to convert string representation of muscles worked to list (for JsonAdaptedExercise).
     */
    public static List<Muscle> stringToMuscleList(String musclesWorked) {
        String[] musclesWorkedArr = musclesWorked.split(", ");

        List<Muscle> musclesWorkedLst = new ArrayList<>();

        for (String muscleStr: musclesWorkedArr) {
            Muscle muscle = Muscle.get(muscleStr);
            musclesWorkedLst.add(muscle);
        }

        return musclesWorkedLst;
    }

    /**
     * Utility method to convert list of muscles worked to its string representation (for JsonAdaptedExercise).
     */
    public static String muscleListToString(List<Muscle> musclesWorked) {
        return musclesWorked.stream().map(muscle -> muscle.toString())
                .collect(Collectors.joining( ", " ));
    }

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}
