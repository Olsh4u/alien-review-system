package com.olsh4u.epam.service.validation.impl;

import com.olsh4u.epam.models.Planet;
import com.olsh4u.epam.exception.ServiceException;
import com.olsh4u.epam.service.factory.ServiceFactory;
import com.olsh4u.epam.service.validation.Validation;

import java.util.HashMap;
import java.util.Map;

import static com.olsh4u.epam.utils.Constants.ATTRIBUTE_PLANET_PROBLEM;
import static com.olsh4u.epam.utils.Constants.MAX_PLANET_TITLE_LENGTH;

/**
 * Implementation of {@link Validation} interface.
 * Validation for {@link Planet}.
 */
public class PlanetValidationImpl implements Validation<Planet> {

    /**
     * Validation for {@link Planet}.
     */
    @Override
    public Map<String, String> isValid(final Planet entity) throws ServiceException {
        Map<String, String> errors = new HashMap<>();
        if (ServiceFactory.getInstance().getPlanetService().findByTitle(entity.getTitle()) != null) {
            errors.put(ATTRIBUTE_PLANET_PROBLEM, "incorrectPlanetTitle");
            return errors;
        }
        if (entity.getTitle().equals("")) {
            errors.put(ATTRIBUTE_PLANET_PROBLEM, "fillOutField");
            return errors;
        }
        if (entity.getTitle().length() > MAX_PLANET_TITLE_LENGTH) {
            errors.put(ATTRIBUTE_PLANET_PROBLEM, "incorrectTitleLength");
            return errors;
        }
        return errors;
    }
}