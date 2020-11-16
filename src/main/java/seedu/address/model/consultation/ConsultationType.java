package seedu.address.model.consultation;

/**
 * Represents the type of consultations in classes.
 */
public enum ConsultationType {
    PERSONAL {
        @Override
        public String toString() {
            return "Personal";
        }
    },
    GROUP {
        @Override
        public String toString() {
            return "Group";
        }
    };
}
