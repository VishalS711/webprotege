package edu.stanford.bmir.protege.web.server.tag;

import edu.stanford.bmir.protege.web.shared.inject.ProjectSingleton;
import edu.stanford.bmir.protege.web.shared.project.ProjectId;
import edu.stanford.bmir.protege.web.shared.tag.Tag;
import edu.stanford.bmir.protege.web.shared.tag.TagId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;

import javax.annotation.Nonnull;
import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 21 Jun 2018
 */
@ProjectSingleton
public class TagRepositoryImpl implements TagRepository {

    @Nonnull
    private final Datastore datastore;

    @Inject
    public TagRepositoryImpl(@Nonnull Datastore datastore) {
        this.datastore = checkNotNull(datastore);
    }

    @Override
    public void ensureIndexes() {
        datastore.ensureIndexes(Tag.class);
    }

    public void saveTag(@Nonnull Tag tag) {
        checkNotNull(tag);
        datastore.save(tag);
    }

    public void saveTags(@Nonnull Iterable<Tag> tags) {
        checkNotNull(tags);
        datastore.save(tags);
    }

    public void deleteTag(@Nonnull TagId tagId) {
        checkNotNull(tagId);
        Query<Tag> query =  datastore.createQuery(Tag.class)
                                     .field(Tag.ID).equal(tagId);
        datastore.delete(query);
    }

    @Nonnull
    public List<Tag> findTagsByProjectId(@Nonnull ProjectId projectId) {
        return datastore.createQuery(Tag.class)
                        .field(Tag.PROJECT_ID).equal(projectId)
                        .asList();
    }

    @Nonnull
    public Optional<Tag> findTagByTagId(@Nonnull TagId tagId) {
        return Optional.ofNullable(
                datastore.createQuery(Tag.class)
                         .field(Tag.ID).equal(tagId)
                         .get()
        );
    }
}
