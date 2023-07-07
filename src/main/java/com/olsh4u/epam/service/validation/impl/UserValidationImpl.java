package com.olsh4u.epam.service.validation.impl;

import com.olsh4u.epam.models.User;
import com.olsh4u.epam.exception.ServiceException;
import com.olsh4u.epam.service.validation.Validation;
import com.olsh4u.epam.service.factory.ServiceFactory;

import java.util.HashMap;
import java.util.Map;

import static com.olsh4u.epam.utils.Constants.*;

/**
 * Implementation of {@link Validation} interface.
 * Validation for {@link User}.
 */
public class UserValidationImpl implements Validation<User> {

    @Override
    public Map<String, String> isValid(final User entity) throws ServiceException {
        Map<String, String> errors = new HashMap<>();
        User user = ServiceFactory.getInstance().getUserService().findByLogin(entity.getLogin());
        if (user != null && user.getId() != entity.getId()) {
            errors.put(ATTRIBUTE_INVALID_LOGIN, "incorrectLogin");
            return errors;
        }
        if (entity.getLogin().length() > MAX_USER_LOGIN_LENGTH) {
            errors.put(ATTRIBUTE_INVALID_LOGIN, "invalidLoginLength");
            return errors;
        }
        if (entity.getAvatar().length() > MAX_USER_AVATAR_NAME_LENGTH) {
            errors.put(ATTRIBUTE_USER_AVATAR_PROBLEM, "incorrectImageNameLength");
        }
        return errors;
    }
}