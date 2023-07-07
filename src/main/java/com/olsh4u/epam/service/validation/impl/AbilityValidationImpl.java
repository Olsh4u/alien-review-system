package com.olsh4u.epam.service.validation.impl;

import com.olsh4u.epam.exception.ServiceException;
import com.olsh4u.epam.models.Ability;
import com.olsh4u.epam.service.factory.ServiceFactory;
import com.olsh4u.epam.service.validation.Validation;

import java.util.HashMap;
import java.util.Map;

import static com.olsh4u.epam.utils.Constants.ATTRIBUTE_ABILITY_PROBLEM;
import static com.olsh4u.epam.utils.Constants.MAX_ABILITY_TITLE_LENGTH;

/**
 * Implementation of {@link Validation} interface.
 * Validation for {@link Ability}.
 */
public class AbilityValidationImpl implements Validation<Ability> {

    /**
     * Validation for {@link Ability}.
     */
    @Override
    public Map<String, String> isValid(final Ability entity) throws ServiceException {
        Map<String, String> errors = new HashMap<>();
        if (ServiceFactory.getInstance().getAbilityService().findByTitle(entity.getTitle()) != null) {
            errors.put(ATTRIBUTE_ABILITY_PROBLEM, "incorrectAbilityTitle");
            return errors;
        }
        if (entity.getTitle().equals("")) {
            errors.put(ATTRIBUTE_ABILITY_PROBLEM, "fillOutField");
            return errors;
        }
        if (entity.getTitle().length() > MAX_ABILITY_TITLE_LENGTH) {
            errors.put(ATTRIBUTE_ABILITY_PROBLEM, "incorrectTitleLength");
            return errors;
        }
        return errors;
    }
}
