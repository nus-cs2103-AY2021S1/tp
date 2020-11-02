package seedu.resireg.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.resireg.logic.commands.EditCommand.EditStudentDescriptor;
import seedu.resireg.model.student.Email;
import seedu.resireg.model.student.Name;
import seedu.resireg.model.student.Phone;
import seedu.resireg.model.student.Student;
import seedu.resireg.model.student.StudentId;
import seedu.resireg.model.student.faculty.Faculty;
import seedu.resireg.model.tag.Tag;

/**
 * A utility class to help with building EditStudentDescriptor objects.
 */
public class EditStudentDescriptorBuilder {

    private EditStudentDescriptor descriptor;

    public EditStudentDescriptorBuilder() {
        descriptor = new EditStudentDescriptor();
    }

    public EditStudentDescriptorBuilder(EditStudentDescriptor descriptor) {
        this.descriptor = new EditStudentDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditStudentDescriptor} with fields containing {@code student}'s details.
     */
    public EditStudentDescriptorBuilder(Student student) {
        descriptor = new EditStudentDescriptor();
        descriptor.setName(student.getName());
        descriptor.setPhone(student.getPhone());
        descriptor.setEmail(student.getEmail());
        descriptor.setFaculty(student.getFaculty());
        descriptor.setStudentId(student.getStudentId());
        descriptor.setTags(student.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code EditStudentDescriptor} that we are building.
     */
    public EditStudentDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditStudentDescriptor} that we are building.
     */
    public EditStudentDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditStudentDescriptor} that we are building.
     */
    public EditStudentDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
        return this;
    }

    /**
     * Sets the {@code Faculty} of the {@code EditStudentDescriptor} that we are building.
     */
    public EditStudentDescriptorBuilder withFaculty(String faculty) {
        descriptor.setFaculty(new Faculty(faculty));
        return this;
    }

    /**
     * Sets the {@code StudentId} of the {@code EditStudentDescriptor} that we are building.
     */
    public EditStudentDescriptorBuilder withStudentId(String studentId) {
        descriptor.setStudentId(new StudentId(studentId));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditStudentDescriptor}
     * that we are building.
     */
    public EditStudentDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public EditStudentDescriptor build() {
        return descriptor;
    }
}
