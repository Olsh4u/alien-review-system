package com.olsh4u.epam.service.validation.impl;

import com.olsh4u.epam.models.Alien;
import com.olsh4u.epam.exception.ServiceException;
import com.olsh4u.epam.service.factory.ServiceFactory;
import com.olsh4u.epam.service.validation.Validation;

import java.util.HashMap;
import java.util.Map;

import static com.olsh4u.epam.utils.Constants.*;

/**
 * Implementation of {@link Validation} interface.
 * Validation for {@link Alien}.
 */
public class AlienValidationImpl implements Validation<Alien> {
    /**
     * Validation for {@link Alien}.
     */
    @Override
    public Map<String, String> isValid(final Alien alien) throws ServiceException {
        Map<String, String> errors = new HashMap<>();
        if (alien.getName() == null || alien.getName().equals("")) {
            errors.put(ATTRIBUTE_ALIEN_NAME_PROBLEM, "fillOutField");
        }
        if (alien.getName() != null && alien.getName().length() > MAX_ALIEN_NAME_LENGTH) {
            errors.put(ATTRIBUTE_ALIEN_NAME_PROBLEM, "incorrectAlienNameLength");
        }
        if (alien.getDescription() == null || alien.getDescription().equals("")) {
            errors.put(ATTRIBUTE_ALIEN_DESCRIPTION_PROBLEM, "fillOutField");
        }
        if (alien.getDescription() != null && alien.getDescription().length() > MAX_SERIAL_DESCRIPTION_LENGTH) {
            errors.put(ATTRIBUTE_ALIEN_DESCRIPTION_PROBLEM, "incorrectDescriptionLength");
        }
        if (alien.getLogo().length() > MAX_ALIEN_LOGO_LENGTH) {
            errors.put(ATTRIBUTE_ALIEN_LOGO_PROBLEM, "incorrectImageNameLength");
        }
        if (alien.getFullSizeLogo().length() > MAX_ALIEN_FULL_LOGO_LENGTH) {
            errors.put(ATTRIBUTE_ALIEN_FULL_LOGO_PROBLEM, "incorrectImageNameLength");
        }
        try {
            Alien searchedAlien = ServiceFactory.getInstance().getAlienService().findByName(alien.getName());
            if (searchedAlien != null && searchedAlien.getId() != alien.getId()) {
                errors.put(ATTRIBUTE_ALIEN_NAME_PROBLEM, "incorrectAlienName");
            }
        } catch (ServiceException ignored) {

        }
        return errors;
    }
}

