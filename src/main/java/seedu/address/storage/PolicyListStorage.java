package seedu.address.storage;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.policy.PolicyList;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

/**
 * Represents a storage for {@link PolicyList}.
 */
public interface PolicyListStorage {
    /**
     * Returns the file path of the data file.
     */
    Path getPolicyListFilePath();

    /**
     * Returns PolicyList data as a {@link PolicyList}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<PolicyList> readPolicyList() throws DataConversionException, IOException;

    /**
     * @see #getPolicyListFilePath()
     */
    Optional<PolicyList> readPolicyList(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link PolicyList} to the storage.
     * @param policyList cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void savePolicyList(PolicyList policyList) throws IOException;

    /**
     * @see #savePolicyList(seedu.address.model.policy.PolicyList)
     */
    void savePolicyList(PolicyList policyList, Path filePath) throws IOException;
}
