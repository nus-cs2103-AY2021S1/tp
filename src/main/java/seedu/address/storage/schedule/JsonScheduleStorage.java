package seedu.address.storage.schedule;

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
import seedu.address.model.event.ReadOnlyEvent;

public class JsonScheduleStorage implements ScheduleStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonScheduleStorage.class);

    private Path filepath;

    public JsonScheduleStorage(Path filepath) {
        this.filepath = filepath;
    }

    @Override
    public Path getScheduleFilePath() {
        return this.filepath;
    }

    @Override
    public Optional<ReadOnlyEvent> readSchedule() throws DataConversionException, IOException {
        return readSchedule(filepath);
    }

    @Override
    public Optional<ReadOnlyEvent> readSchedule(Path filePath) throws DataConversionException, IOException {
        requireNonNull(filePath);

        Optional<JsonSerializableSchedule> jsonSchedule = JsonUtil.readJsonFile(
                filePath, JsonSerializableSchedule.class);
        if (!jsonSchedule.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonSchedule.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveSchedule(ReadOnlyEvent schedule) throws IOException {
        saveSchedule(schedule, filepath);
    }

    @Override
    public void saveSchedule(ReadOnlyEvent schedule, Path filePath) throws IOException {
        requireNonNull(schedule);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableSchedule(schedule), filePath);
    }
}

