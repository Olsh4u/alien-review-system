package com.olsh4u.epam.service.validation.impl;

import com.olsh4u.epam.exception.ServiceException;
import com.olsh4u.epam.models.Source;
import com.olsh4u.epam.service.factory.ServiceFactory;
import com.olsh4u.epam.service.validation.Validation;

import java.util.HashMap;
import java.util.Map;

import static com.olsh4u.epam.utils.Constants.ATTRIBUTE_SOURCE_PROBLEM;
import static com.olsh4u.epam.utils.Constants.MAX_SOURCE_TITLE_LENGTH;


/**
 * Implementation of {@link Validation} interface.
 * Validation for {@link Source}.
 */
public class SourceValidationImpl implements Validation<Source> {

    /**
     * Validation for {@link Source}.
     */
    @Override
    public Map<String, String> isValid(final Source entity) throws ServiceException {
        Map<String, String> errors = new HashMap<>();
        if (ServiceFactory.getInstance().getSourceService().findByTitle(entity.getTitle()) != null) {
            errors.put(ATTRIBUTE_SOURCE_PROBLEM, "incorrectSourceTitle");
            return errors;
        }
        if (entity.getTitle().equals("")) {
            errors.put(ATTRIBUTE_SOURCE_PROBLEM, "fillOutField");
            return errors;
        }
        if (entity.getTitle().length() > MAX_SOURCE_TITLE_LENGTH) {
            errors.put(ATTRIBUTE_SOURCE_PROBLEM, "incorrectTitleLength");
            return errors;
        }
        return errors;
    }
}
