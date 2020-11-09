package seedu.address.model.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import seedu.address.ui.MainWindow;

/**
 * Retrieve information from moduleInfo.json about a specific module
 */
public class ModuleInfoRetriever {
    private static final int STRING_OFFSET = 3; //An offset to cancel out unnecessary characters from JSON file.

    /**
     * Return module-related information.
     * @param moduleName Name of module to retrieve information about
     * @return A HashMap containing values for the module's title, moduleCredit, and SU status
     */
    public static HashMap<String, String> retrieve(String moduleName) {
        HashMap<String, String> map = new HashMap<String, String>();
        InputStream stream = MainWindow.class.getResourceAsStream("/" + "moduleInfo.json");
        try {
            String s = streamToString(stream);
            stream.close();
            int moduleStartIndex = s.lastIndexOf("\"moduleCode\": \"" + moduleName.toUpperCase() + "\",");
            if (moduleStartIndex == -1) {
                return getInvalidMap();
            }
            int moduleEndIndex = s.indexOf("\"moduleCode\"", moduleStartIndex + 1);

            String moduleString = s.substring(moduleStartIndex, moduleEndIndex);

            map.put("title", getKeyValue(moduleString, "title"));
            map.put("moduleCredit", getKeyValue(moduleString, "moduleCredit"));
            map.put("su", getKeyValue(moduleString, "su"));
            return map;
        } catch (IOException e) {
            return getInvalidMap();
        }
    }

    /**
     * Search for relevant information inside the module
     * @param moduleString Contains all the information about the module
     * @param key Description of information being searched for
     * @return String of specific information within module, eg module's title
     */
    public static String getKeyValue(String moduleString, String key) {
        key = "\"" + key + "\"";
        int keyValueStartIndex = moduleString.indexOf(key) + key.length() + STRING_OFFSET;
        int keyValueEndIndex = moduleString.indexOf("\"", keyValueStartIndex);
        if (key.equals("\"su\"")) {
            keyValueStartIndex = keyValueStartIndex - 1;
            keyValueEndIndex = moduleString.indexOf("}", keyValueStartIndex);
        }

        return moduleString.substring(keyValueStartIndex, keyValueEndIndex);
    }

    /**
     * Return invalid map
     * @return Return invalid map with all values being "N/A"
     */
    public static HashMap<String, String> getInvalidMap() {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("title", "N/A");
        map.put("moduleCredit", "N/A");
        map.put("su", "N/A");
        return map;
    }


    /**
     * Reads an InputStream and converts it to a String
     * @param inputStream Stream to get input from
     * @return A String containing the contents of the inputStream
     */
    public static String streamToString(InputStream inputStream) throws IOException {
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;
        while ((length = inputStream.read(buffer)) != -1) {
            result.write(buffer, 0, length);
        }
        inputStream.close();
        result.close();
        return result.toString("UTF-8");
    }
}
