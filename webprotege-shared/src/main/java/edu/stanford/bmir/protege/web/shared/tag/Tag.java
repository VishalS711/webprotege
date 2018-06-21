package edu.stanford.bmir.protege.web.shared.tag;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.auto.value.AutoValue;
import com.google.common.annotations.GwtCompatible;
import com.google.gwt.user.client.rpc.IsSerializable;
import com.mongodb.BasicDBObject;
import edu.stanford.bmir.protege.web.shared.color.Color;
import edu.stanford.bmir.protege.web.shared.project.ProjectId;

import javax.annotation.Nonnull;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 14 Mar 2018
 *
 * Represents a tag in a project.  Tags are used to tag entities with information that can be used for
 * development and project management.
 */
//@Entity(value = "Tags", noClassnameStored = true)
//@Indexes(
//        {
//                // Note, in addition to the unique indexes here, tag ids are globally unique
//                // Labels are unique within a project
//                @Index(fields = {@Field(PROJECT_ID), @Field(TAG_LABEL)}, options = @IndexOptions(unique = true))
//        }
//)
@AutoValue
@GwtCompatible(serializable = true)
public abstract class Tag implements IsSerializable {


    public static final String ID = "_id";

    public static final String PROJECT_ID = "projectId";

    public static final String LABEL = "label";

    private static final String DESCRIPTION = "description";

    private static final String COLOR = "color";

    private static final String BACKGROUND_COLOR = "backgroundColor";

    /**
     * Creates a Tag.
     *
     * @param tagId           The tag id.
     * @param projectId       The project id of the project that the tag belongs to.
     * @param label           The label for the tag.  This must not be empty.
     * @param description     An optional description for the tag.  This may be empty.
     * @param color           A color for the tag.  This is the foreground color of the tag in the user interface.
     * @param backgroundColor A background color for the tag.  This is the background color of the tag in the user
     *                        interface.
     */
    @JsonCreator
    public static Tag get(@Nonnull @JsonProperty(ID) TagId tagId,
                          @Nonnull @JsonProperty(PROJECT_ID) ProjectId projectId,
                          @Nonnull @JsonProperty(LABEL) String label,
                          @Nonnull @JsonProperty(DESCRIPTION) String description,
                          @Nonnull @JsonProperty(COLOR) Color color,
                          @Nonnull @JsonProperty(BACKGROUND_COLOR) Color backgroundColor) {
        checkArgument(!label.isEmpty(), "Tag label cannot be empty");
        return new AutoValue_Tag(tagId, projectId, label, description, color, backgroundColor);
    }

    /**
     * Gets the {@link TagId}
     */
    @JsonProperty(ID)
    @Nonnull
    public abstract TagId getTagId();

    /**
     * Gets the project that this tag belongs to.
     */
    @JsonProperty(PROJECT_ID)
    @Nonnull
    public abstract ProjectId getProjectId();

    /**
     * Gets the human readable name for the tag.
     */
    @JsonProperty(LABEL)
    @Nonnull
    public abstract String getLabel();


    /**
     * Gets a description for this tag.
     *
     * @return The description, possibly empty.
     */
    @JsonProperty(DESCRIPTION)
    @Nonnull
    public abstract String getDescription();

    /**
     * Gets the (foreground) color of the tag.
     *
     * @return The color as a hexadecimal string
     */
    @JsonProperty(COLOR)
    @Nonnull
    public abstract Color getColor();

    /**
     * Gets the background color of this tag.
     *
     * @return The background color as a hexadecimal string.
     */
    @JsonProperty(BACKGROUND_COLOR)
    @Nonnull
    public abstract Color getBackgroundColor();
}
