package edu.stanford.bmir.protege.web.client.form;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.*;

import javax.annotation.Nonnull;
import javax.inject.Inject;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2019-11-25
 */
public class GridRowViewImpl extends Composite implements GridRowView {

    interface GridRowViewImplUiBinder extends UiBinder<HTMLPanel, GridRowViewImpl> {

    }

    private static GridRowViewImplUiBinder ourUiBinder = GWT.create(GridRowViewImplUiBinder.class);

    @UiField
    HTMLPanel rowContainer;

    @Inject
    public GridRowViewImpl() {
        initWidget(ourUiBinder.createAndBindUi(this));
    }

    @Nonnull
    @Override
    public AcceptsOneWidget addCell() {
        SimplePanel cellContainer = new SimplePanel();
        rowContainer.add(cellContainer);
        return cellContainer;
    }

    @Override
    public void clear() {
        rowContainer.clear();
    }
}