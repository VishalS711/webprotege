package edu.stanford.bmir.protege.web.client.match;

import com.google.common.collect.ImmutableList;
import edu.stanford.bmir.protege.web.shared.match.criteria.AnnotationValueCriteria;
import edu.stanford.bmir.protege.web.shared.match.criteria.CompositeAnnotationValueCriteria;

import javax.annotation.Nonnull;
import javax.inject.Inject;
import javax.inject.Provider;


/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 14 Jun 2018
 */
public class AnnotationValueListCriteriaPresenter extends CriteriaListPresenter<AnnotationValueCriteria> {

    @Inject
    public AnnotationValueListCriteriaPresenter(@Nonnull CriteriaListView view,
                                                @Nonnull Provider<CriteriaListCriteriaViewContainer> viewContainerProvider,
                                                @Nonnull AnnotationValueCriteriaPresenterFactory presenterFactory) {
        super(view, viewContainerProvider, presenterFactory);
    }

    @Override
    protected AnnotationValueCriteria createCriteria(@Nonnull ImmutableList<? extends AnnotationValueCriteria> criteria) {
        return CompositeAnnotationValueCriteria.get(criteria);
    }
}
