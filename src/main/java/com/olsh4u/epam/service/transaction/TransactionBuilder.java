package com.olsh4u.epam.service.transaction;

import com.olsh4u.epam.dao.Transaction;
import com.olsh4u.epam.exception.ServiceException;
import com.olsh4u.epam.models.AbstractEntity;

/**
 * Functional interface which help build {@link AbstractEntity} from different DAO classes.
 *
 * @param <T> the {@link AbstractEntity}
 */
@FunctionalInterface
public interface TransactionBuilder<T> {
    /**
     * A method that collects an {@link AbstractEntity} from different DAO classes.
     *
     * @param transaction object to access the data source
     * @param entity      the {@link AbstractEntity}
     * @return the {@link AbstractEntity}
     * @throws ServiceException if the method failed
     */
    T transaction(Transaction transaction, T entity) throws ServiceException;
}
