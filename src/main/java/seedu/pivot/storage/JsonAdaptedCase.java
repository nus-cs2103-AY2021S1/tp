package seedu.pivot.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.pivot.commons.core.LogsCenter;
import seedu.pivot.commons.exceptions.IllegalValueException;
import seedu.pivot.model.investigationcase.ArchiveStatus;
import seedu.pivot.model.investigationcase.Case;
import seedu.pivot.model.investigationcase.Description;
import seedu.pivot.model.investigationcase.Document;
import seedu.pivot.model.investigationcase.Status;
import seedu.pivot.model.investigationcase.Title;
import seedu.pivot.model.investigationcase.caseperson.Suspect;
import seedu.pivot.model.investigationcase.caseperson.Victim;
import seedu.pivot.model.investigationcase.caseperson.Witness;
import seedu.pivot.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Case}.
 */
class JsonAdaptedCase {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Case's %s field is missing!";
    private static final Logger logger = LogsCenter.getLogger(JsonAdaptedCase.class);

    private final String title;
    private final String description;
    private final String status;
    private final String archiveStatus;
    private final List<JsonAdaptedDocument> documents = new ArrayList<>();
    private final List<JsonAdaptedSuspect> suspects = new ArrayList<>();
    private final List<JsonAdaptedVictim> victims = new ArrayList<>();
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();
    private final List<JsonAdaptedWitness> witnesses = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedCase} with the given case details.
     */
    @JsonCreator
    public JsonAdaptedCase(@JsonProperty("title") String title, @JsonProperty("description") String description,
            @JsonProperty("status") String status,
            @JsonProperty("documents") List<JsonAdaptedDocument> documents,
            @JsonProperty("suspects") List<JsonAdaptedSuspect> suspects,
            @JsonProperty("victims") List<JsonAdaptedVictim> victims,
            @JsonProperty("witnesses") List<JsonAdaptedWitness> witnesses,
            @JsonProperty("tagged") List<JsonAdaptedTag> tagged,
            @JsonProperty("archiveStatus") String archiveStatus) {

        this.title = title;
        this.description = description;
        this.status = status;
        this.archiveStatus = archiveStatus;
        if (documents != null) {
            this.documents.addAll(documents);
        }
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
        if (witnesses != null) {
            this.witnesses.addAll(witnesses);
        }
        if (suspects != null) {
            this.suspects.addAll(suspects);
        }
        if (victims != null) {
            this.victims.addAll(victims);
        }
    }

    /**
     * Converts a given {@code Case} into this class for Jackson use.
     */
    public JsonAdaptedCase(Case source) {
        title = source.getTitle().getAlphaNum();
        description = source.getDescription().getAlphaNum();
        status = source.getStatus().name();
        archiveStatus = source.getArchiveStatus().toString();
        documents.addAll(source.getDocuments().stream()
                .map(JsonAdaptedDocument::new)
                .collect(Collectors.toList()));
        suspects.addAll(source.getSuspects().stream()
                .map(JsonAdaptedSuspect::new)
                .collect(Collectors.toList()));
        victims.addAll(source.getVictims().stream()
                .map(JsonAdaptedVictim::new)
                .collect(Collectors.toList()));
        witnesses.addAll(source.getWitnesses().stream()
                .map(JsonAdaptedWitness::new)
                .collect(Collectors.toList()));
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted case object into the model's {@code Case} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted case.
     */
    public Case toModelType() throws IllegalValueException {
        logger.info("Converting JSON to Case");
        final List<Tag> caseTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            caseTags.add(tag.toModelType());
        }

        if (title == null) {
            logger.warning("Title is null. Check data");
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Title.class.getSimpleName()));
        }
        if (!Title.isValidTitle(title)) {
            logger.warning("Title is invalid. Check data");
            throw new IllegalValueException(Title.MESSAGE_CONSTRAINTS);
        }
        final Title modelTitle = new Title(title);

        if (description == null) {
            logger.warning("Description is null. Check data");
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Description.class.getSimpleName()));
        }
        if (!Description.isValidDescription(description)) {
            logger.warning("Description is invalid. Check data");
            throw new IllegalValueException(Description.MESSAGE_CONSTRAINTS);
        }
        final Description modelDescription = new Description(description);

        if (status == null) {
            logger.warning("Status is null. Check data");
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Status.class.getSimpleName()));
        }
        if (!Status.isValidStatus(status)) {
            logger.warning("Status is invalid. Check data");
            throw new IllegalValueException(Status.MESSAGE_CONSTRAINTS);
        }
        final Status modelStatus = Status.createStatus(status);

        final List<Suspect> modelSuspects = new ArrayList<>();
        for (JsonAdaptedSuspect suspect : suspects) {
            modelSuspects.add(suspect.toModelType());
        }

        final List<Victim> modelVictims = new ArrayList<>();
        for (JsonAdaptedVictim victim : victims) {
            modelVictims.add(victim.toModelType());
        }

        final List<Witness> modelWitnesses = new ArrayList<>();
        for (JsonAdaptedWitness witness : witnesses) {
            modelWitnesses.add(witness.toModelType());
        }

        final List<Document> modelDocument = new ArrayList<>();
        for (JsonAdaptedDocument document : documents) {
            modelDocument.add(document.toModelType());
        }

        final Set<Tag> modelTags = new HashSet<>(caseTags);

        if (archiveStatus == null) {
            logger.warning("archiveStatus is null. Check data");
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    ArchiveStatus.class.getSimpleName()));
        }
        final ArchiveStatus modelArchiveStatus = ArchiveStatus.valueOf(archiveStatus);

        return new Case(modelTitle, modelDescription, modelStatus, modelDocument,
                modelSuspects, modelVictims, modelWitnesses, modelTags, modelArchiveStatus);
    }

}
