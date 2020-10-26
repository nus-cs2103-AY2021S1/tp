package seedu.address.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.policy.PolicyList;

/**
 * A class to access PolicyList data stored as a json file on the hard disk.
 */
public class JsonPolicyListStorage implements PolicyListStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonClientListStorage.class);

    private Path filePath;

    public JsonPolicyListStorage(Path filePath) {
        this.filePath = filePath;
    }

    @Override
    public Path getPolicyListFilePath() {
        return filePath;
    }

    @Override
    public Optional<PolicyList> readPolicyList() throws DataConversionException, IOException {
        return readPolicyList(filePath);
    }

    @Override
    public Optional<PolicyList> readPolicyList(Path filePath) throws DataConversionException, IOException {
        requireNonNull(filePath);
        Optional<JsonSerializablePolicyList> jsonPolicyList = JsonUtil.readJsonFile(
                filePath, JsonSerializablePolicyList.class);
        if (!jsonPolicyList.isPresent()) {
            return Optional.empty();
        }
        try {
            return Optional.of(jsonPolicyList.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }


    @Override
    public void savePolicyList(PolicyList policyList) throws IOException {
        savePolicyList(policyList, filePath);
    }

    @Override
    public void savePolicyList(PolicyList policyList, Path filePath) throws IOException {
        requireNonNull(policyList);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializablePolicyList(policyList), filePath);
    }
}
