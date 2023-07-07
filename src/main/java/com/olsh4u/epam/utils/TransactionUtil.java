package com.olsh4u.epam.utils;

import com.olsh4u.epam.dao.Transaction;
import com.olsh4u.epam.exception.ServiceException;
import com.olsh4u.epam.models.AbstractEntity;
import com.olsh4u.epam.service.transaction.TransactionBuilder;

/**
 * Util class.
 */
public final class TransactionUtil {

    private TransactionUtil() {
    }

    /**
     * Method to get data from DAO layer.
     *
     * @param <T>         single or {@link java.util.List} {@link AbstractEntity}
     * @param transaction object to access data
     * @param builder     the Transaction builder
     * @param entity      the {@link AbstractEntity}
     * @return the {@link AbstractEntity}
     * @throws ServiceException if the method failed
     */
    public static <T> T select(final Transaction transaction, final TransactionBuilder<T> builder,
                               final T entity) throws ServiceException {
        return builder.transaction(transaction, entity);
    }

}
