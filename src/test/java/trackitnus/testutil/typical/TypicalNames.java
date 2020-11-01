package trackitnus.testutil.typical;

import java.util.ArrayList;
import java.util.List;

import trackitnus.model.commons.Name;

public class TypicalNames {
    private static final List<Name> names = new ArrayList<>();

    public static List<Name> get() {
        if (!names.isEmpty()) {
            return names;
        }
        names.add(new Name("Do assignment 1"));
        names.add(new Name("Solve tutorial questions"));
        names.add(new Name("Copy Nick's code xD"));
        return names;
    }

    public static Name get(int index) {
        return get().get(index);
    }
}
