package tp.acecs2103.model.task;

public enum TaskCategory {
    ADMIN, IP, TP, TOPIC, TASK;

    /**
     * Gets string based on {@code category}
     */
    public static String categoryToString(TaskCategory category) {
        switch (category) {
        case ADMIN:
            return "Admin";
        case TOPIC:
            return "Topic";
        case IP:
            return "Ip";
        case TP:
            return "Tp";
        default:
            return "";
        }
    }

    /**
     * Checks whether a category belongs to Admin.
     * @param category A valid {@code TaskCategory}.
     */
    public static boolean isAdmin(TaskCategory category) {
        return category == ADMIN;
    }

    /**
     * Checks whether a category belongs to Admin.
     * @param category A valid {@code String}.
     */
    public static boolean isStringAdmin(String category) {
        return category.equals("Admin");
    }

    /**
     * Checks whether a category belongs to Topic.
     * @param category A valid {@code TaskCategory}.
     */
    public static boolean isTopic(TaskCategory category) {
        return category == TOPIC;
    }

    /**
     * Checks whether a category belongs to Topic.
     * @param category A valid {@code String}.
     */
    public static boolean isStringTopic(String category) {
        return category.equals("Topic");
    }

    /**
     * Checks whether a category belongs to IP.
     * @param category A valid {@code TaskCategory}.
     */
    public static boolean isIP(TaskCategory category) {
        return category == IP;
    }

    /**
     * Checks whether a category belongs to IP.
     * @param category A valid {@code String}.
     */
    public static boolean isStringIP(String category) {
        return category.equals("Ip");
    }

    /**
     * Checks whether a category belongs to TP.
     * @param category A valid {@code TaskCategory}.
     */
    public static boolean isTP(TaskCategory category) {
        return category == TP;
    }

    /**
     * Checks whether a category belongs to TP.
     * @param category A valid {@code String}.
     */
    public static boolean isStringTP(String category) {
        return category.equals("Tp");
    }
}
