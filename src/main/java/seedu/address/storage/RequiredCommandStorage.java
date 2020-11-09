package seedu.address.storage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import javafx.collections.ObservableList;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.module.Module;

public class RequiredCommandStorage {
    private ObservableList<Module> requiredFoundation;
    private ObservableList<Module> requiredITprof;
    private ObservableList<Module> requiredMath;
    private ObservableList<Module> requiredScience;
    private ObservableList<Module> requiredInternship;
    private Map<String, String> preclusionMap;

    /**
     * Makes use of classLoaders to convert the original file path
     * into one that can be readable during runtime, such that it
     * can be used to retrieve the File's content.
     *
     * @param filePath Original file path.
     * @return Converted file content of type String.
     * @throws IOException When the provided fileName is invalid.
     */
    private String getFileFromResource(String filePath) throws IOException {
        // The class loader that loaded the class
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(filePath);

        if (inputStream == null) {
            throw new IOException();
        } else {
            StringBuilder dataString = new StringBuilder();

            Reader reader = new BufferedReader(new InputStreamReader(inputStream,
                    Charset.forName(StandardCharsets.UTF_8.name())));
            int c = 0;
            while ((c = reader.read()) != -1) {
                dataString.append((char) c);
            }

            return dataString.toString();
        }
    }

    /**
     * Returns preclusionMap attribute of RequiredCommandStorage object.
     *
     * @return preclusionMap attribute of type Map.
     */
    public Map<String, String> getPreclusionMap() {
        return preclusionMap;
    }

    /**
     * Loads the preclusionMap attribute with Precluded Modules.
     *
     * @param path Path of the Precluded Modules file.
     * @throws IOException When path is invalid.
     */
    public void setPreclusionMap(String path) throws IOException {
        String file = getFileFromResource(path);
        preclusionMap = JsonUtil.getPreclusionMapFromJsonFile(file);
    }

    /**
     * Returns requiredFoundation attribute of RequiredCommandStorage object.
     *
     * @return requiredFoundation attribute of type ObservableList<Module/>.
     */
    public ObservableList<Module> getRequiredFoundation() {
        return requiredFoundation;
    }
    /**
     * Loads the requiredFoundation attribute with Foundation Modules.
     *
     * @param path Path of the Foundation Modules file.
     * @throws IOException When path is invalid.
     * @throws IllegalValueException When the data from the JSON file does not match the
     * specific field headers of the JsonAdaptedModule class (Eg.'moduleCode', 'modularCredits').
     */
    public void setRequiredFoundation(String path) throws IOException, IllegalValueException {
        String file = getFileFromResource(path);
        requiredFoundation = JsonUtil.getModulesFromJsonFile(file);
    }

    /**
     * Returns requiredITprof attribute of RequiredCommandStorage object.
     *
     * @return requiredITprof attribute of type ObservableList<Module/>.
     */
    public ObservableList<Module> getRequiredITprof() {
        return requiredITprof;
    }
    /**
     * Loads the requiredITprof attribute with IT Professionalism Modules.
     *
     * @param path Path of the IT Professionalism Modules file.
     * @throws IOException When path is invalid.
     * @throws IllegalValueException When the data from the JSON file does not match the
     * specific field headers of the JsonAdaptedModule class (Eg.'moduleCode', 'modularCredits').
     */
    public void setRequiredITprof(String path) throws IOException, IllegalValueException {
        String file = getFileFromResource(path);
        requiredITprof = JsonUtil.getModulesFromJsonFile(file);
    }

    /**
     * Returns requiredMath attribute of RequiredCommandStorage object.
     *
     * @return requiredMath attribute of type ObservableList<Module/>.
     */
    public ObservableList<Module> getRequiredMath() {
        return requiredMath;
    }
    /**
     * Loads the requiredMath attribute with Math Modules.
     *
     * @param path Path of the Math Modules file.
     * @throws IOException When path is invalid.
     * @throws IllegalValueException When the data from the JSON file does not match the
     * specific field headers of the JsonAdaptedModule class (Eg.'moduleCode', 'modularCredits').
     */
    public void setRequiredMath(String path) throws IOException, IllegalValueException {
        String file = getFileFromResource(path);
        requiredMath = JsonUtil.getModulesFromJsonFile(file);
    }

    /**
     * Returns requiredScience attribute of RequiredCommandStorage object.
     *
     * @return requiredScience attribute of type ObservableList<Module/>.
     */
    public ObservableList<Module> getRequiredScience() {
        return requiredScience;
    }
    /**
     * Loads the requiredScience attribute with Science Modules.
     *
     * @param path Path of the Science Modules file.
     * @throws IOException When path is invalid.
     * @throws IllegalValueException When the data from the JSON file does not match the
     * specific field headers of the JsonAdaptedModule class (Eg.'moduleCode', 'modularCredits').
     */
    public void setRequiredScience(String path) throws IOException, IllegalValueException {
        String file = getFileFromResource(path);
        requiredScience = JsonUtil.getModulesFromJsonFile(file);
    }

    /**
     * Returns requiredInternship attribute of RequiredCommandStorage object.
     *
     * @return requiredInternship attribute of type ObservableList<Module/>.
     */
    public ObservableList<Module> getRequiredInternship() {
        return requiredInternship;
    }
    /**
     * Loads the requiredInternship attribute with Internship Modules.
     *
     * @param path Path of the Internship Modules file.
     * @throws IOException When path is invalid.
     * @throws IllegalValueException When the data from the JSON file does not match the
     * specific field headers of the JsonAdaptedModule class (Eg.'moduleCode', 'modularCredits').
     */
    public void setRequiredInternship(String path) throws IOException, IllegalValueException {
        String file = getFileFromResource(path);
        requiredInternship = JsonUtil.getModulesFromJsonFile(file);
    }
}

