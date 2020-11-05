package seedu.address.timetable;

public class TimetableUrlParser {
    private static final String nusModsProtocolAndHost = "https://nusmods.com/timetable/";
    private static final String nusModsHost = "nusmods.com/timetable/";

    /**
     * Parses timetable url and returns timetable data.
     */
    public static TimetableData parseTimetableUrl(String url) {
        int semester = parseTimetableUrlForSem(url);
        String[] moduleDataArray = parseTimetableUrlForData(url);
        String[] moduleCodeArray = getModuleCodeArray(moduleDataArray);
        String[] moduleLessonArray = getModuleLessonArray(moduleDataArray);
        return new TimetableData(semester, moduleCodeArray, moduleLessonArray);
    }

    /**
     * Returns true if url is a valid NUSMods timetable URL.
     */
    public static boolean isValidUrl(String url) {
        return url.contains(nusModsProtocolAndHost);
    }

    private static int parseTimetableUrlForSem(String url) {
        String temp = url.split(nusModsHost)[1];
        String semesterType = temp.split("-", 2)[0];
        String semesterNumber = "0";
        if (semesterType.equals("sem")) {
            semesterNumber = url.split("/sem-", 2)[1];
            semesterNumber = semesterNumber.split("/", 2)[0];
        } else if (semesterType.equals("st")) {
            semesterNumber = url.split("/st-", 2)[1];
            semesterNumber = semesterNumber.split("/", 2)[0];
            semesterNumber = semesterNumber.equals("i") ? "3" : "4";
        }
        return Integer.parseInt(semesterNumber);
    }

    private static String[] parseTimetableUrlForData(String url) {
        String moduleData = url.split("\\?", 2)[1];
        String[] moduleDataArray = moduleData.split("&");
        return moduleDataArray; // ["CS=T:1,L=2&MA=L:9"]
    }

    private static String[] getModuleCodeArray(String[] moduleDataArray) {
        String[] moduleCodeArray = moduleDataArray.clone();
        for (int i = 0; i < moduleCodeArray.length; i++) {
            moduleCodeArray[i] = moduleCodeArray[i].split("=", 2)[0];
        }
        return moduleCodeArray; // ["CS","MA"]
    }

    private static String[] getModuleLessonArray(String[] moduleDataArray) {
        String[] moduleLessonArray = moduleDataArray.clone();
        for (int i = 0; i < moduleLessonArray.length; i++) {
            moduleLessonArray[i] = moduleLessonArray[i].split("=", 2)[1];
        }
        return moduleLessonArray; // ["T:1,L:2","L:9"]
    }
}
