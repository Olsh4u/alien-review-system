package com.olsh4u.epam.service.validation;

import com.olsh4u.epam.models.AbstractEntity;
import com.olsh4u.epam.exception.ServiceException;

import java.util.Map;

/**
 * Interface for a Service layer validation.
 *
 * @param <T> the {@link AbstractEntity}
 */
@FunctionalInterface
public interface Validation<T extends AbstractEntity> {
    Map<String, String> isValid(T entity) throws ServiceException;
}
