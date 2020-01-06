package edu.stanford.bmir.protege.web.shared.form.field;

import com.fasterxml.jackson.annotation.*;
import com.google.auto.value.AutoValue;
import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.ImmutableMap;
import edu.stanford.bmir.protege.web.shared.form.HasFormFieldId;
import edu.stanford.bmir.protege.web.shared.lang.LanguageMap;
import org.semanticweb.owlapi.model.OWLProperty;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.Serializable;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 30/03/16
 */
@JsonPropertyOrder({"id", "owlProperty", "label", "elementRun", "fieldDescriptor", "repeatability", "optionality", "help"})
@GwtCompatible(serializable = true)
@AutoValue
public abstract class FormFieldDescriptor implements HasFormFieldId, HasRepeatability, Serializable {


    @JsonCreator
    @Nonnull
    public static FormFieldDescriptor get(@JsonProperty("id") @Nonnull FormFieldId id,
                                          @JsonProperty("owlBinding") @Nullable OwlBinding owlBinding,
                                          @JsonProperty("label") @Nullable LanguageMap formLabel,
                                          @JsonProperty("fieldRun") @Nullable FieldRun fieldRun,
                                          @JsonProperty("formControlDescriptor") @Nonnull FormControlDescriptor fieldDescriptor,
                                          @JsonProperty("repeatability") @Nullable Repeatability repeatability,
                                          @JsonProperty("optionality") @Nullable Optionality optionality,
                                          @JsonProperty("help") @Nullable LanguageMap help,
                                          @JsonProperty("style") @Nullable Map<String, String> style) {
        return new AutoValue_FormFieldDescriptor(id,
                                                 owlBinding,
                                                 formLabel == null ? LanguageMap.empty() : formLabel,
                                                 fieldRun == null ? FieldRun.START : fieldRun,
                                                 fieldDescriptor,
                                                 optionality == null ? Optionality.REQUIRED : optionality,
                                                 repeatability == null ? Repeatability.NON_REPEATABLE : repeatability,
                                                 help == null ? LanguageMap.empty() : help,
                                                 style == null ? ImmutableMap.of() : style);
    }

    public static FormFieldDescriptor getDefault() {
        return FormFieldDescriptor.get(
                FormFieldId.get(""),
                null,
                LanguageMap.empty(),
                FieldRun.START,
                new TextControlDescriptor(LanguageMap.empty(),
                                          StringType.SIMPLE_STRING,
                                          LineMode.SINGLE_LINE,
                                          "",
                                          LanguageMap.empty()),
                Repeatability.NON_REPEATABLE,
                Optionality.REQUIRED,
                LanguageMap.empty(),
                Collections.emptyMap()
        );
    }

    @Nonnull
    @Override
    @JsonProperty("id")
    public abstract FormFieldId getId();

    @JsonIgnore
    @Nullable
    protected abstract OwlBinding getOwlBindingInternal();

    @Nonnull
    public Optional<OwlBinding> getOwlBinding() {
        return Optional.ofNullable(getOwlBindingInternal());
    }

    @JsonIgnore
    public Optional<OWLProperty> getOwlProperty() {
        return getOwlBinding().flatMap(OwlBinding::getOwlProperty);
    }

    @Nonnull
    public abstract LanguageMap getLabel();

    @Nonnull
    public abstract FieldRun getFieldRun();

    @Nonnull
    public abstract FormControlDescriptor getFormControlDescriptor();

    @Nonnull
    public abstract Optionality getOptionality();

    @Nonnull
    public abstract Repeatability getRepeatability();

    @Nonnull
    public abstract LanguageMap getHelp();

    @Nonnull
    public abstract Map<String, String> getStyle();
    
    @JsonIgnore
    public boolean isComposite() {
        return getFormControlDescriptor() instanceof SubFormControlDescriptor;
    }

    @JsonIgnore
    public boolean isNonComposite() {
        return !(getFormControlDescriptor() instanceof SubFormControlDescriptor);
    }
}
