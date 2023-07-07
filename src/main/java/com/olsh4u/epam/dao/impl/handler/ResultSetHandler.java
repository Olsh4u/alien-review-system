package com.olsh4u.epam.dao.impl.handler;

import com.olsh4u.epam.exception.DaoException;

import java.sql.ResultSet;

/**
 * Functional interface which help build {@link  com.olsh4u.epam.models.AbstractEntity} from result set.
 *
 * @param <T> the {@link  com.olsh4u.epam.models.AbstractEntity}
 */
@FunctionalInterface
public interface ResultSetHandler<T> {
    /**
     * Result set based method that return {@link  com.olsh4u.epam.models.AbstractEntity}.
     *
     * @param rs result set
     * @return {@link  com.olsh4u.epam.models.AbstractEntity}
     * @throws DaoException if an error occurs during parsing result set
     */
    T handle(ResultSet rs) throws DaoException;
}
