package seedu.homerce.storage.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.homerce.commons.exceptions.IllegalValueException;
import seedu.homerce.model.manager.ReadOnlyServiceManager;
import seedu.homerce.model.manager.ServiceManager;
import seedu.homerce.model.service.Service;

/**
 * An Immutable serviceManager that is serializable to JSON format.
 */
@JsonRootName(value = "serviceManager")
public class JsonSerializableServiceManager {

    public static final String MESSAGE_DUPLICATE_SERVICE = "Service list contains duplicate service(s).";

    private final List<JsonAdaptedService> services = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableServiceManager } with the given services.
     */
    @JsonCreator
    public JsonSerializableServiceManager(@JsonProperty("services") List<JsonAdaptedService> services) {
        this.services.addAll(services);
    }

    /**
     * Converts a given {@code ReadOnlyServiceManager} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableServiceManager}.
     */
    public JsonSerializableServiceManager(ReadOnlyServiceManager source) {
        services.addAll(source.getServiceList().stream().map(JsonAdaptedService::new).collect(Collectors.toList()));
    }

    /**
     * Converts this serviceManager into the model's {@code ServiceManager} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public ServiceManager toModelType() throws IllegalValueException {
        ServiceManager serviceManager = new ServiceManager();
        for (JsonAdaptedService jsonAdaptedService : services) {
            Service service = jsonAdaptedService.toModelType();
            if (serviceManager.hasService(service)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_SERVICE);
            }
            serviceManager.addService(service);
        }
        return serviceManager;
    }

}
